package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.clients.CocktailDBClient;
import com.ezgroceries.shoppinglist.model.external.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.external.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shoppinglist.model.internal.Cocktail;
import com.ezgroceries.shoppinglist.model.internal.CocktailEntity;
import com.ezgroceries.shoppinglist.model.internal.CocktailRepository;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {

    private final CocktailDBClient cocktailDBClient;

    @Autowired
    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailDBClient cocktailDBClient, CocktailRepository cocktailRepository) {
        this.cocktailDBClient = cocktailDBClient;
        this.cocktailRepository = cocktailRepository;
    }

    public List<Cocktail> searchCocktail(String search) {
        List<Cocktail> cocktails = new ArrayList<>();
        try {
            CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
            cocktails = mergeCocktails(cocktailDBResponse.getDrinks());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return cocktails;
    }

    public List<Cocktail> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
        //Get all the idDrink attributes
        List<String> ids = drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream()
                .collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity();
                newCocktailEntity.setId(UUID.randomUUID());
                newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
                newCocktailEntity.setName(drinkResource.getStrDrink());
                newCocktailEntity.setIngredients(getIngredients(drinkResource));
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to Cocktail instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<Cocktail> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(drinkResource -> new Cocktail(allEntityMap.get(drinkResource.getIdDrink()).getId().toString(),
                drinkResource.getStrDrink(), drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), getIngredients(drinkResource))).collect(Collectors.toList());
    }

    private Set<String> getIngredients(DrinkResource drinkResource) {
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
