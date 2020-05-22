package com.example.myproject.models

import android.os.Parcel
import android.os.Parcelable

class Home : Parcelable {
    var title: String? = null
    var image = 0
    var description: String? = null

    constructor(title: String?, image: Int, description: String?) {
        this.title = title
        this.image = image
        this.description = description
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        title = `in`.readString()
        image = `in`.readInt()
        description = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeInt(image)
        dest.writeString(description)
    }

    companion object {
        val CREATOR: Parcelable.Creator<Home?> =
            object : Parcelable.Creator<Home?> {
                override fun createFromParcel(`in`: Parcel): Home? {
                    return Home(`in`)
                }

                override fun newArray(size: Int): Array<Home?> {
                    return arrayOfNulls(size)
                }
            }
    }
}