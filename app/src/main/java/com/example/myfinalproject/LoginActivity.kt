package com.example.myfinalproject

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_password_recovery.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    lateinit var providers: List<AuthUI.IdpConfig>

    val MY_REQUEST_CODE: Int = 7117

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mTvForgetPassword.setOnClickListener{
            startActivity(Intent(this,PasswordRecovery::class.java))
        }


        mBtnLogin.setOnClickListener {
            //start receiving data from the user
            var email = mEdtUsername.text.toString()
            var password = mEdtPassword.text.toString()
            var time = System.currentTimeMillis()

            var progress = ProgressDialog(this)
            progress.setTitle("Saving")
            progress.setMessage("Please Wait..")
            if (email.isEmpty() or password.isEmpty()) {
                Toast.makeText(this, "Please fill in all the inputs", Toast.LENGTH_LONG).show()
            } else {
                //Proceed to save the data into the database
                //create a child in the db
                var mychild = FirebaseDatabase.getInstance().reference.child("Login/$time")
                var data = UserLogin(email, password)
                progress.show()
                mychild.setValue(data).addOnCompleteListener { task ->
                    progress.dismiss()
                    if (task.isSuccessful) {
                        mEdtUsername.setText(null)
                        mEdtPassword.setText(null)

                        Toast.makeText(this, "Saving Successful", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Saving Failed", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }

        mTvFooter.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }


        //Init
        providers = Arrays.asList<AuthUI.IdpConfig>(

            AuthUI.IdpConfig.FacebookBuilder().build() //Facebook Login
        )

    }


    private fun showSignInOptions() {
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.AppTheme)
                .build(), MY_REQUEST_CODE
        )
    }


}