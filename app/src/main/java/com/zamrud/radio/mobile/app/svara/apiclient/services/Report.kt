package com.zamrud.radio.mobile.app.svara.apiclient.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by solusi247 on 10/09/16.
 */
interface Report {
    @POST("reports")
    fun REPORT(@Body report: com.zamrud.radio.mobile.app.svara.helper.Report?): Call<com.zamrud.radio.mobile.app.svara.helper.Report>
}