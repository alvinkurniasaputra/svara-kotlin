package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
class AudioCdn {
    @SerializedName("provider")
    @Expose
    @JvmField
    var provider: String? = null

    @SerializedName("original")
    @Expose
    @JvmField
    var original: String? = null

    @SerializedName("extraLow")
    @Expose
    @JvmField
    var extraLow: String? = null

    @SerializedName("low")
    @Expose
    @JvmField
    var low: String? = null

    @SerializedName("normal")
    @Expose
    @JvmField
    var normal: String? = null

    @SerializedName("best")
    @Expose
    @JvmField
    var best: String? = null

    @SerializedName("excellent")
    @Expose
    @JvmField
    var excellent: String? = null

    fun getProvider(): String? {
        return provider
    }

    fun setProvider(provider: String?) {
        this.provider = provider
    }

    fun getOriginal(): String? {
        return original
    }

    fun setOriginal(original: String?) {
        this.original = original
    }

    fun getExtraLow(): String? {
        return extraLow
    }

    fun setExtraLow(extraLow: String?) {
        this.extraLow = extraLow
    }

    fun getLow(): String? {
        return low
    }

    fun setLow(low: String?) {
        this.low = low
    }

    fun getNormal(): String? {
        return normal
    }

    fun setNormal(normal: String?) {
        this.normal = normal
    }

    fun getBest(): String? {
        return best
    }

    fun setBest(best: String?) {
        this.best = best
    }

    fun getExcellent(): String? {
        return excellent
    }

    fun setExcellent(excellent: String?) {
        this.excellent = excellent
    }
}