package com.example.myfinalproject

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class DetailsActivity : AppCompatActivity() {
    var mImgDetail: ImageView? = null
    var mTvDetail: TextView? = null
    var ImgUrl: String? = null
    var Txt:kotlin.String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        mImgDetail = findViewById(R.id.imgDetail);
        mTvDetail = findViewById(R.id.tvDetail);
        ImgUrl = getIntent().getStringExtra("url");
        Txt = getIntent().getStringExtra("txt");
        Picasso.with(this).load(ImgUrl).into(mImgDetail);
        mTvDetail!!.setText(Txt);
    }
}