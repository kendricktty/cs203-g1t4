package org.forksmash.recipeapp_backend.recipe;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.forksmash.recipeapp_backend.ingredient.IngredientNotFoundException;
import org.forksmash.recipeapp_backend.ingredient.IngredientRepository;

@RestController
public class RecipeController {
    private RecipeRepository recipes;
    private IngredientRepository ingredients;

    public RecipeController(RecipeRepository recipes, IngredientRepository ingredients){
        this.recipes = recipes;
        this.ingredients = ingredients;
    }

    @GetMapping("/ingredients/{ingredientId}/recipes")
    public List<Recipe> getAllRecipesByIngredientId(@PathVariable (value = "ingredientId") Long ingredientId) {
        if(!ingredients.existsById(ingredientId)) {
            throw new IngredientNotFoundException(ingredientId);
        }
        return recipes.findByIngredientId(ingredientId);
    }

    @PostMapping("/ingredients/{ingredientId}/recipes")
    public Recipe addRecipe(@PathVariable (value = "ingredientId") Long ingredientId, @Valid @RequestBody Recipe recipe) {
        // using "map" to handle the returned Optional object from "findById(ingredientId)"
        return ingredients.findById(ingredientId).map(ingredient ->{
            recipe.setIngredient(ingredient);
            return recipes.save(recipe);
        }).orElseThrow(() -> new IngredientNotFoundException(ingredientId));
    }

    /*
    @PutMapping("/ingredients/{ingredientId}/recipes/{recipeId}")
    public Recipe updateRecipe(@PathVariable (value = "ingredientId") Long ingredientId,
                                 @PathVariable (value = "recipeId") Long recipeId,
                                 @Valid @RequestBody Recipe newRecipe) {
        if(!ingredients.existsById(ingredientId)) {
            throw new IngredientNotFoundException(ingredientId);
        }
        return recipes.findByIdAndIngredientId(recipeId, ingredientId).map(recipe -> {
            recipe.setRecipe(newRecipe.getRecipe());
            return recipes.save(recipe);
        }).orElseThrow(() -> new RecipeNotFoundException(recipeId));
    } */
    //Haiyang: we don't need to update recipe as they are from API

    /**
     * Use a ResponseEntity to have more control over the response sent to client
     */
    @DeleteMapping("/ingredients/{ingredientId}/recipes/{recipeId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable (value = "ingredientId") Long ingredientId,
                              @PathVariable (value = "recipeId") Long recipeId) {
        
        if(!ingredients.existsById(ingredientId)) {
            throw new IngredientNotFoundException(ingredientId);
        }

        return recipes.findByIdAndIngredientId(recipeId, ingredientId).map(recipe -> {
            recipes.delete(recipe);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RecipeNotFoundException(recipeId));
    }
}