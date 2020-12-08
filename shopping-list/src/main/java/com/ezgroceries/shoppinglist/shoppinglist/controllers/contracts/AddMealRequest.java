package com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts;

public class AddMealRequest {

    private String mealId;

    public AddMealRequest() {
    }

    public AddMealRequest(String mealId) {
        this.mealId = mealId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
}
