package com.aslanjavasky.surftech.cocktailbar.data.repoImpls

import com.aslanjavasky.surftech.cocktailbar.data.room.dao.CocktailsDao
import com.aslanjavasky.surftech.cocktailbar.data.room.dao.IngredientsDao
import com.aslanjavasky.surftech.cocktailbar.data.room.entity.IngredientEntity
import com.aslanjavasky.surftech.cocktailbar.data.room.entity.toEntity
import com.aslanjavasky.surftech.cocktailbar.data.room.entity.toItem
import com.aslanjavasky.surftech.cocktailbar.domain.model.CocktailItem
import com.aslanjavasky.surftech.cocktailbar.domain.repos.CocktailRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CocktailRepoImpl @Inject constructor(
    private val cocktailsDao: CocktailsDao,
    private val ingredientsDao: IngredientsDao
) : CocktailRepo {

    override suspend fun getAllCocktails(): List<CocktailItem> = withContext(Dispatchers.IO) {
        cocktailsDao.getAllCocktails().map {
            val ingredients =
                ingredientsDao.getIngredientsByCocktailId(it.id).map { it.description }
            it.toItem(ingredients)
        }
    }

    override suspend fun addNewCocktail(cocktail: CocktailItem) =
        withContext(Dispatchers.IO) {
            cocktailsDao.insertNewCocktail(cocktail.toEntity())
            ingredientsDao.insertIngredients(
                cocktail.ingredients.map {
                    IngredientEntity(
                        cocktailId = cocktail.id,
                        description = cocktail.description
                    )
                }
            )

        }

    override suspend fun getCocktailById(id: Int): CocktailItem =
        withContext(Dispatchers.IO) {
            val cocktailEntity = cocktailsDao.getCocktailById(id)
            val ingredients = ingredientsDao.getIngredientsByCocktailId(cocktailEntity.id)
                .map { it.description }
            return@withContext cocktailEntity.toItem(ingredients)
        }
}