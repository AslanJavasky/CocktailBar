package com.aslanjavasky.surftech.cocktailbar.domain.repos

import com.aslanjavasky.surftech.cocktailbar.domain.model.CocktailItem

interface CocktailRepo {

    suspend fun getAllCocktails(): List<CocktailItem>
    suspend fun addNewCocktail(cocktail: CocktailItem)
    suspend fun getCocktailById(id: Int): CocktailItem
}