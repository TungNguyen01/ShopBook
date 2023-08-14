package com.example.shopbook.ui.main.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.R
import com.example.shopbook.data.model.HistorySearch
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.FragmentSearchBinding
import com.example.shopbook.datasource.local.db.entity.ProductDb
import com.example.shopbook.ui.adapter.BookAdapter
import com.example.shopbook.ui.adapter.HistorySeachAdapter
import com.example.shopbook.utils.ItemSpacingDecoration
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.example.shopbook.utils.MySharedPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.internal.notify
import kotlin.math.min

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: BookAdapter
    private lateinit var adapterHistory: HistorySeachAdapter
    private var bookList = mutableListOf<Product>()
    private var list = mutableListOf<HistorySearch>()
    private var currentPage = 1
    private var idCustomer = 0
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
        viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(requireActivity().application)
        )[SearchViewModel::class.java]
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BookAdapter()
        adapterHistory = HistorySeachAdapter()
        adapter.clearData()
        initViewModel()

        idCustomer = MySharedPreferences.getInt("idCustomer", 0)
        viewModel.getSearchProducts(10, currentPage, 100, queryString, filterType, priceSort)
        when (filterType) {
            1 -> binding?.textProductNew?.let { setTextColor(it, "blue") }
            2 -> binding?.textProdcutSelling?.let { setTextColor(it, "blue") }
            3 -> binding?.textProductPriceSort?.let { setTextColor(it, "blue") }
        }
        if (checkAsc) {
            binding?.imagePriceSort?.setImageResource(R.drawable.ic_incre)
        } else {
            binding?.imagePriceSort?.setImageResource(R.drawable.ic_discre)
        }
        val horizontalSpacing = resources.getDimensionPixelSize(R.dimen.horizontal_spacing)
        val verticalSpacing = resources.getDimensionPixelSize(R.dimen.vertical_spacing)
        binding?.apply {
            editSearch.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    groupHistorySearch.visibility = View.VISIBLE
                    groupSearch.visibility = View.INVISIBLE
                    viewModel.getHistorySearchLocal(idCustomer)
                    textRemoveAll.visibility = View.INVISIBLE
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
                    val newText = editSearch.text.toString()
                    if (newText.isEmpty()) {
                        viewModel.getHistorySearchLocal(idCustomer)
                        textTitleSearch.visibility = View.VISIBLE
                        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        textTitleSearch.layoutParams = layoutParams
                    } else {
                        viewModel.getSearchHistory(newText)
                        textTitleSearch.visibility = View.INVISIBLE
                        layoutParams.height = 0
                        textTitleSearch.layoutParams = layoutParams
                    }
                }
            })
            imageSeach.setOnClickListener {
                val query = editSearch.text.toString()
                if (!query.isEmpty()) {
                    viewModel.insertHistorySearchLocal(
                        ProductDb(
                            idCustomer = idCustomer,
                            productName = query
                        )
                    )
                }
                currentPage = 1
                pastPage = -1
                viewModel.getSearchProducts(10, 1, 100, query, filterType, priceSort)
                val inputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(editSearch.windowToken, 0)
                editSearch.clearFocus()
                groupHistorySearch.visibility = View.INVISIBLE
                groupSearch.visibility = View.VISIBLE
            }
            textRemoveAll.setOnClickListener {
                viewModel.deleteHistorySearchLocal()
                list.clear()
                adapterHistory.clearData()
                textRemoveAll.visibility = View.INVISIBLE
            }
            textProductNew.setOnClickListener {
                adapter.clearData()
                binding?.loadingLayout?.root?.visibility = View.VISIBLE
                currentPage = 1
                pastPage = -1
                filterType = 1
                viewModel.getSearchProducts(
                    10, currentPage, 100, queryString, filterType, priceSort
                )
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
                viewModel.getSearchProducts(10, currentPage, 100, "", filterType, "asc")
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
                setTextColor(textProductPriceSort, "blue")
                setTextColor(textProductNew, "black")
                setTextColor(textProdcutSelling, "black")
            }
            //
            recyclerHistorySearch.layoutManager = LinearLayoutManager(context)
            recyclerHistorySearch.adapter = adapterHistory
            //
            layoutManager = GridLayoutManager(context, 2)
            recyclerProduct.layoutManager = layoutManager
            recyclerProduct.adapter = adapter
            recyclerProduct.addItemDecoration(
                ItemSpacingDecoration(
                    horizontalSpacing, verticalSpacing
                )
            )
            layoutSearch.setOnTouchListener { view, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    val event =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    event.hideSoftInputFromWindow(requireView().windowToken, 0)
                }
                false
            }
            floatButton.setOnClickListener {
                recyclerProduct.scrollToPosition(0)
                floatButton.visibility=View.INVISIBLE
            }
        }
        binding?.apply {
            recyclerProduct.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    lastPosition =
                        (recyclerProduct.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    totalPosition = adapter.itemCount
                    if (lastPosition > 20) {
                        floatButton.visibility=View.VISIBLE
                    }else{
                        floatButton.visibility=View.INVISIBLE
                    }
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

    private fun initViewModel() {
//        binding?.apply {
//            viewModel.productHistoryList.observe(viewLifecycleOwner, Observer {
//                adapterHistory.setData(it)
//                recyclerHistorySearch.layoutManager = LinearLayoutManager(context)
//                recyclerHistorySearch.adapter = adapterHistory
//            })
//        }
        viewModel.productList.observe(viewLifecycleOwner) { state ->
            val isDefaultState = state.isDefaultState
            state.products.let {
                if (pastPage != currentPage && isDefaultState) {
                    it?.let { productList ->
                        if (currentPage > 1) {
                            bookList.addAll(productList)
                        } else {
                            bookList.clear()
                            bookList.addAll(productList)
                        }
                    }
                } else if (!isDefaultState) {
                    bookList = it as MutableList<Product>
                }
                adapter.setData(bookList)
                binding?.loadingLayout?.root?.visibility = View.INVISIBLE
                addItemToCart()
                navToProductDetail()
            }
        }
        viewModel.historyList.observe(viewLifecycleOwner) {
            list.clear()
            for (historyLocal in it.reversed()) {
                list.add(HistorySearch(historyLocal, null))
            }
            if (list.size > 0) {
                binding?.textRemoveAll?.visibility = View.VISIBLE
            }
            adapterHistory.setData(list)
            searchLocalProduct()
            clickRemoveHistory()
        }
        viewModel.productNameList.observe(viewLifecycleOwner) {
            list.clear()
            for (product in it) {
                list.add(HistorySearch(null, product))
            }
            adapterHistory.setData(list)
            binding?.textRemoveAll?.visibility = View.INVISIBLE
            searchSuggestProduct()
        }
    }

    private fun addItemToCart() {
        adapter.setAddItemToCart(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = adapter.getBook(position)
                viewModel.addItemToCart(product.product_id)
                Toast.makeText(
                    context, "ADD ITEM TO CART SUCCESSFUL", Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun navToProductDetail() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = adapter.getBook(position)
                val bundle = Bundle()
                bundle.putString("bookId", product.product_id.toString())
                parentFragmentManager.beginTransaction().replace(R.id.container,
                    ProductdetailFragment().apply { arguments = bundle })
                    .addToBackStack("SearchFragment").commit()
                pastPage = currentPage
            }
        })
    }

    private fun searchSuggestProduct() {
        adapterHistory.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = adapterHistory.getBook(position)
                viewModel.insertHistorySearchLocal(
                    ProductDb(
                        idCustomer = idCustomer,
                        productName = product?.name.toString()
                    )
                )
                val bundle = Bundle()
                bundle.putString("bookId", product?.product_id.toString())
                parentFragmentManager.beginTransaction().replace(R.id.container,
                    ProductdetailFragment().apply { arguments = bundle })
                    .addToBackStack("SearchFragment").commit()
            }
        })
    }

    private fun searchLocalProduct() {
        adapterHistory.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val productName = adapterHistory.getProductNameLocal(position)
                currentPage = 1
                pastPage = -1
                productName?.let {
                    viewModel.getSearchProducts(
                        10, 1, 100,
                        it, filterType, priceSort
                    )
                }
                val inputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                binding?.apply {
                    editSearch.setText(productName)
                    inputMethodManager.hideSoftInputFromWindow(editSearch.windowToken, 0)
                    editSearch.clearFocus()
                    groupHistorySearch.visibility = View.INVISIBLE
                    groupSearch.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun clickRemoveHistory() {
        adapterHistory.clickRemoveItem(object : OnItemClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemClick(position: Int) {
                val productName = adapterHistory.getProductNameLocal(position)
                productName?.let { viewModel.removeItemHistorySearchLocal(it) }
                list.removeAt(position)
                adapterHistory.removeData(position)
                if (list.size == 0) {
                    binding?.textRemoveAll?.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun setTextColor(text: TextView, color: String) {
        when (color) {
            "black" -> text.setTextColor(
                ContextCompat.getColor(
                    requireContext(), R.color.colorsearch
                )
            )
            "blue" -> text.setTextColor(
                ContextCompat.getColor(
                    requireContext(), R.color.status
                )
            )
        }
    }
}