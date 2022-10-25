package org.forksmash.recipeapp_backend.dietaryCalculator;

import java.util.HashMap;

public class dietaryIdeal {
    private static HashMap<String, Double> idealPercentageBreakdown;

    // Implementing the ideal percentage breakdown;
    // Change Here when you wish to change percentage
    private static void mapOfIdealPercentageBreakdown() {
        if (idealPercentageBreakdown == null) {
            idealPercentageBreakdown = new HashMap<>();

            idealPercentageBreakdown.put("Calcium", 24.02333146);
            idealPercentageBreakdown.put("vitaminB", 12.01166573);
            idealPercentageBreakdown.put("copper", 0.021620998);
            idealPercentageBreakdown.put("Fluoride", 0.096093326);
            idealPercentageBreakdown.put("Folic Acid", 0.009609333);
            idealPercentageBreakdown.put("Iodine", 0.0036035);
            idealPercentageBreakdown.put("Iron", 0.288279978);
            idealPercentageBreakdown.put("Magnesium", 9.609332584);
            idealPercentageBreakdown.put("Manganese", 0.048046663);
            idealPercentageBreakdown.put("Phosphorus", 16.81633202);
            idealPercentageBreakdown.put("Selenium", 0.001321283);
            idealPercentageBreakdown.put("Sodium", 36.03499719);
            idealPercentageBreakdown.put("VitaminA", 0.019218665);
            idealPercentageBreakdown.put("VitaminB3", 0.360349972);
            idealPercentageBreakdown.put("VitaminB6", 0.036034997);
            idealPercentageBreakdown.put("VitaminC", 0.019218665);
            idealPercentageBreakdown.put("VitaminD", 0.00036035);
            idealPercentageBreakdown.put("VitaminE", 0.360349972);
            idealPercentageBreakdown.put("Zinc", 0.240233315);
        }
    }

    public static HashMap<String, Double> getIdealPercentageBreakdown() {
        return idealPercentageBreakdown;
    }
}
