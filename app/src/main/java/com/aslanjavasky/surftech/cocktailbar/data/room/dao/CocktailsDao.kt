package com.aslanjavasky.surftech.cocktailbar.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aslanjavasky.surftech.cocktailbar.data.room.entity.CocktailEntity

@Dao
interface CocktailsDao {

    @Insert
    suspend fun insertNewCocktail(data: CocktailEntity)

    @Query("SELECT * FROM cocktails")
    suspend fun getAllCocktails(): List<CocktailEntity>

    @Query("SELECT * FROM cocktails WHERE id=:id")
    suspend fun getCocktailById(id: Int): CocktailEntity


}