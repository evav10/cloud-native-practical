package com.ezgroceries.shoppinglist.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.cocktail.controllers.contracts.CocktailRequest;
import com.ezgroceries.shoppinglist.meal.controllers.contracts.MealRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts.CreateShoppingListRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts.ShoppingListResponse;
import com.ezgroceries.shoppinglist.shoppinglist.services.ShoppingListService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopping-lists", produces = "application/json")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping(value = "/{shoppingListId}")
    public ShoppingListResponse getShoppingList(@PathVariable UUID shoppingListId) {
        return shoppingListService.getShoppingList(shoppingListId);
    }

    @GetMapping
    public List<ShoppingListResponse> getAllShoppingListsJpa() {
        return shoppingListService.getAllShoppingLists();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResponse createShoppingList(@RequestBody CreateShoppingListRequest createShoppingListRequest) {
        return shoppingListService.createShoppingList(createShoppingListRequest);
    }

    @PostMapping(value = "/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResponse addCocktailToShoppingList(@PathVariable String shoppingListId,
            @RequestBody List<CocktailRequest> cocktails) {
        return shoppingListService.addCocktailsToShoppingList(shoppingListId, cocktails);
    }

    @PostMapping(value = "/{shoppingListId}/meals")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResponse addMealToShoppingList(@PathVariable String shoppingListId,
            @RequestBody List<MealRequest> meals) {
        return shoppingListService.addMealsToShoppingList(shoppingListId, meals);
    }
}
