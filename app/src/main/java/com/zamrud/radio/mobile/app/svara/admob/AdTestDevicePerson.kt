package com.zamrud.radio.mobile.app.svara.admob

class AdTestDevicePerson {
    @JvmField
    var name: String? = null
    @JvmField
    var id: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }
}