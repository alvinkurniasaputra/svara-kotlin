package com.zamrud.radio.mobile.app.svara.Player.Model

import android.support.v4.media.MediaMetadataCompat
import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.AudioCdn

/**
 * Created by Hinata on 3/8/2018.
 */
abstract class PlayableModel : ModelWithAction() {
    @JvmField
    var durationMedia: Long = 0
    @JvmField
    var isFree = false
    @JvmField
    protected var contentTypeInt: Int = ModelContract.CATEGORY_MUSIC

    abstract fun getMediaBuilder(): MediaMetadataCompat.Builder?
    abstract fun getAudio(): String?
    abstract fun getAudioCdn(): AudioCdn?
    abstract fun getCoverImage(): String?
    abstract fun getContentTypeInt(): Int
    abstract fun getAlbumId(): String?
    abstract fun getArtistId(): String?
    abstract fun getRadioId(): String?
    abstract fun isVideo(): Boolean
    abstract fun useCookie(): Boolean

    open fun getVideoUrl(): String? {
        return ""
    }

    open fun isFree(): Boolean {
        return isFree
    }

    open fun setFree(free: Boolean) {
        isFree = free
    }

    open fun getDurationMedia(): Long {
        return durationMedia
    }

    open fun setDurationMedia(duration: Long) {
        durationMedia = duration
    }

    protected open fun setContentTypeInt(contentTypeInt: Int) {
        this.contentTypeInt = contentTypeInt
    }
}