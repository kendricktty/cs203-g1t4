package org.forksmash.recipeapp_backend.ingredientType;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.forksmash.recipeapp_backend.ingredient.Ingredient;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class IngredientType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Ingredient Type name should not be null")
    private String name;

    @OneToMany(mappedBy = "ingredientType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;

    public IngredientType(String name) {
        this.name = name;
    }
}
