package com.zamrud.radio.mobile.app.svara.apiclient.services

import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.AppResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.CurationPagesMetadata
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.DataFilter

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by fahziar on 13/04/2016.
 */
interface CurationService {
    @GET("apps/{id}")
    fun getApp(@Path("id") id: String?): Call<AppResponse>

    @GET("curationPages/{id}/contents")
    fun getCurationPages(@Path("id") id: String?, @Query("options") option: String?): Call<List<CurationPagesMetadata>>

    @GET("curationPageContents/getContent")
    fun getCurationPagesContents(@Query("filter") dataFilter: DataFilter?): Call<DataListModel>

    @GET("ttx/{id}/page")
    fun getTtxPage(@Path("id") id: String?): Call<List<CurationPagesMetadata>>

    @GET("apps/{id}/settings/menus/{menuId}/dynamic/contents")
    fun getDynamicCurationPages(@Path("id") id: String?, @Path("menuId") menuId: String?, @Query("options") option: String?): Call<List<CurationPagesMetadata>>
}