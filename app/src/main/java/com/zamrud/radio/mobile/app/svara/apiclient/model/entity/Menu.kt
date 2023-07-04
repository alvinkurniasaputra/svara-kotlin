package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel

/**
 * Created by irfan on 2/5/2018.
 */
class Menu : BaseModel() {
    @SerializedName("displayName")
    @Expose
    var mDisplayName = ""

    @SerializedName("icon")
    @Expose
    var mIcon = ""

    @SerializedName("id")
    @Expose
    var mId = ""

    @SerializedName("type")
    @Expose
    var mType = ""

    @SerializedName("contentType")
    @Expose
    var mContentType = ""

    @SerializedName("contentId")
    @Expose
    var mContentId = ""

    @SerializedName("dynamic")
    @Expose
    @JvmField
    var isDynamic = false

    @SerializedName("curationPageId")
    @Expose
    @JvmField
    var curationPageId = ""

    @SerializedName("childs")
    @Expose
    @JvmField
    var childs: List<Menu>

    init {
        mDisplayName = ""
        mIcon = ""
        mId = ""
        mType = ""
        mContentType = ""
        mContentId = ""
        curationPageId = ""
        childs = ArrayList()
    }

    fun isDynamic(): Boolean {
        return isDynamic
    }

    fun setDynamic(dynamic: Boolean) {
        isDynamic = dynamic
    }

    fun getmDisplayName(): String {
        return mDisplayName
    }

    fun setmDisplayName(mDisplayName: String) {
        this.mDisplayName = mDisplayName
    }

    fun getmIcon(): String {
        return mIcon
    }

    fun setmIcon(mIcon: String) {
        this.mIcon = mIcon
    }

    fun getmId(): String {
        return getmId(false)
    }

    fun getmId(realId: Boolean): String {
        return if (mType == "search" && !realId) curationPageId else mId
    }

    fun setmId(mId: String) {
        this.mId = mId
    }

    fun getmType(): String {
        return mType
    }

    fun setmType(mType: String) {
        this.mType = mType
    }

    fun getmContentType(): String {
        return mContentType
    }

    fun setmContentType(mContentType: String) {
        this.mContentType = mContentType
    }

    fun getmContentId(): String {
        return mContentId
    }

    fun setmContentId(mContentId: String) {
        this.mContentId = mContentId
    }

    fun getChilds(): List<Menu> {
        return childs
    }

    fun setChilds(childs: List<Menu>) {
        this.childs = childs
    }

    fun getCurationPageId(): String {
        return curationPageId
    }

    fun setCurationPageId(curationPageId: String) {
        this.curationPageId = curationPageId
    }
}