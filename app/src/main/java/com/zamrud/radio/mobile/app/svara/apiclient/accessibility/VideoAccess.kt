package com.zamrud.radio.mobile.app.svara.apiclient.accessibility

class VideoAccess {
    @JvmField
    var canPlay = false
    @JvmField
    var onDemand = false
    @JvmField
    var hasVideoAds = false
    @JvmField
    var canNext = false
    @JvmField
    var canPref = false
    @JvmField
    var canSeek = false

    init {
        resetDefault()
    }

    fun resetDefault() {
        canPlay = true
        onDemand = true
        hasVideoAds = true
        canNext = false
        canPref = false
        canSeek = false
    }

    fun isCanPlay(): Boolean {
        return canPlay
    }

    fun setCanPlay(canPlay: Boolean) {
        this.canPlay = canPlay
    }

    fun isOnDemand(): Boolean {
        return onDemand
    }

    fun setOnDemand(onDemand: Boolean) {
        this.onDemand = onDemand
    }

    fun isHasVideoAds(): Boolean {
        return hasVideoAds
    }

    fun setHasVideoAds(hasVideoAds: Boolean) {
        this.hasVideoAds = hasVideoAds
    }

    fun isCanNext(): Boolean {
        return canNext
    }

    fun setCanNext(canNext: Boolean) {
        this.canNext = canNext
    }

    fun isCanPref(): Boolean {
        return canPref
    }

    fun setCanPref(canPref: Boolean) {
        this.canPref = canPref
    }

    fun isCanSeek(): Boolean {
        return canSeek
    }

    fun setCanSeek(canSeek: Boolean) {
        this.canSeek = canSeek
    }
}