package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.UrlUtil
import org.parceler.Parcel

/**
 * Created by solusi247 on 22/09/16.
 */
@Parcel
class CoverImages {
    @SerializedName("coverImage300")
    @Expose
    @JvmField
    var coverImage300: String? = ""

    @SerializedName("coverImage640")
    @Expose
    @JvmField
    var coverImage640: String? = ""

    @SerializedName("coverImage1280")
    @Expose
    @JvmField
    var coverImage1280: String? = ""

    @SerializedName("placeholder")
    @Expose
    @JvmField
    var placeholder: String? = ""

    fun getCoverImage300(): String? {
        return UrlUtil.appendApiUrl(coverImage300)
    }

    fun setCoverImage300(coverImage300: String?) {
        this.coverImage300 = coverImage300
    }

    fun getCoverImage640(): String? {
        return UrlUtil.appendApiUrl(coverImage640)
    }

    fun setCoverImage640(coverImage640: String?) {
        this.coverImage640 = coverImage640
    }

    fun getCoverImage1280(): String? {
        return UrlUtil.appendApiUrl(coverImage1280)
    }

    fun setCoverImage1280(coverImage1280: String?) {
        this.coverImage1280 = coverImage1280
    }

    fun getPlaceholder(): String? {
        return placeholder
    }

    fun setPlaceholder(placeholder: String?) {
        this.placeholder = placeholder
    }
}