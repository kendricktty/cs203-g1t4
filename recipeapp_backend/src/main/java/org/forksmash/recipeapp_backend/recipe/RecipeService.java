package org.forksmash.recipeapp_backend.recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> listRecipes();
    List<Recipe> listRecipesFromProfileId(Long id);
    Recipe getRecipe(Long id);
    Recipe addRecipe(Recipe recipe);
    Recipe updateRecipe(Long id, Recipe recipe);
    void deleteRecipe(Long id);
}
