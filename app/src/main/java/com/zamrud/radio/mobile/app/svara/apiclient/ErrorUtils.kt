package com.zamrud.radio.mobile.app.svara.apiclient

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zamrud.radio.mobile.app.svara.apiclient.model.Error
import com.zamrud.radio.mobile.app.svara.apiclient.model.ErrorResponse
import retrofit2.Response


/**
 * Created by fahziar on 25/03/2016.
 */
class ErrorUtils {
    companion object {
        fun parseError(response: Response<*>): Error? {
            val gson: Gson = GsonBuilder().create()
            return gson.fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java).getError()
        }
    }
}