package com.ezgroceries.shoppinglist.cocktail.persistence.repositories;

import com.ezgroceries.shoppinglist.cocktail.persistence.entities.CocktailEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<CocktailEntity, UUID> {

    List<CocktailEntity> findByIdDrinkIn(List<String> ids);
}
