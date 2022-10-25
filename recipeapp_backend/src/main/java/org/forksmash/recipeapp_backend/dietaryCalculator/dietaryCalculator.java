package org.forksmash.recipeapp_backend.dietaryCalculator;

import java.util.HashMap;

// Logic of dietaryCaclulator
// (Convert json to hashmap)
// Get nutriential breakdown from user's favorite recipe. 
// Convert hashmap of total nutrient to percentage
// Get nutriential percentage from user's favorite recipe.
// From that hashmap, find nutrients where user is lacking. and recommend meals where that is
// the recommended amount for daily meals. (Not good to consume too much at once.)

public class dietaryCalculator {

    // sample test code
    public static void main(String[] args) {

    }

    // input should be json file?
    private HashMap<String, Double> userNutrientBreakdownPercentage() {

        // convert info from json file to hashmap
        // All measurements in mg for now for standardisation.
        // eg. key, value pair. calcium : 1000
        // Once got the total, convert to percentage and return a hashmap of percentage
        // of
        // nutriential breakdown.
        // eg. calcium : 40 where 40 is the percentage of calcium in the user's total
        // diet.

        // Base HashMap

        return null;
    }

    //
    private HashMap<String, Double> userRequiredNutrients() {

        //input is a hashmap of the total nutrient by user.
        
        //

        return null;
    }

    private double getTotal(HashMap<String, Double> totalHashMapOfUser){
        //input is the hashmap of the total nutrient by user.
        Double total = 0.0;
        for(Double value : totalHashMapOfUser.values()){
            total+= value; 
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
