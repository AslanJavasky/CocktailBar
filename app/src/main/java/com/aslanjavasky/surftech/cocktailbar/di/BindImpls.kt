package com.aslanjavasky.surftech.cocktailbar.di

import com.aslanjavasky.surftech.cocktailbar.data.repoImpls.CocktailRepoImpl
import com.aslanjavasky.surftech.cocktailbar.domain.repos.CocktailRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface BindImpls {

    @Binds
    fun bindCocktailRepo(cocktailRepoImpl: CocktailRepoImpl):CocktailRepo




}