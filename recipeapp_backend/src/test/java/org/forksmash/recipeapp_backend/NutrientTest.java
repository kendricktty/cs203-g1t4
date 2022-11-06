package org.forksmash.recipeapp_backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.forksmash.recipeapp_backend.nutrient.Nutrient;
import org.forksmash.recipeapp_backend.nutrient.NutrientDesired;
import org.forksmash.recipeapp_backend.nutrient.NutrientServiceImpl;
import org.forksmash.recipeapp_backend.recipe.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NutrientTest {
    @Mock
    private RecipeRepository recipes;

    @InjectMocks
    private NutrientServiceImpl nutrientService;

    @Test
    void TestNutrientServiceMapsBuild() {
        // Arrange
        Map<NutrientDesired, Double> idealNutrients = nutrientService.getMealBreakdown();

        // Act
        NutrientDesired chromium = new NutrientDesired("Chromium", "mcg");
        double chromiumDesired = idealNutrients.get(chromium);

        // Assert
        assertEquals(35.0, chromiumDesired); // The desired daily amount of chromium in a meal
    }

    @Test
    void TestNutrientsMap() {
        // Arrange
        Map<String, NutrientDesired> nutrientsMap = nutrientService.getNutrients();

        // Act
        NutrientDesired chromium = new NutrientDesired("Chromium", "mcg");

        // Assert
        assertEquals(chromium, nutrientsMap.get("Chromium")); // The desired daily amount of chromium in a meal
    }
}
