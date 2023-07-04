package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import android.content.Context
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by solusi247 on 28/09/16.
 */
@Parcel
class Instalation {
    @SerializedName("appId")
    @Expose
    @JvmField
    var appId: String? = null

    @SerializedName("accountId")
    @Expose
    @JvmField
    var accountId: String? = null

    @SerializedName("deviceToken")
    @Expose
    @JvmField
    var deviceToken: String? = null

    @SerializedName("deviceType")
    @Expose
    @JvmField
    var deviceType: String? = "android"

    //new
    @JvmField
    var appVersion: String? = null
    @JvmField
    var platform: String? = null
    @JvmField
    var brand: String? = null
    @JvmField
    var device: String? = null
    @JvmField
    var deviceId: String? = null
    @JvmField
    var model: String? = null
    @JvmField
    var sdk: String? = null
    @JvmField
    var release: String? = null
    @JvmField
    var incremental: String? = null
//    String lastActive;

    companion object {
        fun BUILD(context: Context?, appId: String?, accountId: String?, token: String?): Instalation {
            val instalation = Instalation()
            instalation.setAppId(appId)
            instalation.setAccountId(accountId)
            instalation.setDeviceToken(token)
            return instalation
        }
    }

    fun getAppVersion(): String? {
        return appVersion
    }

    fun setAppVersion(appVersion: String?) {
        this.appVersion = appVersion
    }

    fun getPlatform(): String? {
        return platform
    }

    fun setPlatform(platform: String?) {
        this.platform = platform
    }

    fun getBrand(): String? {
        return brand
    }

    fun setBrand(brand: String?) {
        this.brand = brand
    }

    fun getDevice(): String? {
        return device
    }

    fun setDevice(device: String?) {
        this.device = device
    }

    fun getDeviceId(): String? {
        return deviceId
    }

    fun setDeviceId(deviceId: String?) {
        this.deviceId = deviceId
    }

    fun getModel(): String? {
        return model
    }

    fun setModel(model: String?) {
        this.model = model
    }

    fun getSdk(): String? {
        return sdk
    }

    fun setSdk(sdk: String?) {
        this.sdk = sdk
    }

    fun getRelease(): String? {
        return release
    }

    fun setRelease(release: String?) {
        this.release = release
    }

    fun getIncremental(): String? {
        return incremental
    }

    fun setIncremental(incremental: String?) {
        this.incremental = incremental
    }

//    public String getLastActive() {
//        return lastActive;
//    }
//
//    public void setLastActive(String lastActive) {
//        this.lastActive = lastActive;
//    }

    fun getAppId(): String? {
        return appId
    }

    fun setAppId(appId: String?) {
        this.appId = appId
    }

    fun getAccountId(): String? {
        return accountId
    }

    fun setAccountId(accountId: String?) {
        this.accountId = accountId
    }

    fun getDeviceToken(): String? {
        return deviceToken
    }

    fun setDeviceToken(deviceToken: String?) {
        this.deviceToken = deviceToken
    }

    fun getDeviceType(): String? {
        return deviceType
    }

    fun setDeviceType(deviceType: String?) {
        this.deviceType = deviceType
    }

    class InstaceId : Any() {

    }
}