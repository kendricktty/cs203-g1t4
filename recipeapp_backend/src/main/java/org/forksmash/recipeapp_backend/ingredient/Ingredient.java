package org.forksmash.recipeapp_backend.ingredient;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class Ingredient {
    private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id;
    @NotNull(message = "Ingredient name shouldn't be null")
    @Size(min = 1, max = 30, message = "Ingredient name should not exceed 30 characters")
    private String name;

    @NotNull(message = "An ingredient type must be assigned to an ingredient")
    private char ingredientType;
    @ManyToMany(mappedBy = "recipes")
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_ingredient", joinColumns = @JoinColumn(name = "quantity"),
     joinColumns = @JoinColumn(name = "ingredientID"), 
     inverseJoinColumns = @JoinColumn(name = "userID"))


    public Ingredient (String name){
        this.name = name;
    }
}
