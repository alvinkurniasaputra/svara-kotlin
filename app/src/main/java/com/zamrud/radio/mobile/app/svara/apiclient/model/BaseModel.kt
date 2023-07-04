package com.zamrud.radio.mobile.app.svara.apiclient.model

import android.util.Log
//import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Feed

/**
 * Created by fahziar on 24/03/2016.
 */
abstract class BaseModel {
    companion object {
        const val SOURCE_CONTENT_FEED = "Feed"
        const val SOURCE_CONTENT_PLAYLIST = "Playlist"
        const val SOURCE_CONTENT_ARTIST = "Artist"
        const val SOURCE_CONTENT_ALBUM = "Album"
        const val SOURCE_CONTENT_SEARCH = "Search"
        const val SOURCE_CONTENT_CURATION = "CurationPageContent"
        const val SOURCE_CONTENT_LIBRARY = "Library"
        const val SOURCE_CONTENT_RADIO_PROFILE = "RadioProfile"
        const val SOURCE_CONTENT_RADIO = "Radio"
        const val SOURCE_CONTENT_MIX = "MixMusic"
        const val SOURCE_CONTENT_PROFILE = "UserProfile"
    }
    @JvmField
    var isFavorite = false
    @JvmField
    var isSelected = false
    var mSourceContentId: String? = null
    var mSourceContentType: String? = null
    var mPosition: Int = 0
//    var mFeed: Feed? = null

    @JvmField
    var isLoadingItem = false

    protected fun baseNullify() {
        mSourceContentId = null
        mSourceContentType = null
//        mFeed = null
    }

    open fun getId(): String? {
        return ""
    }

    open fun getContentTypeString(): String? {
        return " "
    }

    open fun getSourceContentId(): String {
        return if (mSourceContentId == null || mSourceContentId!!.isEmpty()) " " else mSourceContentId!!
    }

    open fun setSourceContentId(sourceContentId: String?) {
        mSourceContentId = sourceContentId
    }

    open fun getSourceContentType(): String {
        return if (mSourceContentType == null || mSourceContentType!!.isEmpty()) " " else mSourceContentType!!
    }

    open fun setSourceContentType(sourceContentType: String?) {
        mSourceContentType = sourceContentType
    }

//    open fun getFeed(): Feed? {
//        return mFeed
//    }
//
//    open fun setFeed(mFeed: Feed?) {
//        this.mFeed = mFeed
//    }

    open fun getPosition(): Int {
        return mPosition
    }

    open fun setPosition(mPosition: Int) {
        this.mPosition = mPosition
    }

    open fun onOffline(): Boolean {
        Log.e("isOffline", "on base")
        return false
    }

    open fun isSelected(): Boolean {
        return isSelected
    }

    open fun setSelected(selected: Boolean) {
        isSelected = selected
    }

    open fun isFavorite(): Boolean {
        return isFavorite
    }

    open fun setFavorite(favorite: Boolean) {
        isFavorite = favorite
    }

    open fun isLoadingItem(): Boolean {
        return isLoadingItem
    }

    open fun setLoadingItem(loadingItem: Boolean) {
        this.isLoadingItem = loadingItem
    }

    /**
     * Boolean isInList digunakan untuk tanda model ini sudah berada di dalam list yang lain
     * penggunaaan pertama untuk menandai item ini suah berada di SeriesCreateFragment.java pada SeriesBrowser
     */
    @JvmField
    var isInList = false
    open fun isInList(): Boolean {
        return isInList
    }
    open fun setInList(inList: Boolean) {
        isInList = inList
    }

}