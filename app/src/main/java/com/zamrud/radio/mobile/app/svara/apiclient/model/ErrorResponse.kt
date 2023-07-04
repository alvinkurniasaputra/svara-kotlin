package com.zamrud.radio.mobile.app.svara.apiclient.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fahziar on 25/03/2016.
 */
class ErrorResponse {
    @SerializedName("error")
    @Expose
    private val mError: Error? = null

    fun getError(): Error? {
        return mError
    }
}