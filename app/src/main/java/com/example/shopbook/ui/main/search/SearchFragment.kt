package com.example.shopbook.ui.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopbook.R
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.FragmentSearchBinding
import com.example.shopbook.ui.adapter.BookAdapter
import com.example.shopbook.ui.adapter.HistorySeachAdapter
import com.example.shopbook.ui.adapter.ItemSpacingDecoration
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: BookAdapter
    private var bookList = mutableListOf<Product>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loadingLayout?.root?.visibility=View.VISIBLE
        viewModel.getAllProducts()
        observeProducts()
        val horizontalSpacing =
            resources.getDimensionPixelSize(R.dimen.horizontal_spacing)
        val verticalSpacing =
            resources.getDimensionPixelSize(R.dimen.vertical_spacing)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.VISIBLE
        binding?.apply {
            recyclerProduct.layoutManager = GridLayoutManager(context, 2)
            recyclerProduct.addItemDecoration(
                ItemSpacingDecoration(
                    horizontalSpacing,
                    verticalSpacing
                )
            )
            textProductNew.setOnClickListener {
//                searchNewProduct()
                Toast.makeText(context, "?????", Toast.LENGTH_SHORT).show()
            }
            editSearch.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    groupHistorySearch.visibility = View.VISIBLE
                    groupSearch.visibility = View.INVISIBLE
//                    val historyList = viewModel.getProducts()
//                    val adapterHistory = HistorySeachAdapter(historyList)
//                    recyclerHistorySearch.layoutManager = LinearLayoutManager(context)
//                    recyclerHistorySearch.adapter = adapterHistory
                } else {
                }
            }

            editSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int,
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun afterTextChanged(editable: Editable) {
                    val layoutParams = textTitleSearch.layoutParams
                    if (editSearch.text.isEmpty()) {
                        textTitleSearch.visibility = View.VISIBLE
                        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        textTitleSearch.layoutParams = layoutParams
                    } else {
                        textTitleSearch.visibility = View.INVISIBLE
                        layoutParams.height = 0
                        textTitleSearch.layoutParams = layoutParams
                    }
                }
            })
        }
    }

    private fun searchNewProduct() {
        viewModel.getSearchNewProduct()
        viewModel.productList.observe(viewLifecycleOwner, Observer { productList ->
            if (productList != null) {
                adapter = BookAdapter(productList)
                binding?.recyclerProduct?.adapter = adapter
                navToProductDetail()
            } else {
                Log.d("NULLLL", "HEllo")
            }
        })
    }

    private fun observeProducts() {
        viewModel.productList.observe(viewLifecycleOwner, Observer { productList ->
            if (productList != null) {
                adapter = BookAdapter(productList)
                binding?.recyclerProduct?.adapter = adapter
                binding?.loadingLayout?.root?.visibility=View.INVISIBLE
                navToProductDetail()
            } else {
                Log.d("NULLLL", "HEllo")
            }
        })
    }

    private fun navToProductDetail() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = adapter.getBook(position)
                val bundle = Bundle()
                bundle.putString("bookId", product.product_id.toString())
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.frame_layout,
                        ProductdetailFragment().apply { arguments = bundle })
                    .addToBackStack("SearchFragment")
                    .commit()
            }
        })
    }
}

