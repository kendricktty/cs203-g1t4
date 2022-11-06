package org.forksmash.recipeapp_backend.userIngredient;

import java.util.List;

public interface UserIngredientService {
    List<UserIngredient> listIngredientsFromProfileId(Long id);
    UserIngredient addIngredient(UserIngredient userIngredient);
    void deleteIngredient(Long id, Long userProfileId);
}
