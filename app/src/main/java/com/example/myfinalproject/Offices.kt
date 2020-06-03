package com.example.myfinalproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.util.*

internal class Offices : AppCompatActivity() {
    //Initialize Variable
    var mEditTextLocation: EditText? = null
    var mTextViewLocation1: TextView? = null
    var mTextViewLocation2: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offices)

        //assign variables
        mEditTextLocation = findViewById(R.id.mEditTextLocation)
        mTextViewLocation1 = findViewById(R.id.mTextViewLocation1)
        mTextViewLocation2 = findViewById(R.id.mTextViewLocation2)

        //Initialize Places
        Places.initialize(
            applicationContext,
            "AIzaSyAahixRSlDS-WH5v-3ZrsoZPyQEbXSc8S8"
        )

        //Set EditText non focusable
        mEditTextLocation!!.setFocusable(false)
        mEditTextLocation!!.setOnClickListener(View.OnClickListener { //Initialize place field list
            val fieldList = Arrays.asList(
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG,
                Place.Field.NAME
            )

            //Create intent
            val intent = Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY
                , fieldList
            ).build(this@Offices)

            //Start Activity result
            startActivityForResult(intent, 100)
        })
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            //When Success
            //Initialize place
            val place = Autocomplete.getPlaceFromIntent(data!!)
            //Set address on Edit Text
            mEditTextLocation!!.setText(place.address)
            //Set locality name
            mTextViewLocation1!!.text = String.format("Locality Name: %s", place.name)
            //Set latitude & longitude
            mTextViewLocation2!!.text = place.latLng.toString()
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            //When error
            //Initialize status
            val status =
                Autocomplete.getStatusFromIntent(data!!)
            //Display Toast
            Toast.makeText(
                applicationContext, status.statusMessage
                , Toast.LENGTH_SHORT
            ).show()
        }
    }
}