package org.forksmash.recipeapp_backend.recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> listRecipes();
    List<Recipe> listRecipesFromProfileId(Long id);
    Recipe getRecipe(int recipeDataId, Long profileId);
    Recipe addRecipe(Recipe recipe);
    Recipe updateRecipe(Long id, Recipe recipe);
    void deleteRecipe(int recipeDataId, Long userProfileId);
}
