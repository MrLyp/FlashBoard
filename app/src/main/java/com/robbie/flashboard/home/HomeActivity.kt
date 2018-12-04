package com.robbie.flashboard.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.robbie.flashboard.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var mViewPager : ViewPager

    private val mIndexMap = mapOf(0 to R.id.navigation_home, 1 to R.id.navigation_dashboard)

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        mViewPager.currentItem = item.order
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mViewPager = findViewById(R.id.view_pager)
        mViewPager.adapter = HomeFragmentAdapter(supportFragmentManager)
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                navigation.selectedItemId = mIndexMap.getOrDefault(p0, 0)
            }
        })
    }
}
