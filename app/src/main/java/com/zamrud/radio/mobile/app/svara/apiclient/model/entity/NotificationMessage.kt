package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by solusi247 on 30/09/16.
 */
@Parcel
class NotificationMessage {
    @SerializedName("username")
    @Expose
    @JvmField
    var username = ""

    @SerializedName("id")
    @Expose
    @JvmField
    var id = ""

    @SerializedName("action")
    @Expose
    @JvmField
    var action = ""

    @SerializedName("contentType")
    @Expose
    @JvmField
    var contentType = ""

    @SerializedName("title")
    @Expose
    @JvmField
    var title = ""

    @SerializedName("contentId")
    @Expose
    @JvmField
    var contentId = ""

    @SerializedName("accountId")
    @Expose
    @JvmField
    var accountId = ""

    @SerializedName("data")
    @Expose
    @JvmField
    var data: NotificationMessageData = NotificationMessageData()

    init {
        username = ""
        id = ""
        action = ""
        contentType = ""
        title = ""
        contentId = ""
        accountId = ""
        data = NotificationMessageData()
    }

    fun getAccountId(): String {
        return accountId
    }

    fun setAccountId(accountId: String) {
        this.accountId = accountId
    }

    fun getData(): NotificationMessageData {
        return data
    }

    fun setData(data: NotificationMessageData) {
        this.data = data
    }

    fun getUsername(): String {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun getId(): String {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getAction(): String {
        return action
    }

    fun setAction(action: String) {
        this.action = action
    }

    fun getContentType(): String {
        return contentType
    }

    fun setContentType(contentType: String) {
        this.contentType = contentType
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getContentId(): String {
        return contentId
    }

    fun setContentId(contentId: String) {
        this.contentId = contentId
    }
}