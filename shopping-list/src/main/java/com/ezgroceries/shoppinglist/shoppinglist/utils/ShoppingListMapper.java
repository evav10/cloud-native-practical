package com.ezgroceries.shoppinglist.shoppinglist.utils;

import static com.ezgroceries.shoppinglist.utils.IngredientMapper.createIngredients;

import com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts.ShoppingListResponse;
import com.ezgroceries.shoppinglist.shoppinglist.persistence.entities.ShoppingListEntity;
import java.util.Optional;

public class ShoppingListMapper {

    public static ShoppingListResponse mapShoppingList(Optional<ShoppingListEntity> existingShoppingListEntity) {
        ShoppingListResponse shoppingListResponse = new ShoppingListResponse();
        shoppingListResponse.setShoppingListId(existingShoppingListEntity.get().getId());
        shoppingListResponse.setName(existingShoppingListEntity.get().getName());
        shoppingListResponse.setIngredients(createIngredients(existingShoppingListEntity.get().getCocktailEntities()));
        return shoppingListResponse;
    }
}
