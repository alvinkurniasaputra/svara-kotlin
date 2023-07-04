package com.zamrud.radio.mobile.app.svara.apiclient.services

import com.zamrud.radio.mobile.app.svara.apiclient.model.account.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by solusi247 on 03/08/16.
 */
interface LoginOther {
    @GET("auth/facebook-token/callback")
    fun GET_TOKEN(@Query("access_token") access_token: String?, @Query("app_id") app_id: String?): Call<LoginResponse>

    @GET("auth/google-token/callback")
    fun LOGIN_GOOGLE(@Query("id_token") access_token: String?, @Query("app_id") app_id: String?): Call<LoginResponse>

    @GET("auth/phone/callback")
    fun LOGIN_PHONE(@Query("phone") code: String?, @Query("app_id") app_id: String?): Call<LoginResponse>
}