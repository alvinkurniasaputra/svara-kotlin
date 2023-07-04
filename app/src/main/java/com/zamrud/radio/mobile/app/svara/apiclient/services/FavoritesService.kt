package com.zamrud.radio.mobile.app.svara.apiclient.services

import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.favorites.RadioFavorite

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by fahziar on 31/03/2016.
 */
interface FavoritesService {
    @GET("accounts/favorites/musics")
    fun getFavoritesMusic(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>

    @GET("accounts/favorites/playlists?filter={\"where\": {\"kind\": \"playlist\"}}")
    fun getFavoritesPlaylist(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>

    @GET("accounts/favorites/all-series")
    fun getFavoritesSeries(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>

    @GET("accounts/favorites/albums")
    fun getFavoritesAlbum(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>

    @GET("accounts/favorites/radios")
    fun getFavoritesRadio(@Query("offset") offset: Int, @Query("limit") limit: Int, @Query("filter") filter: String?): Call<DataListModel>

    @GET("accounts/favorites/radios")
    fun getSubscribedRadio(@Query("filter") filter: String?): Call<DataListModel>

    @GET("accounts/favorites/artists")
    fun getFavoritesArtist(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>

    @GET("accounts/favorites/uploads")
    fun getFavoritesUpload(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>

    @GET("accounts/favorites/radio_contents")
    fun getFavoritesRadioContents(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>

    @GET("accounts/favorites/videos")
    fun getFavoritesVideo(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<DataListModel>
    /*
        Music
     */
    /**
     * Query if music already favorited
     * @param musicId music id
     * @return Call for favorite music
     */
    @GET("musics/favorite/{id}")
    fun isMusicFavorited(@Path("id") musicId: String?): Call<Void>

    @POST("musics/favorite/{id}")
    fun addMusicToFavorite(@Path("id") musicId: String?): Call<Void>

    @DELETE("musics/favorite/{id}")
    fun removeMusicFromFavorite(@Path("id") musicId: String?): Call<Void>

    /*
        Playlist
     */
    @GET("playlists/favorite/{id}")
    fun isPlaylistFavorited(@Path("id") mPlaylistId: String?): Call<Void>

    @POST("playlists/favorite/{id}")
    fun addPlaylistToFavorite(@Path("id") mPlaylistId: String?): Call<Void>

    @DELETE("playlists/favorite/{id}")
    fun removePlaylistFromFavorite(@Path("id") mPlaylistId: String?): Call<Void>

    /*
        Artist
     */
    @GET("artists/favorite/{id}")
    fun isArtistFavorited(@Path("id") mArtistId: String?): Call<Void>

    @POST("artists/favorite/{id}")
    fun addArtistToFavorite(@Path("id") mArtistId: String?): Call<Void>

    @DELETE("artists/favorite/{id}")
    fun removeArtistFromFavorite(@Path("id") mArtistId: String?): Call<Void>

    /*
        Album
     */
    @GET("albums/favorite/{id}")
    fun isAlbumFavorited(@Path("id") mAlbumId: String?): Call<Void>

    @POST("albums/favorite/{id}")
    fun addAlbumToFavorite(@Path("id") mAlbumId: String?): Call<Void>

    @DELETE("albums/favorite/{id}")
    fun removeAlbumFromFavorite(@Path("id") mAlbumId: String?): Call<Void>

    /*
        Radio favorite or subscribe
     */
    @GET("radios/favorite/{id}")
    fun isRadioFavored(@Path("id") mRadioId: String?): Call<RadioFavorite?>?

    @GET("radios/favorite/{id}")
    fun isRadioFavoredVoid(@Path("id") mRadioId: String?): Call<Void>

    @POST("radios/favorite/{id}")
    fun addRadioToFavorite(@Path("id") mRadioId: String?): Call<Void>

    @DELETE("radios/favorite/{id}")
    fun removeRadioFromFavorite(@Path("id") mRadioId: String?): Call<Void>

    @PUT("radios/{id}/subscribe")
    fun subscribe(@Path("id") radioId: String?): Call<Void>

    @DELETE("radios/{id}/subscribe")
    fun unsubscribe(@Path("id") radioId: String?, @Query("deleteFavorite") deleteFavorite: Boolean): Call<Void>

    /*
        Radio Content
     */
    @GET("radio_contents/favorite/{id}")
    fun isRadioContentFavorited(@Path("id") mRadioContentId: String?): Call<Void>

    @POST("radio_contents/favorite/{id}")
    fun addRadioContentToFavorite(@Path("id") mRadioContentId: String?): Call<Void>

    @DELETE("radio_contents/favorite/{id}")
    fun removeRadioContentFromFavorite(@Path("id") mRadioContentId: String?): Call<Void>

    /*
        Uploaded
     */
    @GET("uploads/favorite/{id}")
    fun isUploadFavorited(@Path("id") mUploadsId: String?): Call<Void>

    @POST("uploads/favorite/{id}")
    fun addUploadToFavorite(@Path("id") mUploadsId: String?): Call<Void>

    @DELETE("uploads/favorite/{id}")
    fun removeUploadFromFavorite(@Path("id") mUploadsId: String?): Call<Void>
    /*
        Video
     */
    /**
     * Query if video already favorited
     * @param videoId video id
     * @return Call for favorite video
     */
    @GET("videos/favorite/{id}")
    fun isVideoFavorited(@Path("id") videoId: String?): Call<Void>

    @POST("videos/favorite/{id}")
    fun addVideoToFavorite(@Path("id") videoId: String?): Call<Void>

    @DELETE("videos/favorite/{id}")
    fun removeVideoFromFavorite(@Path("id") videoId: String?): Call<Void>
}