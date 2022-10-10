package org.forksmash.recipeapp_backend.ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> listIngredients();
    Ingredient getIngredient(Long id);
    Ingredient addIngredient(Ingredient ingredient);
    //Haiyang: Add method is not needed for recipe
    //Ingredient updateIngredient(Long id, Ingredient ingredient);
    //Haiyang: we don't need to update ingredient

    /**
     * Change method's signature: do not return a value for delete operation
     * @param id
     */
    void deleteIngredient(Long id); 
}