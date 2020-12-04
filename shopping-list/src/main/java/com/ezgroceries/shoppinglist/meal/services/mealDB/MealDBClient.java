package com.ezgroceries.shoppinglist.meal.services.mealDB;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "mealDBClient", url = "https://www.themealdb.com/api/json/v1/1/")
public interface MealDBClient {

    @GetMapping(value = "search.php")
    MealDBResponse searchMeals(@RequestParam("s") String search);

}
