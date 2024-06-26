package com.aslanjavasky.surftech.cocktailbar.presentation.newCocktailFragment.adapter

import androidx.recyclerview.widget.DiffUtil

class IngredientItemDiffCallback : DiffUtil.ItemCallback<IngredientItem>() {
    override fun areItemsTheSame(oldItem: IngredientItem, newItem: IngredientItem): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: IngredientItem, newItem: IngredientItem): Boolean =
        oldItem == newItem
}