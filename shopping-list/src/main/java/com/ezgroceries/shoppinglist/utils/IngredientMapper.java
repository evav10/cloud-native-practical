package com.ezgroceries.shoppinglist.utils;

import com.ezgroceries.shoppinglist.cocktail.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.cocktail.services.external.cocktailDB.CocktailDBResponse.DrinkResource;
import com.google.common.base.Strings;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IngredientMapper {

    public static Set<String> createIngredients(List<CocktailEntity> cocktailEntities) {
        if (cocktailEntities == null) {
            return null;
        }
        Set<String> ingredients = new HashSet<>();
        for (int i = 0; i < cocktailEntities.size(); i++) {
            ingredients.addAll(cocktailEntities.get(i).getIngredients());
        }
        return ingredients;
    }

    public static Set<String> getDrinkIngredients(DrinkResource drinkResource) {
        return Stream.of(
                drinkResource.getStrIngredient1(),
                drinkResource.getStrIngredient2(),
                drinkResource.getStrIngredient3(),
                drinkResource.getStrIngredient4(),
                drinkResource.getStrIngredient5(),
                drinkResource.getStrIngredient6(),
                drinkResource.getStrIngredient7(),
                drinkResource.getStrIngredient8(),
                drinkResource.getStrIngredient9(),
                drinkResource.getStrIngredient10(),
                drinkResource.getStrIngredient11(),
                drinkResource.getStrIngredient12(),
                drinkResource.getStrIngredient13(),
                drinkResource.getStrIngredient14(),
                drinkResource.getStrIngredient15()
        ).filter(i -> !Strings.isNullOrEmpty(i)
        ).collect(Collectors.toSet());
    }
}
