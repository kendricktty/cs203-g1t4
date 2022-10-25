package org.forksmash.recipeapp_backend.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.forksmash.recipeapp_backend.user.UserRepository;
import org.forksmash.recipeapp_backend.userprofile.UserProfile;
import org.forksmash.recipeapp_backend.userprofile.UserProfileRepository;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private final AuthenticationManager authenticationManager;

    private UserProfileRepository userProfileRepository;
    private UserRepository userRepository;

    // public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
    //     this.authenticationManager = authenticationManager;
    // }

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
        this.authenticationManager = authenticationManager;
        this.userRepository = ctx.getBean(UserRepository.class);
        this.userProfileRepository = ctx.getBean(UserProfileRepository.class);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
 
        try {
            String requestData = request.getReader().lines().collect(Collectors.joining());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode data = objectMapper.readTree(requestData);

            String email = data.get("email").asText();
            String password = data.get("password").asText();

            // String email = request.getParameter("email");
            // String password = request.getParameter("password");
             
            log.info("Email is: {}", email); 
            log.info("Password is: {}", password);
 
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            // log.info(e.getMessage());
           
            try {
                Map<String, Object> error = new HashMap<>();
                error.put("status", HttpStatus.BAD_REQUEST);
                error.put("message", "Invalid credentials");
                
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            } catch (StreamWriteException e1) {
                e1.printStackTrace();
            } catch (DatabindException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } 

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal();

        org.forksmash.recipeapp_backend.user.User appUser = userRepository.findByEmail(user.getUsername());
        UserProfile userProfile = userProfileRepository.findByUserId(appUser.getId());

        // Must hide HMAC256 secret key "secret" in an environment variable later
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String accessToken = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1)) // 1 day
            .withIssuer(request.getRequestURL().toString())
            .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .withClaim("id", userProfile.getProfileId())
            .withClaim("firstName", appUser.getFirstName())
            .withClaim("lastName", appUser.getLastName())
            .sign(algorithm);

        String refreshToken = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 10080 * 60 * 1000))
            .withIssuer(request.getRequestURL().toString())
            .sign(algorithm);

        Map<String, Object> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        tokens.put("status", HttpStatus.OK);

         
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    } 
    
}
