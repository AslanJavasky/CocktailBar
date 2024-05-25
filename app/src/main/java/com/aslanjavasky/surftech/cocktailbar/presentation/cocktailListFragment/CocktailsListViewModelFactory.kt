package com.aslanjavasky.surftech.cocktailbar.presentation.cocktailListFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CocktailsListViewModelFactory @Inject constructor(
    private val viewModel:CocktailsListViewModel
) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailsListViewModel::class.java)){
            return viewModel as T
        }
        throw RuntimeException("Unknown class name: ${modelClass.name}")
    }

}