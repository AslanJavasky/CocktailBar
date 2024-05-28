package com.aslanjavasky.surftech.cocktailbar.presentation.newCocktailFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslanjavasky.surftech.cocktailbar.domain.model.CocktailItem
import com.aslanjavasky.surftech.cocktailbar.domain.usecase.AddNewCocktailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewCocktailViewModel @Inject constructor(
    private val addNewCocktailUseCase: AddNewCocktailUseCase
) : ViewModel() {

    private val _state: MutableLiveData<NewCocktailScreenState> =
        MutableLiveData(NewCocktailScreenState.Initial)

    val state: LiveData<NewCocktailScreenState> = _state

    fun addNewCocktail(
        name: String,
        description: String,
        ingredients: List<String>,
        recipe: String
    ) {
        if (name.isBlank() || ingredients.isEmpty()) {
            _state.value = NewCocktailScreenState.InputError(name.isBlank(), ingredients.isEmpty())
            return
        }
        viewModelScope.launch {
            val cocktail = CocktailItem(
                name = name,
                ingredients = ingredients,
                description = description,
                recipe = recipe
            )
            addNewCocktailUseCase(cocktail)
            _state.value = NewCocktailScreenState.AddedSuccessfully
        }
    }

}