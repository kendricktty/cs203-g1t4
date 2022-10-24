package org.forksmash.recipeapp_backend.userprofile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    // Optional<UserProfile> findById(Long id);

    @Query("FROM UserProfile WHERE user_id = :id")
    UserProfile findByUserId(@Param("id") Long id); 
}
