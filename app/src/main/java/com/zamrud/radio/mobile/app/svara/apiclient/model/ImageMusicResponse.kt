package com.zamrud.radio.mobile.app.svara.apiclient.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageMusicResponse : BaseModel() {
    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("coverArtS")
    @Expose
    private var coverArtS: String? = null

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getCoverArtS(): String? {
        return coverArtS
    }

    fun setCoverArtS(coverArtS: String?) {
        this.coverArtS = coverArtS
    }
}