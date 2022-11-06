package org.forksmash.recipeapp_backend.userIngredient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.forksmash.recipeapp_backend.ingredientType.IngredientType;
import org.forksmash.recipeapp_backend.userprofile.UserProfile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserIngredient {
    private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id;
    
    @NotNull(message = "Ingredient name shouldn't be null")
    @Size(min = 1, max = 30, message = "Ingredient name should not exceed 30 characters")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ProfileId")
    private UserProfile userProfile;
}
