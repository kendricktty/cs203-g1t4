package org.forksmash.recipeapp_backend.ingredient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IngredientExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IngredientExistsException(String name) {
        super("This name exists: " + name);
    }
    
}
