package com.ezgroceries.shoppinglist.shoppinglist.persistence.entities;

import com.ezgroceries.shoppinglist.cocktail.persistence.entities.CocktailEntity;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPPING_LIST")
public class ShoppingListEntity {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "COCKTAIL_SHOPPING_LIST",
            joinColumns = {@JoinColumn(name = "shopping_list_id")},
            inverseJoinColumns = {@JoinColumn(name = "cocktail_id")})
    private List<CocktailEntity> cocktailEntities;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CocktailEntity> getCocktailEntities() {
        return cocktailEntities;
    }

    public void setCocktailEntities(List<CocktailEntity> cocktailEntities) {
        this.cocktailEntities = cocktailEntities;
    }
}
