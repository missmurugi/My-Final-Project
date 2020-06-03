package com.example.myfinalproject

import android.content.Context
import android.view.*
import android.view.ContextMenu.ContextMenuInfo
import android.view.View.OnCreateContextMenuListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.*

class ImageAdapter (
    private val mContext: Context,
    private var mUploads: MutableList<Upload>
) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private var mListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ImageViewHolder {
        val v: View = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false)
        return ImageViewHolder(v)
    }

    override fun onBindViewHolder(holder: ImageViewHolder,position: Int) {
        val uploadCurrent = mUploads[position]
        holder.textViewType.setText(uploadCurrent.getType())
        holder.textViewDistance.setText(uploadCurrent.getDistance())
        holder.textViewBeds.setText(uploadCurrent.getBeds())
        Picasso.with(mContext).load(uploadCurrent.getmImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerInside().into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return mUploads.size
    }

    inner class ImageViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener, OnCreateContextMenuListener,
        MenuItem.OnMenuItemClickListener {
        var textViewType: TextView
        var textViewDistance: TextView
        var textViewBeds: TextView
        var imageView: ImageView
        override fun onClick(v: View) {
            if (mListener != null) {
                //Get the position of the clicked item
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    mListener!!.onItemClick(position)
                }
            }
        }

        // Handle Menu Items
        override fun onCreateContextMenu(menu: ContextMenu,v: View,menuInfo: ContextMenuInfo) {
            menu.setHeaderTitle("Select Action")
            val doWhatever = menu.add(Menu.NONE, 1, 1, "Do Whatever")
            val delete = menu.add(Menu.NONE, 2, 2, "Delete")
            doWhatever.setOnMenuItemClickListener(this)
            delete.setOnMenuItemClickListener(this)
        }

        override fun onMenuItemClick(item: MenuItem): Boolean {
            if (mListener != null) {
                //Get the position of the clicked item
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    when (item.itemId) {
                        1 -> {
                            mListener!!.onWhatEverClick(position)
                            return true
                        }
                        2 -> {
                            mListener!!.onDeleteClick(position)
                            return true
                        }
                    }
                }
            }
            return false
        }

        init {
            textViewType = itemView.findViewById(R.id.text_view_type)
            textViewDistance = itemView.findViewById(R.id.text_view_distance)
            textViewBeds = itemView.findViewById(R.id.text_view_beds)
            imageView = itemView.findViewById(R.id.image_view_upload)
            itemView.setOnClickListener(this)
            itemView.setOnCreateContextMenuListener(this)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onWhatEverClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    fun setOnItemClickListener(listener: ViewresidentialsActivity) {
        mListener = listener
    }

    fun setSearchOperation(newList: List<Upload>?) {
        mUploads = ArrayList()
        mUploads.addAll(newList!!)
        notifyDataSetChanged()
    }

}

