package org.forksmash.recipeapp_backend.userprofile;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Constraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

import org.forksmash.recipeapp_backend.user.User;
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
    private User appUser;

    @Min(0)
    private int age;
    private String sex;
    private double heightCM;
    private double weightKG;

    public UserProfile(int age, String sex, double weightKG, double heightCM, User user) {
        this.age = age;
        this.sex = sex;
        this.heightCM = heightCM;
        this.weightKG = weightKG;
        this.appUser = user;
    } 

    @ElementCollection
    @CollectionTable(name = "Allergies", joinColumns = @JoinColumn(name = "profileId"))
    private List<String> allergies;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> favouriteRecipes;
}
