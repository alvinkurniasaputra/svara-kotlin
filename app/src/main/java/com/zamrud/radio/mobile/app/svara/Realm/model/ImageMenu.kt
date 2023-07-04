package com.zamrud.radio.mobile.app.svara.Realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ImageMenu : RealmObject() {
    @PrimaryKey
    private var id: String? = null
    private var data: ByteArray? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getData(): ByteArray? {
        return data
    }

    fun setData(data: ByteArray?) {
        this.data = data
    }
}