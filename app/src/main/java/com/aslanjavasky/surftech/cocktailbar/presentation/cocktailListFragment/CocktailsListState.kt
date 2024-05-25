package com.aslanjavasky.surftech.cocktailbar.presentation.cocktailListFragment


import com.aslanjavasky.surftech.cocktailbar.domain.model.CocktailItem

sealed class CocktailsListState {
    data object Initial : CocktailsListState()
    data class Content(val data: List<CocktailItem>) : CocktailsListState()

    data object EmptyList : CocktailsListState()
}
