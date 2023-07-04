package com.zamrud.radio.mobile.app.svara.apiclient.services

import com.google.gson.JsonObject
import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListObjectModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.account.*
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by fahziar on 24/03/2016.
 */
interface AccountsService {
    @GET("auth/guest-token/callback")
    fun loginGuest(@Query("uid") uid: String?,
                   @Query("app_id") app_id: String?,
                   @Query("platform") platform: String?,
                   @Query("idfa") idfa: String?,
                   @Query("jwt") jwt: String?): Call<LoginResponse>

    @POST("auth/signup")
    fun SIGNUP(@Body signUpRequest: SignUpRequest): Call<LoginResponse>

    @PUT("accounts/{id}")
    fun UPDATE(@Path("id") id: String?, @Body updateRequest: UpdateRequest): Call<Void>

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("apps/logout")
    fun logout(): Call<Void>

    @GET("accounts/{id}")
    fun getProfile(@Path("id") id: String?): Call<Account>

    @GET("accounts/{id}/stats")
    fun getAccountStats(@Path("id") id: String?): Call<AccountStatsResponse>

    @POST("apps/auth/login")
    fun appLogin(@Body credentials: LoginRequest): Call<LoginAppResponse>

    /**
     * Check whether logged in user already follow an user.
     * @param userId UserId of other user
     * @return Retrofit call. Reponse is successful (200) if logged in user alredy follow other user.
     */
    @HEAD("accounts/following/{id}")
    fun isFollowing(@Path("id") userId: String?): Call<Void>

    /**
     * Make logged in user follow other user.
     * @param userId UserId of user that will be followed
     * @return Retrofit call. Reponse is successful (200) if logged in user succesfully follow other user.
     */
    @PUT("accounts/following/{id}")
    fun follow(@Path("id") userId: String?): Call<Void>

    /**
     * Make logged in user unfollow other user.
     * @param userId UserId of user that will be unfollowed
     * @return Retrofit call. Reponse is successful (200) if logged in user succesfully unfollow other user.
     */
    @DELETE("accounts/following/{id}")
    fun unfollow(@Path("id") userId: String?): Call<Void>

    /**
     * GET followers from account
     * @param id of account
     * @return List of account
     */
    @GET("accounts/{id}/followers")
    fun GET_FOLLOWER(@Path("id") id: String?, @Query("offset") offset: Int, @Query("limit") limit: Int): Call<List<Account>>

    /**
     * GET following from account
     * @param id of account
     * @return List of account
     */
    @GET("accounts/{id}/followings")
    fun GET_FOLLOWING(@Path("id") id: String?, @Query("offset") offset: Int, @Query("limit") limit: Int): Call<List<Account>>

    //    @GET("accounts/{id}/memberships")
    //    Call<Membership> GET_MEMBERSHIP(@Path("id") String id);

    @GET("accounts/notifications")
    fun getNotification(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<List<Notification>>

    @GET("accounts/notifications/count")
    fun getNotificationCount(@Query("read") read: Boolean): Call<Int>

    @PUT("accounts/notifications/mark-all-read")
    fun markAllReadNotification(): Call<Void>

    @FormUrlEncoded
    @PUT("accounts/notifications/{id}/read")
    fun readNotification(@Path("id") id: String?, @Field("read") read: String?): Call<Void>

    @PATCH("accounts/{id}")
    fun updateUserSetting(@Path("id") id: String?, @Body account: Account): Call<Account>

    @Multipart
    @POST("accounts/upload/profilePicture")
    fun uploadProfilePicture(@Part profilePicture: MultipartBody.Part): Call<ProfilePictureResponse>

    @Multipart
    @POST("accounts/upload/cover")
    fun uploadCoverPicture(@Part coverPicture: MultipartBody.Part): Call<CoverPictureResponse>

    @PUT("accounts/{id}/location")
    fun SET_LOCATION(@Path("id") id: String?, @Body loc: JsonObject): Call<Void>

    @GET("accounts/suggestedcontent")
    fun suggestedcontent(): Call<List<DataListObjectModel>>

    @GET("accounts/{id}/current-roles/Radio/{radioId}")
    fun getCurrentRadioRoles(@Path("id") id: String?, @Path("radioId") radioId: String?): Call<AccountCurrentRolesRadio>

    @GET("accounts/{id}/current-roles")
    fun getCurrentRoles(@Path("id") id: String?): Call<AccountCurrentRolesAll>

    @GET("accounts/{id}/uploads")
    fun getAcountUploads(@Path("id") id: String?, @Query("offset") offset: Int?, @Query("limit") limit: Int?): Call<DataListModel>

    @GET("accounts/{id}/documents/me")
    fun getAccountDocument(@Path("id") id: String?, @Query("offset") offset: Int?, @Query("limit") limit: Int?): Call<DataListModel>

    @GET("auth/accountkit-token/callback")
    fun confirmPhoneNumber(@Query("app_id") appId: String?, @Query("code") code: String?, @Query("account_id") accountId: String?, @Query("link") link: Boolean): Call<Void>

    @GET("accounts/{id}/memberships")
    fun GET_MEMBERSHIP(@Path("id") id: String?): Call<Membership>

    @DELETE("accounts/me")
    fun deleteAccount(): Call<Void>

    @GET("accounts/userBlockedAccounts/me")
    fun getUserBlockedAccount(@Query("offset") offset: Int?, @Query("limit") limit: Int?): Call<BlockedAccount>

    @PATCH("accounts/{id}/online")
    fun resetUserOnlineStatus(@Path("id") id: String?): Call<Void>

    @PUT("accounts/{id}")
    fun updateUserStatus(@Path("id") id: String?, @Body defaultAccount: Account.DefaultAccount): Call<Account>

    @PUT("accounts/{id}/changeAccountType")
    fun changeUserAccountType(@Path("id") id: String?, @Body accountTypeId: JsonObject): Call<Account>

    @GET("accounts/{id}/organizationAdmins")
    fun getOrganizationAdmin(@Path("id") id: String?): Call<OrganizationAccount>

    @POST("organizationMappings/addAdmin")
    fun addOrganizationAdmin(@Body jsonObject: JsonObject): Call<Void>

    @HTTP(method = "DELETE", path = "organizationMappings/removeAdmin", hasBody = true)
    fun removeOrganizationAdmin(@Body jsonObject: JsonObject): Call<Void>

    @POST("accounts/switchAccount")
    fun switchAccount(@Body jsonObject: JsonObject): Call<LoginResponse>

    @GET("accounts/{id}/organizations")
    fun getMyOrganization(@Path("id") id: String?): Call<OrganizationAccount>
}