package com.ezgroceries.shoppinglist.shoppinglist.controllers;

import static com.ezgroceries.shoppinglist.shoppinglist.testconfiguration.ShoppingListTestConfiguration.SHOPPING_LIST_ID;
import static com.ezgroceries.shoppinglist.shoppinglist.testconfiguration.ShoppingListTestConfiguration.SHOPPING_LIST_ID_AS_UUID;
import static com.ezgroceries.shoppinglist.shoppinglist.testconfiguration.ShoppingListTestConfiguration.SHOPPING_LIST_NAME;
import static com.ezgroceries.shoppinglist.shoppinglist.testconfiguration.ShoppingListTestConfiguration.getDummyAddCocktailRequest;
import static com.ezgroceries.shoppinglist.shoppinglist.testconfiguration.ShoppingListTestConfiguration.getDummyAddMealRequest;
import static com.ezgroceries.shoppinglist.shoppinglist.testconfiguration.ShoppingListTestConfiguration.getDummyShoppingList;
import static com.ezgroceries.shoppinglist.shoppinglist.testconfiguration.ShoppingListTestConfiguration.getDummyShoppingListRequest;
import static com.ezgroceries.shoppinglist.shoppinglist.testconfiguration.ShoppingListTestConfiguration.getDummyShoppingLists;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.cocktail.testconfiguration.CocktailTestConfiguration;
import com.ezgroceries.shoppinglist.shoppinglist.services.ShoppingListService;
import com.ezgroceries.shoppinglist.shoppinglist.testconfiguration.ShoppingListTestConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ShoppingListController.class)
@ContextConfiguration(classes = {ShoppingListTestConfiguration.class, CocktailTestConfiguration.class})
class ShoppingListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingListService shoppingListService;

    @Test
    void getShoppingList() throws Exception {
        given(shoppingListService.getShoppingList(SHOPPING_LIST_ID_AS_UUID)).willReturn(getDummyShoppingList());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/shopping-lists/{id}", SHOPPING_LIST_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("shoppingListId").value(SHOPPING_LIST_ID))
                .andExpect(jsonPath("name").value(SHOPPING_LIST_NAME))
                .andExpect(jsonPath("$.ingredients").isArray());
    }

    @Test
    void getAllShoppingLists() throws Exception {
        given(shoppingListService.getAllShoppingLists()).willReturn(getDummyShoppingLists());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/shopping-lists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].shoppingListId").value(SHOPPING_LIST_ID))
                .andExpect(jsonPath("$[0].name").value(SHOPPING_LIST_NAME))
                .andExpect(jsonPath("$[0].ingredients").isArray())
                .andExpect(jsonPath("$[1].shoppingListId").value(SHOPPING_LIST_ID))
                .andExpect(jsonPath("$[1].name").value(SHOPPING_LIST_NAME))
                .andExpect(jsonPath("$[1].ingredients").isArray());
    }

    @Test
    void createShoppingList() throws Exception {
        given(shoppingListService.createShoppingList(getDummyShoppingListRequest())).willReturn(getDummyShoppingList());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/shopping-lists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getDummyShoppingList())))
                .andExpect(status().isCreated());
    }

    @Test
    void addCocktailToShoppingList() throws Exception {
        given(shoppingListService.addCocktailsToShoppingList(SHOPPING_LIST_ID, getDummyAddCocktailRequest())).willReturn(getDummyShoppingList());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/shopping-lists/{id}/cocktails", SHOPPING_LIST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getDummyAddCocktailRequest())))
                .andExpect(status().isCreated());
    }

    @Test
    void addMealToShoppingList() throws Exception {
        given(shoppingListService.addMealsToShoppingList(SHOPPING_LIST_ID, getDummyAddMealRequest())).willReturn(getDummyShoppingList());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/shopping-lists/{id}/cocktails", SHOPPING_LIST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getDummyAddMealRequest())))
                .andExpect(status().isCreated());
    }

    // Utility class for converting an object into JSON string
    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}