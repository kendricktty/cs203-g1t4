package org.forksmash.recipeapp_backend.recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

import org.forksmash.recipeapp_backend.ingredient.Ingredient;
import org.forksmash.recipeapp_backend.userprofile.UserProfile;
import org.forksmash.recipeapp_backend.util.JpaConverterJson;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
//@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Recipe {
    private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ProfileId")
    private UserProfile userProfile; 

    // @Column(name = "recipe_data_id", columnDefinition="bigint")
    private int recipeDataId;

    @NotNull(message = "An ingredient type must be assigned to an ingredient")
    //@Convert(converter = JpaConverterJson.class)
    //@Type(type = "json")
    @Column(name = "info", columnDefinition = "json")
    private String info;

    @Column(name = "instructions", columnDefinition = "json")
    private String instructions;

    @Column(name = "extended_ingredients", columnDefinition = "json")
    private String extendedIngredients;

    @Column(name = "nutrition", columnDefinition = "json")
    private String nutrition;

    public Recipe(int recipeDataId, String info, String instructions, String extendedIngredients, String nutrition, UserProfile userProfile) {
        this.recipeDataId = recipeDataId;
        this.info = info;
        this.instructions = instructions;
        this.extendedIngredients = extendedIngredients;
        this.nutrition = nutrition;
        this.userProfile = userProfile;
    }

    

    // @ManyToMany
    // @JoinTable(name = "recipe_ingredients", 
    //     joinColumns = @JoinColumn(name = "recipeId"), 
    //     inverseJoinColumns = @JoinColumn(name = "ingredientId"))
    // private Set<Ingredient> ingredients = new TreeSet<>();

   
}
