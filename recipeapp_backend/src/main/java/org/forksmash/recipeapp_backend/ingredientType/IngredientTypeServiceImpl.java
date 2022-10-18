package org.forksmash.recipeapp_backend.ingredientType;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class IngredientTypeServiceImpl implements IngredientTypeService{
    private IngredientTypeRepository ingredientTypes;

    public IngredientTypeServiceImpl(IngredientTypeRepository ingredientTypes) {
        this.ingredientTypes = ingredientTypes;
        // ingredientTypes.add(new IngredientType("Meat"));
        // ingredientTypes.add(new IngredientType("Seafood"));
        // ingredientTypes.add(new IngredientType("Vegetables"));
        // ingredientTypes.add(new IngredientType("Dairy"));
        // ingredientTypes.add(new IngredientType("Fruits"));
        // ingredientTypes.add(new IngredientType("Others"));
    }

    @Override
    public List<IngredientType> listIngredientTypes() {
        // TODO Auto-generated method stub
        return ingredientTypes.findAll();
    }

    @Override
    public IngredientType getIngredientType(Long id) {
        return ingredientTypes.findById(id).orElse(null);
    }
}
