package com.example.myfinalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

class Offices extends AppCompatActivity {

    //Initialize Variable
    EditText mEditTextLocation;
    TextView mTextViewLocation1,mTextViewLocation2;

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offices);

        //assign variables
        mEditTextLocation = findViewById(R.id.mEditTextLocation);
        mTextViewLocation1 = findViewById(R.id.mTextViewLocation1);
        mTextViewLocation2 = findViewById(R.id.mTextViewLocation2);

        //Initialize Places
        Places.initialize(getApplicationContext(), "AIzaSyAahixRSlDS-WH5v-3ZrsoZPyQEbXSc8S8");

        //Set EditText non focusable
        mEditTextLocation.setFocusable(false);

        mEditTextLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize place field list
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,Place.Field.LAT_LNG,Place.Field.NAME);

                //Create intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                ,fieldList).build(Offices.this);

                //Start Activity result
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode ==RESULT_OK){
            //When Success
            //Initialize place
            Place place = Autocomplete.getPlaceFromIntent(data);
            //Set address on Edit Text
            mEditTextLocation.setText(place.getAddress());
            //Set locality name
            mTextViewLocation1.setText(String.format("Locality Name: %s", place.getName()));
            //Set latitude & longitude
            mTextViewLocation2.setText(String.valueOf(place.getLatLng()));
        }else if (resultCode == AutocompleteActivity.RESULT_ERROR){
            //When error
            //Initialize status
            Status status = Autocomplete.getStatusFromIntent(data);
            //Display Toast
            Toast.makeText(getApplicationContext(),status.getStatusMessage()
            ,Toast.LENGTH_SHORT).show();
        }
    }
}
