package org.forksmash.recipeapp_backend.userIngredient;

import java.util.List;

import org.forksmash.recipeapp_backend.userprofile.UserProfile;
import org.forksmash.recipeapp_backend.userprofile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIngredientServiceImpl implements UserIngredientService {
    private UserIngredientRepository userIngredients;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserIngredientServiceImpl(UserIngredientRepository userIngredientRepository) {
        this.userIngredients = userIngredientRepository;
    }

    @Override
    public UserIngredient addIngredient(UserIngredient userIngredient) {
        return userIngredients.save(userIngredient); 
    }

    @Override
    public void deleteIngredient(Long id, Long userProfileId) {
        userIngredients.deleteByUserIngredientIdAndProfileId(id, userProfileId);
    }

    @Override
    public List<UserIngredient> listIngredientsFromProfileId(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id).get();
        return userIngredients.findAllByProfileId(userProfile.getProfileId());
    }
    
}
