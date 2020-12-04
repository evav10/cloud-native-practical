package com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts;

public class CreateShoppingListRequest {

    private String name;

    public CreateShoppingListRequest() {
    }

    public CreateShoppingListRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
