package com.aslanjavasky.surftech.cocktailbar.di

import android.app.Application
import com.aslanjavasky.surftech.cocktailbar.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Provides
    fun provideCocktailsDao()= App.INSTANCE.db.cocktailsDao()

    @Provides
    fun provideIngredientsDao()= App.INSTANCE.db.ingredientsDao()

}