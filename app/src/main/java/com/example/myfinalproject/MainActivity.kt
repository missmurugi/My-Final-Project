package com.example.myfinalproject;


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myfinalproject.LoginActivity
import com.example.myfinalproject.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hooks
        val first: View
        val second: View
        val third: View
        val fourth: View
        val fifth: View
        val sixth: View
        val makazi: TextView
        val slogan: TextView

        //Animations
        val topAnimation: Animation
        val bottomAnimation: Animation
        val middleAnimation: Animation


        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        middleAnimation = AnimationUtils.loadAnimation(this,R.anim.middle_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        //hooks
        first = findViewById(R.id.first_line)
        second = findViewById(R.id.second_line)
        third = findViewById(R.id.third_line)
        fourth = findViewById(R.id.fourth_line)
        fifth = findViewById(R.id.fifth_line)
        sixth = findViewById(R.id.sixth_line)

        makazi = findViewById(R.id.makazi)
        slogan = findViewById(R.id.tagLine)

        //assigning animations
        first.setAnimation(topAnimation)
        second.setAnimation(topAnimation)
        third.setAnimation(topAnimation)
        fourth.setAnimation(topAnimation)
        fifth.setAnimation(topAnimation)
        sixth.setAnimation(topAnimation)

        makazi.setAnimation(middleAnimation)

        slogan.setAnimation(bottomAnimation)

        //splash screen
        Handler().postDelayed({
            intent =Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },5000)

    }
}
