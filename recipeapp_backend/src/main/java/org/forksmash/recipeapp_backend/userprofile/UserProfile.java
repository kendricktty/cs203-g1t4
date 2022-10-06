package org.forksmash.recipeapp_backend.userprofile;

import java.util.Set;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Constraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

import org.forksmash.recipeapp_backend.appuser.AppUser;
import org.forksmash.recipeapp_backend.ingredient.Ingredient;
import org.forksmash.recipeapp_backend.recipe.Recipe;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserProfile {
    private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long profileId;
    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @Min(0)
    private int age;
    private char sex;
    private double heightCM;
    private double weightKG;

    @ElementCollection
    @CollectionTable(name = "Allergies", joinColumns = @JoinColumn(name = "profileId"))
    private List<String> allergies;

    @ManyToMany(mappedBy = "usersWhoFavourite")
    private Set<Recipe> favouriteRecipes = new TreeSet<>();
}
