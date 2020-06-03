package com.example.myfinalproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_password_recovery.*

class PasswordRecovery : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_recovery)

        mBtnReset.setOnClickListener {
            //start receiving data from the user
            var newpassword = mEdtNew.text.toString()
            var previouspassword = mEdtPrevious.text.toString()
            var time = System.currentTimeMillis()

            var progress = ProgressDialog(this)
            progress.setTitle("Saving")
            progress.setMessage("Please Wait..")
            if (newpassword.isEmpty() or previouspassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all the inputs", Toast.LENGTH_LONG).show()
            } else {
                //Proceed to save the data into the database
                //create a child in the db
                var mychild = FirebaseDatabase.getInstance().reference.child("Login,$time")
                var data = UserPasswordRecovery(newpassword, previouspassword)
                progress.show()
                mychild.setValue(data).addOnCompleteListener { task ->
                    progress.dismiss()
                    if (task.isSuccessful) {
                        mEdtNew.setText(null)
                        mEdtPrevious.setText(null)

                        Toast.makeText(this, "Reset Successful", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Reset Failed", Toast.LENGTH_LONG).show()
                    }

                    if (task.isSuccessful){
                        startActivity(Intent(this,LoginActivity::class.java))
                    }
                }
            }
        }
    }
}