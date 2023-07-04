package com.zamrud.radio.mobile.app.svara.apiclient.services

import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Instalation
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.InstallationCheck
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by solusi247 on 28/09/16.
 */
interface InstallationService {
    @GET("accounts/{id}/installations/check")
    fun getInstallationCheck(@Path("id") id: String?, @Query("deviceToken") deviceToken: String?): Call<InstallationCheck>

    @POST("installations")
    fun instalation(@Body instalation: Instalation?): Call<Instalation>

    @PUT("installations/session")
    fun putInstallationSession(@Query("deviceToken") deviceToken: String?): Call<Void>

    @DELETE("installations/delete-by-token")
    fun deleteInstallation(@Query("deviceToken") deviceToken: String?): Call<Void>
}