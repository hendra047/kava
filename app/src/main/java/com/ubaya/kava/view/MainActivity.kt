package com.ubaya.kava.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.ubaya.kava.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NavController
        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)

        navController.navigate(R.id.itemLogout)

        val appConfig = AppBarConfiguration(
            setOf(
                R.id.itemHome,
                R.id.itemMyBooks,
                R.id.itemProfile,
                R.id.itemLogout
            )
        )

        setupActionBarWithNavController(navController, appConfig)
        bottomNav.setupWithNavController(navController)
        navView.setupWithNavController(navController)
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        // Make bottom navbar only show when on Home Fragment and My Books Fragment
        navController.addOnDestinationChangedListener { _, target, _ ->
            when (target.id) {
                R.id.itemHome  -> bottomNav.visibility = View.VISIBLE
                R.id.itemMyBooks -> bottomNav.visibility = View.VISIBLE
                R.id.itemProfile -> bottomNav.visibility = View.VISIBLE
                else -> bottomNav.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayout) || super.onSupportNavigateUp()
    }

}