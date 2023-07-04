package com.zamrud.radio.mobile.app.svara.apiclient.services

import com.zamrud.radio.mobile.app.svara.apiclient.model.AppSettings
import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.OpenAppData
import com.zamrud.radio.mobile.app.svara.apiclient.model.OpenAppDataBody
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.CurationPagesMetadata
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.DataFilter
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Menu
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.VersionSettings
import com.zamrud.radio.mobile.app.svara.apiclient.model.menu.AppVersionFilter
import com.zamrud.radio.mobile.app.svara.apiclient.model.startupPopup.StartupPopup
import com.zamrud.radio.mobile.app.svara.preference.model.ListPreferenceModel
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Hinata on 1/18/2018.
 */
interface AppService {
    @GET("apps/{id}/settings")
    fun getAppSettings(@Path("id") id: String?): Call<AppSettings>

    @GET("apps/{id}/pref/{type}")
    fun getPreferenceList(@Path("id") id: String?, @Path("type") type: String?,
                          @Query("action") action: String?): Call<ListPreferenceModel>

    @GET("apps/{id}/pref-curation-page/{curationPageId}")
    fun getPreferenceCurationPages(@Path("id") id: String?, @Path("curationPageId") curationPageId: String?): Call<List<CurationPagesMetadata>>

    @GET("apps/{id}/pref-curation-page-content/{cpcId}")
    fun getPreferenceCurationContents(@Path("id") id: String?, @Path("cpcId") cpcId: String?, @Query("filter") dataFilter: DataFilter): Call<DataListModel>


    @PUT("accounts/{id}/apps/{appId}/pref/{type}/{value}")
    fun setPreference(@Path("id") accountId: String?, @Path("appId") appId: String?,
                      @Path("type") type: String?, @Path("value") value: String?): Call<Void>

    @DELETE("accounts/{id}/apps/{appId}/pref/{type}/{value}")
    fun removePreference(@Path("id") accountId: String?, @Path("appId") appId: String?,
                         @Path("type") type: String?, @Path("value") value: String?): Call<Void>

    @GET("accounts/{id}/apps/{appId}/pref")
    fun getCurrentPreference(@Path("id") id: String?, @Path("appId") appId: String?, @Query("type") type: String?): Call<List<Any>>

    @GET("apps/{id}/settings/menus")
    fun getMenu(@Path("id") id: String?, @Query("filter") appVersion: AppVersionFilter): Call<List<Menu>>

    @GET("apps/{id}/settings/version")
    fun getVersionSettings(@Path("id") id: String?): Call<VersionSettings>

    @GET("apps/{id}/popups/available")
    fun getStartupPopup(@Path("id") id: String?): Call<StartupPopup>

    @POST("apps/{id}/app-open-data")
    fun getAllDataOpenApp(@Path("id") id: String?, @Body dataBody: OpenAppDataBody): Call<OpenAppData>

    @GET("apps/{id}/accountTypes")
    fun getAccountType(@Path("id") id: String?): Call<TypeAccount>
}