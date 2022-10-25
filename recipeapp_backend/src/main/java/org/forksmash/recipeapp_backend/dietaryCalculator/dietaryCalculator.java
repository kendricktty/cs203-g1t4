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




    //sample test code
    public static void main(String[] args) {
        
    }

    // input should be json file?
    private HashMap<String, Double> userNutrientBreakdownPercentage(){
        
        // convert info from json file to hashmap
        // All measurements in mg for now for standardisation. 
        // eg. key, value pair. calcium : 1000 
    

        //Once got the total, convert to percentage and return a hashmap of percentage of
        //nutriential breakdown.
        // eg. calcium : 40 where 40 is the percentage of calcium in the user's total diet.
        
        
        return null;
    }



}
