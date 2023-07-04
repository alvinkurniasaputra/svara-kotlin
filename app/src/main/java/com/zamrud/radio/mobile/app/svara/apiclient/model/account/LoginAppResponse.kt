package com.zamrud.radio.mobile.app.svara.apiclient.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel

/**
 * Created by fahziar on 24/03/2016.
 */
class LoginAppResponse : BaseModel() {
    @SerializedName("appId")
    @Expose
    var mAppId: String? = null

    @SerializedName("ttl")
    @Expose
    var mTtl: Long = 0

    @SerializedName("created")
    @Expose
    var mCreated: String? = null

    @SerializedName("accessToken")
    @Expose
    var mAccessToken: String? = null

    init {

    }

    fun getAppId(): String? {
        return mAppId
    }

    fun setAppId(mAppId: String?) {
        this.mAppId = mAppId
    }

    fun getTtl(): Long {
        return mTtl
    }

    fun setTtl(mTtl: Long) {
        this.mTtl = mTtl
    }

    fun getCreated(): String? {
        return mCreated
    }

    fun setCreated(mCreated: String?) {
        this.mCreated = mCreated
    }

    fun getAccessToken(): String? {
        return mAccessToken
    }

    fun setAccessToken(mAccessToken: String?) {
        this.mAccessToken = mAccessToken
    }
}