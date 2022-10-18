package org.forksmash.recipeapp_backend.ingredientType;
import java.util.List;
public interface IngredientTypeService {
    List<IngredientType> listIngredientTypes();
    IngredientType getIngredientType(Long id);
    IngredientType saveIngredientType(IngredientType ingredientType);
}
