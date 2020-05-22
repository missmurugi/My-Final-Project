package com.example.myfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myproject.resources.Home
import kotlinx.android.synthetic.main.activity_makazi_dashboard.*

class MakaziDashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makazi_dashboard)

        mPlaces.setOnClickListener(View.OnClickListener {
                View -> place()
        })

    }

    private fun place(){
        startActivity(Intent(this,ViewPagerItemFragment::class.java))
    }



}
