package org.forksmash.recipeapp_backend.recipe;

import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class RecipeServiceImpl implements RecipeService {
   
    private RecipeRepository recipes;
    

    public RecipeServiceImpl(RecipeRepository recipes){
        this.recipes = recipes;
    }

    @Override
    public List<Recipe> listRecipes() {
        return recipes.findAll();
    }

    
    @Override
    public Recipe getRecipe(Long id){
        return recipes.findById(id).orElse(null);
    }
    
    /**
     * Added logic to avoid adding recipes with the same title
     * Return null if there exists a recipe with the same title
     */
    @Override
    public Recipe addRecipe(Recipe recipe) {
        List<Recipe> sameTitles = recipes.findByTitle(recipe.getTitle());
        if(sameTitles.size() == 0)
            return recipes.save(recipe);
        else
            return null;
    }
    
    /* 
    @Override
    public Recipe updateRecipe(Long id, Recipe newRecipeInfo){
        return recipes.findById(id).map(recipe -> {recipe.setTitle(newRecipeInfo.getTitle());
            return recipes.save(recipe);
        }).orElse(null);

    }*/
    //Haiyang: No need update

    /**
     * Remove a recipe with the given id
     * Spring Data JPA does not return a value for delete operation
     * Cascading: removing a recipe will also remove all its associated reviews
     */
    @Override
    public void deleteRecipe(Long id){
        recipes.deleteById(id);
    }

}
