package org.forksmash.recipeapp_backend.dietaryCalculator.nutrient.bad_nutrient;

import org.forksmash.recipeapp_backend.dietaryCalculator.nutrient.Nutrient;
import lombok.*;

public abstract class BadNutrient extends Nutrient {
    public BadNutrient(String name, double amount, String unit, double percentageOfDailyNeeds) {
        super(name, amount, unit, percentageOfDailyNeeds);
    }
}
