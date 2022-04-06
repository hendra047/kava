package com.ubaya.ulib160419002.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.ulib160419002.R
import java.lang.Exception

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