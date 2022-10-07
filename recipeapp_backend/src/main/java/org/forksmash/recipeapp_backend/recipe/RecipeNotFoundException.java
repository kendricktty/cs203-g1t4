package org.forksmash.recipeapp_backend.recipe;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public RecipeNotFoundException(Long id) {
        super("No recipe found with id " + id);
    }
}