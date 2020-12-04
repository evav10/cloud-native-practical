package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.model.internal.Cocktail;
import com.ezgroceries.shoppinglist.services.CocktailService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public List<Cocktail> get(@RequestParam String search) {
        return cocktailService.searchCocktail(search);
    }
}
