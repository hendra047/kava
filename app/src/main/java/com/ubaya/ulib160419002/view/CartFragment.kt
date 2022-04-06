package com.ubaya.ulib160419002.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ulib160419002.R
import com.ubaya.ulib160419002.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {
    private lateinit var viewModel: CartViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf(), "Cart")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        viewModel.refresh()

        recViewCart.layoutManager = LinearLayoutManager(context)
        recViewCart.adapter = bookListAdapter

        observeViewModel()

        refreshLayoutCart.setOnRefreshListener {
            recViewCart.visibility = View.GONE
            textErrorCart.visibility = View.GONE
            progressLoadCart.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutCart.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.booksLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }
        viewModel.booksLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorCart.visibility = if (it) View.INVISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { // sedang loading data
                recViewCart.visibility = View.GONE
                progressLoadCart.visibility = View.VISIBLE
            }
            else {
                recViewCart.visibility = View.VISIBLE
                progressLoadCart.visibility = View.GONE
            }
        }
    }
}