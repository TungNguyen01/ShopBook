package com.example.shopbook.ui.main.search

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.R
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.FragmentSearchBinding
import com.example.shopbook.ui.adapter.BookAdapter
import com.example.shopbook.ui.adapter.HistorySeachAdapter
import com.example.shopbook.utils.ItemSpacingDecoration
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.example.shopbook.utils.MySharedPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: BookAdapter
    private var bookList = mutableListOf<Product>()
    private var currentPage = 1
    private var lastPosition = 0
    private var totalPosition = 0
    private var currentPosition = 0
    private var pastPage = -1
    private var filterType: Int = 1
    private var queryString: String = ""
    private var priceSort: String = "asc"
    private var checkAsc: Boolean = true
    private lateinit var layoutManager: GridLayoutManager
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
        adapter = BookAdapter()
        adapter.clearData()
        loadData(filterType, queryString, priceSort, 0)
        viewModel.getSearchProducts(10, currentPage, 100, queryString, filterType, priceSort)
        observeProducts()
        when (filterType) {
            1 -> binding?.textProductNew?.let { setTextColor(it, "blue") }
            2 -> binding?.textProdcutSelling?.let { setTextColor(it, "blue") }
            3 -> binding?.textProductPriceSort?.let { setTextColor(it, "blue") }
        }
        Log.d("FILTERTYPE", filterType.toString())
        Log.d("CURRENTPAGE", currentPage.toString())
        if (checkAsc) {
            binding?.imagePriceSort?.setImageResource(R.drawable.ic_incre)
        } else {
            binding?.imagePriceSort?.setImageResource(R.drawable.ic_discre)
        }
        val horizontalSpacing =
            resources.getDimensionPixelSize(R.dimen.horizontal_spacing)
        val verticalSpacing =
            resources.getDimensionPixelSize(R.dimen.vertical_spacing)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.VISIBLE
        binding?.apply {
            val adapterHistory = HistorySeachAdapter()
            editSearch.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    groupHistorySearch.visibility = View.VISIBLE
                    groupSearch.visibility = View.INVISIBLE
                    viewModel.getSearchHistory()
                    viewModel.productHistoryList.observe(viewLifecycleOwner, Observer {
                        adapterHistory.setData(it)
                        recyclerHistorySearch.layoutManager=LinearLayoutManager(context)
                        recyclerHistorySearch.adapter=adapterHistory
                    })

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
                    val queryString=editSearch.text.toString()
                    if (queryString.isEmpty()) {
                        textTitleSearch.visibility = View.VISIBLE
                        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        textTitleSearch.layoutParams = layoutParams
                        //7/8
                        groupHistorySearch.visibility = View.VISIBLE
                        groupSearch.visibility = View.INVISIBLE
                    } else {
                        textTitleSearch.visibility = View.INVISIBLE
                        layoutParams.height = 0
                        textTitleSearch.layoutParams = layoutParams
                    }
                    //7/8
                    imageSeach.setOnClickListener {
                        Toast.makeText(context, queryString, Toast.LENGTH_SHORT).show()
                        viewModel.getSearchProducts(10, currentPage, 100, queryString, 0, "asc")
                        loadData(0, queryString, "asc", 0)
                        groupHistorySearch.visibility = View.INVISIBLE
                        groupSearch.visibility = View.VISIBLE
                    }
                }
            })
            textProductNew.setOnClickListener {
                adapter.clearData()
                binding?.loadingLayout?.root?.visibility = View.VISIBLE
                currentPage = 1
                pastPage = -1
                filterType = 1
                Log.d("FILTERTYPE1", filterType.toString())
                viewModel.getSearchProducts(
                    10,
                    currentPage,
                    100,
                    queryString,
                    filterType,
                    priceSort
                )
                loadData(filterType, queryString, priceSort, 1)
                setTextColor(textProductPriceSort, "black")
                setTextColor(textProductNew, "blue")
                setTextColor(textProdcutSelling, "black")
            }
            textProdcutSelling.setOnClickListener {
                adapter.clearData()
                binding?.loadingLayout?.root?.visibility = View.VISIBLE
                currentPage = 1
                pastPage = -1
                filterType = 2
                Log.d("FILTERTYPE2", filterType.toString())
                viewModel.getSearchProducts(10, currentPage, 100, "", filterType, "asc")
                loadData(filterType, queryString, priceSort,2)
                setTextColor(textProductPriceSort, "black")
                setTextColor(textProductNew, "black")
                setTextColor(textProdcutSelling, "blue")
            }
            linearProductPrice.setOnClickListener {
                adapter.clearData()
                binding?.loadingLayout?.root?.visibility = View.VISIBLE
                currentPage = 1
                pastPage = -1
                filterType = 3
                Log.d("FILTERTYPE3", filterType.toString())
                if (checkAsc) {
                    priceSort = "asc"
                    checkAsc = false
                    imagePriceSort.setImageResource(R.drawable.ic_incre)
                } else {
                    priceSort = "desc"
                    checkAsc = true
                    imagePriceSort.setImageResource(R.drawable.ic_discre)
                }
                viewModel.getSearchProducts(10, currentPage, 100, "", filterType, priceSort)
                loadData(filterType, queryString, priceSort,3)
                setTextColor(textProductPriceSort, "blue")
                setTextColor(textProductNew, "black")
                setTextColor(textProdcutSelling, "black")
            }
            layoutManager = GridLayoutManager(context, 2)
            recyclerProduct.layoutManager = layoutManager
            binding?.recyclerProduct?.adapter = adapter
            recyclerProduct.addItemDecoration(
                ItemSpacingDecoration(
                    horizontalSpacing,
                    verticalSpacing
                )
            )
        }
    }

    private fun addItemToCart() {
        adapter.setAddItemToCart(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = adapter.getBook(position)
                viewModel.addItemToCart(product.product_id)
                Toast.makeText(context, "ADD ITEM TO CART SUCCESSFUL", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observeProducts() {
        viewModel.productList.observe(viewLifecycleOwner, Observer { productList ->
            if (productList != null) {
                if (pastPage != currentPage) {
                    bookList.addAll(productList)
                }
                adapter.setData(bookList)
                binding?.loadingLayout?.root?.visibility = View.INVISIBLE
                addItemToCart()
                navToProductDetail()
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
                pastPage = currentPage
            }
        })
    }

    private fun loadData(filterType: Int, queryString: String, priceSort: String, x:Int) {
        Log.d("FILTERRRR", x.toString())
        Log.d("LOADFILTER", filterType.toString())
        Log.d("CURRENTPAGEFILTER", currentPage.toString())
        binding?.apply {
            recyclerProduct.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    lastPosition =
                        (recyclerProduct.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    totalPosition = adapter.itemCount
//                    val visibleItemCount = layoutManager.childCount
//                    val totalItemCount = layoutManager.itemCount;
//                    val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
//                    Log.d("LoadCurrentPAGE", currentPage.toString())
//                    Log.d("LoadPASTPAGE", pastPage.toString())
//                    Log.d("LoadPRICESORT", priceSort)

                    Log.d("LoadFilter", filterType.toString())
//                    Log.d("LASTPOSITION", lastPosition.toString())
//                    Log.d("TOTALPOSITION", totalPosition.toString())
                    if (currentPage != lastPosition && lastPosition == totalPosition - 3) {
                        currentPage++
                        viewModel.getSearchProducts(
                            10,
                            currentPage,
                            100,
                            queryString,
                            filterType,
                            priceSort,
                        )
                        currentPosition = lastPosition
                    }
                }
            })
        }
    }

    private fun setTextColor(text: TextView, color: String) {
        when (color) {
            "black" -> text.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorsearch
                )
            )
            "blue" -> text.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.status
                )
            )
        }
    }
}