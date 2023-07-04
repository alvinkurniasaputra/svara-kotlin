package com.zamrud.radio.mobile.app.svara.apiclient.accessibility

import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Membership

class Accessibility {

    @JvmField
    var type: String? = null
    @JvmField
    var globalAccess: GlobalAccess? = null
    @JvmField
    var musicAccess: MusicAccess? = null
    @JvmField
    var podcastAccess: PodcastAccess? = null
    @JvmField
    var radioAccess: RadioAccess? = null
    @JvmField
    var radioContentAccess: RadioContentAccess? = null
    @JvmField
    var videoAccess: VideoAccess? = null

    constructor() {
        resetDefault()
    }

    constructor(type: String?) {
        resetDefault()
        this.type = type
        when (type) {
            Membership.MEMBER_TYPE_BASIC -> basicType()
            Membership.MEMBER_TYPE_PREMIUM -> premiumType()
            Membership.MEMBER_TYPE_FREE -> freeType()
            else -> freeType()
        }
    }

    fun getType(): String? {
        return type
    }

    fun resetDefault() {
        type = " "
        globalAccess = GlobalAccess()
        musicAccess = MusicAccess()
        podcastAccess = PodcastAccess()
        radioAccess = RadioAccess()
        radioContentAccess = RadioContentAccess()
        videoAccess = VideoAccess()
    }

    private fun freeType() {
        globalAccess!!.setHasBannerAds(true)
        globalAccess!!.setCanTakeOffline(false)

        musicAccess!!.setCanNext(true)
        musicAccess!!.setCanPlay(true)
        musicAccess!!.setCanPref(false)
        musicAccess!!.setCanSeek(false)
        musicAccess!!.setCanTakeOffline(false)
        musicAccess!!.setHasAudioAds(true)
        musicAccess!!.setOnDemand(false)
        musicAccess!!.setRepeateMode(arrayListOf("NONE"))
        musicAccess!!.setUnlockShuffle(false)

        podcastAccess!!.setCanPlay(true)
        podcastAccess!!.setCanNext(true)
        podcastAccess!!.setCanPref(true)
        podcastAccess!!.setCanSeek(true)
        podcastAccess!!.setCanTakeOffline(false)
        podcastAccess!!.setHasAudioAds(true)
        podcastAccess!!.setOnDemand(true)
        podcastAccess!!.setRepeateMode(arrayListOf("NONE"))
        podcastAccess!!.setUnlockShuffle(true)

        radioContentAccess!!.setCanPlay(true)
        radioContentAccess!!.setCanNext(true)
        radioContentAccess!!.setCanPref(true)
        radioContentAccess!!.setCanSeek(true)
        radioContentAccess!!.setHasAudioAds(true)
        radioContentAccess!!.setOnDemand(true)
        radioContentAccess!!.setRepeateMode(arrayListOf("NONE"))
        radioContentAccess!!.setUnlockShuffle(true)

        radioAccess!!.setCanChat(true)
        radioAccess!!.setCanPlay(true)
        radioAccess!!.setCanPlayVisual(true)
        radioAccess!!.setHasAudioAds(true)

        videoAccess!!.setCanPlay(true)
        videoAccess!!.setOnDemand(true)
        videoAccess!!.setCanNext(true)
        videoAccess!!.setCanSeek(true)
        videoAccess!!.setCanPref(true)
        videoAccess!!.setHasVideoAds(true)
    }

