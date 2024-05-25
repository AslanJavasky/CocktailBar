package com.aslanjavasky.surftech.cocktailbar.presentation.cocktailListFragment.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.aslanjavasky.surftech.cocktailbar.databinding.CocktailListItemBinding
import com.aslanjavasky.surftech.cocktailbar.domain.model.CocktailItem

class CocktailsListAdapter :
    ListAdapter<CocktailItem, CocktailItemViewHolder>(CocktailItemDiffCallback()) {

    var onItemClick: ((CocktailItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailItemViewHolder {
        val binding =
            CocktailListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailItemViewHolder, position: Int) {
        val cocktail = currentList[position]
        with(holder.binding) {
            textViewCocktailTitle.text = cocktail.name
            onItemClick?.let { listener ->
                root.setOnClickListener { listener(cocktail) }
            }

        }
    }
}