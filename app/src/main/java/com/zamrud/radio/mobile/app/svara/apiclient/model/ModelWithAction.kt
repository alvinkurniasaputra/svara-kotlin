package com.zamrud.radio.mobile.app.svara.apiclient.model

import android.content.Context
import android.widget.Toast

import com.zamrud.radio.mobile.app.svara.BaseActivity
import com.zamrud.radio.mobile.app.svara.Player.Model.PlayableModel
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Images
import com.zamrud.radio.mobile.app.svara.apiclient.services.FavoritesService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by fahziar on 21/04/2016.
 *
 * Base class for model that has some action when some event happened. THey also provide some interface
 * for view to figure out how to display this model.
 *
 * Instead of using interface, ModelWithAction is implemented in Abstract Class because Android
 * hasn't support Java 8 feature yet and some ModelWithAction method need default implementation so
 * less boilerplate code is needed.
 */
abstract class ModelWithAction : BaseModel() {
    var PLAYLIST_CONTENT_ID: String? = null

    /**
     * Get lines to be displayed in view
     * Must return list with 3 elements
     * @return Lines to be displayed
     */
    open fun getLines(): ArrayList<CharSequence?>? {
        val out: ArrayList<CharSequence?> = ArrayList(3)
        out.add("")
        out.add("")
        out.add("")
        return out
    }

    /**
     * Get lines to be displayed in search view
     * Must return list with 3 elements
     * @param context Android context
     * @return Lines to be displayed
     */
    open fun getSearchLines(context: Context): List<CharSequence?>? {
        return getLines()
    }

    /**
     * Get URL to small image
     * @return URL to image
     */
    open fun getCoverImageSmall(): String? {
        return ""
    }

    /**
     * Get URL to medium image
     * @return URL to image
     */
    open fun getCoverImageMedium(): String? {
        return ""
    }

    /**
     * Get URL to large image
     * @return URL to image
     */
    open fun getCoverImageLarge(): String? {
        return ""
    }

    /**
     * Get URL to extra large image
     * @return URL to image
     */
    open fun getCoverImageExtraLarge(): String? {
        return ""
    }

    open fun getCoverImageExtraLargeTransparent(): String? {
        return getCoverImageExtraLarge()
    }

    /**
     * Get URL to Wide image
     * @return URL to image
     */
    open fun getCoverImageWide(): String? {
        return ""
    }

    /**
     * Get URL to wide small image
     * @return URL to image
     */
    open fun getWideCoverImageSmall(): String? {
        return ""
    }

    /**
     * Get URL to wide wide medium image
     * @return URL to image
     */
    open fun getWideCoverImageMedium(): String? {
        return ""
    }

    /**
     * Get URL to wide large image
     * @return URL to image
     */
    open fun getWideCoverImageLarge(): String? {
        return ""
    }

    /**
     * Get URL to original image
     * @return URL to image
     */
    open fun getImageOri(): String? {
        return ""
    }

    open fun getPlaceholder(): String? {
        return ""
    }

    open fun getPlaceholderTransparent(): String? {
        return getPlaceholder()
    }

    abstract fun getImages(): Images?

    /**
     * Called when user click on the view. Default implementation: do nothing
     */
    open fun onClick(activity: BaseActivity) {}

    /**
     * Called when user click on the view. Default implementation: do nothing
     */
    open fun onClick(activity: BaseActivity, position: Int) {}

    /**
     * Called when user click on the view. Default implementation: do nothing
     */
    open fun onClick(activity: BaseActivity, list: ArrayList<PlayableModel>?, position: Int, isFree: Boolean) {}

    /**
     * Call when want to play this single model
     * @param activity to get player
     */
    open fun play(activity: BaseActivity) {}

    /**
     * Called when user long click on the view. Default implementation: do nothing
     */
    open fun onLongClick(activity: BaseActivity) {}

    open fun onLongClick(activity: BaseActivity, isMiniPlayer: Boolean) {}

    open fun onAddToPlaylist(activity: BaseActivity) {}

    /**
     * Called when user click on the comment icon. Default implementation: do nothing
     */
    open fun onBtnCommentClick() {}

    /**
     * Called when user click on the favorite icon. Default implementation: do nothing
     */
    open fun onBtnFavoriteClick() {}

    /**
     * Called when user click on the plus icon. Default implementation: do nothing
     */
    open fun onBtnPlusClick() {}

    override fun getId(): String? {
        return ""
    }

    override fun getContentTypeString(): String {
        return " "
    }


    open fun getAuthor(): String? {
        return ""
    }

    open fun getPlaylistContentId(): String {
        return if (PLAYLIST_CONTENT_ID == null) "" else PLAYLIST_CONTENT_ID!!
    }

    open fun setPlaylistContentId(PLAYLIST_CONTENT_ID: String?) {
        this.PLAYLIST_CONTENT_ID = PLAYLIST_CONTENT_ID
    }

    open fun hasPlaylistContentId(): Boolean {
        return PLAYLIST_CONTENT_ID != null && PLAYLIST_CONTENT_ID!!.isNotEmpty()
    }

