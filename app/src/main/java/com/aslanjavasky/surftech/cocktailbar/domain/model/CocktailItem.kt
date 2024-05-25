package com.aslanjavasky.surftech.cocktailbar.domain.model

data class CocktailItem(
    val id: Int = 0,
    val name: String,
    val ingredients: List<String?>,
    val description: String?,
    val recipe: String?
)
