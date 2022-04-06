package com.ubaya.ulib160419002.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.ubaya.ulib160419002.R
import com.ubaya.ulib160419002.util.createNotificationChannel
import com.ubaya.ulib160419002.viewmodel.NotificationViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    companion object {
        private var instance: MainActivity? = null

        fun showNotification(id:Int , title:String, content:String, icon:Int) {
            val channelID = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            instance?.let {
                val notificationBuilder = NotificationCompat.Builder(it.applicationContext,
                    channelID).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }

                val notificationManager = NotificationManagerCompat.from(it.applicationContext)
                notificationManager.notify(id, notificationBuilder.build())
            }
        }
    }

    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Notification
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, getString(R.string.app_name), "App notification channel")

        // NavController
        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)

        bottomNav.setupWithNavController(navController)
        navView.setupWithNavController(navController)

        // Make bottom navbar only show when on Home Fragment and My Books Fragment
        navController.addOnDestinationChangedListener { _, target, _ ->
            when (target.id) {
                R.id.itemHome  -> bottomNav.visibility = View.VISIBLE
                R.id.itemMyBooks -> bottomNav.visibility = View.VISIBLE
                else -> bottomNav.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayout) || super.onSupportNavigateUp()
    }

}