package com.example.shopbook.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.shopbook.ui.main.home.HomeFragment
import com.example.shopbook.ui.main.search.SearchFragment
import com.example.shopbook.ui.main.shoppingbag.ShoppingbagFragment
import com.example.shopbook.ui.main.wishlist.WishlistFragment

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SearchFragment()
            2 -> WishlistFragment()
            3 -> ShoppingbagFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        return 4
    }
}