    open fun checkIsFavorite(activity: BaseActivity, callback: Callback<Void>) {
        val mFavoritesService: FavoritesService = ServiceGenerator.createServiceWithAuth(FavoritesService::class.java, activity)
        val favoriteService: Call<Void> =
            when (ModelContract.getCategoryInt(getContentTypeString())) {
                ModelContract.CATEGORY_MUSIC -> mFavoritesService.isMusicFavorited(getId())
                ModelContract.CATEGORY_ALBUM -> mFavoritesService.isAlbumFavorited(getId())
                ModelContract.CATEGORY_ARTIST -> mFavoritesService.isArtistFavorited(getId())
                ModelContract.CATEGORY_RADIO -> mFavoritesService.isRadioFavoredVoid(getId())
                ModelContract.CATEGORY_RADIO_CONTENT -> mFavoritesService.isRadioContentFavorited(getId())
                ModelContract.CATEGORY_UPLOAD -> mFavoritesService.isUploadFavorited(getId())
                else -> mFavoritesService.isMusicFavorited(getId())
        }

        favoriteService.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setFavorite(true)
                } else {
                    setFavorite(false)
                }
                callback.onResponse(call, response)
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                callback.onFailure(call, t)
            }
        })
    }

    open fun ActionFavorite(activity: BaseActivity) {
        if (isFavorite) removeFromFavorite(activity) else addToFavorite(activity)
    }

    private fun addToFavorite(activity: BaseActivity) {
        val mFavoritesService: FavoritesService = ServiceGenerator.createServiceWithAuth(FavoritesService::class.java, activity)
        val favoriteService: Call<Void> =
            when (ModelContract.getCategoryInt(getContentTypeString())) {
                ModelContract.CATEGORY_MUSIC -> mFavoritesService.addMusicToFavorite(getId())
                ModelContract.CATEGORY_ALBUM -> mFavoritesService.addAlbumToFavorite(getId())
                ModelContract.CATEGORY_ARTIST -> mFavoritesService.addArtistToFavorite(getId())
                ModelContract.CATEGORY_RADIO -> mFavoritesService.addRadioToFavorite(getId())
                ModelContract.CATEGORY_RADIO_CONTENT -> mFavoritesService.addRadioContentToFavorite(getId())
                ModelContract.CATEGORY_UPLOAD -> mFavoritesService.addUploadToFavorite(getId())
                else -> mFavoritesService.addMusicToFavorite(getId())
            }

        favoriteService.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.isSuccessful) {
//                    activity.checkIsFavorite()
//                    activity.showSnackBar(activity.getString(R.string.dialog_success_favorite, getLines()?.get(0)))
                }
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Toast.makeText(activity, activity.getString(R.string.network_failure), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun removeFromFavorite(activity: BaseActivity) {
        val mFavoritesService: FavoritesService = ServiceGenerator.createServiceWithAuth(FavoritesService::class.java, activity)
        val favoriteService: Call<Void> = when (ModelContract.getCategoryInt(getContentTypeString())) {
                ModelContract.CATEGORY_MUSIC -> mFavoritesService.removeMusicFromFavorite(getId())
                ModelContract.CATEGORY_ALBUM -> mFavoritesService.removeAlbumFromFavorite(getId())
                ModelContract.CATEGORY_ARTIST -> mFavoritesService.removeArtistFromFavorite(getId())
                ModelContract.CATEGORY_RADIO -> mFavoritesService.removeRadioFromFavorite(getId())
                ModelContract.CATEGORY_RADIO_CONTENT -> mFavoritesService.removeRadioContentFromFavorite(
                    getId()
                )
                ModelContract.CATEGORY_UPLOAD -> mFavoritesService.removeUploadFromFavorite(getId())
                else -> mFavoritesService.removeMusicFromFavorite(getId())
        }

        favoriteService.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.isSuccessful) {
//                    activity.checkIsFavorite()
//                    activity.showSnackBar(activity.getString(R.string.dialog_success_remove_favorite, getLines()?.get(0)))
                }
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Toast.makeText(activity, activity.getString(R.string.network_failure), Toast.LENGTH_SHORT).show()
            }
        })
    }

    open fun getDynamicField(type: String?): String {
        return ""
    }

    open fun getDynamicField1(type: String?): CharSequence? {
        if (type != null && type != "") {
            val field = getDynamicField(type)
            if (field != "") {
                return field
            }
        }
        return getLines()?.get(0)
    }

    open fun getDynamicField2(type: String?): CharSequence? {
        if (type != null && type != "") {
            val field = getDynamicField(type)
            if (field != "") {
                return field
            }
        }
        return getLines()?.get(1)
    }

    open fun getDynamicField3(type: String?): CharSequence? {
        if (type != null && type != "") {
            val field = getDynamicField(type)
            if (field != "") {
                return field
            }
        }
        return getLines()?.get(2)
    }

    open fun getOwnerId(): String? {
        return ""
    }
}