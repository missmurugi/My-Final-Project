package com.example.myfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_password_recovery.*
import kotlinx.android.synthetic.main.activity_recovery_successful.*

class RecoverySuccessful : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery_successful)

        mBtnResetSuccessful.setOnClickListener(View.OnClickListener {
                View -> load()

        })

    }

    private  fun load(){
        startActivity(Intent(this,LoginActivity::class.java))
    }
}
