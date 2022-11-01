package org.forksmash.recipeapp_backend.nutrient.util;

import java.util.Map;
import java.util.TreeMap;

import org.forksmash.recipeapp_backend.nutrient.Nutrient;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NutrientConverter {
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
}
