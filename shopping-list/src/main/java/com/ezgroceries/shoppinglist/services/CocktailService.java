package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.clients.CocktailDBClient;
import com.ezgroceries.shoppinglist.clients.CocktailDBResponse;
import com.ezgroceries.shoppinglist.clients.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shoppinglist.resources.CocktailResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {

    private final CocktailDBClient cocktailDBClient;

    public CocktailService(CocktailDBClient cocktailDBClient) {
        this.cocktailDBClient = cocktailDBClient;
    }

    public List<CocktailResource> searchCocktail(String search) {
        List<CocktailResource> cocktails = new ArrayList<>();
        try {
            CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
            for (DrinkResource drinkResource : cocktailDBResponse.getDrinks()) {
                cocktails.add(mapDBDrinkResourceToCocktailResource(drinkResource));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return cocktails;
    }

    private CocktailResource mapDBDrinkResourceToCocktailResource(DrinkResource drinkResource) {
        CocktailResource cocktailResource = new CocktailResource();
        cocktailResource.setCocktailId(drinkResource.getIdDrink());
        cocktailResource.setName(drinkResource.getStrDrink());
        cocktailResource.setGlass(drinkResource.getStrGlass());
        cocktailResource.setImage(drinkResource.getStrDrinkThumb());
        cocktailResource.setInstructions(drinkResource.getStrInstructions());
        List<String> ingredients = Arrays.asList(drinkResource.getStrIngredient1(), drinkResource.getStrIngredient2(),
                drinkResource.getStrIngredient3(), drinkResource.getStrIngredient4(), drinkResource.getStrIngredient5(),
                drinkResource.getStrIngredient6(), drinkResource.getStrIngredient7(), drinkResource.getStrIngredient8(),
                drinkResource.getStrIngredient9(), drinkResource.getStrIngredient10(), drinkResource.getStrIngredient11(),
                drinkResource.getStrIngredient12(), drinkResource.getStrIngredient13(), drinkResource.getStrIngredient14(),
                drinkResource.getStrIngredient15());
        cocktailResource.setIngredients(ingredients);
        return cocktailResource;
    }
}
