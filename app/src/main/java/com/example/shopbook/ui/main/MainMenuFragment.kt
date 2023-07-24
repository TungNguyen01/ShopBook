package com.example.shopbook.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentMainMenuBinding
import com.example.shopbook.ui.main.home.HomeFragment
import com.example.shopbook.ui.main.search.SearchFragment
import com.example.shopbook.ui.main.shoppingbag.ShoppingbagFragment
import com.example.shopbook.ui.main.wishlist.WishlistFragment
import com.example.shopbook.ui.productdetail.ProductdetailFragment

class MainMenuFragment : Fragment() {
    private lateinit var binding: FragmentMainMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadFragment(HomeFragment())
        binding.navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.menu_search -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.menu_wishlist -> {
                    loadFragment(WishlistFragment())
                    true
                }
                else -> {
                    loadFragment(ShoppingbagFragment())
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }
}