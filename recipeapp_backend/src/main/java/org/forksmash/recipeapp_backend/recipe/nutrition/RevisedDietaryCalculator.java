package org.forksmash.recipeapp_backend.recipe.nutrition;

import java.util.Map;
import java.util.HashMap;

import org.forksmash.recipeapp_backend.recipe.nutrition.Nutrient;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RevisedDietaryCalculator {
    // Nutrients that are bad (e.g. fats, sugar) will be de-emphasised, while nutrients that are good will be emphasised

    private static Nutrient[] nutrientJsonToArray(String nutritionJson) {
        ObjectMapper mapper = new ObjectMapper();
        Nutrient[] nutrients = null;
        try {
            nutrients = mapper.readValue(nutritionJson, Nutrient[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nutrients;
    }
    
    public static Map<Nutrient, Integer> calculateNutrientExcessOrDeficit(String nutritionJson) {
        Nutrient[] nutrients = nutrientJsonToArray(nutritionJson);
        Map<Nutrient, Integer> excessOrDeficitMap = new HashMap<>();
        // Award a deficiency score based on the %DV of that nutrient, from 1 to 10.
        // If the nutrient is bad, negate the score. This will mean the system should recommend less recipes that contain a lot of that nutrient
        for (Nutrient nutrient : nutrients) {
            int score = (int) nutrient.getPercentageOfDailyNeeds() / 10;
            if (nutrient.isBad()) {
                score *= -1;
            }
            excessOrDeficitMap.put(nutrient, score);
        }
        return excessOrDeficitMap;
    }
}
