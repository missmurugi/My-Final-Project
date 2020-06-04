package com.example.myfinalproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_viewresidentials.*
import java.util.*
import kotlin.collections.ArrayList

class ViewresidentialsActivity : AppCompatActivity(), ImageAdapter.OnItemClickListener {

    private var mAdapter: ImageAdapter? = null

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private var mUploads: List<Upload>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewresidentials)

        recycler_view!!.setHasFixedSize(true)
        recycler_view!!.setLayoutManager(LinearLayoutManager(this))
        mUploads = ArrayList()



        mAdapter = ImageAdapter(applicationContext, mUploads as ArrayList<Upload>)
        recycler_view!!.setAdapter(mAdapter)
        mAdapter!!.setOnItemClickListener(this)
        var progress = ProgressDialog(this)
        progress.setTitle("Loading properties!!!")
        progress.setMessage("Please wait...")


        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("properties")
        progress.show()
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                (mUploads as ArrayList<Upload>).clear()
                for (postSnapshot in dataSnapshot.children) {
                    val upload = postSnapshot.getValue(Upload::class.java)
//                    upload!!.setKey(postSnapshot.key)
                    (mUploads as ArrayList<Upload>).add(upload!!)
                    Collections.reverse(mUploads)
                }
                mAdapter!!.notifyDataSetChanged()
                progress_circle!!.setVisibility(View.INVISIBLE)
                progress.dismiss()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ViewresidentialsActivity, "Error "+databaseError.message, Toast.LENGTH_SHORT).show()
                progress_circle!!.setVisibility(View.INVISIBLE)
                progress.dismiss()
            }
        })
    }
    override fun onItemClick(position: Int) {
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        intent.putExtra("url", mUploads!![position].getmImageUrl())
        intent.putExtra("txt", mUploads!![position].getType())
        startActivity(intent)
    }

    override fun onWhatEverClick(position: Int) {
        Toast.makeText(this, "Whatever Click at position: $position", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClick(position: Int) {
        val selectedItem = mUploads!![position]
        val selecetedKey: String? = selectedItem.getKey()
        val imageRef = mStorage!!.getReferenceFromUrl(selectedItem.getmImageUrl()!!)
        imageRef.delete().addOnSuccessListener {
            if (selecetedKey != null) {
                mDatabaseRef!!.child(selecetedKey).removeValue()
            }
            Toast.makeText(this@ViewresidentialsActivity, "Item Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}
