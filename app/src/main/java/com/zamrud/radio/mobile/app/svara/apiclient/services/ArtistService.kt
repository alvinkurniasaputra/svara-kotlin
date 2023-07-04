package com.zamrud.radio.mobile.app.svara.apiclient.services
//
//import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
//import com.zamrud.radio.mobile.app.svara.apiclient.model.ImageMusicResponse
//import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Artist
//import com.zamrud.radio.mobile.app.svara.artist.model.ArtistRequest
//import okhttp3.MultipartBody
//import retrofit2.Call
//import retrofit2.http.*
//
///**
// * Created by fahziar on 25/07/2016.
// */
//interface ArtistService {
//    @GET("artists/{id}")
//    fun getArtistProfile(@Path("id") artistId: String?): Call<Artist>
//
//    @GET("artists/{id}/albums")
//    fun getAlbums(@Path("id") artistId: String?, @Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>
//
//    @GET("artists/{id}/musics")
//    fun getTopMusics(@Path("id") artistId: String?, @Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>
//
//    @GET("artists/{id}/similar")
//    fun getSimilarArtist(@Path("id") artistId: String?, @Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>
//
//    @Multipart
//    @POST("artists/upload/coverArt")
//    fun uploadArtistImage(@Part file: MultipartBody.Part): Call<ImageMusicResponse>
//
//    @PUT("artists/{id}")
//    fun editArtist(@Path("id") id: String?, @Body artistRequest: ArtistRequest): Call<Artist>
//}