package org.forksmash.recipeapp_backend;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jayway.jsonpath.Option;

import org.forksmash.recipeapp_backend.userprofile.User;
import org.forksmash.recipeapp_backend.userprofile.UserRepository;
import org.forksmash.recipeapp_backend.ingredient.Ingredient;

/** Start an actual HTTP server listening at a random port*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IngredientIntegrationTest {

	@LocalServerPort
	private int port;

	private final String baseUrl = "http://localhost:";

	@Autowired
	/**
	 * Use TestRestTemplate for testing a real instance of your application as an external actor.
	 * TestRestTemplate is just a convenient subclass of RestTemplate that is suitable for integration tests.
 	 * It is fault tolerant, and optionally can carry Basic authentication headers.
	 */
	private TestRestTemplate restTemplate;

	@Autowired
	private IngredientRepository ingredients;

	@Autowired
	private UserRepository users;

	@Autowired
	private BCryptPasswordEncoder encoder;


	@AfterEach
	void tearDown(){
		// clear the database after each test
		ingredients.deleteAll();
		users.deleteAll();
	}

	@Test
	public void getIngredients_Success() throws Exception {
		URI uri = new URI(baseUrl + port + "/ingredients");
		ingredients.save(new Ingredient("Gone With The Wind"));
		
		// Need to use array with a ReponseEntity here
		ResponseEntity<Ingredient[]> result = restTemplate.getForEntity(uri, Ingredient[].class);
		Ingredient[] ingredients = result.getBody();
		
		assertEquals(200, result.getStatusCode().value());
		assertEquals(1, ingredients.length);
	}

	@Test
	public void getIngredient_ValidIngredientId_Success() throws Exception {
		Ingredient ingredient = new Ingredient("Gone With The Wind");
		Long id = ingredients.save(ingredient).getId();
		URI uri = new URI(baseUrl + port + "/ingredients/" + id);
		
		ResponseEntity<Ingredient> result = restTemplate.getForEntity(uri, Ingredient.class);
			
		assertEquals(200, result.getStatusCode().value());
		assertEquals(ingredient.getName(), result.getBody().getName());
	}

	@Test
	public void getIngredient_InvalidIngredientId_Failure() throws Exception {
		URI uri = new URI(baseUrl + port + "/ingredients/1");
		
		ResponseEntity<Ingredient> result = restTemplate.getForEntity(uri, Ingredient.class);
			
		assertEquals(404, result.getStatusCode().value());
	}

	@Test
	public void addIngredient_Success() throws Exception {
		URI uri = new URI(baseUrl + port + "/ingredients");
		Ingredient ingredient = new Ingredient("A New Hope");
		users.save(new User("admin", encoder.encode("goodpassword"), "ROLE_ADMIN"));

		ResponseEntity<Ingredient> result = restTemplate.withBasicAuth("admin", "goodpassword")
										.postForEntity(uri, ingredient, Ingredient.class);
			
		assertEquals(201, result.getStatusCode().value());
		assertEquals(ingredient.getName(), result.getBody().getName());
	}

	/**
	 * TODO: Activity 2 (Week 6)
	 * Add integration tests for delete/update a ingredient.
	 * For delete operation: there should be two tests for success and failure (ingredient not found) scenarios.
	 * Similarly, there should be two tests for update operation.
	 * You should assert both the HTTP response code, and the value returned if any
	 * 
	 * For delete and update, you should use restTemplate.exchange method to send the request
	 * E.g.: ResponseEntity<Void> result = restTemplate.withBasicAuth("admin", "goodpassword")
										.exchange(uri, HttpMethod.DELETE, null, Void.class);
	 */
	// your code here
	@Test
	public void deleteIngredient_ValidIngredientId_Success() throws Exception{
		Ingredient ingredient = ingredients.save(new Ingredient("A New Hope"));
		URI uri = new URI(baseUrl + port + "/ingredient/" + ingredient.getId().longValue());
		users.save(new User("admin", encoder.encode("goodpassword"),"ROLE_ADMIN"));

		ResponseEntity<Void> result = restTemplate.withBasicAuth("admin", "goodpassword").exchange(uri, HttpMethod.DELETE, null, Void.class);
		assertEquals(200, result.getStatusCode().value());
		//An empty optional should be returned by "findById" after deletion
		Optional<Ingredient> emptyValue = Optional.empty();
		assertEquals(emptyValue, ingredients.findById(ingredient.getId()));
	}

	@Test
	public void deleteIngredient_InvalidIngredientId_Faliure() throws Exception {
		URI uri = new URI(baseUrl + port + "/ingredients/1");
		users.save(new User("admin", encoder.encode("goodpassword"),"ROLE_ADMIN"));

		ResponseEntity<Void> result = restTemplate.withBasicAuth("admin", "goodpassword").exchange(uri, HttpMethod.DELETE, null, Void.class);
		assertEquals(404, result.getStatusCode().value());
	}

	@Test
	public void updateIngredient_ValidIngredientId_Success() throws Exception{
		Ingredient ingredient = ingredients.save(new Ingredient("A New Hope"));
		URI uri = new URI(baseUrl + port + "/ingredient/" + ingredient.getId().longValue());
		Ingredient newIngredientInfo = new Ingredient("A New Vision");
		users.save(new User("admin", encoder.encode("goodpassword"),"ROLE_ADMIN"));

		ResponseEntity<Ingredient> result = restTemplate.withBasicAuth("admin", "goodpassword").exchange(uri, HttpMethod.PUT, new HttpEntity<>(newIngredientInfo), Ingredient.class);
		assertEquals(200, result.getStatusCode().value());
		
		assertEquals(newIngredientInfo.getName(), result.getBody().getName());
	}

	@Test
	public void updateIngredient_InvalidIngredientId_Failure() throws Exception{
		URI uri = new URI(baseUrl + port + "/ingredient/1" ); 
		Ingredient newIngredientInfo = new Ingredient("A New Vision");
		users.save(new User("admin", encoder.encode("goodpassword"),"ROLE_ADMIN"));

		ResponseEntity<Ingredient> result = restTemplate.withBasicAuth("admin", "goodpassword").exchange(uri, HttpMethod.PUT, new HttpEntity<>(newIngredientInfo), Ingredient.class);
		assertEquals(404, result.getStatusCode().value());
	}
}

