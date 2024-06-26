package com.aslanjavasky.surftech.cocktailbar.presentation.newCocktailFragment
sealed class NewCocktailScreenState {
    data object Initial : NewCocktailScreenState()

    data class InputError(val missingName: Boolean, val missingIngredients: Boolean) :
        NewCocktailScreenState()

    data object AddedSuccessfully : NewCocktailScreenState()


}