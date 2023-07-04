package com.zamrud.radio.mobile.app.svara.apiclient.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel

/**
 * Created by fahziar on 03/10/2016.
 */
class ProfilePictureResponse : BaseModel() {
    @SerializedName("message")
    @Expose
    private val mMessage: String? = null

    @SerializedName("profilePicture")
    @Expose
    private val mProfilePicture: String? = null

    fun getMessage(): String? {
        return mMessage
    }

    fun getProfilePicture(): String? {
        return mProfilePicture
    }
}