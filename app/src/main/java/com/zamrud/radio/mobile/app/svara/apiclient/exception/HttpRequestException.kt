package com.zamrud.radio.mobile.app.svara.apiclient.exception

/**
 * Created by fahziar on 25/03/2016.
 */
class HttpRequestException : RuntimeException {
    private var mStatusCode = 0
    private var mMessage: String? = null

    constructor(statusCode: Int): super("Request error: $statusCode") {
        mStatusCode = statusCode
    }

    constructor(statusCode: Int, message: String?): super(message) {
        mStatusCode = statusCode
        mMessage = message
    }

    fun getStatusCode(): Int {
        return mStatusCode
    }

    fun getmMessage(): String? {
        return mMessage
    }
}