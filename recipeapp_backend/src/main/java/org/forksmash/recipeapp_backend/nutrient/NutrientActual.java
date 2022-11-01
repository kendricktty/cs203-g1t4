package org.forksmash.recipeapp_backend.nutrient;
import lombok.*;

@ToString
@Getter
@Setter
public class NutrientActual extends Nutrient {
    private double amount;
    private double percentageOfDailyNeeds;

    public NutrientActual(String name, double amount, String unit, double percentageOfDailyNeeds) {
        super(name, unit);
        this.amount = amount;
        this.percentageOfDailyNeeds = percentageOfDailyNeeds;
    }

    public boolean isLow() {
        return amount <= 5.00;
    } // Low means 5% DV or less

    public boolean isHigh() {
        return amount >= 20.00;
    } // Too high means 20% DV or more
}
