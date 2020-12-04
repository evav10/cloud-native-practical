package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.model.internal.Cocktail;
import com.ezgroceries.shoppinglist.model.internal.ShoppingList;
import com.ezgroceries.shoppinglist.model.internal.ShoppingListEntity;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
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
    public ShoppingList getShoppingList(@PathVariable UUID shoppingListId) {
        return shoppingListService.getShoppingList(shoppingListId);
    }

    @GetMapping
    public List<ShoppingList> getAllShoppingListsJpa() {
        return shoppingListService.getAllShoppingLists();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListEntity createShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.createShoppingList(shoppingList);
    }

    @PostMapping(value = "/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList addCocktailToShoppingList(@PathVariable String shoppingListId,
            @RequestBody List<Cocktail> cocktail) {
        return shoppingListService.addCocktailsToShoppingList(shoppingListId, cocktail);
    }
}
