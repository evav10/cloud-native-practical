package com.ezgroceries.shoppinglist.shoppinglist.services;

import static com.ezgroceries.shoppinglist.shoppinglist.utils.ShoppingListMapper.mapShoppingList;

import com.ezgroceries.shoppinglist.cocktail.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.cocktail.persistence.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.meal.persistence.entities.MealEntity;
import com.ezgroceries.shoppinglist.meal.persistence.repositories.MealRepository;
import com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts.AddCocktailRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts.AddMealRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts.CreateShoppingListRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts.ShoppingListResponse;
import com.ezgroceries.shoppinglist.shoppinglist.persistence.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.shoppinglist.persistence.repositories.ShoppingListRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    @Autowired
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    private final CocktailRepository cocktailRepository;

    @Autowired
    private final MealRepository mealRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailRepository cocktailRepository,
            MealRepository mealRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailRepository = cocktailRepository;
        this.mealRepository = mealRepository;
    }

    public ShoppingListResponse createShoppingList(CreateShoppingListRequest createShoppingListRequest) {
        ShoppingListEntity existingShoppingListEntity = shoppingListRepository.findByName(createShoppingListRequest.getName());
        if (existingShoppingListEntity == null) {
            ShoppingListEntity newShoppingListEntity = new ShoppingListEntity();
            newShoppingListEntity.setId(UUID.randomUUID());
            newShoppingListEntity.setName(createShoppingListRequest.getName());
            shoppingListRepository.save(newShoppingListEntity);
            return mapShoppingList(Optional.ofNullable(newShoppingListEntity));
        }
        return mapShoppingList(Optional.ofNullable(existingShoppingListEntity));
    }

    public ShoppingListResponse addCocktailsToShoppingList(String shoppingListId, List<AddCocktailRequest> cocktails) {
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findById(UUID.fromString(shoppingListId)).get();
        if (shoppingListEntity == null) {
            throw new RuntimeException("shopping list not found");
        }
        List<CocktailEntity> linkedCocktailEntities = shoppingListEntity.getCocktailEntities().stream().collect(Collectors.toList());
        for (int i = 0; i < cocktails.size(); i++) {
            CocktailEntity cocktailEntity = cocktailRepository.findById(UUID.fromString(cocktails.get(i).getCocktailId())).get();
            if (cocktailEntity == null) {
                throw new RuntimeException("cocktail not found");
            }
            if (linkedCocktailEntities.contains(cocktailEntity)) {
            } else {
                shoppingListEntity.getCocktailEntities().add(cocktailEntity);
            }
        }
        shoppingListRepository.save(shoppingListEntity);
        return mapShoppingList(Optional.of(shoppingListEntity));
    }

    public ShoppingListResponse addMealsToShoppingList(String shoppingListId, List<AddMealRequest> meals) {
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findById(UUID.fromString(shoppingListId)).get();
        if (shoppingListEntity == null) {
            throw new RuntimeException("shopping list not found");
        }
        List<MealEntity> linkedMealEntities = shoppingListEntity.getMealEntities().stream().collect(Collectors.toList());
        for (int i = 0; i < meals.size(); i++) {
            MealEntity mealEntity = mealRepository.findById(UUID.fromString(meals.get(i).getMealId())).get();
            if (mealEntity == null) {
                throw new RuntimeException("meal not found");
            }
            if (linkedMealEntities.contains(mealEntity)) {
            } else {
                shoppingListEntity.getMealEntities().add(mealEntity);
            }
        }
        shoppingListRepository.save(shoppingListEntity);
        return mapShoppingList(Optional.of(shoppingListEntity));
    }


    public ShoppingListResponse getShoppingList(UUID shoppingListId) {
        Optional<ShoppingListEntity> existingShoppingListEntity = shoppingListRepository.findById(shoppingListId);
        if (existingShoppingListEntity == null) {
            throw new RuntimeException("shopping list not found");
        }
        ShoppingListResponse shoppingListResponse = mapShoppingList(existingShoppingListEntity);
        return shoppingListResponse;
    }

    public List<ShoppingListResponse> getAllShoppingLists() {
        List<ShoppingListResponse> shoppingListResponses = new ArrayList<ShoppingListResponse>();
        for (ShoppingListEntity shoppingListEntity : shoppingListRepository.findAll()) {
            shoppingListResponses.add(mapShoppingList(Optional.ofNullable(shoppingListEntity)));
        }
        return shoppingListResponses;
    }
}
