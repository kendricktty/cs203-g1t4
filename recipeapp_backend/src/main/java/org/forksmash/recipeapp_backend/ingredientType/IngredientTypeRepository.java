package org.forksmash.recipeapp_backend.ingredientType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientTypeRepository extends JpaRepository<IngredientType, Long> {
    //derived query to find IngredientType by name
    List<IngredientType> findByName(String name);
}
