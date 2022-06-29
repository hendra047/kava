package com.ubaya.kava.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.kava.R
import com.ubaya.kava.model.KavaDatabase
import java.lang.Exception
import java.util.concurrent.Executors


const val DB_NAME = "bookdb"

val MIGRATION_1_2 = object : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE book ADD COLUMN bookmarked INTEGER DEFAULT 0 NOT NULL")
    }
}

fun buildDb(context: Context) = Room.databaseBuilder(context, KavaDatabase::class.java, DB_NAME)
    .fallbackToDestructiveMigration()
    .addMigrations(MIGRATION_1_2)
    .addCallback(initDatabase)
    .build()

val initDatabase: RoomDatabase.Callback = object : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        Executors.newSingleThreadExecutor().execute {
            // FILL `Book` TABLE
            db.execSQL("""INSERT INTO `book` (`title`, `subtitle`, `book_no`, `pages`, `language`, `author`, `publisher`, `description`, `rating`, `cover_url`) VALUES("Elektra: A Novel of the House of Atreus", NULL, "9781250773616", 320, "English", "Jennifer Saint", "Flatiron Books", "The House of Atreus is cursed. A bloodline tainted by a generational cycle of violence and vengeance. This is the story of three women, their fates inextricably tied to this curse, and the fickle nature of men and gods.\r\n\r\nClytemnestra\r\nThe sister of Helen, wife of Agamemnon - her hopes of averting the curse are dashed when her sister is taken to Troy by the feckless Paris. Her husband raises a great army against them, and determines to win, whatever the cost.\r\n\r\nCassandra\r\nPrincess of Troy, and cursed by Apollo to see the future but never to be believed when she speaks of it. She is powerless in her knowledge that the city will fall.\r\n\r\nElektra\r\nThe youngest daughter of Clytemnestra and Agamemnon, Elektra is horrified by the bloodletting of her kin. But, can she escape the curse, or is her own destiny also bound by violence?\r\n\r\nPraise for Jennifer Saint and ARIADNE:\r\n\r\n\'A lyrical, insightful re-telling\' Daily Mail\r\n\r\n\'Relevant and revelatory\' Stylist\r\n\r\n\'Energetic and compelling\' Times\r\n\r\n\'An illuminating read\' Woman & Home\r\n\r\n\'A story that\'s impossible to forget\' Culturefly", 4.1, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1639951176l/58725016.jpg"),("Dating Dr. Dil", "If Shakespeare was an Auntie #1", "9780063001107", 352, "English", "Nisha Sharma", "Avon", "Kareena Mann dreams of having a love story like her parents, but she prefers restoring her classic car to swiping right on dating apps. When her father announces he’s selling her mother’s home, Kareena makes a deal with him: he’ll gift her the house if she can get engaged in four months. Her search for her soulmate becomes impossible when her argument with Dr. Prem Verma, host of The Dr. Dil Show, goes viral. Now the only man in her life is the one she doesn’t want.\r\n\r\nDr. Prem Verma is dedicated to building a local community health center, but he needs to get donors with deep pockets. The Dr. Dil Show was doing just that, until his argument with Kareena went viral, and he’s left short changed. That’s when Kareena’s meddling aunties presented him with a solution: convince Kareena he’s her soulmate and they’ll fund his clinic.  \r\n\r\nEven though they have conflicting views on love-matches and arranged-matches, the more time Prem spends with Kareena, the more he begins to believe she’s the woman he wants to spend the rest of his life with. But for Prem and Kareena to find their happily ever after, they must admit that hate has turned into fate.", 3.9, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1642405300l/57007401.jpg"),("Hook, Line, and Sinker", "It Happened One Summer #2", "9780063045699", 400, "English", "Tessa Bailey", "Avon Books", "King crab fisherman Fox Thornton has a reputation as a sexy, carefree flirt. Everyone knows he\'s a guaranteed good time--in bed and out--and that\'s exactly how he prefers it. Until he meets Hannah Bellinger. She\'s immune to his charm and looks, but she seems to enjoy his... personality? And wants to be friends? Bizarre. But he likes her too much to risk a fling, so platonic pals it is.\r\n\r\nNow, Hannah\'s in town for work, crashing in Fox\'s spare bedroom. She knows he\'s a notorious ladies\' man, but they\'re definitely just friends. In fact, she\'s nursing a hopeless crush on a colleague and Fox is just the person to help with her lackluster love life. Armed with a few tips from Westport\'s resident Casanova, Hannah sets out to catch her coworker\'s eye... yet the more time she spends with Fox, the more she wants him instead. As the line between friendship and flirtation begins to blur, Hannah can\'t deny she loves everything about Fox, but she refuses to be another notch on his bedpost.\r\n\r\nLiving with his best friend should have been easy. Except now she\'s walking around in a towel, sleeping right across the hall, and Fox is fantasizing about waking up next to her for the rest of his life and... and... man overboard! He\'s fallen for her, hook, line, and sinker. Helping her flirt with another guy is pure torture, but maybe if Fox can tackle his inner demons and show Hannah he\'s all in, she\'ll choose him instead?\r\n\r\nIn the follow-up to It Happened One Summer, Tessa Bailey delivers another deliciously fun rom-com about a former player who accidentally falls for his best friend while trying to help her land a different man...", 4.2, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1627068858l/58283080.jpg"),("Perahu Kertas", NULL, "9789791227780", 456, "Indonesia", "Dee Lestari", "Bentang Pustaka & Truedee", "Namanya Kugy. Mungil, pengkhayal, dan berantakan. Dari benaknya, mengalir untaian dongeng indah. Keenan belum pernah bertemu manusia seaneh itu.\r\n\r\n...\r\n\r\nNamanya Keenan. Cerdas, artistik, dan penuh kejutan. Dari tangannya, mewujud lukisan-lukisan magis. Kugy belum pernah bertemu manusia seajaib itu.\r\n\r\n...\r\n\r\nDan kini mereka berhadapan di antara hamparan misteri dan rintangan. Akankah dongeng dan lukisan itu bersatu?\r\n\r\nAkankah hati dan impian mereka bertemu?", 3.9, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1356225544l/6765740.jpg"),("Laskar Pelangi", "Tetralogi Laskar Pelangi #1", "9789793062792", 534, "Indonesia", "Andrea Hirata", "Bentang Pustaka", "Begitu banyak hal menakjubkan yang terjadi dalam masa kecil para anggota Laskar Pelangi. Sebelas orang anak Melayu Belitong yang luar biasa ini tak menyerah walau keadaan tak bersimpati pada mereka. Tengoklah Lintang, seorang kuli kopra cilik yang genius dan dengan senang hati bersepeda 80 kilometer pulang pergi untuk memuaskan dahaganya akan ilmu—bahkan terkadang hanya untuk menyanyikan Padamu Negeri di akhir jam sekolah. Atau Mahar, seorang pesuruh tukang parut kelapa sekaligus seniman dadakan yang imajinatif, tak logis, kreatif, dan sering diremehkan sahabat-sahabatnya, namun berhasil mengangkat derajat sekolah kampung mereka dalam karnaval 17 Agustus. Dan juga sembilan orang Laskar Pelangi lain yang begitu bersemangat dalam menjalani hidup dan berjuang meraih cita-cita. Selami ironisnya kehidupan mereka, kejujuran pemikiran mereka, indahnya petualangan mereka, dan temukan diri Anda tertawa, menangis, dan tersentuh saat membaca setiap lembarnya. Buku ini dipersembahkan buat mereka yang meyakini the magic of childhood memories, dan khususnya juga buat siapa saja yang masih meyakini adanya pintu keajaiban lain untuk mengubah dunia: pendidikan.", 4.2, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1489732961l/1362193._SY475_.jpg"),("Hujan", NULL, "9786020324784", 320, "Indonesia", "Tere Liye", "Gramedia Pustaka Utama", "Tentang persahabatan\r\nTentang cinta\r\nTentang perpisahan\r\nTentang melupakan\r\nTentang hujan", 4.4, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1451905281l/28446637._SY475_.jpg"),("Dilan: Dia Adalah Dilanku Tahun 1990", "Dilan #1", "9786027870413", 332, "Indonesia", "Pidi Baiq", "Pastel Books (Mizan Group)", '"Milea, kamu cantik, tapi aku belum mencintaimu. Enggak tahu kalau sore. Tunggu aja" (Dilan 1990)\r\n\r\n"Milea, jangan pernah bilang ke aku ada yang menyakitimu, nanti, besoknya, orang itu akan hilang." (Dilan 1990)\r\n\r\n"Cinta sejati adalah kenyamanan, kepercayaan, dan dukungan. Kalau kamu tidak setuju, aku tidak peduli." (Milea 1990)', 4.1, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1442310576l/22037542._SX318_.jpg")""")

            // FILL `User` TABLE
            db.execSQL("""INSERT INTO `user` (`username`, `password`, `name`, `gender`, `phone`, `photo_url`) VALUES('daniel123', 'daniel123', 'Daniel Zeys', 'Male', '081237485913', 'https://randomuser.me/api/portraits/men/13.jpg'),('sharline123', 'sharline123', 'Sharline Mayer', 'Female', '081374185913', 'https://randomuser.me/api/portraits/women/12.jpg'),('john123', 'john123', 'John Mendis', 'Male', '085777930501', 'https://randomuser.me/api/portraits/men/11.jpg')""")

            // FILL `Order` TABLE
            db.execSQL("""INSERT INTO `order` (`reserved_date`, `end_date`, `is_paid`, `user_id`, `book_id`) VALUES('2022-03-29 20:50:11', '2022-04-08 20:50:11', 0, 1, 3),('2022-03-16 11:00:11', '2022-03-23 11:00:11', 1, 1, 4),('2022-03-14 20:51:20', '2022-03-21 20:51:20', 1, 1, 7),('2022-03-25 08:00:00', '2022-04-07 08:00:00', 1, 1, 7),('2022-03-17 20:52:39', '2022-04-12 13:25:20', 1, 2, 5),('2022-03-23 18:30:39', '2022-04-10 18:30:39', 1, 2, 1),('2022-03-28 15:45:38', '2022-04-04 15:45:38', 0, 2, 1),('2022-03-30 20:53:38', '2022-04-06 20:53:38', 0, 3, 2),('2022-03-30 20:55:08', '2022-04-16 20:55:08', 0, 3, 1),('2022-03-31 07:20:08', '2022-04-07 07:20:08', 0, 3, 6)""")

            // FILL `Order` TABLE
            db.execSQL("""INSERT INTO `order` (`reserved_date`, `end_date`, `is_paid`, `user_id`, `book_id`) VALUES('2022-03-29 20:50:11', '2022-04-08 20:50:11', 0, 1, 3),('2022-03-16 11:00:11', '2022-03-23 11:00:11', 1, 1, 4),('2022-03-14 20:51:20', '2022-03-21 20:51:20', 1, 1, 7),('2022-03-25 08:00:00', '2022-04-07 08:00:00', 1, 1, 7),('2022-03-17 20:52:39', '2022-04-12 13:25:20', 1, 2, 5),('2022-03-23 18:30:39', '2022-04-10 18:30:39', 1, 2, 1),('2022-03-28 15:45:38', '2022-04-04 15:45:38', 0, 2, 1),('2022-03-30 20:53:38', '2022-04-06 20:53:38', 0, 3, 2),('2022-03-30 20:55:08', '2022-04-16 20:55:08', 0, 3, 1),('2022-03-31 07:20:08', '2022-04-07 07:20:08', 0, 3, 6)""")
        }
    }
}

fun ImageView.loadImage(width: Int, height: Int, url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(width,height)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object:Callback{
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) { }
        })
}

@BindingAdapter("android:widthImageUrl","android:heightImageUrl","android:imageUrl", "android:progressBar")
fun loadPhotoURL(view: ImageView, width: Int, height: Int, url:String?, pb:ProgressBar){
    if(url != null){
        view.loadImage(width, height, url,pb)
    }
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