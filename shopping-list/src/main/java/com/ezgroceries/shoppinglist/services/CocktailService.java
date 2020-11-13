package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.clients.CocktailDBClient;
import com.ezgroceries.shoppinglist.clients.CocktailDBResponse;
import com.ezgroceries.shoppinglist.clients.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shoppinglist.resources.CocktailResource;
import java.util.ArrayList;
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
        cocktailResource.setIngredients(drinkResource.getIngredients());
        return cocktailResource;
    }
}
