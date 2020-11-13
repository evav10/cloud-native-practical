package com.ezgroceries.shoppinglist.controllers;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.resources.CocktailResource;
import com.ezgroceries.shoppinglist.resources.ShoppingListResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ShoppingListController.class)
class ShoppingListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getShoppingList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/shopping-lists/{id}", "97c8e5bd-5353-426e-b57b-69eb2260ace3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("shoppingListId").value("97c8e5bd-5353-426e-b57b-69eb2260ace3"))
                .andExpect(jsonPath("name").value("Stephanie's birthday"))
                .andExpect(jsonPath("$.ingredients[0]").value("Tequila"))
                .andExpect(jsonPath("$.ingredients[1]").value("Triple sec"))
                .andExpect(jsonPath("$.ingredients[2]").value("Lime juice"))
                .andExpect(jsonPath("$.ingredients[3]").value("Salt"))
                .andExpect(jsonPath("$.ingredients[4]").value("Blue Curacao"));
    }

    @Test
    void getAllShoppingLists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/shopping-lists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].shoppingListId").value(any(String.class)))
                .andExpect(jsonPath("$[0].name").value("Stephanie's birthday"))
                .andExpect(jsonPath("$[0].ingredients[0]").value("Tequila"))
                .andExpect(jsonPath("$[0].ingredients[1]").value("Triple sec"))
                .andExpect(jsonPath("$[0].ingredients[2]").value("Lime juice"))
                .andExpect(jsonPath("$[0].ingredients[3]").value("Salt"))
                .andExpect(jsonPath("$[0].ingredients[4]").value("Blue Curacao"))
                .andExpect(jsonPath("$[1].shoppingListId").value(any(String.class)))
                .andExpect(jsonPath("$[1].name").value("My Birthday"))
                .andExpect(jsonPath("$[1].ingredients[0]").value("Tequila"))
                .andExpect(jsonPath("$[1].ingredients[1]").value("Triple sec"))
                .andExpect(jsonPath("$[1].ingredients[2]").value("Lime juice"))
                .andExpect(jsonPath("$[1].ingredients[3]").value("Salt"))
                .andExpect(jsonPath("$[1].ingredients[4]").value("Blue Curacao"));
    }

    @Test
    void createShoppingList() throws Exception {
        ShoppingListResource shoppingListResource = new ShoppingListResource("name");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/shopping-lists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(shoppingListResource)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("shoppingListId").value(any(String.class)))
                .andExpect(jsonPath("name").value("name"));
    }

    @Test
    void addCocktailToShoppingList() throws Exception {
        List<CocktailResource> cocktailResources = Arrays.asList(new CocktailResource("RANDOMID", null, null, null, null, null));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/shopping-lists/{id}/cocktails", "97c8e5bd-5353-426e-b57b-69eb2260ace3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cocktailResources)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cocktailId").value("RANDOMID"));
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