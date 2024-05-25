package com.aslanjavasky.surftech.cocktailbar.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cocktailId: Int,
    val description: String?
)