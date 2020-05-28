package com.example.myfinalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myfinalproject.ui.swipeview.SwipeViewViewModel.Home
import com.google.android.material.tabs.TabLayout
import java.util.*

internal class Main2Activity : AppCompatActivity() {
    //widgets
    private var mViewPager: ViewPager? = null
    private var mTabLayout: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mViewPager = findViewById(R.id.view_pager)
        mTabLayout = findViewById(R.id.tab_layout)
        init()
    }

    private fun init() {
        val fragments =
            ArrayList<Fragment>()
        val homes: Homes = Homes
        for (home in homes) {
            val fragment = (home)
            fragments.add(fragment)
        }
        val myPagerAdapter =
            MyPagerAdapter(supportFragmentManager, fragments, SwipeView.fm)
        mViewPager!!.adapter = SwipeView.pagerAdapter
        mTabLayout!!.setupWithViewPager(mViewPager, true)
    }
}

private fun <E> ArrayList<E>.add(fragment: Home?) {
    TODO("Not yet implemented")
}
