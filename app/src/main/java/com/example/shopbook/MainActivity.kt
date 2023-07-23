package com.example.shopbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.shopbook.ui.main.adapter.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_nav)
        viewPager = findViewById(R.id.viewPager)


        val adapter = ViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottomNavigationView.menu.findItem(R.id.mHome).isChecked = true
                    1 -> bottomNavigationView.menu.findItem(R.id.mSearch).isChecked = true
                    2 -> bottomNavigationView.menu.findItem(R.id.mWishList).isChecked = true
                    3 -> bottomNavigationView.menu.findItem(R.id.mBag).isChecked = true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mHome -> viewPager.currentItem = 0
                R.id.mSearch -> viewPager.currentItem = 1
                R.id.mWishList -> viewPager.currentItem = 2
                R.id.mBag -> viewPager.currentItem = 3
            }
            true
        }
    }
}