package org.forksmash.recipeapp_backend.userprofile;

public class UserProfileServiceImpl implements UserProfileService {
    private UserProfileRepository profiles;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.profiles = userProfileRepository;
    }
    
    @Override
    public UserProfile getProfile(Long id) {
        return profiles.findById(id).orElse(null);
    }

    @Override
    public UserProfile addProfile(UserProfile profile) {
        return profiles.save(profile);
    }

    @Override
    public UserProfile updateProfile(Long id, UserProfile newProfile) {
        return profiles.findById(id).map(profile -> {
            profile.setAge(newProfile.getAge());
            profile.setSex(newProfile.getSex());
            profile.setHeightCM(newProfile.getHeightCM());
            profile.setWeightKG(newProfile.getWeightKG());
            profile.setAllergies(newProfile.getAllergies());
            return profiles.save(profile);
        }).orElse(null);
    }

    @Override
    public void deleteProfile(Long id) {
        profiles.deleteById(id);
    }
}
