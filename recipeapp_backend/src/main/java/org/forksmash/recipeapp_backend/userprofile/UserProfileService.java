package org.forksmash.recipeapp_backend.userprofile;

public interface UserProfileService {
    UserProfile getProfile(Long id);
    UserProfile addProfile(UserProfile profile);
    UserProfile updateProfile(Long id, UserProfile userProfile);
    void deleteProfile(Long id);
}
