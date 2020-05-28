package com.example.myfinalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import com.example.myfinalproject.ui.swipeview.SwipeViewFragment
import com.example.myfinalproject.ui.swipeview.SwipeViewViewModel

class SwipeView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.swipe_view_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SwipeViewFragment.getInstance(SwipeViewViewModel.Home()))
                .commitNow()
        }
    }

    companion object {
        lateinit var fm: FragmentManager
        lateinit var pagerAdapter: PagerAdapter
    }
}
