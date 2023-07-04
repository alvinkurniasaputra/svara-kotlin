package com.zamrud.radio.mobile.app.svara.apiclient.ipAddress

import retrofit2.Call
import retrofit2.http.GET

interface IpService {
    @GET("https://api.ipify.org/?format=json")
    fun getIp(): Call<IpResponse>
}