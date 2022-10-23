package org.forksmash.recipeapp_backend.ingredient;

import java.util.List;

import org.forksmash.recipeapp_backend.IngredientServiceTest;
import org.forksmash.recipeapp_backend.ingredientType.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * We only need this interface declaration
 * Spring will automatically generate an implementation of the repo
 * 
 * 
 */
@Repository
public interface IngredientRepository extends JpaRepository <Ingredient, Long> {
    // derived query to find ingredients by name
    List<Ingredient> findByName(String name);

    IngredientType save(IngredientServiceTest ingredient);

}
