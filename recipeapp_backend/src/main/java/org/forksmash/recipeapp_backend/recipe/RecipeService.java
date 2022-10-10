package org.forksmash.recipeapp_backend.recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> listRecipes();
    Recipe getRecipe(Long id);
    Recipe addRecipe(Recipe recipe);
    //Recipe updateRecipe(Long id, Recipe recipe);
    //Haiyang: No need update

    /**
     * Change method's signature: do not return a value for delete operation
     * @param id
     */
    void deleteRecipe(Long id);
}