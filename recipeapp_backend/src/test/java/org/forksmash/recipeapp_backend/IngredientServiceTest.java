package org.forksmash.recipeapp_backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.forksmash.recipeapp_backend.ingredient.*;
import org.forksmash.recipeapp_backend.ingredientType.*;

@ExtendWith(MockitoExtension.class)

public class IngredientServiceTest {
    @Mock
    private IngredientRepository ingredients;

    @InjectMocks
    private IngredientServiceImpl ingredientService;
    
    @Test
    void addIngredient_NewName_ReturnSavedIngredient(){
        // arrange ***
        Ingredient ingredient = new Ingredient("beef",new IngredientType("meet"));
        // mock the "findbyName" operation
        when(ingredients.findByName(any(String.class))).thenReturn(new ArrayList<Ingredient>());
        // mock the "save" operation 
        when(ingredients.save(any(Ingredient.class))).thenReturn(ingredient);

        // act ***
        Ingredient savedIngredient = ingredientService.addIngredient(ingredient);
        
        // assert ***
        assertNotNull(savedIngredient);
        verify(ingredients).findByName(ingredient.getName());
        verify(ingredients).save(ingredient);
    }

    /**
     * Write a test case for adding a new ingredient but the id already exists
     * The test case should pass if IngredientServiceImpl.addIngredient(ingredient)
     * returns null (can't add ingredient), otherwise it will fail.
     * Remember to include suitable "verify" operations
     * 
     */
    @Test
    void addIngredient_SameName_ReturnNull(){
        
        Ingredient ingredient = new Ingredient("beef",new IngredientType("meet"));
        List<Ingredient> sameNames = new ArrayList<Ingredient>();
        sameNames.add(new Ingredient("beef",new IngredientType("meet")));
        when(ingredients.findByName(ingredient.getName())).thenReturn(sameNames);

        Ingredient savedIngredient = ingredientService.addIngredient(ingredient);
        
        assertNull(savedIngredient);
        verify(ingredients).findByName(ingredient.getName());
    }

}
