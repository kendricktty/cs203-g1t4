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

import org.forksmash.recipeapp_backend.ingredient.*;
import org.forksmash.recipeapp_backend.ingredientType.IngredientType;
import org.forksmash.recipeapp_backend.user.*;

/** Start an actual HTTP server listening at a random port*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class IngredientIntegrationTest {
    @LocalServerPort
	private int port;

	private final String baseUrl = "http://localhost:";

	@Autowired
	/**
	 * Use TestRestTemplate for testing a real instance of the application as an external actor.
	 * Convenient subclass of RestTemplate that is suitable for integration tests.
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
		ingredients.deleteAll();
		users.deleteAll();
	}

	@Test
	public void getIngredients_Success() throws Exception {
		URI uri = new URI(baseUrl + port + "api/ingredients");
		ingredients.save(new Ingredient("beef",new IngredientType("meet")));
		
		// Need to use array with a ReponseEntity here
		ResponseEntity<IngredientServiceTest[]> result = restTemplate.getForEntity(uri, IngredientServiceTest[].class);
		IngredientServiceTest[] ingredients = result.getBody();
		
		assertEquals(200, result.getStatusCode().value());
		assertEquals(1, ingredients.length);
	}

    @Test
	public void getIngredient_ValidIngredientId_Success() throws Exception {
		Ingredient ingredient = new Ingredient("beef", new IngredientType("meat"));
		Long id = ingredients.save(ingredient).getId();
		URI uri = new URI(baseUrl + port + "api/ingredients/" + id);
		
		ResponseEntity<Ingredient> result = restTemplate.getForEntity(uri, Ingredient.class);
			
		assertEquals(200, result.getStatusCode().value());
		assertEquals(ingredient.getId(), result.getBody().getId());
	}

	@Test
	public void getIngredient_InvalidIngredientId_Failure() throws Exception {
		URI uri = new URI(baseUrl + port + "api/ingredients/1");
		
		ResponseEntity<Ingredient> result = restTemplate.getForEntity(uri, Ingredient.class);
			
		assertEquals(404, result.getStatusCode().value());
	}

	@Test
	public void addIngredient_Success() throws Exception {
		URI uri = new URI(baseUrl + port + "api/ingredients");
		Ingredient ingredient = new Ingredient("beef", new IngredientType("meat"));
		users.save(new User("John", "Steven", "john@gmail.com", "password"));

		ResponseEntity<Ingredient> result = restTemplate.withBasicAuth("john@gmail.com", "password")
										.postForEntity(uri, ingredient, Ingredient.class);
			
		assertEquals(201, result.getStatusCode().value());
		assertEquals(ingredient.getId(), result.getBody().getId());
	}

	/**
	 * Integration tests for delete/update a ingredient.
	 * For delete operation: there should be two tests for success and failure scenarios.
	 * Similarly, there should be two tests for update operation.
	 */
	@Test
	public void deleteIngredient_ValidIngredientId_Success() throws Exception {
		IngredientServiceTest ingredient = ingredients.save(new IngredientServiceTest());
		URI uri = new URI(baseUrl + port + "api/ingredients/" + ingredient.getId().longValue());
		users.save(new User("admin", encoder.encode("goodpassword"), "ROLE_ADMIN"));
		
		//restTemplate.withBasicAuth("admin", "goodpassword").delete(uri);
		ResponseEntity<Void> result = restTemplate.withBasicAuth("admin", "goodpassword")
										.exchange(uri, HttpMethod.DELETE, null, Void.class);
		
		assertEquals(200, result.getStatusCode().value());
		// An empty Optional should be returned by "findById" after deletion
		Optional<IngredientServiceTest> emptyValue = Optional.empty();
		assertEquals(emptyValue, ingredients.findById(ingredient.getId()));
	}

	@Test
	public void deleteIngredient_InvalidIngredientId_Failure() throws Exception {
		URI uri = new URI(baseUrl + port + "api/ingredients/1");
		users.save(new User("admin", encoder.encode("goodpassword"), "ROLE_ADMIN"));
		
		ResponseEntity<Void> result = restTemplate.withBasicAuth("admin", "goodpassword")
										.exchange(uri, HttpMethod.DELETE, null, Void.class);
		
		assertEquals(404, result.getStatusCode().value());
	}

}
