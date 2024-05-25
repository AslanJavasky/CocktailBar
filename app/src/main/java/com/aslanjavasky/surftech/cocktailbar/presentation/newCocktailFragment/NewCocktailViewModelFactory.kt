package com.aslanjavasky.surftech.cocktailbar.presentation.newCocktailFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class NewCocktailViewModelFactory @Inject constructor(
    private val viewModel: NewCocktailViewModel
) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewCocktailViewModel::class.java)){
            return viewModel as T
        }
        throw RuntimeException("Unknown class name: ${modelClass.name}")
    }
}