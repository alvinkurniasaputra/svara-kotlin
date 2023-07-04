package com.zamrud.radio.mobile.app.svara.apiclient.services

import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.document.CreateDocumentForm
import com.zamrud.radio.mobile.app.svara.apiclient.model.document.CreateDocumentResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.document.EditReceiverModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.document.ReceiversResponseModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Document

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DocumentService {
    @POST("documents")
    fun createDocument(@Body doc: CreateDocumentForm?): Call<CreateDocumentResponse>

    @PUT("documents/{id}")
    fun updateDocument(@Path("id") id: String?, @Body doc: CreateDocumentForm?): Call<CreateDocumentResponse>

    @DELETE("documents/{id}")
    fun delete(@Path("id") id: String?): Call<Void>

    @GET("documents/{id}")
    fun getDocument(@Path("id") id: String?): Call<Document>

    @Streaming
    @GET
    fun download(@Url url: String?): Call<ResponseBody>

    @GET("rapors?withPagination=true&sort=createdAt DESC")
    fun getPrivateDoc(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>

    @GET("rapors/{id}/users")
    fun getReceivers(@Path("id") id: String?): Call<List<ReceiversResponseModel>>

    @POST("documents/addRaporUser")
    fun addReceiver(@Body receiverModel: EditReceiverModel?): Call<ReceiversResponseModel>

    @DELETE("documents/removeRaporUser/{mappingId}")
    fun removeReceiver(@Path("mappingId") mappingId: String?): Call<String>
}