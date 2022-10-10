package org.forksmash.recipeapp_backend.recipe;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    // additional derived queries specified here will be implemented by Spring Data JPA
    // start the derived query with "findBy", then reference the entity attributes you want to filter
    List<Recipe> findByIngredientId(Long ingredientId);
    Optional<Recipe> findByIdAndIngredientId(Long id, Long ingredientId);
}