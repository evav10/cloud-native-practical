package com.ezgroceries.shoppinglist.cocktail.controllers.contracts;

public class CocktailRequest {

    private String cocktailId;

    public CocktailRequest() {
    }

    public CocktailRequest(String cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(String cocktailId) {
        this.cocktailId = cocktailId;
    }
}
