package com.ubaya.kava.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.kava.R
import com.ubaya.kava.viewmodel.NotificationViewModel
import kotlinx.android.synthetic.main.fragment_notification.*

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : Fragment() {
    private lateinit var viewModel: NotificationViewModel
    private val notificationsAdapter = NotificationListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        viewModel.refresh()

        recViewNotif.layoutManager = LinearLayoutManager(context)
        recViewNotif.adapter = notificationsAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.notificationLiveData.observe(viewLifecycleOwner) {
            notificationsAdapter.updateNotificationList(it)
        }
        viewModel.notificationLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorNotif.visibility = if (it) View.INVISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { // sedang loading data
                recViewNotif.visibility = View.GONE
                progressLoadNotif.visibility = View.VISIBLE
            }
            else {
                recViewNotif.visibility = View.VISIBLE
                progressLoadNotif.visibility = View.GONE
            }
        }
    }
}