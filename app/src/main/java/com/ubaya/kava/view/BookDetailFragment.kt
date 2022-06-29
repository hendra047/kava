package com.ubaya.kava.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.kava.R
import com.ubaya.kava.viewmodel.DetailBookViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [BookDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookDetailFragment : Fragment() {
    private lateinit var viewModel: DetailBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val bookID = BookDetailFragmentArgs.fromBundle(requireArguments()).bookID
            viewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
            viewModel.fetch(bookID)

            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.bookLiveData.observe(viewLifecycleOwner) {
            it?.let {
//                imageBookmark.setImageResource(if (it.bookmarked == 1) R.drawable.ic_baseline_bookmark_24 else R.drawable.ic_outline_bookmark_border_24)
//                imageCover.loadImage(300, 400, it.coverUrl)
//                imageCoverBG.loadImage(400, 400, it.coverUrl)
                textTitle.text = it.title
                textSubtitle.text = it.subtitle
                textRating.text = it.rating.toString()
                textBookNo.text = it.bookNumber
                textPages.text = it.pages.toString()
                textAuthor.text = it.author
                textPublisher.text = it.publisher
                textLanguage.text = it.language
                textDescription.text = it.description
            }
        }
    }
}