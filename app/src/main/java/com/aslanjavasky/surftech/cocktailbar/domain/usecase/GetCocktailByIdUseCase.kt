package com.aslanjavasky.surftech.cocktailbar.domain.usecase

import com.aslanjavasky.surftech.cocktailbar.domain.repos.CocktailRepo
import javax.inject.Inject

class GetCocktailByIdUseCase @Inject constructor(
    private val repo: CocktailRepo
) {
    suspend operator fun invoke(id: Int) =
        repo.getCocktailById(id)
}