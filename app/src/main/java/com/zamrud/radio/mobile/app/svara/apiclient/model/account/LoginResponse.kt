package com.zamrud.radio.mobile.app.svara.apiclient.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel
import org.parceler.Parcel

/**
 * Created by fahziar on 24/03/2016.
 */

@Parcel
class LoginResponse : BaseModel() {
    @SerializedName("appId")
    @Expose
    var mAppId: String? = null

    @SerializedName("ttl")
    @Expose
    var mTtl: Long = 0

    @SerializedName("created")
    @Expose
    var mCreated: String? = null

    @SerializedName("userId")
    @Expose
    var mUserId: String? = null

    @SerializedName("accessToken")
    @Expose
    var mAccessToken: String? = null

    @SerializedName("isGuest")
    @JvmField
    var isGuest = false

    @SerializedName("message")
    @Expose
    var mMessage: String? = null

    @SerializedName("statusCode")
    @Expose
    var mStatusCode = 200

    @SerializedName("accountTypeId")
    @Expose
    @JvmField
    var accountTypeId: String? = null

    fun getMessage(): String? {
        return mMessage
    }

    fun setMessage(mMessage: String?) {
        this.mMessage = mMessage
    }

    fun getStatusCode(): Int {
        return mStatusCode
    }

    fun setStatusCode(mStatusCode: Int) {
        this.mStatusCode = mStatusCode
    }

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

    fun setTtl(ttl: Long) {
        mTtl = ttl
    }

    fun getCreated(): String? {
        return mCreated
    }

    fun setCreated(created: String?) {
        mCreated = created
    }

    fun getUserId(): String? {
        return mUserId
    }

    fun setUserId(userId: String?) {
        mUserId = userId
    }

    fun getAccessToken(): String? {
        return mAccessToken
    }

    fun setAccessToken(mAccessToken: String?) {
        this.mAccessToken = mAccessToken
    }

    fun isGuest(): Boolean {
        return isGuest
    }

    fun setGuest(guest: Boolean) {
        isGuest = guest
    }

    fun getAccountTypeId(): String? {
        return accountTypeId
    }

    fun setAccountTypeId(accountTypeId: String?) {
        this.accountTypeId = accountTypeId
    }

}