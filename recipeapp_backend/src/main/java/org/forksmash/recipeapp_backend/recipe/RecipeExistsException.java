package org.forksmash.recipeapp_backend.recipe;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RecipeExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RecipeExistsException(String name) {
        super("This name exists: " + name);
    }
    
}
