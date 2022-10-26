package org.forksmash.recipeapp_backend.recipe.nutrition;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Nutrient {
    private final String name;
    private double amount;
    private final String unit;
    private double percentageOfDailyNeeds;
    private final boolean isBad;

    public Nutrient(String name, double amount, String unit, double percentageOfDailyNeeds) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.percentageOfDailyNeeds = percentageOfDailyNeeds;
        if (BadNutrients.BAD_NUTRIENTS.contains(this.name)) {
            this.isBad = true;
        } else {
            this.isBad = false;
        }
    }

    // Source: https://www.fda.gov/food/new-nutrition-facts-label/daily-value-new-nutrition-and-supplement-facts-labels#referenceguide
    

    public boolean isLow() {
        return amount <= 5.00;
    } // Low means 5% DV or less

    public boolean isHigh() {
        return amount >= 20.00;
    } // Too high means 20% DV or more
}
