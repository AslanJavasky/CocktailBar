package com.aslanjavasky.surftech.cocktailbar.presentation.cocktailListFragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.aslanjavasky.surftech.cocktailbar.domain.model.CocktailItem


class CocktailItemDiffCallback : DiffUtil.ItemCallback<CocktailItem>() {
    override fun areItemsTheSame(oldItem: CocktailItem, newItem: CocktailItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CocktailItem, newItem: CocktailItem): Boolean =
        oldItem == newItem
}