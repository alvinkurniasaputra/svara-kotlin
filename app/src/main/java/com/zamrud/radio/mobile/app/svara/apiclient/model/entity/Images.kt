package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.UrlUtil
import org.parceler.Parcel

/**
 * Created by solusi247 on 19/08/16.
 */
@Parcel
class Images {
    @SerializedName("image64")
    @Expose
    @JvmField
    var image64: String? = ""

    @SerializedName("image150")
    @Expose
    @JvmField
    var image150: String?  = ""

    @SerializedName("image300")
    @Expose
    @JvmField
    var image300: String?  = ""

    @SerializedName("image640")
    @Expose
    @JvmField
    var image640: String?  = ""

    @SerializedName("imagew640")
    @Expose
    @JvmField
    var imagew640: String?  = ""

    @SerializedName("imageOri")
    @Expose
    @JvmField
    var imageOri: String?  = ""

    @SerializedName("placeholder")
    @Expose
    @JvmField
    var placeholder: String?  = ""

    @SerializedName("placeholderWide")
    @Expose
    @JvmField
    var placeholderWide: String?  = ""

    @SerializedName("coverImages")
    @Expose
    @JvmField
    var coverImages: CoverImages? = null

    @SerializedName("verticalImage")
    @Expose
    @JvmField
    var verticalImage: VerticalImage? = null

    fun getVerticalImage(): VerticalImage? {
        return verticalImage
    }

    fun setVerticalImage(verticalImage: VerticalImage?) {
        this.verticalImage = verticalImage
    }

    fun getImage64(): String? {
        println("REQUEST IMAGE 64 = " + UrlUtil.appendApiUrl(image64))
        return UrlUtil.appendApiUrl(image64)
    }

    fun setImage64(image64: String?) {
        this.image64 = image64
    }

    fun getImage150(): String? {
        println("REQUEST IMAGE 150 = " + UrlUtil.appendApiUrl(image150))
        return UrlUtil.appendApiUrl(image150)
    }

    fun setImage150(image150: String?) {
        this.image150 = image150
    }

    fun getImage300(): String? {
        return UrlUtil.appendApiUrl(image300)
    }

    fun setImage300(image300: String?) {
        this.image300 = image300
    }

    fun getImage640(): String? {
        return UrlUtil.appendApiUrl(image640)
    }

    fun setImage640(image640: String?) {
        this.image640 = image640
    }

    fun getImagew640(): String? {
        return UrlUtil.appendApiUrl(imagew640)
    }

    fun setImagew640(imagew640: String?) {
        this.imagew640 = imagew640
    }

    fun getImageOri(): String? {
        return imageOri
    }

    fun setImageOri(imageOri: String?) {
        this.imageOri = imageOri
    }

    fun getPlaceholder(): String? {
        return placeholder
    }

    fun setPlaceholder(placeholder: String?) {
        this.placeholder = placeholder
    }

    fun getPlaceholderWide(): String? {
        return placeholderWide
    }

    fun setPlaceholderWide(placeholderWide: String?) {
        this.placeholderWide = placeholderWide
    }

    fun getCoverImages(): CoverImages {
        return if (coverImages == null) CoverImages() else coverImages!!
    }

    fun setCoverImages(coverImages: CoverImages?) {
        this.coverImages = coverImages
    }
}