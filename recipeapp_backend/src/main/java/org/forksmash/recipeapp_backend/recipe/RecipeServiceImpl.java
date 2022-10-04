package org.forksmash.recipeapp_backend.recipe;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipes;
    public RecipeServiceImpl(RecipeRepository recipes) {
        this.recipes = recipes;
    }

    @Override
    public List<Recipe> listRecipes() { return recipes.findAll(); }

    @Override
    public Recipe getRecipe(Long id) { return recipes.findById(id).orElse(null); }

    @Override
    public Recipe addRecipe(Recipe recipe) { return recipes.save(recipe); }

    @Override
    public Recipe updateRecipe(Long id, Recipe newRecipe) {
        return recipes.findById(id).map(recipe -> {
            recipe.setData(newRecipe.getData());
            return recipes.save(recipe);
        }).orElse(null);
    }

    @Override
    public void deleteRecipe(Long id) { recipes.deleteById(id); }

}
