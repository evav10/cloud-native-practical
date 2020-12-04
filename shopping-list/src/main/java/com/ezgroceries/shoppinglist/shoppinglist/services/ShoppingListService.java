package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.model.internal.Cocktail;
import com.ezgroceries.shoppinglist.model.internal.CocktailEntity;
import com.ezgroceries.shoppinglist.model.internal.CocktailRepository;
import com.ezgroceries.shoppinglist.model.internal.ShoppingList;
import com.ezgroceries.shoppinglist.model.internal.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.internal.ShoppingListRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    @Autowired
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    private final CocktailRepository cocktailRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailRepository cocktailRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailRepository = cocktailRepository;
    }

    public ShoppingListEntity createShoppingList(ShoppingList shoppingList) {
        ShoppingListEntity existingShoppingListEntity = shoppingListRepository.findByName(shoppingList.getName());
        if (existingShoppingListEntity == null) {
            ShoppingListEntity newShoppingListEntity = new ShoppingListEntity();
            newShoppingListEntity.setId(UUID.randomUUID());
            newShoppingListEntity.setName(shoppingList.getName());
            shoppingListRepository.save(newShoppingListEntity);
            return newShoppingListEntity;
        }
        return existingShoppingListEntity;
    }

    public ShoppingList addCocktailsToShoppingList(String shoppingListId, List<Cocktail> cocktails) {
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findById(UUID.fromString(shoppingListId)).get();
        if (shoppingListEntity == null) {
            throw new RuntimeException("shopping list not found");
        }
        List<CocktailEntity> linkedCocktailEntities = shoppingListEntity.getCocktailEntities().stream().collect(Collectors.toList());
        for (int i = 0; i < cocktails.size(); i++) {
            CocktailEntity cocktailEntity = cocktailRepository.findById(UUID.fromString(cocktails.get(i).getCocktailId())).get();
            if (cocktailEntity == null) {
                throw new RuntimeException("cocktail not found");
            }
            if (linkedCocktailEntities.contains(cocktailEntity)) {
            } else {
                shoppingListEntity.getCocktailEntities().add(cocktailEntity);
            }
        }
        shoppingListRepository.save(shoppingListEntity);
        return getShoppingList(Optional.of(shoppingListEntity));
    }

    public ShoppingList getShoppingList(UUID shoppingListId) {
        Optional<ShoppingListEntity> existingShoppingListEntity = shoppingListRepository.findById(shoppingListId);
        if (existingShoppingListEntity == null) {
            throw new RuntimeException("shopping list not found");
        }
        ShoppingList shoppingList = getShoppingList(existingShoppingListEntity);
        return shoppingList;
    }

    public List<ShoppingList> getAllShoppingLists() {
        List<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();
        for (ShoppingListEntity shoppingListEntity : shoppingListRepository.findAll()) {
            shoppingLists.add(getShoppingList(Optional.ofNullable(shoppingListEntity)));
        }
        return shoppingLists;
    }

    private ShoppingList getShoppingList(Optional<ShoppingListEntity> existingShoppingListEntity) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setShoppingListId(existingShoppingListEntity.get().getId());
        shoppingList.setName(existingShoppingListEntity.get().getName());
        shoppingList.setIngredients(createIngredients(existingShoppingListEntity.get().getCocktailEntities()));
        return shoppingList;
    }

    private Set<String> createIngredients(List<CocktailEntity> cocktailEntities) {
        Set<String> ingredients = new HashSet<>();
        for (int i = 0; i < cocktailEntities.size(); i++) {
            ingredients.addAll(cocktailEntities.get(i).getIngredients());
        }
        return ingredients;
    }

}
