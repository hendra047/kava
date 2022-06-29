package com.ubaya.kava.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.ubaya.kava.model.Book
import com.ubaya.kava.model.KavaDatabase
import com.ubaya.kava.util.DB_NAME
import com.ubaya.kava.util.buildDb
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ListBookViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val booksLD = MutableLiveData<List<Book>>()
    val booksLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        booksLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            val db = buildDb(getApplication())

            booksLD.value = db.bookDao().selectAllBook()
            loadingLD.value = false
            Log.d("coba", db.bookDao().selectAllBook().count().toString())
        }
    }

    fun removeBook(todo: Book) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                KavaDatabase::class.java, DB_NAME).build()

            db.bookDao().deleteBook(todo)
            booksLD.value = db.bookDao().selectAllBook()
        }
    }
}