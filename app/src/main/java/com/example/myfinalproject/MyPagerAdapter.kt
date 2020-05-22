package com.example.myfinalproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class MyPagerAdapter(
    fm: FragmentManager?,
    private val mfragments: ArrayList<Fragment>
) : FragmentStatePagerAdapter(fm!!) {
    override fun getItem(i: Int): Fragment {
        return mfragments[i]
    }

    override fun getCount(): Int {
        return mfragments.size
    }

}