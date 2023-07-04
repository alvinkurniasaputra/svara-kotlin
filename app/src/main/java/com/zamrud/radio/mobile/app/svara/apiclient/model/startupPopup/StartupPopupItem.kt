package com.zamrud.radio.mobile.app.svara.apiclient.model.startupPopup

import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Images

class StartupPopupItem {
    private var title = ""
    private var image = ""
    private var type = ""
    private var message = ""
    private var action = ""
    private var order = 0f
    private var enabled = false
    private var skippable = true
    private var appId = ""
    private var actionName = ""
    private var id = ""
    private var hasAction = false
    private var isOneTime = false
    private var images: Images? = null
    private var contentId = ""
    private var contentType = ""

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getImage(): String {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun getType(): String {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getMessage(): String {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getAction(): String {
        return action
    }

    fun setAction(action: String) {
        this.action = action
    }

    fun getOrder(): Float {
        return order
    }

    fun setOrder(order: Float) {
        this.order = order
    }

    fun isEnabled(): Boolean {
        return enabled
    }

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    fun isSkippable(): Boolean {
        return skippable
    }

    fun setSkippable(skippable: Boolean) {
        this.skippable = skippable
    }

    fun getAppId(): String {
        return appId
    }

    fun setAppId(appId: String) {
        this.appId = appId
    }

    fun getActionName(): String {
        return actionName
    }

    fun setActionName(actionName: String) {
        this.actionName = actionName
    }

    fun getId(): String {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun isHasAction(): Boolean {
        return hasAction
    }

    fun setHasAction(hasAction: Boolean) {
        this.hasAction = hasAction
    }

    fun isOneTime(): Boolean {
        return isOneTime
    }

    fun setOneTime(oneTime: Boolean) {
        isOneTime = oneTime
    }

    fun getImages(): Images? {
        return if (images == null) Images() else images
    }

    fun setImages(images: Images?) {
        this.images = images
    }

    fun getContentId(): String {
        return contentId
    }

    fun setContentId(contentId: String) {
        this.contentId = contentId
    }

    fun getContentType(): String {
        return contentType
    }

    fun setContentType(contentType: String) {
        this.contentType = contentType
    }
}