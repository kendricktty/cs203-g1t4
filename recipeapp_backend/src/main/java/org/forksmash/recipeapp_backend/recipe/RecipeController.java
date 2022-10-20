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
import org.forksmash.recipeapp_backend.user.User;
import org.forksmash.recipeapp_backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RecipeController {
    private RecipeService recipeService;
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;
    
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getRecipes() {
        return ResponseEntity.ok().body(recipeService.listRecipes());
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
        // Find the user 
        User user = userRepository.findById((long)3).get();
        // Recipe recipe = recipeRepository.findById((long)1).get();
        // // if recipe has user id and recipe id
        // if (user.getId() && recipeR)

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
