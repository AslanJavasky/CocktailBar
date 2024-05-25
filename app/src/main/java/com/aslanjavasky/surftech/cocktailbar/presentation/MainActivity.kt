package com.aslanjavasky.surftech.cocktailbar.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aslanjavasky.surftech.cocktailbar.R
import com.aslanjavasky.surftech.cocktailbar.presentation.cocktailListFragment.CocktailsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, CocktailsListFragment.newInstance(), null)
                .commit()
        }
    }
}