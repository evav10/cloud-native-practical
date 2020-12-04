package com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts;

import java.util.Set;
import java.util.UUID;

public class ShoppingListResponse {

    private UUID shoppingListId;
    private String name;
    private Set<String> ingredients;

    public ShoppingListResponse() {
    }

    public ShoppingListResponse(String name) {
        this.shoppingListId = UUID.randomUUID();
        this.name = name;
    }

    public ShoppingListResponse(UUID shoppingListId, String name, Set<String> ingredients) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.ingredients = ingredients;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
