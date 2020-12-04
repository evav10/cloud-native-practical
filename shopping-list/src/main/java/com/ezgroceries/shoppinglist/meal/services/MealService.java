package com.ezgroceries.shoppinglist.meal.services;

import static com.ezgroceries.shoppinglist.utils.IngredientMapper.getMealIngredients;

import com.ezgroceries.shoppinglist.meal.controllers.contracts.MealResponse;
import com.ezgroceries.shoppinglist.meal.persistence.entities.MealEntity;
import com.ezgroceries.shoppinglist.meal.persistence.repositories.MealRepository;
import com.ezgroceries.shoppinglist.meal.services.mealDB.MealDBClient;
import com.ezgroceries.shoppinglist.meal.services.mealDB.MealDBResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {

    private final MealDBClient mealDBClient;

    @Autowired
    private final MealRepository mealRepository;

    public MealService(MealDBClient mealDBClient, MealRepository mealRepository) {
        this.mealDBClient = mealDBClient;
        this.mealRepository = mealRepository;
    }

    public List<MealResponse> searchMeal(String search) {
        List<MealResponse> mealResponses = new ArrayList<>();
        try {
            MealDBResponse mealDBResponse = mealDBClient.searchMeals(search);
            mealResponses = mergeMeals(mealDBResponse.getMeals());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return mealResponses;
    }

    public List<MealResponse> mergeMeals(List<MealDBResponse.Meal> meals) {
        //Get all the idMeal attributes
        List<String> ids = meals.stream().map(MealDBResponse.Meal::getIdMeal).collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, MealEntity> existingEntityMap = mealRepository.findByIdMealIn(ids).stream()
                .collect(Collectors.toMap(MealEntity::getIdMeal, o -> o, (o, o2) -> o));

        //Stream over all the meals, map them to the existing ones, persist a new one if not existing
        Map<String, MealEntity> allEntityMap = meals.stream().map(meal -> {
            MealEntity mealEntity = existingEntityMap.get(meal.getIdMeal());
            if (mealEntity == null) {
                MealEntity newMealEntity = new MealEntity();
                newMealEntity.setId(UUID.randomUUID());
                newMealEntity.setIdMeal(meal.getIdMeal());
                newMealEntity.setName(meal.getStrMeal());
                newMealEntity.setIngredients(getMealIngredients(meal));
                newMealEntity.setInstructions(meal.getStrInstructions());
                newMealEntity.setImage_Link(meal.getStrMealThumb());
                mealEntity = mealRepository.save(newMealEntity);
            }
            return mealEntity;
        }).collect(Collectors.toMap(MealEntity::getIdMeal, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to Meal instances
        return mergeAndTransform(meals, allEntityMap);
    }

    private List<MealResponse> mergeAndTransform(List<MealDBResponse.Meal> drinks, Map<String, MealEntity> allEntityMap) {
        return drinks.stream().map(meal -> new MealResponse(allEntityMap.get(meal.getIdMeal()).getId().toString(),
                meal.getStrMeal(), meal.getStrInstructions(), meal.getStrMealThumb(), getMealIngredients(meal)))
                .collect(Collectors.toList());
    }
}
