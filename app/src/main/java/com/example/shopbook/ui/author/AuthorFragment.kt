package com.example.shopbook.ui.author

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopbook.R
import com.example.shopbook.adapter.BookAdapter
import com.example.shopbook.databinding.FragmentAuthorBinding
import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Objects

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)


        binding?.searchProduct?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    binding?.textAuthor?.setVisibility(View.VISIBLE);
                } else {
                    val layoutParams = binding?.searchProduct?.layoutParams as ViewGroup.MarginLayoutParams
                    val newMarginTopInDp = 12
                    val newMarginTopInPx = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, newMarginTopInDp.toFloat(),
                        resources.displayMetrics
                    ).toInt()
                    layoutParams.topMargin = newMarginTopInPx
                    binding?.searchProduct?.layoutParams = layoutParams

                    binding?.textAuthor?.setVisibility(View.GONE);
                }
                return false;
            }
        })

        val bookList = viewModel.getProducts()
        val adapter = BookAdapter(bookList)
        binding?.recyclerAuthor?.layoutManager = GridLayoutManager(context, 2)
        binding?.recyclerAuthor?.adapter = adapter
        adapter.setOnItemClickListener(object : BookAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val productFragment = ProductdetailFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, productFragment)
                    .addToBackStack("AuthorFragment")
                    .commit()
            }
        })
        binding?.imageLeft?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

}