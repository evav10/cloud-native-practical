package com.ezgroceries.shoppinglist.model.internal;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, UUID> {

    ShoppingListEntity findByName(String name);
}
