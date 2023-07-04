package com.zamrud.radio.mobile.app.svara.apiclient.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fahziar on 13/04/2016.
 */
class DataListObjectModel : BaseModel() {
    @SerializedName("contentType")
    @Expose
    private var contentType = ""

    @SerializedName("data")
    @Expose
    private var data: JsonObject? = null

    @SerializedName("autoplay")
    @Expose
    private var autoplay = false

    fun getContentType(): String {
        return contentType
    }

    fun setContentType(contentType: String) {
        this.contentType = contentType
    }

    fun getData(): JsonObject? {
        return data
    }

    fun setData(data: JsonObject?) {
        this.data = data
    }

    fun isAutoplay(): Boolean {
        return autoplay
    }

    fun setAutoplay(autoplay: Boolean) {
        this.autoplay = autoplay
    }

}