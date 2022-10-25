package org.forksmash.recipeapp_backend.ingredientType;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class IngredientTypeServiceImpl implements IngredientTypeService{
    private IngredientTypeRepository ingredientTypeRepository;

    public IngredientTypeServiceImpl(IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    @Override
    public List<IngredientType> listIngredientTypes() {
        return ingredientTypeRepository.findAll();
    }

    @Override
    public IngredientType getIngredientType(Long id) {
        return ingredientTypeRepository.findById(id).orElse(null);
    }

    @Override
    public IngredientType saveIngredientType(IngredientType ingredientType) {
        return ingredientTypeRepository.save(ingredientType);
    }  
}
