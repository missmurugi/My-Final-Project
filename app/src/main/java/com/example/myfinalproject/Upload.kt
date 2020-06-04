package com.example.myfinalproject

import com.google.firebase.database.Exclude

class Upload {
    private var Type: String? = null
    private var Distance: String? = null
    private var description: String? = null
    private var mImageUrl: String? = null
    private var key: String? = null

    constructor(Type: String?, Distance: String?, Beds: String?, mImageUrl: String?, key: String?) {
        this.Type = Type
        this.Distance = Distance
        this.description = Beds
        this.mImageUrl = mImageUrl
        this.key = key
    }

    constructor(){}


    fun getType(): String? {
        return Type
    }

    fun setType(Type: String) {
        this.Type = Type
    }
    fun getDistance(): String? {
        return Distance
    }

    fun setDistance(Distance: String) {
        this.Distance = Distance
    }
    fun getBeds(): String? {
        return description
    }

    fun setBeds(Type: String) {
        this.description = description
    }

    fun getmImageUrl(): String? {
        return mImageUrl
    }

    fun setmImageUrl(mImageUrl: String?) {
        this.mImageUrl = mImageUrl
    }

    @Exclude
    fun getKey(): String? {
        return key
    }

    @Exclude
    fun setKey(key: String?) {
        this.key = key
    }
}