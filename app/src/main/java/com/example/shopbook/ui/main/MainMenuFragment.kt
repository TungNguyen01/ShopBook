package com.example.shopbook.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
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
//        loadFragment(HomeFragment())
//        binding.navigation.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.menu_home -> {
//                    loadFragment(HomeFragment())
//                    true
//                }
//                R.id.menu_search -> {
//                    loadFragment(SearchFragment())
//                    true
//                }
//                R.id.menu_wishlist -> {
//                    loadFragment(WishlistFragment())
//                    true
//                }
//                else -> {
//                    loadFragment(ShoppingbagFragment())
//                    true
//                }
//            }
//        }
//        val adapter = ViewPagerAdapter(
//            requireActivity().supportFragmentManager,
//            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
//        )
        val fragments = listOf(
            HomeFragment(),
            SearchFragment(),
            WishlistFragment(),
            ShoppingbagFragment(),
        )
        val adapter=ViewPagerAdapter(requireActivity(), fragments)
        binding.viewPager.adapter = adapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> binding.navigation.menu.findItem(R.id.menu_home).isChecked = true
                    1 -> binding.navigation.menu.findItem(R.id.menu_search).isChecked = true
                    2 -> binding.navigation.menu.findItem(R.id.menu_wishlist).isChecked = true
                    3 -> binding.navigation.menu.findItem(R.id.menu_cart).isChecked = true
                }
            }
        })
//        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int,
//            ) {
//            }
//
//            override fun onPageSelected(position: Int) {
//                when (position) {
//                    0 -> binding.navigation.menu.findItem(R.id.menu_home).isChecked = true
//                    1 -> binding.navigation.menu.findItem(R.id.menu_search).isChecked = true
//                    2 -> binding.navigation.menu.findItem(R.id.menu_wishlist).isChecked = true
//                    3 -> binding.navigation.menu.findItem(R.id.menu_cart).isChecked = true
////                    0 -> loadFragment(HomeFragment())
////                    1 -> loadFragment(SearchFragment())
////                    2 -> loadFragment(WishlistFragment())
////                    3 -> loadFragment(ShoppingbagFragment())
//                }
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//            }
//        })

        binding.apply {
            navigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> viewPager.currentItem = 0
                    R.id.menu_search -> viewPager.currentItem = 1
                    R.id.menu_wishlist -> viewPager.currentItem = 2
                    R.id.menu_cart -> viewPager.currentItem = 3
                }
                false
            }
        }
    }

//    private fun loadFragment(fragment: Fragment) {
//        val transaction = parentFragmentManager.beginTransaction()
//        transaction.replace(R.id.container, fragment)
//        transaction.commit()
//    }
}