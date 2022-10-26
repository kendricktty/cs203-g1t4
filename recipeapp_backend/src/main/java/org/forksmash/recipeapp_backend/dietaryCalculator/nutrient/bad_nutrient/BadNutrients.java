package org.forksmash.recipeapp_backend.dietaryCalculator.nutrient.bad_nutrient;
import java.util.Set;
import java.util.HashSet;
public class BadNutrients {
    public static final Set<String> BAD_NUTRIENTS = new HashSet<>();;
    public static void populateBadNutrients() {
        BAD_NUTRIENTS.add("Fat");
        BAD_NUTRIENTS.add("Saturated Fat");
        BAD_NUTRIENTS.add("Carbohydrates");
        BAD_NUTRIENTS.add("Net Carbohydrates");
        BAD_NUTRIENTS.add("Sugar");
        BAD_NUTRIENTS.add("Cholesterol");
        BAD_NUTRIENTS.add("Sodium");

    }
}
