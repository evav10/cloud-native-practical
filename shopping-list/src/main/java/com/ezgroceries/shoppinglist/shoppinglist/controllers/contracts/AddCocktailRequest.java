package com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts;

public class AddCocktailRequest {

    private String cocktailId;

    public AddCocktailRequest() {
    }

    public AddCocktailRequest(String cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(String cocktailId) {
        this.cocktailId = cocktailId;
    }
}
