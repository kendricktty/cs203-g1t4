package org.forksmash.recipeapp_backend.user;

import java.util.List;

import org.forksmash.recipeapp_backend.role.Role;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    User getUser(String email);
    List<User> getUsers();
}
