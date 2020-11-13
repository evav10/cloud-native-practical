package com.ezgroceries.shoppinglist.controllers;

import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.ANOTHER_COCKTAIL_ID;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.ANOTHER_IMAGE;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.BLUE_CURACAO;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.BLUE_MARGERITA;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.COCKTAIL_GLASS;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.COCKTAIL_ID;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.IMAGE;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.INSTRUCTIONS;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.LIME_JUICE;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.MARGERITA;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.OTHER_INSTRUCTIONS;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.SALT;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.TEQUILA;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.TRIPLE_SEC;
import static com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration.getDummyResources;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.services.CocktailService;
import com.ezgroceries.shoppinglist.testconfiguration.CocktailTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(CocktailController.class)
@ContextConfiguration(classes = CocktailTestConfiguration.class)
class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailService cocktailService;

    @Test
    public void getCocktail() throws Exception {
        given(cocktailService.searchCocktail("test")).willReturn(getDummyResources());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cocktails")
                .param("search", "test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].cocktailId").value(COCKTAIL_ID))
                .andExpect(jsonPath("$[0].name").value(MARGERITA))
                .andExpect(jsonPath("$[0].glass").value(COCKTAIL_GLASS))
                .andExpect(jsonPath("$[0].instructions").value(INSTRUCTIONS))
                .andExpect(jsonPath("$[0].image").value(IMAGE))
                .andExpect(jsonPath("$[0].ingredients[0]").value(TEQUILA))
                .andExpect(jsonPath("$[0].ingredients[1]").value(TRIPLE_SEC))
                .andExpect(jsonPath("$[0].ingredients[2]").value(LIME_JUICE))
                .andExpect(jsonPath("$[0].ingredients[3]").value(SALT))
                .andExpect(jsonPath("$[1].cocktailId").value(ANOTHER_COCKTAIL_ID))
                .andExpect(jsonPath("$[1].name").value(BLUE_MARGERITA))
                .andExpect(jsonPath("$[1].glass").value(COCKTAIL_GLASS))
                .andExpect(jsonPath("$[1].instructions").value(OTHER_INSTRUCTIONS))
                .andExpect(jsonPath("$[1].image").value(ANOTHER_IMAGE))
                .andExpect(jsonPath("$[1].ingredients[0]").value(TEQUILA))
                .andExpect(jsonPath("$[1].ingredients[1]").value(BLUE_CURACAO))
                .andExpect(jsonPath("$[1].ingredients[2]").value(LIME_JUICE))
                .andExpect(jsonPath("$[1].ingredients[3]").value(SALT));
    }
}