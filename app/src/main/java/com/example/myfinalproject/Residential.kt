package com.example.myfinalproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.util.*

internal class Residential : AppCompatActivity() {
    //Initialize Variable
    var mResidentLocation: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residential)

        //assign variables
        mResidentLocation = findViewById(R.id.mResidentLocation)
        val ImgResident =
            findViewById<View>(R.id.ImgResident) as ImageView
        val ImgResident1 =
            findViewById<View>(R.id.ImgResident1) as ImageView
        val ImgResident2 =
            findViewById<View>(R.id.ImgResident2) as ImageView
        val actv1 =
            findViewById<View>(R.id.actv1) as AutoCompleteTextView
        val actv2 =
            findViewById<View>(R.id.actv2) as AutoCompleteTextView
        val actv3 =
            findViewById<View>(R.id.actv3) as AutoCompleteTextView
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            propertytype
        )
        actv1.setAdapter(adapter)
        val adapter1 = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            propertytype
        )
        actv2.setAdapter(adapter)
        val adapter2 = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            propertytype
        )
        actv3.setAdapter(adapter)
        ImgResident.setOnClickListener { actv1.showDropDown() }
        ImgResident1.setOnClickListener { actv2.showDropDown() }
        ImgResident2.setOnClickListener { actv3.showDropDown() }


        //Initialize Places
        Places.initialize(
            applicationContext,
            "AIzaSyAahixRSlDS-WH5v-3ZrsoZPyQEbXSc8S8"
        )

        //Set EditText non focusable
        mResidentLocation!!.setFocusable(false)
        mResidentLocation!!.setOnClickListener(View.OnClickListener { //Initialize place field list
            val fieldList = Arrays.asList(
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG,
                Place.Field.NAME
            )

            //Create intent
            val intent = Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY
                , fieldList
            ).build(this@Residential)

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
            mResidentLocation!!.setText(place.address)
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

    companion object {
        private val propertytype =
            arrayOf("Property Type", "House", "Flat/Apartment", "New Homes")
        private val distance =
            arrayOf("Distance", "5 miles", "10 miles", "20 miles")
        private val bedrooms =
            arrayOf("Bedrooms", "Studio", "1+", "2+", "3+")
    }
}