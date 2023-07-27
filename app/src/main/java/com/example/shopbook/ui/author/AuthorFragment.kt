package com.example.shopbook.ui.author

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopbook.R
import com.example.shopbook.ui.adapter.BookAdapter
import com.example.shopbook.databinding.FragmentAuthorBinding
import com.example.shopbook.ui.adapter.ItemSpacingDecoration
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.productdetail.ProductdetailFragment

class AuthorFragment : Fragment() {

    companion object {
        fun newInstance() = AuthorFragment()
    }

    private lateinit var viewModel: AuthorViewModel
    private var binding: FragmentAuthorBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAuthorBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookList = viewModel.getProducts()
        val adapter = BookAdapter(bookList)
        val horizontalSpacing =
            resources.getDimensionPixelSize(R.dimen.horizontal_spacing)
        val verticalSpacing =
            resources.getDimensionPixelSize(R.dimen.vertical_spacing)
        binding?.apply {
            searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    // task HERE
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty()) {
                        textAuthor.visibility = View.VISIBLE;
                    } else {
                        val layoutParams =
                            searchProduct.layoutParams as ViewGroup.MarginLayoutParams
                        val newMarginTopInDp = 12
                        val newMarginTopInPx = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, newMarginTopInDp.toFloat(),
                            resources.displayMetrics
                        ).toInt()
                        layoutParams.topMargin = newMarginTopInPx
                        searchProduct.layoutParams = layoutParams
                        textAuthor.visibility = View.GONE;
                    }
                    return false;
                }
            })
            recyclerAuthor.addItemDecoration(
                ItemSpacingDecoration(
                    horizontalSpacing,
                    verticalSpacing
                )
            )
            recyclerAuthor.layoutManager = GridLayoutManager(context, 2)
            recyclerAuthor.adapter = adapter
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val productFragment = ProductdetailFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, productFragment)
                    .addToBackStack("AuthorFragment")
                    .commit()
            }
        })
    }
}