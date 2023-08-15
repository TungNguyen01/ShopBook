package com.example.shopbook.ui.main.shoppingbag

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.R
import com.example.shopbook.data.api.RetrofitClient.apiService
import com.example.shopbook.data.model.CartItemBag
import com.example.shopbook.databinding.FragmentShoppingBagBinding
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.main.adapter.BagAdapter
import com.example.shopbook.ui.main.shoppingbag.viewmodel.ShoppingbagViewModel
import com.example.shopbook.ui.order.checkout.CheckOutFragment
import com.example.shopbook.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class ShoppingbagFragment : Fragment() {
    private val cartList = mutableListOf<CartItemBag>()
    private lateinit var bookAdapter: BagAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ShoppingbagViewModel
    private lateinit var imgAdd : ImageView
    private lateinit var imgReduce : ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(ShoppingbagViewModel::class.java)
        val binding = FragmentShoppingBagBinding.inflate(inflater, container, false)


        viewModel.getCart()
        bookAdapter = BagAdapter()

        viewModel.cart.observe(viewLifecycleOwner, {cart ->
            bookAdapter.updateData(cart)
        })
        binding?.apply {
            imageProfile.setOnClickListener {
                val profileFragment = ProfileFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, profileFragment)
                    .addToBackStack("HomeFragment")
                    .commit()
            }
            textCheckout.setOnClickListener {
                val checkoutFragment = CheckOutFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, checkoutFragment)
                    .addToBackStack("HomeFragment")
                    .commit()
            }
            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing=false
                viewModel.getCart()
            }
           // textPrice.text =
        }

        binding.recyclerviewBag.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = bookAdapter
            addItem()
            reduceItem()
            val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val deletedItem = viewModel.cart.value?.get(position)

                    Log.d("tungnguyen", deletedItem.toString())
                    deletedItem?.let {
                        viewModel.deleteProduct(it.item_id)
                        viewModel.getCart()
                        bookAdapter.notifyDataSetChanged()
                    }
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeCallback)
            itemTouchHelper.attachToRecyclerView(this)
        }
        return binding.root
    }
        private fun addItem(){
            bookAdapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val additem = viewModel.cart.value?.get(position)
                    Log.d("sontung", additem.toString())
                    additem?.let {
                        viewModel.updateQuantity(it.item_id, it.quantity + 1)
                        viewModel.getCart()
                        bookAdapter.notifyDataSetChanged()
                    }
                    Toast.makeText(context, "OKOKOK", Toast.LENGTH_SHORT).show()
                }
            })
        }
    private fun reduceItem(){
        bookAdapter.setOnItemClickListener2(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val reduceitem = viewModel.cart.value?.get(position)
                reduceitem?.let {
                    viewModel.updateQuantity(it.item_id, it.quantity - 1)
                    viewModel.getCart()
                    bookAdapter.notifyDataSetChanged()
                }
                Toast.makeText(context, "OKOKOK", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


