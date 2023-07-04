package com.zamrud.radio.mobile.app.svara.apiclient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ResponseHelper<T> : Callback<T> {
    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        if (response.isSuccessful && response.body() != null)
            onSuccess(response.body())
        else {
//            Error error = ErrorUtils.parseError(response);
            onError(response.message())
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        onError(t.message)
    }

    abstract fun onSuccess(body: T?)
    open fun onError(message: String?) {}
}