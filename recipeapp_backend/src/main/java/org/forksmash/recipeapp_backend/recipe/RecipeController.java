package org.forksmash.recipeapp_backend.recipe;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

public class RecipeController {
    private RecipeService recipeService;
    
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return recipeService.listRecipes();
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null)
            throw new RecipeNotFoundException(id);
        return recipe;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/recipes")
    public Recipe addRecipe(@Valid @RequestBody Recipe recipe) { 
        return recipeService.addRecipe(recipe); 
    }

    @PutMapping("/recipes/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe newRecipe) {
        Recipe recipe = recipeService.updateRecipe(id, newRecipe);
        if (recipe == null)
            throw new RecipeNotFoundException(id);
        return recipe;
    }

    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        try {
            recipeService.deleteRecipe(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecipeNotFoundException(id);
        }
    }
}
