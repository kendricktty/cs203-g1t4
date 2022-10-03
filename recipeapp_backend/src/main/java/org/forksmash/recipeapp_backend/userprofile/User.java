package org.forksmash.recipeapp_backend.userprofile;

package org.forksmash.recipeapp_backend.recipe;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
public class User {
    private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long userId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
