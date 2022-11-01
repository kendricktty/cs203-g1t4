package org.forksmash.recipeapp_backend.nutrient;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class Nutrient {
    private String name;
    private String unit;
}
