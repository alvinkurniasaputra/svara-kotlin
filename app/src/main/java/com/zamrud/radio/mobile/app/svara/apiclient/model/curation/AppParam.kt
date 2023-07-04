package com.zamrud.radio.mobile.app.svara.apiclient.model.curation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.IntroCard.IntroCard

class AppParam : BaseModel() {
    @SerializedName("radioId")
    @Expose
    private var radioId: String? = null

    @SerializedName("isSingleRadio")
    @Expose
    private var isSingleRadio = false

    @SerializedName("logo")
    @Expose
    private var logo: String? = null

    @SerializedName("introCards")
    @Expose
    private var introCards: List<IntroCard>? = null

    fun getRadioId(): String? {
        return radioId
    }

    fun setRadioId(radioId: String?) {
        this.radioId = radioId
    }

    fun isSingleRadio(): Boolean {
        return isSingleRadio
    }

    fun setSingleRadio(singleRadio: Boolean) {
        isSingleRadio = singleRadio
    }

    fun getLogo(): String? {
        return logo
    }

    fun setLogo(logo: String?) {
        this.logo = logo
    }

    fun getIntroCards(): List<IntroCard>? {
        return introCards
    }

    fun setIntroCards(introCards: List<IntroCard>?) {
        this.introCards = introCards
    }
}