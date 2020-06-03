package com.example.myfinalproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.activity_login.*

class Contact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        button_submit.setOnClickListener {
            //start receiving data from the user
            var name = Edtnamecontact.text.toString()
            var email = Edtmailcontact.text.toString()
            var number = Edtphonecotact.text.toString()
            var message = Edtmessagecontact.text.toString()
            var time = System.currentTimeMillis()

            var progress = ProgressDialog(this)
            progress.setTitle("Sending Message")
            progress.setMessage("Please Wait..")
            if (email.isEmpty() or name.isEmpty() or number.isEmpty() or message.isEmpty()) {
                Toast.makeText(this, "Please fill in all the inputs", Toast.LENGTH_LONG).show()
            } else {
                //Proceed to save the data into the database
                //create a child in the db
                var mychild = FirebaseDatabase.getInstance().reference.child("Login/$time")
                var data = UserContact(email, name,number,message)
                progress.show()
                mychild.setValue(data).addOnCompleteListener { task ->
                    progress.dismiss()
                    if (task.isSuccessful) {
                        Edtnamecontact.setText(null)
                        Edtmailcontact.setText(null)
                        Edtphonecotact.setText(null)
                        Edtmessagecontact.setText(null)
                        startActivity(Intent(this,MakaziDashboard::class.java))

                        Toast.makeText(this, "Message Sent Successful", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Message Sending Failed", Toast.LENGTH_LONG).show()
                    }

                }
            }
        }
    }
}
