package com.ezgroceries.shoppinglist.meal.utils;

import static com.ezgroceries.shoppinglist.utils.IngredientMapper.getMealIngredients;

import com.ezgroceries.shoppinglist.meal.controllers.contracts.MealResponse;
import com.ezgroceries.shoppinglist.meal.services.mealDB.MealDBResponse.Meal;
import java.util.ArrayList;
import java.util.List;

public class MealMapper {

    public static List<MealResponse> mapMeals(List<Meal> meals) {
        List<MealResponse> mealResponses = new ArrayList<>();
        for (int i = 0; i < meals.size(); i++) {
            MealResponse mealResponse = new MealResponse();
            mealResponse.setMealId(meals.get(i).getIdMeal());
            mealResponse.setName(meals.get(i).getStrMeal());
            mealResponse.setInstructions(meals.get(i).getStrInstructions());
            mealResponse.setImage(meals.get(i).getStrMealThumb());
            mealResponse.setIngredients(getMealIngredients(meals.get(i)));
            mealResponses.add(mealResponse);
        }
        return mealResponses;
    }
}
