package com.zamrud.radio.mobile.app.svara.apiclient.accessibility

class RadioAccess {
    @JvmField
    var canPlay = false
    @JvmField
    var canPlayVisual = false
    @JvmField
    var hasAudioAds = false
    @JvmField
    var canChat = false

    init {
        resetDefault()
    }

    fun resetDefault() {
        canPlay = true
        canPlayVisual = true
        hasAudioAds = true
        canChat = true
    }

    fun isCanPlay(): Boolean {
        return canPlay
    }

    fun setCanPlay(canPlay: Boolean) {
        this.canPlay = canPlay
    }

    fun isCanPlayVisual(): Boolean {
        return canPlayVisual
    }

    fun setCanPlayVisual(canPlayVisual: Boolean) {
        this.canPlayVisual = canPlayVisual
    }

    fun isHasAudioAds(): Boolean {
        return hasAudioAds
    }

    fun setHasAudioAds(hasAudioAds: Boolean) {
        this.hasAudioAds = hasAudioAds
    }

    fun isCanChat(): Boolean {
        return canChat
    }

    fun setCanChat(canChat: Boolean) {
        this.canChat = canChat
    }
}