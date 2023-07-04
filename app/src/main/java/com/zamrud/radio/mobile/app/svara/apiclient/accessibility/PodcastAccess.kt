package com.zamrud.radio.mobile.app.svara.apiclient.accessibility

class PodcastAccess {
    @JvmField
    var canPlay = false
    @JvmField
    var onDemand = false
    @JvmField
    var hasAudioAds = false
    @JvmField
    var canNext = false
    @JvmField
    var canPref = false
    @JvmField
    var canSeek = false
    @JvmField
    var unlockShuffle = false
    @JvmField
    var canTakeOffline = false
    @JvmField
    var repeateMode: ArrayList<String>? = null

    init {
        resetDefault()
    }

    fun resetDefault() {
        canPlay = true
        onDemand = true
        hasAudioAds = true
        canNext = true
        canPref = true
        canSeek = true
        unlockShuffle = true
        canTakeOffline = false
        repeateMode = ArrayList()
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

    fun isHasAudioAds(): Boolean {
        return hasAudioAds
    }

    fun setHasAudioAds(hasAudioAds: Boolean) {
        this.hasAudioAds = hasAudioAds
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

    fun isUnlockShuffle(): Boolean {
        return unlockShuffle
    }

    fun setUnlockShuffle(unlockShuffle: Boolean) {
        this.unlockShuffle = unlockShuffle
    }

    fun getRepeateMode(): ArrayList<String>? {
        return repeateMode
    }

    fun setRepeateMode(repeateMode: ArrayList<String>?) {
        this.repeateMode = repeateMode
    }

    fun isCanTakeOffline(): Boolean {
        return canTakeOffline
    }

    fun setCanTakeOffline(canTakeOffline: Boolean) {
        this.canTakeOffline = canTakeOffline
    }
}