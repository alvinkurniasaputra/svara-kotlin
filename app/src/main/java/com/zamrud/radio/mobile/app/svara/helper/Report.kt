package com.zamrud.radio.mobile.app.svara.helper

//import com.zamrud.radio.mobile.app.svara.BuildConfig

/**
 * Created by solusi247 on 21/06/16.
 */
class Report {
    @JvmField
    var email = ""
    @JvmField
    var device = ""
    @JvmField
    var message = ""
    @JvmField
    var type = ""
    @JvmField
    var appVersion: String = "0.25.7"
    @JvmField
    var accountId = ""

    fun getAppVersion(): String {
        return appVersion
    }

    fun setAppVersion(appVersion: String) {
        this.appVersion = appVersion
    }

    fun getAccountId(): String {
        return accountId
    }

    fun setAccountId(accountId: String) {
        this.accountId = accountId
    }

    fun getType(): String {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getEmail(): String {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getDevice(): String {
        return device
    }

    fun setDevice(device: String) {
        this.device = device
    }

    fun getMessage(): String {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

}