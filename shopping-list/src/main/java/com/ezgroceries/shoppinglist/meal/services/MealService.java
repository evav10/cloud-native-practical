package com.ezgroceries.shoppinglist.meal.services;

import static com.ezgroceries.shoppinglist.meal.utils.MealMapper.mapMeals;

import com.ezgroceries.shoppinglist.meal.controllers.contracts.MealResponse;
import com.ezgroceries.shoppinglist.meal.services.mealDB.MealDBClient;
import com.ezgroceries.shoppinglist.meal.services.mealDB.MealDBResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MealService {

    private final MealDBClient mealDBClient;

    public MealService(MealDBClient mealDBClient) {
        this.mealDBClient = mealDBClient;
    }

    public List<MealResponse> searchMeal(String search) {
        List<MealResponse> mealResponses = new ArrayList<>();
        try {
            MealDBResponse mealDBResponse = mealDBClient.searchMeals(search);
            mealResponses = mapMeals(mealDBResponse.getMeals());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return mealResponses;
    }
}
