package org.forksmash.recipeapp_backend.dietaryCalculator;

import java.util.Map;

import org.forksmash.recipeapp_backend.dietaryCalculator.nutrient.Nutrient;
import org.forksmash.recipeapp_backend.dietaryCalculator.nutrient.bad_nutrient.BadNutrient;
import org.forksmash.recipeapp_backend.dietaryCalculator.nutrient.good_nutrient.GoodNutrient;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RevisedDietaryCalculator {
    public Map<Nutrient, Double> calculateNutrientExcessShortage(String nutritionJson) {
        ObjectMapper mapper = new ObjectMapper();
        Nutrient[] nutrients = null;
        try {
            nutrients = mapper.readValue(nutritionJson, Nutrient[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        for (Nutrient nutrient : nutrients) {

        }
    }
}
