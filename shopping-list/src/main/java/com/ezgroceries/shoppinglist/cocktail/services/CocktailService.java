package com.ezgroceries.shoppinglist.cocktail.services;

import static com.ezgroceries.shoppinglist.utils.IngredientMapper.getDrinkIngredients;

import com.ezgroceries.shoppinglist.cocktail.controllers.contracts.CocktailResponse;
import com.ezgroceries.shoppinglist.cocktail.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.cocktail.persistence.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.cocktail.services.external.cocktailDB.CocktailDBClient;
import com.ezgroceries.shoppinglist.cocktail.services.external.cocktailDB.CocktailDBResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
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

    public List<CocktailResponse> searchCocktail(String search) {
        List<CocktailResponse> cocktailResponses = new ArrayList<>();
        try {
            CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
            cocktailResponses = mergeCocktails(cocktailDBResponse.getDrinks());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return cocktailResponses;
    }

    public List<CocktailResponse> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
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
                newCocktailEntity.setIngredients(getDrinkIngredients(drinkResource));
                newCocktailEntity.setGlass(drinkResource.getStrGlass());
                newCocktailEntity.setInstructions(drinkResource.getStrInstructions());
                newCocktailEntity.setImage_Link(drinkResource.getStrDrinkThumb());
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to Cocktail instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<CocktailResponse> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(drinkResource -> new CocktailResponse(allEntityMap.get(drinkResource.getIdDrink()).getId().toString(),
                drinkResource.getStrDrink(), drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), getDrinkIngredients(drinkResource)))
                .collect(Collectors.toList());
    }
}
