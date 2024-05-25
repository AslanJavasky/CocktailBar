package com.aslanjavasky.surftech.cocktailbar.domain.usecase

import com.aslanjavasky.surftech.cocktailbar.domain.model.CocktailItem
import com.aslanjavasky.surftech.cocktailbar.domain.repos.CocktailRepo
import javax.inject.Inject

class AddNewCocktailUseCase @Inject constructor(
    private val repo: CocktailRepo
) {
    suspend operator fun invoke(cocktailItem: CocktailItem) =
        repo.addNewCocktail(cocktailItem)
}