package com.aslanjavasky.surftech.cocktailbar.data.room.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aslanjavasky.surftech.cocktailbar.data.room.dao.CocktailsDao
import com.aslanjavasky.surftech.cocktailbar.data.room.dao.IngredientsDao
import com.aslanjavasky.surftech.cocktailbar.data.room.entity.CocktailEntity
import com.aslanjavasky.surftech.cocktailbar.data.room.entity.IngredientEntity

@Database(
    entities = [CocktailEntity::class, IngredientEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailsDao(): CocktailsDao
    abstract fun ingredientsDao(): IngredientsDao

    companion object {
        private var INSTANCE: CocktailDatabase? = null
        private const val DATABASE_NAME = "main.db"

        fun getInstance(application: Application): CocktailDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    application,
                    CocktailDatabase::class.java,
                    DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
    }

}