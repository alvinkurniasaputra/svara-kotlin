package com.zamrud.radio.mobile.app.svara.apiclient.accessibility

class GlobalAccess {
    @JvmField
    var hasBannerAds = false
    @JvmField
    var canTakeOffline = false

    init {
        resetDefault()
    }

    fun resetDefault() {
        hasBannerAds = true
        canTakeOffline = false
    }

    fun isHasBannerAds(): Boolean {
        return hasBannerAds
    }

    fun setHasBannerAds(hasBannerAds: Boolean) {
        this.hasBannerAds = hasBannerAds
    }

    fun isCanTakeOffline(): Boolean {
        return canTakeOffline
    }

    fun setCanTakeOffline(canTakeOffline: Boolean) {
        this.canTakeOffline = canTakeOffline
    }
}