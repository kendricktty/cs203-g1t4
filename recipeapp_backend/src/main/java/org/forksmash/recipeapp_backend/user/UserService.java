package org.forksmash.recipeapp_backend.user;

import java.util.List;

import org.forksmash.recipeapp_backend.role.Role;
import org.forksmash.recipeapp_backend.userprofile.UserProfile;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    void addProfileToUser(String email, UserProfile userProfile);
    User getUser(String email);
    List<User> getUsers();
}
