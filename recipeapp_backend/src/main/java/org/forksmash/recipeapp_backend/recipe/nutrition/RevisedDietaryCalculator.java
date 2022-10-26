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
    
    public static Map<Nutrient, Double> calculateNutrientExcessOrDeficit(String nutritionJson) {
        Nutrient[] nutrients = nutrientJsonToArray(nutritionJson);
        Map<Nutrient, Double> excessOrDeficitMap = new HashMap<>();
        /* 
         * Awards a deficiency/excess score between 1 and 10 based on the %DV of that nutrient.
         * The score is derived by taking the %DV of that nutrient and dividing it by 10
         * The smaller the score, the less healthy the nutrient is, and the less the
         * program should recommend recipes containing large quantities of that recipe.
         */
        for (Nutrient nutrient : nutrients) {
            double score = nutrient.getPercentageOfDailyNeeds() / 10;
            /* If a nutrient is bad, like fats or sugar, the score derived at this stage is an excess 
             * Hence, take 10 minus the derived score to convert it into a "deficiency" value
            */
            if (nutrient.isBad()) {
                score = 10 - score;
            }
            excessOrDeficitMap.put(nutrient, score);
        }
        return excessOrDeficitMap;
    }
}
