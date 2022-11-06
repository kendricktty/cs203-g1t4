package org.forksmash.recipeapp_backend.dietaryCalculator;

import java.util.HashMap;

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

public class dietaryCalculator {

    // sample test code
    public static void main(String[] args) {

    }

    // THIS IS ESSENTIALLY THE actual main method.
    // Return hashmap of deficient key with recommeded values per meal
    // INCOMPLETE
    private HashMap<String, Double> userRequiredNutrients() {

        // Handling input to get total actual hashmap.
        // Find the overallTotal value with the method below.
        // With the overallTotal -> Get projected hashmap.
        // actual hashmap minus projected hashmap.
        // Only concerned with negative (deficit)
        // Return hashmap of key : value pair, key being deficit nutrients and value
        // being the
        // recommended values per meal.

        return null;
    }

    private HashMap<String, Double> projectedHashMap(Double overallTotal) {
        // Get projected hashmap from the overall total.
        // Use dietaryIdeal.

        return null;

    }

    private double getTotal(HashMap<String, Double> totalHashMapOfUser) {
        // input is the hashmap of the total nutrient by user.
        Double total = 0.0;
        for (Double value : totalHashMapOfUser.values()) {
            total += value;
        }

        return total;
    }

    private HashMap<String, Double> createBaseMap() {

        // Keep key(String name) to be same as dietaryIdeal file ty.
        HashMap<String, Double> basemap = new HashMap<>();

        basemap.put("Calcium", 0.0);
        basemap.put("vitaminB", 0.0);
        basemap.put("copper", 0.0);
        basemap.put("Fluoride", 0.0);
        basemap.put("Folic Acid", 0.0);
        basemap.put("Iodine", 0.0);
        basemap.put("Iron", 0.0);
        basemap.put("Magnesium", 0.0);
        basemap.put("Manganese", 0.0);
        basemap.put("Phosphorus", 0.0);
        basemap.put("Selenium", 0.0);
        basemap.put("Sodium", 0.0);
        basemap.put("VitaminA", 0.0);
        basemap.put("VitaminB3", 0.0);
        basemap.put("VitaminB6", 0.0);
        basemap.put("VitaminC", 0.0);
        basemap.put("VitaminD", 0.0);
        basemap.put("VitaminE", 0.0);
        basemap.put("Zinc", 0.0);

        return basemap;

    }

}
