package org.forksmash.recipeapp_backend.nutrient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class NutrientController {
    private NutrientService nutrientService;

    @Autowired
    public NutrientController(NutrientService nutrientService) {
        this.nutrientService = nutrientService;
    }

    @GetMapping("/nutrient/deficit")
    public ResponseEntity<Map<String, Double>> getNutrientDeficit() {
        Map<String, Double> nutrientDeficitMap = nutrientService.getNutrientDeficit();
        return ResponseEntity.ok().body(nutrientDeficitMap);
    }
}
