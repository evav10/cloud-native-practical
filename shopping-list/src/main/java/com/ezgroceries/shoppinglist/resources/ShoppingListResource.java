package com.ezgroceries.shoppinglist.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.UUID;

@JsonInclude(Include.NON_NULL)
public class ShoppingListResource {

    private UUID shoppingListId;
    private String name;
    private List<String> ingredients;

    public ShoppingListResource() {
    }

    public ShoppingListResource(String name) {
        this.shoppingListId = UUID.randomUUID();
        this.name = name;
    }

    public ShoppingListResource(UUID shoppingListId, String name, List<String> ingredients) {
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
