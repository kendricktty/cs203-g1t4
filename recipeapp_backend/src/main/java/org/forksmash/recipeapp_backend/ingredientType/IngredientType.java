package org.forksmash.recipeapp_backend.ingredientType;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.forksmash.recipeapp_backend.recipe.Recipe;

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

    @NotNull

    public IngredientType(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "ingredients", cascade = CascadeType.ALL, orphanRemoval = true)
}
