package com.zamrud.radio.mobile.app.svara.apiclient.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel

/**
 * Created by fahziar on 08/11/2016.
 */
class CoverPictureResponse : BaseModel() {
    @SerializedName("message")
    @Expose
    private val mMessage: String? = null

    @SerializedName("cover")
    @Expose
    private val mCoverPicture: String? = null

    fun getMessage(): String? {
        return mMessage
    }

    fun getCoverPicture(): String? {
        return mCoverPicture
    }
}