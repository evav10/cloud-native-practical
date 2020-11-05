package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.resources.CocktailResource;
import com.ezgroceries.shoppinglist.resources.ShoppingListResource;
import java.util.Arrays;
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

    @GetMapping(value = "/{shoppingListId}")
    public ShoppingListResource getShoppingList(@PathVariable UUID shoppingListId) {
        return new ShoppingListResource(shoppingListId,
                "Stephanie's birthday",
                Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"));
    }

    @GetMapping
    public List<ShoppingListResource> getAllShoppingLists() {
        return getDummyResources();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createShoppingList(@RequestBody ShoppingListResource shoppingListResource) {
        return new ShoppingListResource(shoppingListResource.getName());
    }

    @PostMapping(value = "/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CocktailResource> addCocktailToShoppingList(@PathVariable String shoppingListId,
            @RequestBody List<CocktailResource> cocktailResource) {
        return cocktailResource;
    }

    private List<ShoppingListResource> getDummyResources() {
        return Arrays.asList(
                new ShoppingListResource(
                        UUID.randomUUID(), "Stephanie's birthday",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao")),
                new ShoppingListResource(
                        UUID.randomUUID(), "My Birthday",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao")));
    }
}