    private fun basicType() {
        globalAccess!!.setHasBannerAds(false)
        globalAccess!!.setCanTakeOffline(true)

        musicAccess!!.setCanNext(true)
        musicAccess!!.setCanPlay(true)
        musicAccess!!.setCanPref(true)
        musicAccess!!.setCanSeek(false)
        musicAccess!!.setCanTakeOffline(false)
        musicAccess!!.setHasAudioAds(false)
        musicAccess!!.setOnDemand(false)
        musicAccess!!.setRepeateMode(arrayListOf("ALL", "NONE"))
        musicAccess!!.setUnlockShuffle(false)

        podcastAccess!!.setCanPlay(true)
        podcastAccess!!.setCanNext(true)
        podcastAccess!!.setCanPref(true)
        podcastAccess!!.setCanSeek(true)
        podcastAccess!!.setCanTakeOffline(false)
        podcastAccess!!.setHasAudioAds(false)
        podcastAccess!!.setOnDemand(true)
        podcastAccess!!.setRepeateMode(arrayListOf("ALL", "NONE", "ONE"))
        podcastAccess!!.setUnlockShuffle(true)

        radioContentAccess!!.setCanPlay(true)
        radioContentAccess!!.setCanNext(true)
        radioContentAccess!!.setCanPref(true)
        radioContentAccess!!.setCanSeek(true)
        radioContentAccess!!.setHasAudioAds(false)
        radioContentAccess!!.setOnDemand(true)
        radioContentAccess!!.setRepeateMode(arrayListOf("ALL", "NONE", "ONE"))
        radioContentAccess!!.setUnlockShuffle(true)

        radioAccess!!.setCanChat(true)
        radioAccess!!.setCanPlay(true)
        radioAccess!!.setCanPlayVisual(true)
        radioAccess!!.setHasAudioAds(false)

        videoAccess!!.setCanPlay(true)
        videoAccess!!.setOnDemand(true)
        videoAccess!!.setCanNext(true)
        videoAccess!!.setCanSeek(true)
        videoAccess!!.setCanPref(true)
        videoAccess!!.setHasVideoAds(false)
    }

    private fun premiumType() {
        globalAccess!!.setHasBannerAds(false)
        globalAccess!!.setCanTakeOffline(true)

        musicAccess!!.setCanNext(true)
        musicAccess!!.setCanPlay(true)
        musicAccess!!.setCanPref(true)
        musicAccess!!.setCanSeek(true)
        musicAccess!!.setCanTakeOffline(true)
        musicAccess!!.setHasAudioAds(false)
        musicAccess!!.setOnDemand(true)
        musicAccess!!.setRepeateMode(arrayListOf("ALL", "NONE", "ONE"))
        musicAccess!!.setUnlockShuffle(true)

        podcastAccess!!.setCanPlay(true)
        podcastAccess!!.setCanNext(true)
        podcastAccess!!.setCanPref(true)
        podcastAccess!!.setCanSeek(true)
        podcastAccess!!.setCanTakeOffline(true)
        podcastAccess!!.setHasAudioAds(false)
        podcastAccess!!.setOnDemand(true)
        podcastAccess!!.setRepeateMode(arrayListOf("ALL", "NONE", "ONE"))
        podcastAccess!!.setUnlockShuffle(true)

        radioContentAccess!!.setCanPlay(true)
        radioContentAccess!!.setCanNext(true)
        radioContentAccess!!.setCanPref(true)
        radioContentAccess!!.setCanSeek(true)
        radioContentAccess!!.setHasAudioAds(false)
        radioContentAccess!!.setOnDemand(true)
        radioContentAccess!!.setRepeateMode(arrayListOf("ALL", "NONE", "ONE"))
        radioContentAccess!!.setUnlockShuffle(true)

        radioAccess!!.setCanChat(true)
        radioAccess!!.setCanPlay(true)
        radioAccess!!.setCanPlayVisual(true)
        radioAccess!!.setHasAudioAds(false)

        videoAccess!!.setCanPlay(true)
        videoAccess!!.setOnDemand(true)
        videoAccess!!.setCanNext(true)
        videoAccess!!.setCanSeek(true)
        videoAccess!!.setCanPref(true)
        videoAccess!!.setHasVideoAds(false)
    }

    fun getGlobalAccess(): GlobalAccess? {
        return globalAccess
    }

    fun setGlobalAccess(globalAccess: GlobalAccess?) {
        this.globalAccess = globalAccess
    }

    fun getMusicAccess(): MusicAccess? {
        return musicAccess
    }

    fun setMusicAccess(musicAccess: MusicAccess?) {
        this.musicAccess = musicAccess
    }

    fun getPodcastAccess(): PodcastAccess? {
        return podcastAccess
    }

    fun setPodcastAccess(podcastAccess: PodcastAccess?) {
        this.podcastAccess = podcastAccess
    }

    fun getRadioAccess(): RadioAccess? {
        return radioAccess
    }

    fun setRadioAccess(radioAccess: RadioAccess?) {
        this.radioAccess = radioAccess
    }

    fun getRadioContentAccess(): RadioContentAccess? {
        return radioContentAccess
    }

    fun setRadioContentAccess(radioContentAccess: RadioContentAccess?) {
        this.radioContentAccess = radioContentAccess
    }

    fun getVideoAccess(): VideoAccess? {
        return videoAccess
    }

    fun setVideoAccess(videoAccess: VideoAccess?) {
        this.videoAccess = videoAccess
    }

}