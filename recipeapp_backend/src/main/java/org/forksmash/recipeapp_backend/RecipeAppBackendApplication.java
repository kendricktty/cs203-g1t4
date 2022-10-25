package org.forksmash.recipeapp_backend;

import org.forksmash.recipeapp_backend.ingredient.Ingredient;
import org.forksmash.recipeapp_backend.ingredient.IngredientRepository;
import org.forksmash.recipeapp_backend.ingredientType.IngredientType;
import org.forksmash.recipeapp_backend.ingredientType.IngredientTypeRepository;
import org.forksmash.recipeapp_backend.recipe.Recipe;
import org.forksmash.recipeapp_backend.recipe.RecipeRepository;
import org.forksmash.recipeapp_backend.recipe.RecipeService;
import org.forksmash.recipeapp_backend.role.Role;
import org.forksmash.recipeapp_backend.user.User;
import org.forksmash.recipeapp_backend.user.UserRepository;
import org.forksmash.recipeapp_backend.user.UserService;
import org.forksmash.recipeapp_backend.userprofile.UserProfile;
import org.forksmash.recipeapp_backend.userprofile.UserProfileRepository;
import org.forksmash.recipeapp_backend.userprofile.UserProfileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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


		// JPA User Profile repository init
		UserService userService = ctx.getBean(UserService.class);
		User user = userService.getUser("johnappleseed@example.com");
		UserProfileRepository userProfileRepository = ctx.getBean(UserProfileRepository.class);
		userProfileRepository.save(new UserProfile(21, "M", 170, 60, user));

		// JPA Recipe repository init
		// UserProfileService userProfileService = ctx.getBean(UserProfileService.class);
		// UserProfile userProfile = userProfileService.getProfile((long) 1);
		// Optional<UserProfile> userProfile = userProfileRepository.findById((long) 1);
		RecipeRepository recipeRepository = ctx.getBean(RecipeRepository.class);
		recipeRepository.save(
			new Recipe( 
				(long) 665029,
				"[{\"id\": 2044, \"meta\": [\"coarsely chopped\"], \"name\": \"basil leaves\", \"unit\": \"tbsp\", \"aisle\": \"Produce\", \"image\": \"fresh-basil.jpg\", \"amount\": 2, \"measures\": {\"us\": {\"amount\": 2, \"unitLong\": \"Tbsps\", \"unitShort\": \"Tbsps\"}, \"metric\": {\"amount\": 2, \"unitLong\": \"Tbsps\", \"unitShort\": \"Tbsps\"}}, \"original\": \"2 tbsp basil leaves, coarsely chopped\", \"nameClean\": \"fresh basil\", \"consistency\": \"SOLID\", \"originalName\": \"basil leaves, coarsely chopped\"}, {\"id\": 9160, \"meta\": [], \"name\": \"juice of lime\", \"unit\": \"\", \"aisle\": \"Produce\", \"image\": \"lime-juice.png\", \"amount\": 2, \"measures\": {\"us\": {\"amount\": 2, \"unitLong\": \"\", \"unitShort\": \"\"}, \"metric\": {\"amount\": 2, \"unitLong\": \"\", \"unitShort\": \"\"}}, \"original\": \"juice from 1/2 a lime\", \"nameClean\": \"lime juice\", \"consistency\": \"LIQUID\", \"originalName\": \"juice from a lime\"}, {\"id\": 2064, \"meta\": [\"coarsely chopped\"], \"name\": \"mint leaves\", \"unit\": \"tbsp\", \"aisle\": \"Produce;Spices and Seasonings\", \"image\": \"mint.jpg\", \"amount\": 2, \"measures\": {\"us\": {\"amount\": 2, \"unitLong\": \"Tbsps\", \"unitShort\": \"Tbsps\"}, \"metric\": {\"amount\": 2, \"unitLong\": \"Tbsps\", \"unitShort\": \"Tbsps\"}}, \"original\": \"2 tbsp mint leaves, coarsely chopped\", \"nameClean\": \"mint\", \"consistency\": \"SOLID\", \"originalName\": \"mint leaves, coarsely chopped\"}, {\"id\": 2047, \"meta\": [], \"name\": \"salt\", \"unit\": \"tablespoon\", \"aisle\": \"Spices and Seasonings\", \"image\": \"salt.jpg\", \"amount\": 1, \"measures\": {\"us\": {\"amount\": 1, \"unitLong\": \"Tbsp\", \"unitShort\": \"Tbsp\"}, \"metric\": {\"amount\": 1, \"unitLong\": \"Tbsp\", \"unitShort\": \"Tbsp\"}}, \"original\": \"1 tablespoon salt\", \"nameClean\": \"table salt\", \"consistency\": \"SOLID\", \"originalName\": \"salt\"}, {\"id\": 9326, \"meta\": [\"cubed\"], \"name\": \"watermelon\", \"unit\": \"cups\", \"aisle\": \"Produce\", \"image\": \"watermelon.png\", \"amount\": 4, \"measures\": {\"us\": {\"amount\": 4, \"unitLong\": \"cups\", \"unitShort\": \"cups\"}, \"metric\": {\"amount\": 946.352, \"unitLong\": \"milliliters\", \"unitShort\": \"ml\"}}, \"original\": \"4 cups cubed watermelon flesh\", \"nameClean\": \"watermelon\", \"consistency\": \"SOLID\", \"originalName\": \"cubed watermelon flesh\"}]",
				"{\"id\": 665029, \"gaps\": \"GAPS_FULL\", \"cheap\": false, \"diets\": [\"gluten free\", \"dairy free\", \"paleolithic\", \"lacto ovo vegetarian\", \"primal\", \"whole 30\", \"vegan\"], \"image\": \"https://spoonacular.com/recipeImages/665029-556x370.jpg\", \"title\": \"Watermelon Popsicles with Mint, Basil & Lime\", \"vegan\": true, \"license\": \"CC BY 3.0\", \"summary\": \"Watermelon Popsicles with Mint, Basil & Lime might be just the side dish you are searching for. This caveman, gluten free, primal, and whole 30 recipe serves 8 and costs <b>22 cents per serving</b>. One serving contains <b>25 calories</b>, <b>1g of protein</b>, and <b>0g of fat</b>. 13 people have made this recipe and would make it again. It can be enjoyed any time, but it is especially good for <b>Summer</b>. If you have basil leaves, watermelon flesh, salt, and a few other ingredients on hand, you can make it. From preparation to the plate, this recipe takes roughly <b>45 minutes</b>. All things considered, we decided this recipe <b>deserves a spoonacular score of 18%</b>. This score is rather bad. Try <a href=\\\"https://spoonacular.com/recipes/watermelon-lime-mint-popsicles-480038\\\">Watermelon Lime Mint Popsicles</a>, <a href=\\\"https://spoonacular.com/recipes/watermelon-aguas-frescas-with-lime-mint-basil-syrup-27656\\\">Watermelon Aguas Frescas With Lime, Mint & Basil Syrup</a>, and <a href=\\\"https://spoonacular.com/recipes/watermelon-mint-popsicles-with-blueberries-603118\\\">Watermelon-Mint Popsicles with Blueberries</a> for similar recipes.\", \"cuisines\": [], \"servings\": 8, \"dairyFree\": true, \"dishTypes\": [\"side dish\"], \"imageType\": \"jpg\", \"lowFodmap\": false, \"occasions\": [\"summer\"], \"sourceUrl\": \"http://www.foodista.com/recipe/DVBZGTMM/watermelon-popsicles-with-mint-basil-lime\", \"glutenFree\": true, \"originalId\": null, \"sourceName\": \"Foodista\", \"vegetarian\": true, \"creditsText\": \"Foodista.com – The Cooking Encyclopedia Everyone Can Edit\", \"healthScore\": 0, \"sustainable\": false, \"veryHealthy\": false, \"veryPopular\": false, \"winePairing\": {}, \"instructions\": \"<ol><li>Combine basil, lime juice, mint leaves, salt, and cubed watermelon in a blender.  Blend at highest speed until liquefied.</li><li>Pour into popsicle molds or ice cube tray and freeze for a minimum of 4 hours.</li><li>Serve.</li></ol>\", \"aggregateLikes\": 13, \"cookingMinutes\": -1, \"readyInMinutes\": 45, \"pricePerServing\": 21.94, \"preparationMinutes\": -1, \"analyzedInstructions\": [{\"name\": \"\"}], \"spoonacularSourceUrl\": \"https://spoonacular.com/watermelon-popsicles-with-mint-basil-lime-665029\", \"weightWatcherSmartPoints\": 0}",
				"[{\"step\": \"Combine basil, lime juice, mint leaves, salt, and cubed watermelon in a blender.  Blend at highest speed until liquefied.\", \"number\": 1, \"equipment\": [{\"id\": 404726, \"name\": \"blender\", \"image\": \"blender.png\", \"localizedName\": \"blender\"}], \"ingredients\": [{\"id\": 2064, \"name\": \"mint\", \"image\": \"mint.jpg\", \"localizedName\": \"mint\"}, {\"id\": 9160, \"name\": \"lime juice\", \"image\": \"lime-juice.png\", \"localizedName\": \"lime juice\"}, {\"id\": 9326, \"name\": \"watermelon\", \"image\": \"watermelon.png\", \"localizedName\": \"watermelon\"}, {\"id\": 2044, \"name\": \"basil\", \"image\": \"basil.jpg\", \"localizedName\": \"basil\"}, {\"id\": 2047, \"name\": \"salt\", \"image\": \"salt.jpg\", \"localizedName\": \"salt\"}]}, {\"step\": \"Pour into popsicle molds or ice cube tray and freeze for a minimum of 4 hours.\", \"length\": {\"unit\": \"minutes\", \"number\": 240}, \"number\": 2, \"equipment\": [{\"id\": 405929, \"name\": \"popsicle molds\", \"image\": \"popsicle-molds.jpg\", \"localizedName\": \"popsicle molds\"}, {\"id\": 406911, \"name\": \"ice cube tray\", \"image\": \"ice-cube-tray.jpg\", \"localizedName\": \"ice cube tray\"}], \"ingredients\": [{\"id\": 10014412, \"name\": \"ice cubes\", \"image\": \"ice-cubes.png\", \"localizedName\": \"ice cubes\"}]}, {\"step\": \"Serve.\", \"number\": 3, \"equipment\": [], \"ingredients\": []}]",
				"[{\"name\": \"Calories\", \"unit\": \"kcal\", \"amount\": 25.32, \"percentOfDailyNeeds\": 1.27}, {\"name\": \"Fat\", \"unit\": \"g\", \"amount\": 0.13, \"percentOfDailyNeeds\": 0.2}, {\"name\": \"Saturated Fat\", \"unit\": \"g\", \"amount\": 0.01, \"percentOfDailyNeeds\": 0.09}, {\"name\": \"Carbohydrates\", \"unit\": \"g\", \"amount\": 6.47, \"percentOfDailyNeeds\": 2.16}, {\"name\": \"Net Carbohydrates\", \"unit\": \"g\", \"amount\": 6.08, \"percentOfDailyNeeds\": 2.21}, {\"name\": \"Sugar\", \"unit\": \"g\", \"amount\": 4.84, \"percentOfDailyNeeds\": 5.38}, {\"name\": \"Cholesterol\", \"unit\": \"mg\", \"amount\": 0, \"percentOfDailyNeeds\": 0}, {\"name\": \"Sodium\", \"unit\": \"mg\", \"amount\": 873.15, \"percentOfDailyNeeds\": 37.96}, {\"name\": \"Protein\", \"unit\": \"g\", \"amount\": 0.56, \"percentOfDailyNeeds\": 1.12}, {\"name\": \"Vitamin A\", \"unit\": \"IU\", \"amount\": 536.26, \"percentOfDailyNeeds\": 10.73}, {\"name\": \"Vitamin C\", \"unit\": \"mg\", \"amount\": 8.82, \"percentOfDailyNeeds\": 10.69}, {\"name\": \"Vitamin K\", \"unit\": \"µg\", \"amount\": 6.66, \"percentOfDailyNeeds\": 6.34}, {\"name\": \"Potassium\", \"unit\": \"mg\", \"amount\": 101, \"percentOfDailyNeeds\": 2.89}, {\"name\": \"Manganese\", \"unit\": \"mg\", \"amount\": 0.06, \"percentOfDailyNeeds\": 2.76}, {\"name\": \"Magnesium\", \"unit\": \"mg\", \"amount\": 9.55, \"percentOfDailyNeeds\": 2.39}, {\"name\": \"Copper\", \"unit\": \"mg\", \"amount\": 0.04, \"percentOfDailyNeeds\": 2.1}, {\"name\": \"Vitamin B6\", \"unit\": \"mg\", \"amount\": 0.04, \"percentOfDailyNeeds\": 2}, {\"name\": \"Vitamin B1\", \"unit\": \"mg\", \"amount\": 0.03, \"percentOfDailyNeeds\": 1.85}, {\"name\": \"Vitamin B5\", \"unit\": \"mg\", \"amount\": 0.18, \"percentOfDailyNeeds\": 1.82}, {\"name\": \"Fiber\", \"unit\": \"g\", \"amount\": 0.39, \"percentOfDailyNeeds\": 1.56}, {\"name\": \"Iron\", \"unit\": \"mg\", \"amount\": 0.27, \"percentOfDailyNeeds\": 1.48}, {\"name\": \"Folate\", \"unit\": \"µg\", \"amount\": 4.56, \"percentOfDailyNeeds\": 1.14}, {\"name\": \"Vitamin B2\", \"unit\": \"mg\", \"amount\": 0.02, \"percentOfDailyNeeds\": 1.14}, {\"name\": \"Calcium\", \"unit\": \"mg\", \"amount\": 10.67, \"percentOfDailyNeeds\": 1.07}, {\"name\": \"Phosphorus\", \"unit\": \"mg\", \"amount\": 10.58, \"percentOfDailyNeeds\": 1.06}]",
				userProfileRepository.findById((long)1).get()
			) 
		); 
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

