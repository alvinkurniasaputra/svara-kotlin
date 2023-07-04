package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by irfan on 7/4/2018.
 */

@Parcel
class NotificationMessageData {
    companion object {
        const val ROOM_TYPE_RADIO = "Radio"
        const val ROOM_TYPE_DIRECT = "direct"
    }

    @SerializedName("accountId")
    @Expose
    @JvmField
    var accountId = ""

    @SerializedName("roomType")
    @Expose
    @JvmField
    var roomType = ""

    @SerializedName("roomId")
    @Expose
    @JvmField
    var roomId = ""

    @SerializedName("roomName")
    @Expose
    @JvmField
    var roomName = ""

    init {
        var accountId = ""
        roomType = ""
        roomId = ""
        roomName = ""
    }

    fun getAccountId(): String {
        return accountId
    }

    fun setAccountId(accountId: String) {
        this.accountId = accountId
    }

    fun getRoomType(): String {
        return roomType
    }

    fun setRoomType(roomType: String) {
        this.roomType = roomType
    }

    fun getRoomId(): String {
        return roomId
    }

    fun setRoomId(roomId: String) {
        this.roomId = roomId
    }

    fun getRoomName(): String {
        return roomName
    }

    fun setRoomName(roomName: String) {
        this.roomName = roomName
    }
}