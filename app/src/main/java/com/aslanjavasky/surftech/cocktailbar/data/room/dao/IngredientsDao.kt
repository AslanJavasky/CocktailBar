package com.aslanjavasky.surftech.cocktailbar.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aslanjavasky.surftech.cocktailbar.data.room.entity.IngredientEntity

@Dao
interface IngredientsDao {

    @Insert
    suspend fun insertIngredients(data: List<IngredientEntity>)

    @Query("SELECT * FROM ingredients WHERE cocktailId=:cocktailId")
    suspend fun getIngredientsByCocktailId(cocktailId: Int): List<IngredientEntity>

}