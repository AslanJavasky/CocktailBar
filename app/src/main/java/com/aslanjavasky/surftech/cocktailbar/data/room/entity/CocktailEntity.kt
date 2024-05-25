package com.aslanjavasky.surftech.cocktailbar.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aslanjavasky.surftech.cocktailbar.domain.model.CocktailItem

@Entity(tableName = "cocktails")
data class CocktailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String?,
    val recipe: String?
)

fun CocktailEntity.toItem(ingredients: List<String?>) = CocktailItem(
    id = this.id, ingredients = ingredients, name = this.name,
    description = this.description, recipe = this.recipe
)

fun CocktailItem.toEntity() = CocktailEntity(
    id = this.id, name = this.name,
    description = this.description, recipe = this.recipe
)
