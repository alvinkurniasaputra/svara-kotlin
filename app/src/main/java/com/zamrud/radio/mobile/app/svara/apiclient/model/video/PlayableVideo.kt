package com.zamrud.radio.mobile.app.svara.apiclient.model.video

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
class PlayableVideo {
    @SerializedName("raw")
    @Expose
    @JvmField
    var raw = ""

    @SerializedName("hls")
    @Expose
    @JvmField
    var hls = ""

    fun getRaw(): String {
        return raw
    }

    fun setRaw(raw: String) {
        this.raw = raw
    }

    fun getHls(): String {
        return hls
    }

    fun setHls(hls: String) {
        this.hls = hls
    }
}