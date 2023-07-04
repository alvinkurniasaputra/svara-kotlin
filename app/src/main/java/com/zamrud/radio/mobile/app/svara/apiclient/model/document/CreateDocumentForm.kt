package com.zamrud.radio.mobile.app.svara.apiclient.model.document

import com.zamrud.radio.mobile.app.svara.SvaraApplication
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils

class CreateDocumentForm {
    @JvmField
    var name: String? = ""
    @JvmField
    var caption: String? = ""
    @JvmField
    var isPrivate = false
    @JvmField
    var container: String? = ""
    @JvmField
    var key: String? = ""
    @JvmField
    var keyId: String? = ""
    @JvmField
    var fileSize: Long = 0
    @JvmField
    var provider: String? = ""
    @JvmField
    var ttxIds: List<String>? = null
    @JvmField
    var raporUser: List<DocumentReceiver>? = null
    @JvmField
    var appId: String? = ""
    @JvmField
    var image: String? = null
    @JvmField
    var postToTimeline = false

    init {
        appId = AuthenticationUtils.getLoggeInAppUserId(SvaraApplication.getAppContext())
        ttxIds = ArrayList()
        raporUser = ArrayList()
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getCaption(): String? {
        return caption
    }

    fun setCaption(caption: String?) {
        this.caption = caption
    }

    fun isPrivate(): Boolean {
        return isPrivate
    }

    fun setPrivate(aPrivate: Boolean) {
        isPrivate = aPrivate
    }

    fun getContainer(): String? {
        return container
    }

    fun setContainer(container: String?) {
        this.container = container
    }

    fun getKey(): String? {
        return key
    }

    fun setKey(key: String?) {
        this.key = key
    }

    fun getKeyId(): String? {
        return keyId
    }

    fun setKeyId(keyId: String?) {
        this.keyId = keyId
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

    fun getTtxIds(): List<String>? {
        return ttxIds
    }

    fun setTtxIds(ttxIds: List<String>?) {
        this.ttxIds = ttxIds
    }

    fun getAppId(): String? {
        return appId
    }

    fun setAppId(appId: String?) {
        this.appId = appId
    }

    fun isPostToTimeline(): Boolean {
        return postToTimeline
    }

    fun setPostToTimeline(postToTimeline: Boolean) {
        this.postToTimeline = postToTimeline
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String?) {
        this.image = image
    }

    fun getRaporUser(): List<DocumentReceiver>? {
        return raporUser
    }

    fun setRaporUser(raporUser: List<DocumentReceiver>?) {
        this.raporUser = raporUser
    }
}