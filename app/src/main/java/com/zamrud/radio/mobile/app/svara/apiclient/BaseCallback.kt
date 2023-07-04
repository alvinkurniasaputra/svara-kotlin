package com.zamrud.radio.mobile.app.svara.apiclient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by fahziar on 31/03/2016.
 */
abstract class BaseCallback<T> : Callback<T> {
    override fun onFailure(call: Call<T?>, t: Throwable) {
        onVoid(t)
        onError()
    }

    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        onVoid(null)
        //TODO: Check if response contains error
        if (response.isSuccessful) {
            onSuccess(call, response)
        } else {
            onError()
        }
    }

    abstract fun onSuccess(call: Call<T?>, response: Response<T?>)
    open fun onError() {}
    open fun onVoid(t: Throwable?) {}
}