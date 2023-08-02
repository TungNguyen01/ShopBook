package com.example.shopbook.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentMainMenuBinding
import com.example.shopbook.ui.author.AuthorFragment
import com.example.shopbook.ui.category.CategoryBookFragment
import com.example.shopbook.ui.main.adapter.ViewPagerAdapter
import com.example.shopbook.ui.main.home.HomeFragment
import com.example.shopbook.ui.main.search.SearchFragment
import com.example.shopbook.ui.main.shoppingbag.ShoppingbagFragment
import com.example.shopbook.ui.main.wishlist.WishlistFragment
import com.example.shopbook.ui.order.orderdetail.OrderDetailFragment
import com.example.shopbook.ui.order.orderhistory.OrderHistoryFragment
import com.example.shopbook.ui.productdetail.ProductdetailFragment

class MainMenuFragment : Fragment() {
    private lateinit var binding: FragmentMainMenuBinding
//    private lateinit var viewPager: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
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
                    loadFragment(AuthorFragment())
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