package com.example.myfinalproject.ui.swipeview

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myfinalproject.R
import com.example.myfinalproject.ui.swipeview.SwipeViewViewModel.Home

class SwipeViewFragment : Fragment()  {
    //widgets
    private var mImageView: ImageView? = null
    private var mTitle: TextView? = null
    private var mDetail: TextView? = null

    //vars
    private var mHome: Home? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mHome = requireArguments().getParcelable("Home")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.swipe_view_fragment, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        mImageView = view.findViewById(R.id.ImgSwipe)
        mTitle = view.findViewById(R.id.mTvTitle)
        mDetail = view.findViewById(R.id.mDetail)
    }

    private fun init() {
        if (mHome != null) {
            val options = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
            Glide.with(Activity())
                .setDefaultRequestOptions(options)
                .load(mHome!!.image)
                .into(mImageView!!)
            mTitle!!.text = mHome!!.title
            mDetail!!.javaClass
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(home: Home?): SwipeViewFragment {
            val fragment = SwipeViewFragment()
            if (home != null) {
                val bundle = Bundle()
                bundle.putParcelable("Home", home as Parcelable?)
                fragment.arguments = bundle
            }
            return fragment
        }
    }
}