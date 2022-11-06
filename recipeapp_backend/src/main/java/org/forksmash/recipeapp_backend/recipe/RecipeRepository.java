package org.forksmash.recipeapp_backend.recipe;

import java.util.List;

import org.forksmash.recipeapp_backend.userprofile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    List<Recipe> findAllByUserProfile(UserProfile userProfile);

    @Query("FROM Recipe WHERE profile_id = :id")
    List<Recipe> findAllByProfileId(@Param("id") Long id); 

    @Query("FROM Recipe WHERE profile_id = :id AND recipe_data_id = :recipeDataId")
    Recipe findByRecipeDataIdAndProfileId(@Param("recipeDataId") int recipeDataId, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Recipe WHERE recipe_data_id = :recipeDataId AND profile_id = :profileId")
    void deleteByRecipeDataIdAndProfileId(@Param("recipeDataId") int recipeDataId, @Param("profileId") Long profileId);
}
