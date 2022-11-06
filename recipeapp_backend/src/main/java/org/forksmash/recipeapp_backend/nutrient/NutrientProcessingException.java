package org.forksmash.recipeapp_backend.nutrient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NutrientProcessingException extends RuntimeException {

    public NutrientProcessingException(Throwable exception) {
        super(exception);
    }
    
}
