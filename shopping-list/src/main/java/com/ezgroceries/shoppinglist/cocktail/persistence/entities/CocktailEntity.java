package com.ezgroceries.shoppinglist.cocktail.persistence.entities;

import com.ezgroceries.shoppinglist.shoppinglist.persistence.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.utils.StringSetConverter;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COCKTAIL")
public class CocktailEntity {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "ID_DRINK")
    private String idDrink;

    @Column(name = "name")
    private String name;

    @Convert(converter = StringSetConverter.class)
    @Column(name = "INGREDIENTS")
    private Set<String> ingredients;

    @Column(name = "GLASS")
    private String glass;

    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @Column(name = "IMAGE_LINK")
    private String image_Link;

    @ManyToMany
    @JoinTable(
            name = "COCKTAIL_SHOPPING_LIST",
            joinColumns = {@JoinColumn(name = "cocktail_id")},
            inverseJoinColumns = {@JoinColumn(name = "shopping_list_id")}
    )
    private List<ShoppingListEntity> shoppingListEntities;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String drinkId) {
        this.idDrink = drinkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<ShoppingListEntity> getShoppingListEntities() {
        return shoppingListEntities;
    }

    public void setShoppingListEntities(List<ShoppingListEntity> shoppingListEntities) {
        this.shoppingListEntities = shoppingListEntities;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage_Link() {
        return image_Link;
    }

    public void setImage_Link(String image_Link) {
        this.image_Link = image_Link;
    }
}
