package org.forksmash.recipeapp_backend.dietaryCalculator.nutrient;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public abstract class Nutrient {
    private final String name;
    private double amount;
    private final String unit;
    private double percentageOfDailyNeeds;

    // Source: https://www.fda.gov/food/new-nutrition-facts-label/daily-value-new-nutrition-and-supplement-facts-labels#referenceguide

    public boolean isLow() {
        return amount <= 5.00;
    } // Low means 5% DV or less

    public boolean isHigh() {
        return amount >= 20.00;
    } // Too high means %20 DV or more

    public abstract boolean isExcessOrShortage();
}
