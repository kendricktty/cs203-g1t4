package org.forksmash.recipeapp_backend.nutrient.util;

import java.util.Map;
import java.util.TreeMap;

import org.forksmash.recipeapp_backend.nutrient.NutrientActual;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NutrientConverter {
    public static NutrientActual[] nutrientJsonToArray(String nutritionJson) {
        ObjectMapper mapper = new ObjectMapper();
        NutrientActual[] nutrients = null;
        try {
            nutrients = mapper.readValue(nutritionJson, NutrientActual[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nutrients;
    }
}
