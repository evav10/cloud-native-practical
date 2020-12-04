package com.ezgroceries.shoppinglist.shoppinglist.persistence.repositories;

import com.ezgroceries.shoppinglist.shoppinglist.persistence.entities.ShoppingListEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, UUID> {

    ShoppingListEntity findByName(String name);
}
