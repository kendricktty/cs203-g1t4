package org.forksmash.recipeapp_backend.recipe;

import java.util.List;

import org.forksmash.recipeapp_backend.userprofile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    List<Recipe> findAllByUserProfile(UserProfile userProfile);

    @Query("FROM Recipe WHERE profile_id = :id")
    List<Recipe> findAllByProfileId(@Param("id") Long id); 
}
