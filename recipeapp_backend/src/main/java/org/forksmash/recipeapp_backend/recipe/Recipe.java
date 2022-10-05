package org.forksmash.recipeapp_backend.recipe;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

import org.forksmash.recipeapp_backend.ingredient.Ingredient;
import org.forksmash.recipeapp_backend.userprofile.UserProfile;
import org.forksmash.recipeapp_backend.util.JpaConverterJson;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Recipe {
    private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id;

    @NotNull(message = "Ingredient name shouldn't be null")
    @Size(min = 1, max = 30, message = "Ingredient name should not exceed 30 characters")
    private String name;

    @NotNull(message = "An ingredient type must be assigned to an ingredient")
    @Convert(converter = JpaConverterJson.class)
    private String data;

    @ManyToMany
    @JoinTable(name = "ingredients", 
        joinColumns = @JoinColumn(name = "recipeId"), 
        inverseJoinColumns = @JoinColumn(name = "ingredientId"))
    private Set<Ingredient> ingredients = new TreeSet<>();

    // @ManyToMany
    // @JoinTable(name = "recipes",
    //     joinColumns = @JoinColumn(name = "recipeId"),
    //     inverseJoinColumns = @JoinColumn(name = "userId"))
    // private Set<User> favouritedUsers = new HashSet<>();
}
