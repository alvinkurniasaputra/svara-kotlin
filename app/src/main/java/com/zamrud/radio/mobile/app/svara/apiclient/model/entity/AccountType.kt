package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
class AccountType {
    @SerializedName("name")
    @Expose
    @JvmField
    var name: String? = null

    @SerializedName("features")
    @Expose
    @JvmField
    var featuresAccountType: FeaturesAccountType? = null

    @SerializedName("order")
    @Expose
    @JvmField
    var order = 0f

    @SerializedName("id")
    @Expose
    @JvmField
    var id: String? = null

    @SerializedName("appId")
    @Expose
    @JvmField
    var appId: String? = null

    @SerializedName("createdAt")
    @Expose
    @JvmField
    var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    @JvmField
    var updatedAt: String? = null

    @SerializedName("isDeleted")
    @Expose
    @JvmField
    var isDeleted = false

    @Parcel
    class FeaturesAccountType {
        @SerializedName("status")
        @Expose
        @JvmField
        var status = false

        @SerializedName("eCommerce")
        @Expose
        var eCommerce = false

        @SerializedName("whatsApp")
        @Expose
        @JvmField
        var whatsApp = false

        fun isStatus(): Boolean {
            return status
        }

        fun setStatus(status: Boolean) {
            this.status = status
        }

        fun iseCommerce(): Boolean {
            return eCommerce
        }

        fun seteCommerce(eCommerce: Boolean) {
            this.eCommerce = eCommerce
        }

        fun isWhatsApp(): Boolean {
            return whatsApp
        }

        fun setWhatsApp(whatsApp: Boolean) {
            this.whatsApp = whatsApp
        }
    }

    fun getOrder(): Float {
        return order
    }

    fun setOrder(order: Float) {
        this.order = order
    }

    fun getAppId(): String? {
        return appId
    }

    fun setAppId(appId: String?) {
        this.appId = appId
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    fun isDeleted(): Boolean {
        return isDeleted
    }

    fun setDeleted(deleted: Boolean) {
        isDeleted = deleted
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getFeaturesAccountType(): FeaturesAccountType {
        return featuresAccountType!!
    }

    fun setFeaturesAccountType(featuresAccountType: FeaturesAccountType) {
        this.featuresAccountType = featuresAccountType
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

}