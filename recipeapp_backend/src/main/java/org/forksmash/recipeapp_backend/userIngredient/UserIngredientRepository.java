package org.forksmash.recipeapp_backend.userIngredient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserIngredientRepository extends JpaRepository<UserIngredient, Long> {
    // Optional<UserProfile> findById(Long id);

    @Query("FROM UserIngredient WHERE profile_id = :id")
    List<UserIngredient> findAllByProfileId(@Param("id") Long id); 

    @Transactional
    @Modifying
    @Query("DELETE FROM UserIngredient WHERE id = :id AND profile_id = :profileId")
    void deleteByUserIngredientIdAndProfileId(@Param("id") Long id, @Param("profileId") Long profileId);
}