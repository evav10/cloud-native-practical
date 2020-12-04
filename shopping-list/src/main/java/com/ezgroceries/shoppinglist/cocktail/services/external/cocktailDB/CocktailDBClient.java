package com.ezgroceries.shoppinglist.cocktail.services.external.cocktailDB;

import com.ezgroceries.shoppinglist.cocktail.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.cocktail.persistence.repositories.CocktailRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1", fallback = CocktailDBClient.CocktailDBClientFallback.class)
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

    @Component
    class CocktailDBClientFallback implements CocktailDBClient {

        private final CocktailRepository cocktailRepository;

        public CocktailDBClientFallback(CocktailRepository cocktailRepository) {
            this.cocktailRepository = cocktailRepository;
        }

        @Override
        public CocktailDBResponse searchCocktails(String search) {
            List<CocktailEntity> cocktailEntities = cocktailRepository.findByNameContainingIgnoreCase(search);

            CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
            cocktailDBResponse.setDrinks(cocktailEntities.stream().map(cocktailEntity -> {
                CocktailDBResponse.DrinkResource drinkResource = new CocktailDBResponse.DrinkResource();
                drinkResource.setIdDrink(cocktailEntity.getIdDrink());
                drinkResource.setStrDrink(cocktailEntity.getName());
                drinkResource.setStrGlass(cocktailEntity.getGlass());
                drinkResource.setStrInstructions(cocktailEntity.getInstructions());
                drinkResource.setStrDrinkThumb(cocktailEntity.getImage_Link());
                String[] ingredients = cocktailEntity.getIngredients().toArray(new String[15]);
                drinkResource.setStrIngredient1(ingredients[0]);
                drinkResource.setStrIngredient2(ingredients[1]);
                drinkResource.setStrIngredient3(ingredients[2]);
                drinkResource.setStrIngredient4(ingredients[3]);
                drinkResource.setStrIngredient5(ingredients[4]);
                drinkResource.setStrIngredient6(ingredients[5]);
                drinkResource.setStrIngredient7(ingredients[6]);
                drinkResource.setStrIngredient8(ingredients[7]);
                drinkResource.setStrIngredient9(ingredients[8]);
                drinkResource.setStrIngredient10(ingredients[9]);
                drinkResource.setStrIngredient11(ingredients[10]);
                drinkResource.setStrIngredient12(ingredients[11]);
                drinkResource.setStrIngredient13(ingredients[12]);
                drinkResource.setStrIngredient14(ingredients[13]);
                drinkResource.setStrIngredient15(ingredients[14]);
                return drinkResource;
            }).collect(Collectors.toList()));

            return cocktailDBResponse;
        }
    }
}
