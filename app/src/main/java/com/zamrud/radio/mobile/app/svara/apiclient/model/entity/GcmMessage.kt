package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

/**
 * Created by solusi247 on 05/10/16.
 */
class GcmMessage {
    @JvmField
    var contentId: String? = ""
    @JvmField
    var action: String? = ""
    @JvmField
    var title: String? = ""
    @JvmField
    var contentType: String? = ""
    @JvmField
    var username: String? = ""
    @JvmField
    var id: String? = ""
    @JvmField
    var accountId: String? = ""
    @JvmField
    var unreadCount = -1

    fun getContentId(): String? {
        return contentId
    }

    fun setContentId(contentId: String?) {
        this.contentId = contentId
    }

    fun getAction(): String? {
        return action
    }

    fun setAction(action: String?) {
        this.action = action
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getContentType(): String? {
        return contentType
    }

    fun setContentType(contentType: String?) {
        this.contentType = contentType
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getAccountId(): String? {
        return accountId
    }

    fun setAccountId(accountId: String?) {
        this.accountId = accountId
    }

    fun getUnreadCount(): Int {
        return unreadCount
    }

    fun setUnreadCount(unreadCount: Int) {
        this.unreadCount = unreadCount
    }
}