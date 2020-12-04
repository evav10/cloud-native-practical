package com.ezgroceries.shoppinglist.cocktail.controllers;

import com.ezgroceries.shoppinglist.cocktail.controllers.contracts.CocktailResponse;
import com.ezgroceries.shoppinglist.cocktail.services.CocktailService;
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
    public List<CocktailResponse> get(@RequestParam String search) {
        return cocktailService.searchCocktail(search);
    }
}
