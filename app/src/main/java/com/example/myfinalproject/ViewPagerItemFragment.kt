package com.example.myfinalproject

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
import com.example.myproject.resources.Home

class ViewPagerItemFragment : Fragment() {
    //widgets
    private var mImageView: ImageView? = null
    private var mTitle: TextView? = null
    private var mDescription: TextView? = null

    //vars
    private var mHome: Home? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mHome = requireArguments().get("Home") as Home?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_viewpager_item, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        mImageView = view.findViewById(R.id.img)
        mTitle = view.findViewById(R.id.mTvTitle)
        mDescription = requireView().findViewById(R.id.mTvDescription)
        init()
    }

    private fun init() {
        if (mHome != null) {
            val options = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
            Glide.with(requireActivity())
                .setDefaultRequestOptions(options)
                .load(mHome!!.javaClass)
                .into(mImageView!!)
            mTitle!!.text = mHome.toString()
            mDescription!!.text = mHome.toString()
        }
    }

    companion object {
        fun getInstance(home: Home?): ViewPagerItemFragment {
            val fragment = ViewPagerItemFragment()
            if (home != null) {
                val bundle = Bundle()
                bundle.putParcelable("Home", home as Parcelable?)
                fragment.arguments = bundle
            }
            return fragment
        }
    }
}