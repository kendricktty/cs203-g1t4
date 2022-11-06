package org.forksmash.recipeapp_backend.nutrient;
import lombok.*;

@ToString
@Getter
@Setter
public class NutrientActual extends Nutrient {
    private double amount;
    private double percentOfDailyNeeds;

    // Default constructor required for Jackson JSON processor to work
    public NutrientActual(){}

    public NutrientActual(String name, String unit, double amount, double percentOfDailyNeeds) {
        super(name, unit);
        this.amount = amount;
        this.percentOfDailyNeeds = percentOfDailyNeeds;
    }

    public boolean isLow() {
        return amount <= 5.00;
    } // Low means 5% DV or less

    public boolean isHigh() {
        return amount >= 20.00;
    } // Too high means 20% DV or more
}
