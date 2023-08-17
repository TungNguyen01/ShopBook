package com.example.shopbook.ui.publisher

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.R
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.FragmentPublisherBinding
import com.example.shopbook.ui.adapter.BookAdapter

import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.main.adapter.NXBAdapter

import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.example.shopbook.utils.ItemSpacingDecoration


class PublisherFragment : Fragment() {
    companion object {
        fun newInstance() = PublisherFragment()
    }

    private lateinit var viewModel: PublisherViewModel
    private var binding: FragmentPublisherBinding? = null
    private lateinit var gridadapter: NXBAdapter
    private lateinit var listadapter : NXBAdapter
    private var isGridLayoutManager = true
    private var bookList = mutableListOf<Product>()
    private var currentPage = 1
    private var lastPosition = 0
    private var totalPosition = 0
    private var currentPosition = 0
    private var pastPage = -1
    private val searchHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublisherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPublisherBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridadapter = NXBAdapter(true)
        listadapter = NXBAdapter(false)
        initViewModel()
        val supplyId = arguments?.getString("publisherId")?.toInt()
     //   val supplyName=arguments?.getString("publisherName")
        supplyId?.let {
            viewModel.getSuplly(it, 10, currentPage, 100)
        }
        val vertical = resources.getDimensionPixelSize(R.dimen.vertical)
        val horizontal = resources.getDimensionPixelSize(R.dimen.horizontal)
        val horizontalSpacing =
            resources.getDimensionPixelSize(R.dimen.horizontal_spacing)
        val verticalSpacing =
            resources.getDimensionPixelSize(R.dimen.vertical_spacing)
        binding?.apply {
          //  textCategory.text = supplyName
            searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    // task HERE
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty()) {
                        currentPage = 1
                        supplyId?.let { supplyId ->
                            viewModel.getSuplly(supplyId, 10, 1, 100)
                        }
                        loadingLayout.root.visibility = View.VISIBLE
                    } else {
                        val delayMillis = 300L
                        searchHandler.removeCallbacksAndMessages(null)
                        searchHandler.postDelayed({
                            supplyId?.let {
                                viewModel.getSearchSupply(it, 1, newText)
                            }
                        }, delayMillis)
                    }
                    return false;
                }
            })
            layoutCategory.setOnTouchListener { view, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    val event =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    event.hideSoftInputFromWindow(requireView().windowToken, 0)
                }
                false
            }
            if(isGridLayoutManager) {
                recyclerCategory.addItemDecoration(
                    ItemSpacingDecoration(
                        horizontalSpacing,
                        verticalSpacing
                    )
                )
            }
            if(isGridLayoutManager == true) {
                recyclerCategory.layoutManager = GridLayoutManager(context, 2)
                recyclerCategory.adapter = gridadapter
            }else{
                recyclerCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                recyclerCategory.adapter = listadapter
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageChange.setOnClickListener {
                isGridLayoutManager = !isGridLayoutManager
                if(isGridLayoutManager){
                    recyclerCategory.layoutManager = GridLayoutManager(context, 2)
                    recyclerCategory.adapter = gridadapter
                    gridadapter.setData(bookList)
                }else{
                    recyclerCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    recyclerCategory.adapter = listadapter
                    listadapter.setData(bookList)
                }
            }
        }
        binding?.apply {
            recyclerCategory.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    lastPosition =
                        if(isGridLayoutManager){
                            (recyclerCategory.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                        }else{
                            (recyclerCategory.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                        }
                    totalPosition = if(isGridLayoutManager){
                        gridadapter.itemCount
                    }else {
                        listadapter.itemCount
                    }
                    if (lastPosition != currentPosition && ((lastPosition == totalPosition - 3 && totalPosition % 2 == 0) || (lastPosition == totalPosition - 2 && totalPosition % 2 != 0))) {
                        currentPage++
                        if (supplyId != null) {
                            viewModel.getSuplly(supplyId, 10, currentPage, 100)
                        }
                        currentPosition = lastPosition
                    }
                }
            })
        }
    }
    private fun navToProductDetail() {
        if(isGridLayoutManager){
        gridadapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val productFragment = ProductdetailFragment()
                val product = gridadapter.getBook(position)
                val bundle = Bundle()
                bundle.putString("bookId", product.product_id.toString())
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, productFragment.apply { arguments = bundle })
                    .addToBackStack("PublisherFragment")
                    .commit()
                pastPage = currentPage
            }
        })}else{
            listadapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val productFragment = ProductdetailFragment()
                    val product = listadapter.getBook(position)
                    val bundle = Bundle()
                    bundle.putString("bookId", product.product_id.toString())
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, productFragment.apply { arguments = bundle })
                        .addToBackStack("PublisherFragment")
                        .commit()
                    pastPage = currentPage
                }
            })
        }
    }

    private fun addItemToCart() {
        if(isGridLayoutManager){
        gridadapter.setAddItemToCart(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = gridadapter.getBook(position)

                viewModel.addItemToCart(product.product_id)
                Toast.makeText(context, "ADD ITEM TO CART SUCCESSFUL", Toast.LENGTH_SHORT).show()
            }
        })} else{
            listadapter.setAddItemToCart(object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val product = listadapter.getBook(position)

                    viewModel.addItemToCart(product.product_id)
                    Toast.makeText(context, "ADD ITEM TO CART SUCCESSFUL", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun initViewModel() {
        viewModel.producList.observe(viewLifecycleOwner, Observer { state ->
            val isDefaultState = state.isDefaultState
            state.products?.let {
                if (pastPage != currentPage && isDefaultState) {
                    if (currentPage > 1) {
                        bookList.addAll(it)
                    } else {
                        bookList.clear()
                        bookList.addAll(it)
                    }
                } else if (!isDefaultState) {
                    bookList = it as MutableList<Product>
                }
                if(isGridLayoutManager) {
                    gridadapter.setData(bookList)
                }else{
                    listadapter.setData(bookList)
                }
                navToProductDetail()
                addItemToCart()
                binding?.loadingLayout?.root?.visibility = View.INVISIBLE
            }
        })
    }
}
