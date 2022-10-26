package org.forksmash.recipeapp_backend.dietaryCalculator.nutrient.good_nutrient;

import org.forksmash.recipeapp_backend.dietaryCalculator.nutrient.Nutrient;

public abstract class GoodNutrient extends Nutrient {
    public GoodNutrient(String name, double amount, String unit, double percentageOfDailyNeeds) {
        super(name, amount, unit, percentageOfDailyNeeds);
    }
    
    
}
