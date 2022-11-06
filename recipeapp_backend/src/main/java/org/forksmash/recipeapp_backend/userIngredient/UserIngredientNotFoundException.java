package org.forksmash.recipeapp_backend.userIngredient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserIngredientNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UserIngredientNotFoundException(long id) {
        super("No recipe found with id " + id);
    }
}