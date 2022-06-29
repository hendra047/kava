package com.ubaya.kava.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.kava.R
import com.ubaya.kava.model.KavaDatabase
import java.lang.Exception

const val DB_NAME = "bookdb"

val MIGRATION_1_2 = object : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("INSERT INTO `book` (`title`, `subtitle`, `book_no`, `pages`, `language`, `author`, `publisher`, `description`, `rating`, `cover_url`) VALUES ('Elektra: A Novel of the House of Atreus',NULL,'9781250773616',320,'English','Jennifer Saint','Flatiron Books','The House of Atreus is cursed. A bloodline tainted by a generational cycle of violence and vengeance. This is the story of three women, their fates inextricably tied to this curse, and the fickle nature of men and gods.\\\\r\\\\n\\\\r\\\\nClytemnestra\\\\r\\\\nThe sister of Helen, wife of Agamemnon - her hopes of averting the curse are dashed when her sister is taken to Troy by the feckless Paris. Her husband raises a great army against them, and determines to win, whatever the cost.\\\\r\\\\n\\\\r\\\\nCassandra\\\\r\\\\nPrincess of Troy, and cursed by Apollo to see the future but never to be believed when she speaks of it. She is powerless in her knowledge that the city will fall.\\\\r\\\\n\\\\r\\\\nElektra\\\\r\\\\nThe youngest daughter of Clytemnestra and Agamemnon, Elektra is horrified by the bloodletting of her kin. But, can she escape the curse, or is her own destiny also bound by violence?\\\\r\\\\n\\\\r\\\\nPraise for Jennifer Saint and ARIADNE:\\\\r\\\\n\\\\r\\\\n\\\\ A lyrical, insightful re-telling\\\\ Daily Mail\\\\r\\\\n\\\\r\\\\n\\\\Relevant and revelatory\\\\ Stylist\\\\r\\\\n\\\\r\\\\n\\\\Energetic and compelling\\\\ Times\\\\r\\\\n\\\\r\\\\n\\\\An illuminating read\\\\ Woman & Home\\\\r\\\\n\\\\r\\\\n\\\\A story that\\\\s impossible to forget\\\\ Culturefly',4.1,'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1639951176l/58725016.jpg')")
    }
}

fun buildDb(context: Context) = Room.databaseBuilder(context, KavaDatabase::class.java, DB_NAME)
    .fallbackToDestructiveMigration()
    .addMigrations(MIGRATION_1_2)
    .build()

fun ImageView.loadImage(width: Int, height: Int, url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(width,height)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) { }
        })
}

fun ImageView.loadImage(width: Int, height: Int, url: String?) {
    Picasso.get()
        .load(url)
        .resize(width,height)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback {
            override fun onSuccess() {  }

            override fun onError(e: Exception?) { }
        })
}

fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelID = "${context.packageName}-$name"
        val channel = NotificationChannel(channelID, name, importance).apply {
            this.description = description
            setShowBadge(showBadge)
        }

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}