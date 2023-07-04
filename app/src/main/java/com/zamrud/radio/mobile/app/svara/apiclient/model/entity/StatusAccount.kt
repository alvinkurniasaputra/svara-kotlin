package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
class StatusAccount {
    @SerializedName("name")
    @Expose
    @JvmField
    var defaultName: String? = null

    @SerializedName("type")
    @Expose
    @JvmField
    var defaultType: String? = null

    @SerializedName("color")
    @Expose
    @JvmField
    var defaultColor: String? = null

    @SerializedName("available")
    @Expose
    @JvmField
    var statusAccountList: ArrayList<StatusAccount> = ArrayList()

    fun getDefaultName(): String? {
        return defaultName
    }

    fun setDefaultName(defaultName: String?) {
        this.defaultName = defaultName
    }

    fun getDefaultType(): String? {
        return defaultType
    }

    fun setDefaultType(defaultType: String?) {
        this.defaultType = defaultType
    }

    fun getDefaultColor(): String? {
        return defaultColor
    }

    fun setDefaultColor(defaultColor: String?) {
        this.defaultColor = defaultColor
    }

    fun getStatusAccountList(): ArrayList<StatusAccount> {
        return statusAccountList
    }

    fun setStatusAccountList(defaultAccountList: ArrayList<StatusAccount>) {
        statusAccountList = defaultAccountList
    }
}