package org.forksmash.recipeapp_backend.recipe;

import org.forksmash.recipeapp_backend.userprofile.UserProfile;
import org.forksmash.recipeapp_backend.userprofile.UserProfileRepository;
import org.forksmash.recipeapp_backend.nutrient.Nutrient;
import org.forksmash.recipeapp_backend.recipe.nutrition.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipes;

    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public RecipeServiceImpl(RecipeRepository recipes) {
        this.recipes = recipes; 
    }

    @Override
    public List<Recipe> listRecipes() { return recipes.findAll(); }

    @Override
    public List<Recipe> listRecipesFromProfileId(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id).get();
        return recipes.findAllByProfileId(userProfile.getProfileId());
    }  

    @Override
    public Recipe getRecipe(Long id) { return recipes.findById(id).orElse(null); }

    @Override
    public Recipe addRecipe(Recipe recipe) { 
        log.info("Recipe (recipeServiceImpl): ", recipe);
        return recipes.save(recipe); 
    }

    @Override
    public Recipe updateRecipe(Long id, Recipe newRecipe) {
        return recipes.findById(id).map(recipe -> {
            // recipe.setData(newRecipe.getData());
            recipe.setInfo(newRecipe.getInfo());
            recipe.setInstructions(newRecipe.getInstructions());
            recipe.setExtendedIngredients(newRecipe.getExtendedIngredients());
            recipe.setNutrition(newRecipe.getNutrition());
            return recipes.save(recipe);
        }).orElse(null);
    }

    @Override
    public void deleteRecipe(Long id) { recipes.deleteById(id); }

    /* 
     * Based on all recipes favourited by the user, determine the average excess/deficiency score
     * for that recipe
     * Refer to RevisedDietaryCalculator.java for more details
     */

    // Nested class to keep track of the current sum and total number of elements to find the average later
    private class Series {
        private double sum;
        private int total;
        public Series(int sum, int total) {
            this.sum = sum;
            this.total = total;
        }
        public double average() {
            return sum / total;
        }
    }
}
