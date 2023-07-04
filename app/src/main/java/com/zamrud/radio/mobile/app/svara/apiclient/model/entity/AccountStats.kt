package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel

/**
 * Created by fahziar on 06/04/2016.
 */
class AccountStats : BaseModel() {
    @SerializedName("showlists")
    @Expose
    private var mShowlists: Int? = null

    @SerializedName("uploads")
    @Expose
    private var mUploads: Int? = null

    @SerializedName("followers")
    @Expose
    private var mFollowers: Int? = null

    @SerializedName("followings")
    @Expose
    private var mFollowings: Int? = null

    init {
        mShowlists = 0
        mUploads = 0
        mFollowers = 0
        mFollowings = 0
    }

    fun getShowlists(): Int? {
        return mShowlists
    }

    fun setShowlists(showlists: Int) {
        mShowlists = showlists
    }

    fun getUploads(): Int? {
        return mUploads
    }

    fun setUploads(uploads: Int) {
        mUploads = uploads
    }

    fun getFollowers(): Int? {
        return mFollowers
    }

    fun setFollowers(followers: Int) {
        mFollowers = followers
    }

    fun getFollowings(): Int? {
        return mFollowings
    }

    fun setFollowings(followings: Int) {
        mFollowings = followings
    }

}