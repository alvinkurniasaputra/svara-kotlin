package com.zamrud.radio.mobile.app.svara.apiclient.model.favorites

class RadioFavorite {
    private var exist = true
    private var subscribed = false

    fun isExist(): Boolean {
        return exist
    }

    fun setExist(exist: Boolean) {
        this.exist = exist
    }

    fun isSubscribed(): Boolean {
        return subscribed
    }

    fun setSubscribed(subscribed: Boolean) {
        this.subscribed = subscribed
    }
}