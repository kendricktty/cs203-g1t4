package org.forksmash.recipeapp_backend.recipe.nutrition;
import java.util.Set;
import java.util.HashSet;
public class BadNutrients {
    public static final Set<String> BAD_NUTRIENTS = new HashSet<>() {{
        add("Fat");
        add("Saturated Fat");
        add("Carbohydrates");
        add("Net Carbohydrates");
        add("Sugar");
        add("Cholesterol");
        add("Sodium");
    }};
}
