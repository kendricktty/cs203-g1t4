package org.forksmash.recipeapp_backend.recipe;

import org.forksmash.recipeapp_backend.userprofile.UserProfile;
import org.forksmash.recipeapp_backend.userprofile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipes;

    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public RecipeServiceImpl(RecipeRepository recipes) {
        this.recipes = recipes; 
    }

    @Override
    public List<Recipe> listRecipes() { return recipes.findAll(); }

    @Override
    public List<Recipe> listRecipesFromProfileId(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id).get();
        return recipes.findAllByProfileId(userProfile.getProfileId());
    }  

    @Override
    public Recipe getRecipe(int recipeDataId, Long profileId) { 
        return recipes.findByRecipeDataIdAndProfileId(recipeDataId, profileId); 
    }

    @Override
    public Recipe addRecipe(Recipe recipe) { 
        log.info("Recipe (recipeServiceImpl): ", recipe);
        return recipes.save(recipe); 
    }

    // @Override
    // public Recipe updateRecipe(Long id, Recipe newRecipe) {
    //     return recipes.findById(id).map(recipe -> {
    //         // recipe.setData(newRecipe.getData());
    //         recipe.setInfo(newRecipe.getInfo());
    //         recipe.setInstructions(newRecipe.getInstructions());
    //         recipe.setExtendedIngredients(newRecipe.getExtendedIngredients());
    //         recipe.setNutrition(newRecipe.getNutrition());
    //         return recipes.save(recipe);
    //     }).orElse(null);
    // }

    @Override
    public void deleteRecipe(int recipeDataId, Long userProfileId) { 
        recipes.deleteByRecipeDataIdAndProfileId(recipeDataId, userProfileId); 
    }

}
