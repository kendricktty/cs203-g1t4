package org.forksmash.recipeapp_backend.ingredient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IngredientNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IngredientNotFoundException(Long id) {
        super("Could not find ingredient " + id);
    }
    
}
