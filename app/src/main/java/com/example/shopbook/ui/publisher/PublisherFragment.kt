package com.example.shopbook.ui.publisher

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.R
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.FragmentCategoryBookBinding
import com.example.shopbook.databinding.FragmentPublisherBinding
import com.example.shopbook.ui.adapter.BookAdapter
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.category.CategoryBookFragment
import com.example.shopbook.ui.category.CategoryBookViewModel
import com.example.shopbook.ui.main.adapter.NXBAdapter
import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.example.shopbook.utils.ItemSpacingDecoration


class PublisherFragment : Fragment() {
    companion object {
        fun newInstance() = PublisherFragment()
    }

    private lateinit var viewModel: PublisherViewModel
    private var binding: FragmentPublisherBinding? = null
    private lateinit var adapter: BookAdapter
    private lateinit var layoutManager: GridLayoutManager
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
        adapter = BookAdapter()
        initViewModel()
        val supplyId = arguments?.getString("publisherId")?.toInt()
     //   val supplyName=arguments?.getString("publisherName")
        supplyId?.let {
            viewModel.getSuplly(it, 10, currentPage, 100)
        }
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
            recyclerCategory.addItemDecoration(
                ItemSpacingDecoration(
                    horizontalSpacing,
                    verticalSpacing
                )
            )
            layoutManager = GridLayoutManager(context, 2)
            recyclerCategory.layoutManager = layoutManager
            recyclerCategory.adapter = adapter
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
        binding?.apply {
            recyclerCategory.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    lastPosition =
                        (recyclerCategory.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    totalPosition = adapter.itemCount
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
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val productFragment = ProductdetailFragment()
                val product = adapter.getBook(position)
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

    private fun addItemToCart() {
        adapter.setAddItemToCart(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = adapter.getBook(position)
                viewModel.addItemToCart(product.product_id)
                Toast.makeText(context, "ADD ITEM TO CART SUCCESSFUL", Toast.LENGTH_SHORT).show()
            }
        })
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
                adapter.setData(bookList)
                navToProductDetail()
                addItemToCart()
                binding?.loadingLayout?.root?.visibility = View.INVISIBLE
            }
        })
    }
}
