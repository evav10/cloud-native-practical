package com.ezgroceries.shoppinglist.meal.controllers;

import com.ezgroceries.shoppinglist.meal.controllers.contracts.MealResponse;
import com.ezgroceries.shoppinglist.meal.services.MealService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/meals", produces = "application/json")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public List<MealResponse> get(@RequestParam String search) {
        return mealService.searchMeal(search);
    }
}