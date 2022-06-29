package com.ubaya.kava.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.kava.R
import com.ubaya.kava.viewmodel.ListBookViewModel
import com.ubaya.kava.viewmodel.NotificationViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.notifications_item.view.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var viewModel: ListBookViewModel
    private lateinit var viewModelNotification: NotificationViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf(), "Home")
    private var counter =  1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        Observable.timer(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModelNotification = ViewModelProvider(this).get(NotificationViewModel::class.java)
                viewModelNotification.refresh()

                viewModelNotification.notificationLiveData.observe(viewLifecycleOwner) {
                    if (it != null) {
                        for (notification in it) {
                            notification.remainDays?.let { remainDay ->
                                val value =
                                    if (remainDay > 1) "$remainDay days" else "$remainDay day"
                                notification.title?.let { title ->
                                    MainActivity.showNotification(
                                        counter,
                                        "Renew your subscription book \"$title\"",
                                        "Remaining days: $value",
                                        R.drawable.ic_baseline_library_books_24
                                    )
                                    counter++
                                }
                            }
                        }
                    }
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
            .apply {
                fabCreateBook.setOnClickListener {
                    val action = HomeFragmentDirections.actionCreateBookFragment()
                    Navigation.findNavController(this).navigate(action)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListBookViewModel::class.java)
        viewModel.refresh()

        recViewHome.layoutManager = LinearLayoutManager(context)
        recViewHome.adapter = bookListAdapter

        observeViewModel()

        refreshLayoutHome.setOnRefreshListener {
            recViewHome.visibility = View.GONE
            textErrorHome.visibility = View.GONE
            progressLoadHome.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutHome.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.booksLD.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }
        viewModel.booksLoadErrorLD.observe(viewLifecycleOwner) {
            textErrorHome.visibility = if (it) View.INVISIBLE else View.GONE
        }
        viewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it) { // sedang loading data
                recViewHome.visibility = View.GONE
                progressLoadHome.visibility = View.VISIBLE
            }
            else {
                recViewHome.visibility = View.VISIBLE
                progressLoadHome.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_top, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                super.onOptionsItemSelected(item)
    }
}