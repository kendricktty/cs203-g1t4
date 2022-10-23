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

@ExtendWith(MockitoExtension.class)

public class IngredientServiceTest {
    @Mock
    private IngredientRepository ingredients;

    @InjectMocks
    private IngredientServiceImpl ingredientService;
    
    
    @Test
    void addIngredient_NewTitle_ReturnSavedIngredient(){
        // arrange ***
        Ingredient ingredient = new Ingredient("This is a New Title");
        // mock the "findbytitle" operation
        when(ingredients.findByTitle(any(String.class))).thenReturn(new ArrayList<Ingredient>());
        // mock the "save" operation 
        when(ingredients.save(any(Ingredient.class))).thenReturn(ingredient);

        // act ***
        Ingredient savedIngredient = ingredientService.addIngredient(ingredient);
        
        // assert ***
        assertNotNull(savedIngredient);
        verify(ingredients).findByTitle(ingredient.getTitle());
        verify(ingredients).save(ingredient);
    }
    /**
     * Write a test case for adding a new ingredient but the title already exists
     * The test case should pass if IngredientServiceImpl.addIngredient(ingredient)
     * returns null (can't add ingredient), otherwise it will fail.
     * Remember to include suitable "verify" operations
     * 
     */
    @Test
    void addIngredient_SameTitle_ReturnNull(){
        // your code here
        Ingredient ingredient = new Ingredient("The Same Title Exists");
        List<Ingredient> sameTitles = new ArrayList<Ingredient>();
        sameTitles.add(new Ingredient("The Same Title Exists"));
        when(ingredients.findByTitle(ingredient.getTitle())).thenReturn(sameTitles);

        Ingredient savedIngredient = ingredientService.addIngredient(ingredient);
        
        assertNull(savedIngredient);
        verify(ingredients).findByTitle(ingredient.getTitle());
    }

    @Test
    void updateIngredient_NotFound_ReturnNull(){
        Ingredient ingredient = new Ingredient("Updated Title of Ingredient");
        Long ingredientId = 10L;
        when(ingredients.findById(ingredientId)).thenReturn(Optional.empty());
        
        Ingredient updatedIngredient = ingredientService.updateIngredient(ingredientId, ingredient);
        
        assertNull(updatedIngredient);
        verify(ingredients).findById(ingredientId);
    }

    
    @Test
    void countLongestTitles_OneLongest_Return1(){
        // your code here
        List<Ingredient> newIngredients = new ArrayList<Ingredient>();
        newIngredients.add(new Ingredient("The Longest"));
        newIngredients.add(new Ingredient("The Longest Title"));
        newIngredients.add(new Ingredient("The Longest Title of Ingredient"));
        when(ingredients.findAll()).thenReturn(newIngredients);

        int count = ingredientService.countLongestIngredientTitles();
        
        assertEquals(1, count);
        verify(ingredients).findAll();
    }

    @Test
    void countLongestTitles_TwoLongest_Return2(){
        // your code here
        List<Ingredient> newIngredients = new ArrayList<Ingredient>();
        newIngredients.add(new Ingredient("The Longest"));
        newIngredients.add(new Ingredient("The Longer Title of Ingredient"));
        newIngredients.add(new Ingredient("The Longest"));
        newIngredients.add(new Ingredient("The Longest Title of Ingredient"));
        
        when(ingredients.findAll()).thenReturn(newIngredients);

        int count = ingredientService.countLongestIngredientTitles();
        
        assertEquals(2, count);
        verify(ingredients).findAll();
    }

    @Test
    void countLongestTitles_AllLongest_Return4(){
        // your code here
        List<Ingredient> newIngredients = new ArrayList<Ingredient>();
        newIngredients.add(new Ingredient("The Long Ingredient"));
        newIngredients.add(new Ingredient("The Longer Ingredient"));
        newIngredients.add(new Ingredient("The Longerer Ingredient"));
        newIngredients.add(new Ingredient("The Longest Ingredient"));
        
        when(ingredients.findAll()).thenReturn(newIngredients);

        int count = ingredientService.countLongestIngredientTitles();
        
        assertEquals(4, count);
        verify(ingredients).findAll();
    }
   
}
