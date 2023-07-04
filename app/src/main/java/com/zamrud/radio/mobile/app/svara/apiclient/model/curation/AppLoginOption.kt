package com.zamrud.radio.mobile.app.svara.apiclient.model.curation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AppLoginOption {
    @SerializedName("disableFacebookLogin")
    @Expose
    private var disableFacebookLogin = false

    @SerializedName("disableGoogleLogin")
    @Expose
    private var disableGoogleLogin = false

    @SerializedName("disablePhoneLogin")
    @Expose
    private var disablePhoneLogin = false

    fun isDisableFacebookLogin(): Boolean {
        return disableFacebookLogin
    }

    fun setDisableFacebookLogin(disableFacebookLogin: Boolean) {
        this.disableFacebookLogin = disableFacebookLogin
    }

    fun isDisableGoogleLogin(): Boolean {
        return disableGoogleLogin
    }

    fun setDisableGoogleLogin(disableGoogleLogin: Boolean) {
        this.disableGoogleLogin = disableGoogleLogin
    }

    fun isDisablePhoneLogin(): Boolean {
        return disablePhoneLogin
    }

    fun setDisablePhoneLogin(disablePhoneLogin: Boolean) {
        this.disablePhoneLogin = disablePhoneLogin
    }
}