package com.ezgroceries.shoppinglist.cocktail.testconfiguration;

import com.ezgroceries.shoppinglist.cocktail.controllers.contracts.CocktailResponse;
import com.ezgroceries.shoppinglist.cocktail.services.external.cocktailDB.CocktailDBResponse;
import com.ezgroceries.shoppinglist.cocktail.services.external.cocktailDB.CocktailDBResponse.DrinkResource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class CocktailTestConfiguration {

    public static String COCKTAIL_ID = "cocktailId";
    public static String ANOTHER_COCKTAIL_ID = "another cocktailId";
    public static String MARGERITA = "Margerita";
    public static String BLUE_MARGERITA = "Blue Margerita";
    public static String COCKTAIL_GLASS = "cocktail glass";
    public static String INSTRUCTIONS = "instructions";
    public static String OTHER_INSTRUCTIONS = "other instructions";
    public static String IMAGE = "image";
    public static String ANOTHER_IMAGE = "another image";
    public static String TEQUILA = "Tequila";
    public static String TRIPLE_SEC = "Triple sec";
    public static String LIME_JUICE = "Lime juice";
    public static String SALT = "Salt";
    public static String BLUE_CURACAO = "Blue Curacao";

    public static List<CocktailResponse> getDummyCocktails() {

        return Arrays.asList(
                new CocktailResponse(COCKTAIL_ID, MARGERITA, COCKTAIL_GLASS, INSTRUCTIONS, IMAGE,
                        Arrays.asList(TEQUILA, TRIPLE_SEC, LIME_JUICE, SALT).stream().collect(Collectors.toSet())),
                new CocktailResponse(ANOTHER_COCKTAIL_ID, BLUE_MARGERITA, COCKTAIL_GLASS, OTHER_INSTRUCTIONS, ANOTHER_IMAGE,
                        Arrays.asList(TEQUILA, BLUE_CURACAO, LIME_JUICE, SALT).stream().collect(Collectors.toSet())));
    }

    public static CocktailDBResponse getdummyDBResponse() {
        DrinkResource drinkResource1 = new DrinkResource();
        drinkResource1.setIdDrink(COCKTAIL_ID);
        drinkResource1.setStrDrink(MARGERITA);
        drinkResource1.setStrGlass(COCKTAIL_GLASS);
        drinkResource1.setStrInstructions(INSTRUCTIONS);
        drinkResource1.setStrDrinkThumb(IMAGE);
        drinkResource1.setStrIngredient1(TEQUILA);
        drinkResource1.setStrIngredient2(TRIPLE_SEC);
        drinkResource1.setStrIngredient3(LIME_JUICE);
        drinkResource1.setStrIngredient4(SALT);

        DrinkResource drinkResource2 = new DrinkResource();
        drinkResource2.setIdDrink(ANOTHER_COCKTAIL_ID);
        drinkResource2.setStrDrink(BLUE_MARGERITA);
        drinkResource2.setStrGlass(COCKTAIL_GLASS);
        drinkResource2.setStrInstructions(OTHER_INSTRUCTIONS);
        drinkResource2.setStrDrinkThumb(ANOTHER_IMAGE);
        drinkResource2.setStrIngredient1(TEQUILA);
        drinkResource2.setStrIngredient2(BLUE_CURACAO);
        drinkResource2.setStrIngredient3(LIME_JUICE);
        drinkResource2.setStrIngredient4(SALT);

        CocktailDBResponse dummyResponse = new CocktailDBResponse();
        dummyResponse.setDrinks(Arrays.asList(drinkResource1, drinkResource2));

        return dummyResponse;
    }
}
