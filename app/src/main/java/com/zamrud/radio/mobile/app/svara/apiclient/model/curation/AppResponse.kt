package com.zamrud.radio.mobile.app.svara.apiclient.model.curation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel
import com.zamrud.radio.mobile.app.svara.setting.SettingUtil

class AppResponse : BaseModel() {
    @SerializedName("name")
    @Expose
    private var mName: String? = null

    @SerializedName("username")
    @Expose
    private var mUsername: String? = null

    @SerializedName("email")
    @Expose
    private var mEmail: String? = null

    @SerializedName("id")
    @Expose
    private var mId: String? = null

    @SerializedName("adsPlaceholder")
    @Expose
    private var adsPlaceholder: AdsPlaceholder? = null

    @SerializedName("appParam")
    @Expose
    private lateinit var appParam: AppParam

    @SerializedName("compressSettings")
    @Expose
    private var compressSettings = CompressSettings()

    @SerializedName("loginOption")
    @Expose
    private var loginOption: AppLoginOption = AppLoginOption()

    fun getName(): String? {
        return mName
    }

    fun setName(name: String?) {
        mName = name
    }

    fun getUsername(): String? {
        return mUsername
    }

    fun setUsername(username: String?) {
        mUsername = username
    }

    fun getEmail(): String? {
        return mEmail
    }

    fun setEmail(email: String?) {
        mEmail = email
    }

    override fun getId(): String? {
        return mId
    }

    fun setId(id: String?) {
        mId = id
    }

    fun getAdsPlaceholder(): AdsPlaceholder? {
        return adsPlaceholder
    }

    fun setAdsPlaceholder(adsPlaceholder: AdsPlaceholder?) {
        this.adsPlaceholder = adsPlaceholder
    }

    fun getAppParam(): AppParam {
        return appParam
    }

    fun setAppParam(appParam: AppParam) {
        this.appParam = appParam
    }

    fun getCompressSettings(): AppResponse.CompressSettings {
        return compressSettings
    }

    fun setCompressSettings(compressSettings: AppResponse.CompressSettings) {
        this.compressSettings = compressSettings
    }

    fun getLoginOption(): AppLoginOption {
        return loginOption
    }

    fun setLoginOption(loginOption: AppLoginOption) {
        this.loginOption = loginOption
    }

    class CompressSettings {
        @SerializedName("autoCompress")
        @Expose
        @JvmField
        var autoCompress = false

        @SerializedName("useCompressedContent")
        @Expose
        @JvmField
        var useCompressedContent = false

        @SerializedName("autoCompressOnCreate")
        @Expose
        @JvmField
        var autoCompressOnCreate = false

        @SerializedName("defaultStreamAudioQuality")
        @Expose
        @JvmField
        var defaultStreamAudioQuality: String = SettingUtil.STREAM_AUDIO_ORIGINAL

        fun isAutoCompress(): Boolean {
            return autoCompress
        }

        fun setAutoCompress(autoCompress: Boolean) {
            this.autoCompress = autoCompress
        }

        fun isUseCompressedContent(): Boolean {
            return useCompressedContent
        }

        fun setUseCompressedContent(useCompressedContent: Boolean) {
            this.useCompressedContent = useCompressedContent
        }

        fun isAutoCompressOnCreate(): Boolean {
            return autoCompressOnCreate
        }

        fun setAutoCompressOnCreate(autoCompressOnCreate: Boolean) {
            this.autoCompressOnCreate = autoCompressOnCreate
        }

        fun getDefaultStreamAudioQuality(): String {
            return defaultStreamAudioQuality
        }

        fun setDefaultStreamAudioQuality(defaultStreamAudioQuality: String?) {
            this.defaultStreamAudioQuality = defaultStreamAudioQuality!!
        }
    }
}