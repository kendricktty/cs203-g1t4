package org.forksmash.recipeapp_backend.nutrient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@RestController
@RequestMapping("/api")
public class NutrientController {
    private NutrientService nutrientService;

    @Autowired
    public NutrientController(NutrientService nutrientService) {
        this.nutrientService = nutrientService;
    }

    @GetMapping("/nutrient/deficit")
    public ResponseEntity<Map<String, Double>> getNutrientDeficitById(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        long userProfileId = decodedJWT.getClaim("id").asLong();

        try {
            Map<String, Double> nutrientDeficitMap = nutrientService.getNutrientDeficitById(userProfileId);
            return ResponseEntity.ok().body(nutrientDeficitMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NutrientProcessingException(e);
        }
    }
}
