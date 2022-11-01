package org.forksmash.recipeapp_backend.nutrient;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.*;

public class NutrientIdeal {
    public static final TreeMap<NutrientDesired, Double> mealBreakdownMap = new TreeMap<>() {
        {
            // Source: https://www.fda.gov/media/99069/download

            NutrientDesired vitaminA = new NutrientDesired("Vitamin A", "mcg");
            mealBreakdownMap.put(vitaminA, 900.0);

            NutrientDesired vitaminC = new NutrientDesired("Vitamin C", "mg");
            mealBreakdownMap.put(vitaminC, 90.0);

            NutrientDesired calcium = new NutrientDesired("Calcium", "mg");
            mealBreakdownMap.put(calcium, 1300.0);

            NutrientDesired iron = new NutrientDesired("Iron", "mcg");
            mealBreakdownMap.put(calcium, 18.0);

            NutrientDesired vitaminD = new NutrientDesired("Vitamin D", "mcg");
            mealBreakdownMap.put(vitaminD, 20.0);

            NutrientDesired vitaminE = new NutrientDesired("Vitamin E", "mg");
            mealBreakdownMap.put(vitaminE, 15.0);

            NutrientDesired vitaminK = new NutrientDesired("Vitamin K", "mcg");
            mealBreakdownMap.put(vitaminK, 120.0);

            NutrientDesired thiamin = new NutrientDesired("Thiamin", "mg");
            mealBreakdownMap.put(thiamin, 1.2);

            NutrientDesired riboflavin = new NutrientDesired("Riboflavin", "mg");
            mealBreakdownMap.put(riboflavin, 1.3);

            NutrientDesired niacin = new NutrientDesired("Niacin", "mg");
            mealBreakdownMap.put(niacin, 16.0);

            NutrientDesired vitaminB6 = new NutrientDesired("Vitamin B6", "mg");
            mealBreakdownMap.put(vitaminB6, 1.7);

            NutrientDesired folate = new NutrientDesired("Folate", "mcg");
            mealBreakdownMap.put(folate, 15.0);

            NutrientDesired vitaminB = new NutrientDesired("Vitamin B", "mcg");
            mealBreakdownMap.put(vitaminB, 2.4);

            NutrientDesired biotin = new NutrientDesired("Biotin", "mcg");
            mealBreakdownMap.put(biotin, 30.0);

            NutrientDesired pantothenicAcid = new NutrientDesired("Pantothenic acid", "mg");
            mealBreakdownMap.put(pantothenicAcid, 5.0);

            NutrientDesired zinc = new NutrientDesired("Zinc", "mg");
            mealBreakdownMap.put(zinc, 11.0);

            NutrientDesired selenium = new NutrientDesired("Selenium", "mcg");
            mealBreakdownMap.put(selenium, 55.0);

            NutrientDesired copper = new NutrientDesired("Copper", "mg");
            mealBreakdownMap.put(copper, 0.9);

            NutrientDesired manganese = new NutrientDesired("Manganese", "mg");
            mealBreakdownMap.put(manganese, 2.3);

            NutrientDesired chromium = new NutrientDesired("Chromium", "mcg");
            mealBreakdownMap.put(chromium, 35.0);

            NutrientDesired phosphorus = new NutrientDesired("phosphorus", "mg");
            mealBreakdownMap.put(phosphorus, 1250.0);

            NutrientDesired iodine = new NutrientDesired("iodine", "mcg");
            mealBreakdownMap.put(iodine, 150.0);

            NutrientDesired magnesium = new NutrientDesired("magnesium", "mg");
            mealBreakdownMap.put(magnesium, 420.0);

            NutrientDesired molybdenum = new NutrientDesired("molybdenum", "mcg");
            mealBreakdownMap.put(molybdenum, 45.0);

            NutrientDesired chloride = new NutrientDesired("chloride", "mg");
            mealBreakdownMap.put(chloride, 2300.0);

            NutrientDesired potassium = new NutrientDesired("potassium", "mg");
            mealBreakdownMap.put(potassium, 4700.0);

            NutrientDesired choline = new NutrientDesired("choline", "mg");
            mealBreakdownMap.put(choline, 550.0);

            NutrientDesired protein = new NutrientDesired("protein", "g");
            mealBreakdownMap.put(protein, 11.0);
        }
    };

    // Source
    // https://www.webmd.com/vitamins-and-supplements/vitamins-minerals-how-much-should-you-take
    // Implementing actual ideal breakdown per meal.
    // CHANGE THE VALUES HERE. THIS IS STILL PERCENTAGE
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
}
