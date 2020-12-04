package com.ezgroceries.shoppinglist.meal.controllers.contracts;

public class MealRequest {

    private String mealId;

    public MealRequest() {
    }

    public MealRequest(String mealId) {
        this.mealId = mealId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
}
