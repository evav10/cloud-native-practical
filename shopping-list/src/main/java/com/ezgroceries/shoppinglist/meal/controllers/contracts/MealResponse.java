package com.ezgroceries.shoppinglist.meal.controllers.contracts;

import java.util.Set;

/**
 * @author Eva Van Thienen (jd03799)
 * @since release/202005
 */
public class MealResponse {

    private String mealId;
    private String name;
    private String instructions;
    private String image;
    private Set<String> ingredients;

    public MealResponse() {
    }

    public MealResponse(String mealId, String name, String instructions, String image, Set<String> ingredients) {
        this.mealId = mealId;
        this.name = name;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
