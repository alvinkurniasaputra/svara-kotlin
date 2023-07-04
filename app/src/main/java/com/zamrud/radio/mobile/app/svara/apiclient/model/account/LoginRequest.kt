package com.zamrud.radio.mobile.app.svara.apiclient.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.TextUtils
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel

/**
 * Created by fahziar on 24/03/2016.
 */
class LoginRequest : BaseModel {
    @SerializedName("username")
    @Expose
    private var mUsername: String? = null

    @SerializedName("password")
    @Expose
    private var mPassword: String? = null

    @SerializedName("email")
    @Expose
    private var mEmail: String? = null

    @SerializedName("appKey")
    @Expose
    private var mAppKey: String? = null

    @SerializedName("appKeyName")
    @Expose
    private var mAppKeyName: String? = null

    constructor(){
    }

    constructor(username: String?, password: String?, isAppLogin: Boolean) {
        if (isAppLogin) {
            mAppKeyName = username
            mAppKey = password
        } else {
            mPassword = password
            if (TextUtils.isEmail(username)) mEmail = username
            else mUsername = username
        }
    }

    fun getUsername(): String? {
        return mUsername
    }

    fun setUsername(username: String?) {
        mUsername = username
    }

    fun getPassword(): String? {
        return mPassword
    }

    fun setPassword(password: String?) {
        mPassword = password
    }

    fun getEmail(): String? {
        return mEmail
    }

    fun setEmail(email: String?) {
        this.mEmail = email
    }

    fun getAppKey(): String? {
        return mAppKey
    }

    fun setAppKey(mAppKey: String?) {
        this.mAppKey = mAppKey
    }

    fun getAppKeyName(): String? {
        return mAppKeyName
    }

    fun setAppKeyName(mAppKeyName: String?) {
        this.mAppKeyName = mAppKeyName
    }

}