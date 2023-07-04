package com.zamrud.radio.mobile.app.svara.apiclient.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fahziar on 25/03/2016.
 */
class Error : BaseModel() {
    @SerializedName("name")
    @Expose
    private var mName: String? = null

    @SerializedName("status")
    @Expose
    private var mStatus = 0

    @SerializedName("message")
    @Expose
    private var mMessage: String? = null

    @SerializedName("statusCode")
    @Expose
    private var mStatusCode = 0

    @SerializedName("code")
    @Expose
    private var mCode: String? = null

    @SerializedName("stack")
    @Expose
    private var mStack: String? = null

    init {
    }

    fun getName(): String? {
        return mName
    }

    fun setName(name: String?) {
        mName = name
    }

    fun getStatus(): Int {
        return mStatus
    }

    fun setStatus(status: Int) {
        mStatus = status
    }

    fun getMessage(): String? {
        return mMessage
    }

    fun setMessage(message: String?) {
        mMessage = message
    }

    fun getStatusCode(): Int {
        return mStatusCode
    }

    fun setStatusCode(statusCode: Int) {
        mStatusCode = statusCode
    }

    fun getCode(): String? {
        return mCode
    }

    fun setCode(code: String?) {
        mCode = code
    }

    fun getStack(): String? {
        return mStack
    }

    fun setStack(stack: String?) {
        mStack = stack
    }
}