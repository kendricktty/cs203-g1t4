package org.forksmash.recipeapp_backend.nutrient;

import java.util.Map;
import java.util.TreeMap;

import org.forksmash.recipeapp_backend.nutrient.util.NutrientConverter;
import org.forksmash.recipeapp_backend.recipe.Recipe;
import org.forksmash.recipeapp_backend.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.HashMap;
import java.util.List;

/* Logical-Breakdown
Input json
    See users fav recipe -> get nutriential values from those -> 

 * -> Hashmap of total actual nutrient -> function to calculate total nutrient value
 * -> Determine hashmap of expected nutriential breakdown
 * -> hashmap of total actual nutrient minus projected 
 * -> Only concerned with negative (deficit)
 * -> Return hashmap of key : value pair, key being deficit nutrients and value being the 
 * recommended values per meal.
 * 
 */
@Service
public class NutrientServiceImpl implements NutrientService {
    @Autowired
    private RecipeService recipeService;

    private final Map<NutrientDesired, Double> MEAL_BREAKDOWN = new HashMap<>();
    private final Map<String, NutrientDesired> NUTRIENTS = new HashMap<>();

    public Map<NutrientDesired, Double> getMealBreakdown() {
        return MEAL_BREAKDOWN;
    }

    public Map<String, NutrientDesired> getNutrients() {
        return NUTRIENTS;
    }

    @Autowired
    public NutrientServiceImpl(RecipeService recipeService) {
        populateMealBreakdown();
        populateNutrients();
        this.recipeService = recipeService;
    }

    @Override
    public Map<String, Double> getNutrientDeficitById(Long id) throws JsonProcessingException {
        List<Recipe> recipeList = recipeService.listRecipesFromProfileId(id);
        TreeMap<String, Double> nutrientDeficit = new TreeMap<>();

        for (Recipe recipe : recipeList) {
            // Convert the JSON into Nutrient array
            String recipeNutrition = recipe.getNutrition();
            NutrientActual[] jsonToArray = NutrientConverter.nutrientJsonToArray(recipeNutrition);
            for (NutrientActual nutrient : jsonToArray) {
                String nutrientName = nutrient.getName();
                System.out.println("Nutrient: " + nutrientName);

                NutrientDesired desiredNutrient = NUTRIENTS.get(nutrientName);
                Double desired = MEAL_BREAKDOWN.get(desiredNutrient);
                if (desired != null) {
                    double deficit = nutrient.getAmount() - desired;
                    nutrientDeficit.put(nutrientName, deficit);
                }
            }
        }
        return nutrientDeficit;
    }

    private void populateMealBreakdown() {
        // Source: https://www.fda.gov/media/99069/download
        // Populates MEAL_BREAKDOWN
        NutrientDesired vitaminA = new NutrientDesired("Vitamin A", "mcg");
        MEAL_BREAKDOWN.put(vitaminA, 900.0);

        NutrientDesired vitaminC = new NutrientDesired("Vitamin C", "mg");
        MEAL_BREAKDOWN.put(vitaminC, 90.0);

        NutrientDesired calcium = new NutrientDesired("Calcium", "mg");
        MEAL_BREAKDOWN.put(calcium, 1300.0);

        NutrientDesired iron = new NutrientDesired("Iron", "mcg");
        MEAL_BREAKDOWN.put(iron, 18.0);

        NutrientDesired vitaminD = new NutrientDesired("Vitamin D", "mcg");
        MEAL_BREAKDOWN.put(vitaminD, 20.0);

        NutrientDesired vitaminE = new NutrientDesired("Vitamin E", "mg");
        MEAL_BREAKDOWN.put(vitaminE, 15.0);

        NutrientDesired vitaminK = new NutrientDesired("Vitamin K", "mcg");
        MEAL_BREAKDOWN.put(vitaminK, 120.0);

        NutrientDesired thiamin = new NutrientDesired("Thiamin", "mg");
        MEAL_BREAKDOWN.put(thiamin, 1.2);

        NutrientDesired riboflavin = new NutrientDesired("Riboflavin", "mg");
        MEAL_BREAKDOWN.put(riboflavin, 1.3);

        NutrientDesired niacin = new NutrientDesired("Niacin", "mg");
        MEAL_BREAKDOWN.put(niacin, 16.0);

        NutrientDesired vitaminB6 = new NutrientDesired("Vitamin B6", "mg");
        MEAL_BREAKDOWN.put(vitaminB6, 1.7);

        NutrientDesired folate = new NutrientDesired("Folate", "mcg");
        MEAL_BREAKDOWN.put(folate, 15.0);

        NutrientDesired vitaminB = new NutrientDesired("Vitamin B", "mcg");
        MEAL_BREAKDOWN.put(vitaminB, 2.4);

        NutrientDesired biotin = new NutrientDesired("Biotin", "mcg");
        MEAL_BREAKDOWN.put(biotin, 30.0);

        NutrientDesired pantothenicAcid = new NutrientDesired("Pantothenic acid", "mg");
        MEAL_BREAKDOWN.put(pantothenicAcid, 5.0);

        NutrientDesired zinc = new NutrientDesired("Zinc", "mg");
        MEAL_BREAKDOWN.put(zinc, 11.0);

        NutrientDesired selenium = new NutrientDesired("Selenium", "mcg");
        MEAL_BREAKDOWN.put(selenium, 55.0);

        NutrientDesired copper = new NutrientDesired("Copper", "mg");
        MEAL_BREAKDOWN.put(copper, 0.9);

        NutrientDesired manganese = new NutrientDesired("Manganese", "mg");
        MEAL_BREAKDOWN.put(manganese, 2.3);

        NutrientDesired chromium = new NutrientDesired("Chromium", "mcg");
        MEAL_BREAKDOWN.put(chromium, 35.0);

        NutrientDesired phosphorus = new NutrientDesired("phosphorus", "mg");
        MEAL_BREAKDOWN.put(phosphorus, 1250.0);

        NutrientDesired iodine = new NutrientDesired("iodine", "mcg");
        MEAL_BREAKDOWN.put(iodine, 150.0);

        NutrientDesired magnesium = new NutrientDesired("magnesium", "mg");
        MEAL_BREAKDOWN.put(magnesium, 420.0);

        NutrientDesired molybdenum = new NutrientDesired("molybdenum", "mcg");
        MEAL_BREAKDOWN.put(molybdenum, 45.0);

        NutrientDesired chloride = new NutrientDesired("chloride", "mg");
        MEAL_BREAKDOWN.put(chloride, 2300.0);

        NutrientDesired potassium = new NutrientDesired("potassium", "mg");
        MEAL_BREAKDOWN.put(potassium, 4700.0);

        NutrientDesired choline = new NutrientDesired("choline", "mg");
        MEAL_BREAKDOWN.put(choline, 550.0);

        NutrientDesired protein = new NutrientDesired("protein", "g");
        MEAL_BREAKDOWN.put(protein, 11.0);
    }

    private void populateNutrients() {
        for (NutrientDesired nutrient : MEAL_BREAKDOWN.keySet()) {
            NUTRIENTS.put(nutrient.getName(), nutrient);
        }
    }
}
