package com.example.myfinalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myproject.resources.Home
import com.google.android.material.tabs.TabLayout
import java.util.*

class Main2Activity : AppCompatActivity() {
    //widgets
    private var mMyViewPager: ViewPager? = null
    private var mTabLayout: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mMyViewPager = findViewById(R.id.view_pager)
        mTabLayout = findViewById(R.id.tab_layout)
        init()
    }

    private fun init() {
        val fragments =
            ArrayList<Fragment>()
        val homes =
            Home.homes
        for (home in homes) {
            val fragment = ViewPagerItemFragment.getInstance(home)
            fragments.add(fragment)
        }
        val pagerAdapter = MyPagerAdapter(supportFragmentManager, fragments)
        mMyViewPager!!.adapter = pagerAdapter
        mTabLayout!!.setupWithViewPager(mMyViewPager, true)
    }
}