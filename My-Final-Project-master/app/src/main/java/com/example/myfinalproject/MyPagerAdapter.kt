package com.example.myfinalproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class MyPagerAdapter : FragmentStatePagerAdapter {
    private var mFragments: ArrayList<Fragment>? = null

    constructor(fm: FragmentManager) : super(fm) {}

    override fun getItem(i: Int): Fragment {
        return mFragments!![i]
    }

    override fun getCount(): Int {
        return mFragments!!.size
    }

    constructor(
        fa: FragmentManager?,
        fragments: ArrayList<Fragment>?,
        fm: FragmentManager?
    ) : super(fm!!) {
        mFragments = fragments
    }
}