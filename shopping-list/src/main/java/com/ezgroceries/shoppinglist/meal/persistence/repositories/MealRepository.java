package com.ezgroceries.shoppinglist.meal.persistence.repositories;

import com.ezgroceries.shoppinglist.meal.persistence.entities.MealEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<MealEntity, UUID> {

    List<MealEntity> findByIdMealIn(List<String> ids);

}
