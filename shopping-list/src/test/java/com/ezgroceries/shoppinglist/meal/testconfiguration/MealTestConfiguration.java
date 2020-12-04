package com.ezgroceries.shoppinglist.meal.testconfiguration;

import com.ezgroceries.shoppinglist.meal.controllers.contracts.MealResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class MealTestConfiguration {

    public static String MEAL_ID = "meal id";
    public static String ANOTHER_MEAL_ID = "another mealId";
    public static String MEAL_NAME = "meal name";
    public static String ANOTHER_MEAL_NAME = "another meal name";
    public static String MEAL_INSTRUCTIONS = "meal instructions";
    public static String OTHER_MEAL_INSTRUCTIONS = "other meal instructions";
    public static String MEAL_IMAGE = "meal image";
    public static String ANOTHER_MEAL_IMAGE = "another meal image";
    public static String MEAL_INGREDIENT = "ingredient";
    public static String SECOND_MEAL_INGREDIENT = "second meal ingredient";
    public static String THIRD_MEAL_INGREDIENT = "third meal ingredient";
    public static String ANOTHER_MEAL_INGREDIENT = "another meal ingredient";

    public static List<MealResponse> getDummyMeals() {

        return Arrays.asList(
                new MealResponse(MEAL_ID, MEAL_NAME, MEAL_INSTRUCTIONS, MEAL_IMAGE,
                        Arrays.asList(MEAL_INGREDIENT, SECOND_MEAL_INGREDIENT, ANOTHER_MEAL_INGREDIENT).stream().collect(Collectors.toSet())),
                new MealResponse(ANOTHER_MEAL_ID, ANOTHER_MEAL_NAME, OTHER_MEAL_INSTRUCTIONS, ANOTHER_MEAL_IMAGE,
                        Arrays.asList(MEAL_INGREDIENT, THIRD_MEAL_INGREDIENT, ANOTHER_MEAL_INGREDIENT).stream().collect(Collectors.toSet())));
    }
}
