package com.ubaya.ulib160419002.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ulib160419002.R
import com.ubaya.ulib160419002.model.Notification
import kotlinx.android.synthetic.main.notifications_item.view.*

class NotificationListAdapter(val notifications:ArrayList<Notification>) : RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder>() {
    class NotificationViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.notifications_item, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        with(holder.view) {
            textNotifTitle.text = notification.title
            notification.remainDays?.let {
                val value = if (it > 1) "$it days" else "$it day"
                textRemainDay.text = "Remaining days: $value"
            }
        }
    }

    override fun getItemCount() = notifications.size

    fun updateNotificationList(notificationList: ArrayList<Notification>) {
        notifications.clear()
        notifications.addAll(notificationList)
        notifyDataSetChanged()
    }
}