package org.forksmash.recipeapp_backend.nutrient;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface NutrientService {
    Map<String, Double> getNutrientDeficitById(Long id) throws JsonProcessingException, JsonProcessingException;
}
