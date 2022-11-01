package org.forksmash.recipeapp_backend.nutrient;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class NutrientDesired {
    private String name;
    private String unit;
}
