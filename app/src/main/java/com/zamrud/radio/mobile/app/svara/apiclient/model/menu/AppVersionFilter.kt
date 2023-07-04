package com.zamrud.radio.mobile.app.svara.apiclient.model.menu

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by irfan on 7/10/2018.
 */
class AppVersionFilter(mVersion: String?, mPlatform: String?) {
    @SerializedName("version")
    @Expose
    var mVersion: String? = null

    @SerializedName("platform")
    @Expose
    var mPlatform: String? = null

    init {
        this.mVersion = mVersion
        this.mPlatform = mPlatform
    }

    fun getmVersion(): String? {
        return mVersion
    }

    fun setmVersion(mVersion: String?) {
        this.mVersion = mVersion
    }

    fun getmPlatform(): String? {
        return mPlatform
    }

    fun setmPlatform(mPlatform: String?) {
        this.mPlatform = mPlatform
    }

    /**
     * Return JSON representation of DataFilter object.
     *
     * This function is called by Retrofit
     * to serialize this object when querying web service. Currently Retrofit doesn't serialize
     * query using JSON, instead it call toString method.
     *
     * @return JSON representation of DataFilter object
     */
    override fun toString(): String {
        val gson: Gson = GsonBuilder().create()
        return gson.toJson(this)
    }
}