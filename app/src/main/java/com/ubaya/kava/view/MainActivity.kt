package com.ubaya.kava.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.ubaya.kava.R
import com.ubaya.kava.model.KavaDatabase
import com.ubaya.kava.util.createNotificationChannel
import kotlinx.android.synthetic.main.activity_main.*

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

        Log.d("trans-1", "-1")
        KavaDatabase.getInstance(this)
        Log.d("trans-1", "0")

        // Notification
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, getString(R.string.app_name), "Reminder of Expiration on book subscription that has been purchased. The reminder will be created when 7 days before its expiration subscription")

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