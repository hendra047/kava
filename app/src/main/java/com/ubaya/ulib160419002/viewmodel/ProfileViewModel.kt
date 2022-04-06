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
import com.ubaya.ulib160419002.model.GlobalData
import com.ubaya.ulib160419002.model.User

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    val profileLiveData = MutableLiveData<User>()
    val TAG = "profileTag"
    private var queue: RequestQueue? = null

    fun fetch() {
        queue = Volley.newRequestQueue(getApplication())

        val url = "https://ubaya.fun/native/160419002/ulib/users.php?id=${GlobalData.userID}"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val sType = object : TypeToken<ArrayList<User>>() {}.type
                val result = Gson().fromJson<ArrayList<User>>(it, sType)

                profileLiveData.value = result[0]
                Log.d("profile_user", result.toString())
            },
            {
                Log.d("error_profile_user", it.message.toString())
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