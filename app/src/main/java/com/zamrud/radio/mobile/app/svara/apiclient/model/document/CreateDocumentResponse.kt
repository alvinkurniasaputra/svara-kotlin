package com.zamrud.radio.mobile.app.svara.apiclient.model.document

import java.util.*

class CreateDocumentResponse {
    @JvmField
    var id: String? = ""
    @JvmField
    var name: String? = ""
    @JvmField
    var keyId: String? = ""
    @JvmField
    var container: String? = ""
    @JvmField
    var content: String? = ""
    @JvmField
    var fileSize: Long = 0
    @JvmField
    var provider: String? = ""
    @JvmField
    var isPrivate = false
    @JvmField
    var caption: String? = ""
    @JvmField
    var createdAt: Date? = Date()
    @JvmField
    var accountId: String? = ""
    @JvmField
    var appId: String? = ""

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getKeyId(): String? {
        return keyId
    }

    fun setKeyId(keyId: String?) {
        this.keyId = keyId
    }

    fun getContainer(): String? {
        return container
    }

    fun setContainer(container: String?) {
        this.container = container
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }

    fun getFileSize(): Long {
        return fileSize
    }

    fun setFileSize(fileSize: Long) {
        this.fileSize = fileSize
    }

    fun getProvider(): String? {
        return provider
    }

    fun setProvider(provider: String?) {
        this.provider = provider
    }

    fun isPrivate(): Boolean {
        return isPrivate
    }

    fun setPrivate(aPrivate: Boolean) {
        isPrivate = aPrivate
    }

    fun getCaption(): String? {
        return caption
    }

    fun setCaption(caption: String?) {
        this.caption = caption
    }

    fun getCreatedAt(): Date? {
        return createdAt
    }

    fun setCreatedAt(createdAt: Date?) {
        this.createdAt = createdAt
    }

    fun getAccountId(): String? {
        return accountId
    }

    fun setAccountId(accountId: String?) {
        this.accountId = accountId
    }

    fun getAppId(): String? {
        return appId
    }

    fun setAppId(appId: String?) {
        this.appId = appId
    }
}