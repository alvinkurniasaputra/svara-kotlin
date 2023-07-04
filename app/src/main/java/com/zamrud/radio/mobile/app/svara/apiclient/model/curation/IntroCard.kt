package com.zamrud.radio.mobile.app.svara.apiclient.model.IntroCard

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel

/**
 * Created by fahziar on 13/04/2016.
 */
class IntroCard : BaseModel() {
    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("image")
    @Expose
    private lateinit var image: IntroImage

    @SerializedName("colorHex")
    @Expose
    private var colorHex: String? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getImage(): IntroImage {
        return image
    }

    fun setImage(image: IntroImage) {
        this.image = image
    }

    fun getColorHex(): String? {
        return colorHex
    }

    fun setColorHex(colorHex: String?) {
        this.colorHex = colorHex
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    class IntroImage {
        @SerializedName("url")
        @Expose
        private var url: String? = null

        @SerializedName("placeholder")
        @Expose
        private var placeholder: String? = null

        fun getUrl(): String? {
            return url
        }

        fun setUrl(url: String?) {
            this.url = url
        }

        fun getPlaceholder(): String? {
            return placeholder
        }

        fun setPlaceholder(placeholder: String?) {
            this.placeholder = placeholder
        }
    }

}