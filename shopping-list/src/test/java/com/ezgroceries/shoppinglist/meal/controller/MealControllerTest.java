package com.ezgroceries.shoppinglist.meal.controller;

import static com.ezgroceries.shoppinglist.meal.testconfiguration.MealTestConfiguration.ANOTHER_MEAL_ID;
import static com.ezgroceries.shoppinglist.meal.testconfiguration.MealTestConfiguration.ANOTHER_MEAL_IMAGE;
import static com.ezgroceries.shoppinglist.meal.testconfiguration.MealTestConfiguration.ANOTHER_MEAL_NAME;
import static com.ezgroceries.shoppinglist.meal.testconfiguration.MealTestConfiguration.MEAL_ID;
import static com.ezgroceries.shoppinglist.meal.testconfiguration.MealTestConfiguration.MEAL_IMAGE;
import static com.ezgroceries.shoppinglist.meal.testconfiguration.MealTestConfiguration.MEAL_INSTRUCTIONS;
import static com.ezgroceries.shoppinglist.meal.testconfiguration.MealTestConfiguration.MEAL_NAME;
import static com.ezgroceries.shoppinglist.meal.testconfiguration.MealTestConfiguration.OTHER_MEAL_INSTRUCTIONS;
import static com.ezgroceries.shoppinglist.meal.testconfiguration.MealTestConfiguration.getDummyMeals;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.meal.controllers.MealController;
import com.ezgroceries.shoppinglist.meal.services.MealService;
import com.ezgroceries.shoppinglist.meal.testconfiguration.MealTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(MealController.class)
@ContextConfiguration(classes = MealTestConfiguration.class)
public class MealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService mealService;

    @Test
    public void getMeal() throws Exception {
        given(mealService.searchMeal("test")).willReturn(getDummyMeals());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/meals")
                .param("search", "test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].mealId").value(MEAL_ID))
                .andExpect(jsonPath("$[0].name").value(MEAL_NAME))
                .andExpect(jsonPath("$[0].instructions").value(MEAL_INSTRUCTIONS))
                .andExpect(jsonPath("$[0].image").value(MEAL_IMAGE))
                .andExpect(jsonPath("$[0].ingredients").isArray())
                .andExpect(jsonPath("$[1].mealId").value(ANOTHER_MEAL_ID))
                .andExpect(jsonPath("$[1].name").value(ANOTHER_MEAL_NAME))
                .andExpect(jsonPath("$[1].instructions").value(OTHER_MEAL_INSTRUCTIONS))
                .andExpect(jsonPath("$[1].image").value(ANOTHER_MEAL_IMAGE))
                .andExpect(jsonPath("$[1].ingredients").isArray());

    }
}
