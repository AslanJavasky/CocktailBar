package com.aslanjavasky.surftech.cocktailbar

import android.app.Application
import com.aslanjavasky.surftech.cocktailbar.data.room.database.CocktailDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {

    lateinit var db:CocktailDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE=this
        db=CocktailDatabase.getInstance(this)
    }

    companion object{
        lateinit var INSTANCE: App
            private set
    }
}