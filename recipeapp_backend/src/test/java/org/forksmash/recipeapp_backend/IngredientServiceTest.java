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
import org.forksmash.recipeapp_backend.ingredientType.IngredientType;

@ExtendWith(MockitoExtension.class)

public class IngredientServiceTest {
    @Mock
    private IngredientRepository ingredients;

    @InjectMocks
    private IngredientServiceImpl ingredientService;
    
    
    public IngredientServiceTest(String string) {
    }
    public IngredientServiceTest(Ingredient ingredient) {
    }
    @Test
    void addIngredient_NewId_ReturnSavedIngredient(){
        // arrange ***
        Ingredient ingredient = new Ingredient("beef",new IngredientType("meet"));
        // mock the "findbytitle" operation
        when(ingredients.findById((long) 11)).thenReturn(new ArrayList<Ingredient>());
        // mock the "save" operation 
        when(ingredients.save(any(Ingredient.class))).thenReturn(ingredient);

        // act ***
        Ingredient savedIngredient = ingredientService.addIngredient(ingredient);
        
        // assert ***
        assertNotNull(savedIngredient);
        verify(ingredients).findById(ingredient.getId());
        verify(ingredients).save(ingredient);
    }
    private IngredientType IngredientType(String string) {
        return null;
    }
    /**
     * Write a test case for adding a new ingredient but the title already exists
     * The test case should pass if IngredientServiceImpl.addIngredient(ingredient)
     * returns null (can't add ingredient), otherwise it will fail.
     * Remember to include suitable "verify" operations
     * 
     */
    @Test
    void addIngredient_SameId_ReturnNull(){
        // your code here
        Ingredient ingredient = new Ingredient("beef",new IngredientType("meet"));
        List<Ingredient> sameIds = new ArrayList<Ingredient>();
        sameIds.add(new Ingredient("beef",new IngredientType("meet")));
        when(ingredients.findById(ingredient.getId())).thenReturn(sameIds);

        Ingredient savedIngredient = ingredientService.addIngredient(ingredient);
        
        assertNull(savedIngredient);
        verify(ingredients).findById(ingredient.getId());
    }
    public Long getId() {
        return null;
    }

}
