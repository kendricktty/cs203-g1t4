package org.forksmash.recipeapp_backend.registration;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String string) {
        // REGEX to validate email
        return true;
    }
}
