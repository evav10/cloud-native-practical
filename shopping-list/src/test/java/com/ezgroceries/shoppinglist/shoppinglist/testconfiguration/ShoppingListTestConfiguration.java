package com.ezgroceries.shoppinglist.shoppinglist.testconfiguration;

import com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts.CreateShoppingListRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controllers.contracts.ShoppingListResponse;
import com.ezgroceries.shoppinglist.shoppinglist.persistence.entities.ShoppingListEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class ShoppingListTestConfiguration {

    public static String SHOPPING_LIST_ID = "97c8e5bd-5353-426e-b57b-69eb2260ace3";
    public static UUID SHOPPING_LIST_ID_AS_UUID = UUID.fromString(SHOPPING_LIST_ID);
    public static String SHOPPING_LIST_NAME = "shoppingListName";

    public static ShoppingListResponse getDummyShoppingList() {
        ShoppingListResponse shoppingListResponse = new ShoppingListResponse();
        shoppingListResponse.setShoppingListId(SHOPPING_LIST_ID_AS_UUID);
        shoppingListResponse.setName(SHOPPING_LIST_NAME);
        shoppingListResponse.setIngredients(Collections.emptySet());
        return shoppingListResponse;
    }

    public static CreateShoppingListRequest getDummyShoppingListRequest() {
        CreateShoppingListRequest createShoppingListRequest = new CreateShoppingListRequest();
        createShoppingListRequest.setName(SHOPPING_LIST_NAME);
        return createShoppingListRequest;
    }

    public static List<ShoppingListResponse> getDummyShoppingLists() {
        return Arrays.asList(getDummyShoppingList(), getDummyShoppingList());
    }

    public static ShoppingListEntity getDummyShoppingListEntity() {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setId(SHOPPING_LIST_ID_AS_UUID);
        shoppingListEntity.setName(SHOPPING_LIST_NAME);
        shoppingListEntity.setCocktailEntities(Collections.emptyList());
        return shoppingListEntity;
    }
}
