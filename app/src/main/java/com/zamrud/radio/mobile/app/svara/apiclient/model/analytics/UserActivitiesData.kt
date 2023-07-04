package com.zamrud.radio.mobile.app.svara.apiclient.model.analytics

import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Images
import java.util.*

class UserActivitiesData : ModelWithAction() {
    @JvmField
    var contentId: String? = ""
    @JvmField
    var contentType: String? = ""
    @JvmField
    var name: String? = ""
    @JvmField
    var appId: String? = ""
    @JvmField
    var app: String? = ""
    @JvmField
    var tsl: Long = 0
    @JvmField
    var status: String? = ""
    @JvmField
    var ts: Date? = Date()
    @JvmField
    var content: ModelWithAction? = null

    fun getContentId(): String? {
        return contentId
    }

    fun setContentId(contentId: String?) {
        this.contentId = contentId
    }

    fun getContentType(): String? {
        return contentType
    }

    fun setContentType(contentType: String?) {
        this.contentType = contentType
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getAppId(): String? {
        return appId
    }

    fun setAppId(appId: String?) {
        this.appId = appId
    }

    fun getApp(): String? {
        return app
    }

    fun setApp(app: String?) {
        this.app = app
    }

    fun getTsl(): Long {
        return tsl
    }

    fun setTsl(tsl: Long) {
        this.tsl = tsl
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getTs(): Date? {
        return ts
    }

    fun setTs(ts: Date?) {
        this.ts = ts
    }

    fun getContent(): ModelWithAction? {
        return content
    }

    fun setContent(content: ModelWithAction?) {
        this.content = content
    }

    override fun getImages(): Images? {
        return null
    }

}