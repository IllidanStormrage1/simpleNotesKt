package com.example.simplenotes.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.simplenotes.R
import com.example.simplenotes.domain.utils.Consts.SP_DARK
import com.example.simplenotes.domain.utils.Consts.SP_DARK_THEME
import com.example.simplenotes.domain.utils.Consts.SP_THEME
import com.example.simplenotes.domain.utils.apply
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isDarkThemeEnabled())
            setTheme(R.style.AppTheme_dark)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        NavigationUI.setupWithNavController(
            toolbar,
            findNavController(R.id.nav_host_fragment)
        )
    }

    private fun isDarkThemeEnabled(): Boolean =
        getSharedPreferences(SP_THEME, Context.MODE_PRIVATE).getBoolean(SP_DARK_THEME, false)

    private fun changeTheme() {
        val outValue = TypedValue()
        theme.resolveAttribute(R.attr.name, outValue, true)
        getSharedPreferences(SP_THEME, Context.MODE_PRIVATE).apply {
            putBoolean(SP_DARK_THEME, outValue.string.toString() != SP_DARK)
        }
        recreate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(
            R.menu.main_menu, menu
        )
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.darkThemeSwitch -> changeTheme()
        }
        return super.onOptionsItemSelected(item)
    }
}
