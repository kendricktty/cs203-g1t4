package org.forksmash.recipeapp_backend.ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IngredientController {
    private IngredientService ingredientService;

    public IngredientController(IngredientService bs){
        this.ingredientService = bs;
    }

    /**
     * List all ingredients in the system
     * @return list of all ingredients
     */
    @GetMapping("/ingredients")
    public ResponseEntity<Map<String, List<Ingredient>>> getIngredients() {

        List<Ingredient> meatIngredients = new ArrayList<>();
        List<Ingredient> seafoodIngredient = new ArrayList<>();
        List<Ingredient> vegetableIngredients = new ArrayList<>();
        List<Ingredient> dairyIngredients = new ArrayList<>();
        List<Ingredient> fruitIngredients = new ArrayList<>();
        List<Ingredient> othersIngredients = new ArrayList<>();

        ingredientService.listIngredients().forEach(ingredient -> {
            switch (ingredient.getIngredientType().getName()) {
                case "Meat":
                    meatIngredients.add(ingredient);
                    break;
                case "Seafood":
                    seafoodIngredient.add(ingredient);
                    break;
                case "Vegetable":
                    vegetableIngredients.add(ingredient);
                    break;
                case "Dairy":
                    dairyIngredients.add(ingredient);
                    break;
                case "Fruit":
                    fruitIngredients.add(ingredient);
                    break;
                default:
                    othersIngredients.add(ingredient);
            }
        });

        Map<String, List<Ingredient>> ingredientsDict = new HashMap<>();
        ingredientsDict.put("meats", meatIngredients);
        ingredientsDict.put("seafoods", seafoodIngredient);
        ingredientsDict.put("vegetables", vegetableIngredients);
        ingredientsDict.put("dairies", dairyIngredients);
        ingredientsDict.put("fruits", fruitIngredients);
        ingredientsDict.put("others", othersIngredients);

        return ResponseEntity.ok().body(ingredientsDict);
    }
    // public List<Ingredient> getIngredients(){
    //     return ingredientService.listIngredients();
    // }

    /**
     * Search for ingredient with the given id
     * If there is no ingredient with the given "id", throw a IngredientNotFoundException
     * @param id
     * @return ingredient with the given id
     */
    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredient(@PathVariable Long id){
        Ingredient ingredient = ingredientService.getIngredient(id);

        // Need to handle "ingredient not found" error using proper HTTP status code
        // In this case it should be HTTP 404
        if(ingredient == null) throw new IngredientNotFoundException(id);
        return ingredientService.getIngredient(id);

    }
    /**
     * Add a new ingredient with POST request to "/ingredients"
     * Note the use of @RequestBody
     * @param ingredient
     * @return the newly added ingredient
     */
    
    // @ResponseStatus(HttpStatus.CREATED)
    // @PostMapping("/ingredients")
    // public Ingredient addIngredient(@Valid @RequestBody Ingredient ingredient){
    //     Ingredient savedIngredient = ingredientService.addIngredient(ingredient);
    //     if (savedIngredient ==  null) throw new IngredientExistsException(ingredient.getName());
    //     return savedIngredient;
    // }

    /* 
     * If there is no ingredient with the given "id", throw a IngredientNotFoundException
     * @param id
     * @param newIngredientInfo
     * @return the updated, or newly added ingredient
    
    @PutMapping("/ingredients/{id}")
    public Ingredient updateIngredient(@PathVariable Long id, @Valid @RequestBody Ingredient newIngredientInfo){
        Ingredient ingredient = ingredientService.updateIngredient(id, newIngredientInfo);
        if(ingredient == null) throw new IngredientNotFoundException(id);
        
        return ingredient;
    }
    Haiyang: We don't need to updateIngredient as they are from API*/

    /**
     * Remove a ingredient with the DELETE request to "/ingredients/{id}"
     * If there is no ingredient with the given "id", throw a IngredientNotFoundException
     * @param id
     */
    @DeleteMapping("/ingredients/{id}")
    public void deleteIngredient(@PathVariable Long id){
        try{
            ingredientService.deleteIngredient(id);
         }catch(EmptyResultDataAccessException e) {
            throw new IngredientNotFoundException(id);
         }
    }
}