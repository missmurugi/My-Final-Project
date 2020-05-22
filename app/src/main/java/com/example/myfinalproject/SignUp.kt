package com.example.myfinalproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mBtnSignUp.setOnClickListener {

            //start receiving data from the user
            var fullname = mEdtFullName.text.toString()
            var username = mEdtUser.text.toString()
            var email = mEdtEmail.text.toString()
            var password = mEdtSigninPass.text.toString()
            var confirmpassword = mConfirmPass.text.toString()
            var time = System.currentTimeMillis()

            var progress = ProgressDialog(this)
            progress.setTitle("Saving")
            progress.setMessage("Please Wait")
            if (fullname.isEmpty() or username.isEmpty() or email.isEmpty() or password.isEmpty() or confirmpassword.isEmpty()) {
                Toast.makeText(this, "Please fill out all the inputs", Toast.LENGTH_LONG).show()
            } else {
                //Proceed to save the data in the database
                //create a child in the db
                var mychild = FirebaseDatabase.getInstance().reference.child("SignUp,$time")
                var data = UserSignUp(fullname, username, email, password, confirmpassword)
                //to save the data set the data to mychild
                startActivity(Intent(this, SignUp::class.java))
                progress.show()
                mychild.setValue(data).addOnCompleteListener { task ->
                    progress.dismiss()
                    if (task.isSuccessful) {
                        mEdtFullName.setText(null)
                        mEdtUser.setText(null)
                        mEdtEmail.setText(null)
                        mEdtSigninPass.setText(null)
                        mConfirmPass.setText(null)

                        Toast.makeText(this, "Saving Successful", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Saving Failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    }

