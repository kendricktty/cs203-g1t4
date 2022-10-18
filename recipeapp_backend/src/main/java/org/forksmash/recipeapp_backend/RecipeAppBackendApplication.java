package org.forksmash.recipeapp_backend;

import org.forksmash.recipeapp_backend.ingredient.Ingredient;
import org.forksmash.recipeapp_backend.ingredient.IngredientRepository;
import org.forksmash.recipeapp_backend.ingredientType.IngredientType;
import org.forksmash.recipeapp_backend.ingredientType.IngredientTypeRepository;
import org.forksmash.recipeapp_backend.role.Role;
import org.forksmash.recipeapp_backend.user.User;
import org.forksmash.recipeapp_backend.user.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RecipeAppBackendApplication {
 
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(RecipeAppBackendApplication.class, args);

		// JPA ingredient type repository init
        IngredientTypeRepository ingredientTypes = ctx.getBean(IngredientTypeRepository.class);
		List<String> ingredientTypeNames = Arrays.asList("Meat", "Seafood", "Vegetable", "Dairy", "Fruit", "Others");

		ingredientTypeNames.forEach(ingredientType -> {
			System.out.println("[Add ingredient type]: " + ingredientTypes.save(new IngredientType(ingredientType)).getName());
		});

		// JPA ingredient repository init
        IngredientRepository ingredients = ctx.getBean(IngredientRepository.class);

		List<String> meatIngredients = Arrays.asList("Beef", "Chicken", "Duck", "Pork", "Lamb", "Venison");
		meatIngredients.forEach(ingredient -> {
			ingredients.save(new Ingredient(ingredient, ingredientTypes.findByName("Meat").get(0)));
		});

		List<String> seafoodIngredients = Arrays.asList("Salmon", "Tuna", "Mackerel", "Cod", "Squid", "Prawn");
		seafoodIngredients.forEach(ingredient -> {
			ingredients.save(new Ingredient(ingredient, ingredientTypes.findByName("Seafood").get(0)));
		});

		List<String> vegetableIngredients = Arrays.asList("Asparagus", "Bean", "Broccoli", "Cauliflower", "Cabbage", "Egg Plant", "Onion", "Potato", "Tomato", "Kimchi");
		vegetableIngredients.forEach(ingredient -> {
			ingredients.save(new Ingredient(ingredient, ingredientTypes.findByName("Vegetable").get(0)));
		});

		List<String> dairyIngredients = Arrays.asList("Butter", "Cheese", "Cream", "Milk", "Yoghurt");
		dairyIngredients.forEach(ingredient -> {
			ingredients.save(new Ingredient(ingredient, ingredientTypes.findByName("Dairy").get(0)));
		});

		List<String> fruitsIngredients = Arrays.asList("Apple", "Avocado", "Banana", "Blackberry", "Blueberry", "Grapefruit", "Orange", "Pineapple", "Lemon", "Lime", "Strawberry");
		fruitsIngredients.forEach(ingredient -> {
			ingredients.save(new Ingredient(ingredient, ingredientTypes.findByName("Fruit").get(0)));
		});

		List<String> othersIngredients = Arrays.asList("Miso", "Egg");
		othersIngredients.forEach(ingredient -> {
			ingredients.save(new Ingredient(ingredient, ingredientTypes.findByName("Others").get(0)));
		}); 
	} 

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
  
	@Bean 
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.saveUser(new User(null, "John", "Appleseed", "johnappleseed@example.com", "password", new ArrayList<>()));
			userService.saveUser(new User(null, "Jane", "Appleseed", "janeappleseed@example.com", "password", new ArrayList<>()));
		
			userService.addRoleToUser("johnappleseed@example.com", "ROLE_ADMIN");
			userService.addRoleToUser("johnappleseed@example.com", "ROLE_USER");
			userService.addRoleToUser("janeappleseed@example.com", "ROLE_USER");
		};
	}

	// @Bean 
	// CommandLineRunner run(IngredientTypeService ingredientTypeService) {
	// 	return args -> {
	// 		ingredientTypeService.saveIngredientType(new IngredientType("Meat"));
	// 		ingredientTypeService.saveIngredientType(new IngredientType("Seafood"));
	// 		ingredientTypeService.saveIngredientType(new IngredientType("Vegetable"));
	// 		ingredientTypeService.saveIngredientType(new IngredientType("Dairy"));
	// 		ingredientTypeService.saveIngredientType(new IngredientType("Fruit"));
	// 		ingredientTypeService.saveIngredientType(new IngredientType("Others"));
	// 	};
	// }
 
	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
					.addMapping("/**")
					.allowedHeaders("*")
					.allowedOrigins("*");
			}
		};
	} 

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	} 
   
} 

