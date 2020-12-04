package com.ezgroceries.shoppinglist.shoppinglist.utils;

import static com.ezgroceries.shoppinglist.utils.IngredientMapper.createIngredients;

import com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts.ShoppingListResponse;
import com.ezgroceries.shoppinglist.shoppinglist.persistence.entities.ShoppingListEntity;
import java.util.Optional;

public class ShoppingListMapper {

    public static ShoppingListResponse mapShoppingList(Optional<ShoppingListEntity> shoppingListEntity) {
        ShoppingListResponse shoppingListResponse = new ShoppingListResponse();
        shoppingListResponse.setShoppingListId(shoppingListEntity.get().getId());
        shoppingListResponse.setName(shoppingListEntity.get().getName());
        shoppingListResponse
                .setIngredients(createIngredients(shoppingListEntity.get().getCocktailEntities(), shoppingListEntity.get().getMealEntities()));
        return shoppingListResponse;
    }
}
