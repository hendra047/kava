package com.ubaya.kava.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.kava.model.Book
import com.ubaya.kava.model.GlobalData
import com.ubaya.kava.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailBookViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val bookLiveData = MutableLiveData<Book>()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun addBook(booklist: List<Book>){
        launch {
            val db = buildDb(getApplication())
            db.bookDao().insertAllBook(*booklist.toTypedArray())
        }
    }

    fun fetch(bookID: Int) {
        launch {
            val db = buildDb(getApplication())
            bookLiveData.value =  db.bookDao().selectBook(bookID)
        }
    }

    fun update(id:Int, title:String, subtitle:String, bookNumber:String, pages:Int, language:String, author:String, publisher:String, description:String, rating:Double, coverUrl:String){
        launch{
            val db = buildDb(getApplication())
            db.bookDao().update(id,title,subtitle,bookNumber,pages,language,author,publisher, description, rating, coverUrl)
        }
    }

}