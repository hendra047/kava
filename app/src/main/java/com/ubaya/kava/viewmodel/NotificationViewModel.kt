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
import com.ubaya.kava.model.GlobalData
import com.ubaya.kava.model.Notification

class NotificationViewModel(application: Application) : AndroidViewModel(application) {
    val notificationLiveData = MutableLiveData<ArrayList<Notification>>()
    val notificationLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "notificationTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        notificationLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())

        val url = "https://ubaya.fun/native/160419002/ulib/notifications.php?user_id=${GlobalData.username}"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val sType = object : TypeToken<ArrayList<Notification>>() {}.type
                val result = Gson().fromJson<ArrayList<Notification>>(it, sType)

                if (result != null) {
                    notificationLiveData.value = result
                    loadingLiveData.value = false
                    Log.d("notification", result.toString())
                }
            },
            {
                loadingLiveData.value = false
                notificationLoadErrorLiveData.value = true
                Log.d("error_notification", it.message.toString())
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