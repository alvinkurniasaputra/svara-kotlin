package com.zamrud.radio.mobile.app.svara.apiclient.model.apps

class ChannelActionButton(@JvmField var type: String, @JvmField var enable: Boolean) {
    @JvmField
    var active = false

    fun getType(): String {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun isEnable(): Boolean {
        return enable
    }

    fun setEnable(enable: Boolean) {
        this.enable = enable
    }

    fun isActive(): Boolean {
        return active
    }

    fun setActive(active: Boolean) {
        this.active = active
    }
}