package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

/**
 * Created by solusi247 on 05/10/16.
 */
class GcmNotif {
    companion object {
        const val KEY_SENT_TIME = "google.sent_time"
        const val KEY_MESSAGE_ID = "google.message_id"
        const val KEY_MESSAGE = "message"
        const val KEY_COLLAPSE = "collapse_key"

        const val ACTION_NONE = "None"
        const val ACTION_OPEN_PAGE = "OpenPage"
        const val ACTION_PLAY = "Play"

        const val TYPE_NOTIFICATION = "Notification"
        const val TYPE_PAYMENT = "Payment"
        const val TYPE_REFERRAL = "Referral"
        const val TYPE_TAP = "TAP"
        const val TYPE_ACCOUNT = "Account"
        const val TYPE_MUSIC = "Music"
        const val TYPE_ALBUM = "Album"
        const val TYPE_PLAYLIST = "Playlist"
        const val TYPE_ARTIST = "Artist"
        const val TYPE_UPLOAD = "Upload"
        const val TYPE_RADIO_CONTENT = "RadioContent"
        const val TYPE_DOCUMENT = "Document"
        const val TYPE_VIDEO = "Video"
        const val TYPE_RADIO = "Radio"
        const val TYPE_ARTICLE = "Article"
        const val TYPE_FEED = "Feed"
        const val TYPE_URL = "URL"
    }

    private var sent_time = 0
    private var message_id: String? = null
    private var message: ArrayList<GcmMessage>? = null
    private var collapse_key: String? = null

    fun getSent_time(): Int {
        return sent_time
    }

    fun setSent_time(sent_time: Int) {
        this.sent_time = sent_time
    }

    fun getMessage_id(): String? {
        return message_id
    }

    fun setMessage_id(message_id: String?) {
        this.message_id = message_id
    }

    fun getMessage(): ArrayList<GcmMessage>? {
        return message
    }

    fun setMessage(message: ArrayList<GcmMessage>?) {
        this.message = message
    }

    fun getCollapse_key(): String? {
        return collapse_key
    }

    fun setCollapse_key(collapse_key: String?) {
        this.collapse_key = collapse_key
    }

}