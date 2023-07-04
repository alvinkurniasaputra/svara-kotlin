package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by fahziar on 05/04/2016.
 */
@Parcel
class OrganizationMapping {
    @SerializedName("appId")
    @Expose
    @JvmField
    var appId: String? = null

    @SerializedName("accountId")
    @Expose
    @JvmField
    var accountId: String? = null

    @SerializedName("organizationAccountId")
    @Expose
    @JvmField
    var organizationAccountId: String? = null

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
    var isDeleted: Boolean? = null

    @SerializedName("id")
    @Expose
    @JvmField
    var id: String? = null

    @SerializedName("account")
    @Expose
    @JvmField
    var account: Account? = null

    @SerializedName("organization")
    @Expose
    @JvmField
    var accountOrganization: Account? = null

    @SerializedName("isOnline")
    @Expose
    @JvmField
    var isOnline: Boolean? = null

    @SerializedName("isMe")
    @Expose
    @JvmField
    var isMe = false

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

    fun getOrganizationAccountId(): String? {
        return organizationAccountId
    }

    fun setOrganizationAccountId(organizationAccountId: String?) {
        this.organizationAccountId = organizationAccountId
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

    fun getDeleted(): Boolean? {
        return isDeleted
    }

    fun setDeleted(deleted: Boolean) {
        isDeleted = deleted
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getAccount(): Account {
        return account!!
    }

    fun setAccount(account: Account) {
        this.account = account
    }

    fun getOnline(): Boolean? {
        return isOnline
    }

    fun setOnline(online: Boolean?) {
        isOnline = online
    }

    fun getAccountOrganization(): Account {
        return accountOrganization!!
    }

    fun setAccountOrganization(accountOrganization: Account) {
        this.accountOrganization = accountOrganization
    }

    fun getMe(): Boolean {
        return isMe
    }

    fun setMe(me: Boolean) {
        isMe = me
    }
}