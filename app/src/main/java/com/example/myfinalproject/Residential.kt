package com.example.myfinalproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_residential.*


internal class ResidentialActivity : AppCompatActivity() {
    //Initialize Variable
    var mResidentLocation: EditText? = null
    private val PICK_IMAGE_REQUEST = 1
    private var mImageView: ImageView? = null
    private var mProgressBar: ProgressBar? = null
    private var mImageUri: Uri? = null
    private var mStorageRef: StorageReference? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mUploadTask: StorageTask<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residential)

        //assign variables

        mImageView = findViewById(R.id.imageView)
        mProgressBar = findViewById(R.id.progress_bar)
        mProgressBar = findViewById(R.id.progress_bar)
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads")


        //Choosing an Image from Gallery

        //Choosing an Image from Gallery
        imageView.setOnClickListener {
            openFileChooser()
        }
        //Uploading an Image
        //Uploading an Image
        button_upload.setOnClickListener {
            if (mUploadTask != null && mUploadTask!!.isInProgress) {
                Toast.makeText(this@ResidentialActivity, "Upload in Progress", Toast.LENGTH_LONG)
                    .show()
            } else {
                uploadFile()
            }
        }
        //Showing uploaded images
        //Showing uploaded images
        text_view_show_uploads.setOnClickListener {
            openImagesActivity()
        }

    }

    fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode ==
            Activity.RESULT_OK && data != null && data.data != null
        ) {
            mImageUri = data.data
            Picasso.with(this).load(mImageUri).into(mImageView)
            //You Can Also Use
            //mImageView.setImageURI(mImageUri);
        }
    }


    // Getting an extension from our file (eg. Jpeg,Png,Jpg etc)
    private fun getFileExtension(uri: Uri): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }

    private fun uploadFile() {
        if (mImageUri != null) {
            val fileReference = mStorageRef!!.child(
                System.currentTimeMillis().toString() + "." + getFileExtension(mImageUri!!)
            )
            mUploadTask = fileReference.putFile(mImageUri!!).addOnSuccessListener { taskSnapshot ->

                //Success
                val delay: Thread = object : Thread() {
                    override fun run() {
                        try {
                            sleep(500)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                }
                delay.start()
                Toast.makeText(this@ResidentialActivity, "Upload Successful", Toast.LENGTH_LONG)
                    .show()
                var property_type = mEdtPropertyType.text.toString()
                var property_distance = mEdtDistance.text.toString()
                var property_bedrooms = mEdtBedrooms.text.toString()
                val upload = Upload(
                    property_type,
                    property_distance,
                    property_bedrooms,
                    taskSnapshot.storage.getDownloadUrl().toString(),
                    System.currentTimeMillis().toString()
                )
                val uploadId = mDatabaseRef!!.push().key
                mDatabaseRef!!.child(System.currentTimeMillis().toString() + "").setValue(upload)
            }.addOnFailureListener { e ->
                //Failure
                Toast.makeText(this@ResidentialActivity, e.message, Toast.LENGTH_SHORT).show()
            }.addOnProgressListener { taskSnapshot ->

                //Updating the Progress Bar
                val progress =
                    100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                mProgressBar!!.progress = progress.toInt()
            }
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openImagesActivity() {
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        startActivity(intent)
    }


}