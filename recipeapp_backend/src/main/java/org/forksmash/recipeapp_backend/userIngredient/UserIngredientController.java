package org.forksmash.recipeapp_backend.userIngredient;

import java.util.List;

import javax.validation.Valid;

import org.forksmash.recipeapp_backend.userprofile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserIngredientController {
    private UserIngredientService userIngredientService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserIngredientController(UserIngredientService userIngredientService) {
        this.userIngredientService = userIngredientService;
    }

    @GetMapping("/useringredients")
    public ResponseEntity<List<UserIngredient>> getUserIngredients(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        long userProfileId = decodedJWT.getClaim("id").asLong();

        return ResponseEntity.ok().body(userIngredientService.listIngredientsFromProfileId(userProfileId));
    }

   
    @PostMapping("/useringredients")
    public UserIngredient addRecipe(@Valid @RequestBody UserIngredient userIngredient, @RequestHeader("Authorization") String bearerToken) { 

        String token = bearerToken.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        long userProfileId = decodedJWT.getClaim("id").asLong();

        userIngredient.setUserProfile(userProfileRepository.findById(userProfileId).get());

        return userIngredientService.addIngredient(userIngredient);
    }

    @DeleteMapping("/useringredients/{id}")
    public void deleteRecipe(@PathVariable Long id, @RequestHeader("Authorization") String bearerToken) {
        try {
            String token = bearerToken.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            long userProfileId = decodedJWT.getClaim("id").asLong();
            
            System.out.println("id" + id);
            System.out.println("userProfileId" + userProfileId);

            userIngredientService.deleteIngredient(id, userProfileId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserIngredientNotFoundException(id);
        }
    }
}
