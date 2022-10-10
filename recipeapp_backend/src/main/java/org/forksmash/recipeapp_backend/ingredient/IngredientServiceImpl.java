package org.forksmash.recipeapp_backend.ingredient;

import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class IngredientServiceImpl implements IngredientService {
   
    private IngredientRepository ingredients;
    

    public IngredientServiceImpl(IngredientRepository ingredients){
        this.ingredients = ingredients;
    }

    @Override
    public List<Ingredient> listIngredients() {
        return ingredients.findAll();
    }

    
    @Override
    public Ingredient getIngredient(Long id){
        return ingredients.findById(id).orElse(null);
    }
    
    /**
     * Added logic to avoid adding ingredients with the same name
     * Return null if there exists a ingredient with the same name
     */
    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        List<Ingredient> sameNames = ingredients.findByName(ingredient.getName());
        if(sameNames.size() == 0)
            return ingredients.save(ingredient);
        else
            return null;
    }
    //Haiyang: this one need to check
    
    /* 
    @Override
    public Ingredient updateIngredient(Long id, Ingredient newIngredientInfo){
        return ingredients.findById(id).map(ingredient -> {ingredient.setName(newIngredientInfo.getName());
            return ingredients.save(ingredient);
        }).orElse(null);

    }*/
    //Haiyang: update ingredient is not needed

    /**
     * Remove a ingredient with the given id
     * Spring Data JPA does not return a value for delete operation
     * Cascading: removing a ingredient will also remove all its associated recipes
     */
    @Override
    public void deleteIngredient(Long id){
        ingredients.deleteById(id);
    }

}