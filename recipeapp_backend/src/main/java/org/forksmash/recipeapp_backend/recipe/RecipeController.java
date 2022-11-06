package org.forksmash.recipeapp_backend.recipe;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;

import org.forksmash.recipeapp_backend.nutrient.NutrientService;
import org.forksmash.recipeapp_backend.user.User;
import org.forksmash.recipeapp_backend.user.UserRepository;
import org.forksmash.recipeapp_backend.userprofile.UserProfile;
import org.forksmash.recipeapp_backend.userprofile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
@RestController
@RequestMapping("/api")
public class RecipeController {
    private RecipeService recipeService;
    private UserRepository userRepository;
    private NutrientService nutrientService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private RecipeRepository recipeRepository;
    
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getRecipes(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        long userProfileId = decodedJWT.getClaim("id").asLong();

        // System.out.println("fav recipes: " + recipeService.listRecipesFromProfileId(userProfileId));

        return ResponseEntity.ok().body(recipeService.listRecipesFromProfileId(userProfileId));
    }  

    @GetMapping("/recipes/{recipeDataId}")
    public Recipe getRecipe(@PathVariable("recipeDataId") int recipeDataId, @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        long userProfileId = decodedJWT.getClaim("id").asLong();
        
        Recipe recipe = recipeService.getRecipe(recipeDataId, userProfileId);
        if (recipe == null)
            throw new RecipeNotFoundException(recipeDataId);
        return recipe;
    }
 
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/recipes")
    public Recipe addRecipe(@Valid @RequestBody Recipe recipe, @RequestHeader("Authorization") String bearerToken) { 

        String token = bearerToken.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        long userProfileId = decodedJWT.getClaim("id").asLong();

        recipe.setUserProfile(userProfileRepository.findById(userProfileId).get());

        return recipeService.addRecipe(recipe);
    }

    // @PutMapping("/recipes/{id}")
    // public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe newRecipe) {
    //     Recipe recipe = recipeService.updateRecipe(id, newRecipe);
    //     if (recipe == null)
    //         throw new RecipeNotFoundException(id);
    //     return recipe;
    // }

    @DeleteMapping("/recipes/{recipeDataId}")
    public void deleteRecipe(@PathVariable int recipeDataId, @RequestHeader("Authorization") String bearerToken) {
        try {
            String token = bearerToken.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            long userProfileId = decodedJWT.getClaim("id").asLong();
            
            System.out.println("recipeDataId" + recipeDataId);
            System.out.println("userProfileId" + userProfileId);

            recipeService.deleteRecipe(recipeDataId, userProfileId);
        } catch (EmptyResultDataAccessException e) {
            throw new RecipeNotFoundException(recipeDataId);
        }
    }
}
