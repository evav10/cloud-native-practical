package com.ezgroceries.shoppinglist.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(CocktailController.class)
class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCocktail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/cocktails")
                .param("search", "test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].cocktailId").value("23b3d85a-3928-41c0-a533-6538a71e17c4"))
                .andExpect(jsonPath("$[0].name").value("Margerita"))
                .andExpect(jsonPath("$[0].glass").value("Cocktail glass"))
                .andExpect(jsonPath("$[0].instructions")
                        .value("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten.."))
                .andExpect(jsonPath("$[0].image").value("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg"))
                .andExpect(jsonPath("$[0].ingredients[0]").value("Tequila"))
                .andExpect(jsonPath("$[0].ingredients[1]").value("Triple sec"))
                .andExpect(jsonPath("$[0].ingredients[2]").value("Lime juice"))
                .andExpect(jsonPath("$[0].ingredients[3]").value("Salt"))
                .andExpect(jsonPath("$[1].cocktailId").value("d615ec78-fe93-467b-8d26-5d26d8eab073"))
                .andExpect(jsonPath("$[1].name").value("Blue Margerita"))
                .andExpect(jsonPath("$[1].glass").value("Cocktail glass"))
                .andExpect(jsonPath("$[1].instructions").value("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt.."))
                .andExpect(jsonPath("$[1].image").value("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg"))
                .andExpect(jsonPath("$[1].ingredients[0]").value("Tequila"))
                .andExpect(jsonPath("$[1].ingredients[1]").value("Blue Curacao"))
                .andExpect(jsonPath("$[1].ingredients[2]").value("Lime juice"))
                .andExpect(jsonPath("$[1].ingredients[3]").value("Salt"));
    }
}