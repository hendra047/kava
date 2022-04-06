package com.ubaya.ulib160419002.viewmodel

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
import com.ubaya.ulib160419002.model.Book
import com.ubaya.ulib160419002.model.GlobalData

class DetailBookViewModel(application: Application) : AndroidViewModel(application) {
    val bookLiveData = MutableLiveData<Book>()
    val TAG = "detailBookTag"
    private var queue: RequestQueue? = null

    fun fetch(bookID: Int) {
        queue = Volley.newRequestQueue(getApplication())

        val url = "https://ubaya.fun/native/160419002/ulib/books.php?book_id=$bookID&user_id=${GlobalData.userID}"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val sType = object : TypeToken<ArrayList<Book>>() {}.type
                val result = Gson().fromJson<ArrayList<Book>>(it, sType)

                bookLiveData.value = result[0]
                Log.d("detail_book", result.toString())
            },
            {
                Log.d("error_detail_book", it.message.toString())
            }
        ).apply {
            tag = TAG
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}