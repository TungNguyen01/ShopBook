package com.example.shopbook.ui.main.wishlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.R

import com.example.shopbook.databinding.FragmentWishlistBinding
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.main.adapter.BagAdapter
import com.example.shopbook.ui.main.adapter.WishListAdapter
import com.example.shopbook.ui.main.shoppingbag.viewmodel.ShoppingbagViewModel
import com.example.shopbook.ui.main.wishlist.viewmodel.WishlistViewModel
import com.example.shopbook.ui.order.checkout.CheckOutFragment
import com.example.shopbook.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class WishlistFragment : Fragment() {
    private lateinit var viewModel: WishlistViewModel
    private lateinit var wishListAdapter: WishListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(WishlistViewModel::class.java)
        val binding = FragmentWishlistBinding.inflate(inflater, container, false)
       // viewModel.removeItemInWishList(productId)
        viewModel.getWishlist()
        wishListAdapter = WishListAdapter()
        viewModel.wishlist.observe(viewLifecycleOwner, {wishlist ->
            wishListAdapter.updateData(wishlist)
        })
        binding?.apply {
            imageProfile.setOnClickListener {
                val profileFragment = ProfileFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, profileFragment)
                    .addToBackStack("WishlistFragment")
                    .commit()
            }
        }
        binding.recyclerviewWishlist.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = wishListAdapter
            val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            addItemToBag()
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
                    val deletedItem = viewModel.wishlist.value?.get(position)

                    //Log.d("tungnguyen", deletedItem.toString())
                    deletedItem?.let {
                        viewModel.removeItemInWishList(it.product_id)
                        viewModel.getWishlist()
                        wishListAdapter.notifyDataSetChanged()
                    }
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeCallback)
            itemTouchHelper.attachToRecyclerView(this)
        }
        return binding.root
    }

    private fun addItemToBag(){
        wishListAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val additem = viewModel.wishlist.value?.get(position)
                Log.d("sontung", additem.toString())
                additem?.let {
                    viewModel.addItemToCart(it.product_id)
                    wishListAdapter.notifyDataSetChanged()
                }
                Toast.makeText(context, "OKOKOK", Toast.LENGTH_SHORT).show()
            }
        })
    }

}