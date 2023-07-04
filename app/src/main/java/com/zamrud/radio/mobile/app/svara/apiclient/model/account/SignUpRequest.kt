package com.zamrud.radio.mobile.app.svara.apiclient.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by solusi247 on 29/07/16.
 */
class SignUpRequest {
    @SerializedName("gender")
    @Expose
    private var mGender: String? = null

    @SerializedName("firstName")
    @Expose
    private var mFirstName: String? = null

    @SerializedName("lastName")
    @Expose
    private var mLastName: String? = null

    @SerializedName("username")
    @Expose
    private var mUsername: String? = null

    @SerializedName("email")
    @Expose
    private var mEmail: String? = null

    @SerializedName("password")
    @Expose
    private var mPassword: String? = null

    @SerializedName("birthday")
    @Expose
    private var mBirthday: String? = null

    @SerializedName("userParam")
    @Expose
    private lateinit var mUserParam: UserParam

    fun getGender(): String? {
        return mGender
    }

    fun setGender(gender: String?) {
        mGender = gender
    }

    fun getFirstName(): String? {
        return mFirstName
    }

    fun setFirstName(firstName: String?) {
        mFirstName = firstName
    }

    fun getLastName(): String? {
        return mLastName
    }

    fun setLastName(lastName: String?) {
        mLastName = lastName
    }

    fun getUsername(): String? {
        return mUsername
    }

    fun setUsername(username: String?) {
        mUsername = username
    }

    fun getEmail(): String? {
        return mEmail
    }

    fun setEmail(email: String?) {
        mEmail = email
    }

    fun getPassword(): String? {
        return mPassword
    }

    fun setPassword(password: String?) {
        mPassword = password
    }

    fun getBirthday(): String? {
        return mBirthday
    }

    fun setBirthday(birthday: String?) {
        mBirthday = birthday
    }

    fun getUserParam(): UserParam {
        return mUserParam
    }

    fun setUserParam(userParam: UserParam) {
        mUserParam = userParam
    }

    fun newUserParam(): UserParam {
        return UserParam()
    }

    inner class UserParam {
        @SerializedName("appId")
        @Expose
        private var mAppId: String? = null

        fun getAppId(): String? {
            return mAppId
        }

        fun setAppId(appId: String?) {
            mAppId = appId
        }
    }
}