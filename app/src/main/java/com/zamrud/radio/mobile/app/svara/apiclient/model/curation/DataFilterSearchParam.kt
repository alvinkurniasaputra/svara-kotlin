package com.zamrud.radio.mobile.app.svara.apiclient.model.curation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
class DataFilterSearchParam {
    @SerializedName("title")
    @Expose
    @JvmField
    var title: List<String>? = null

    @SerializedName("subTitle")
    @Expose
    @JvmField
    var subTitle: List<String>? = null

    @SerializedName("showSearch")
    @Expose
    @JvmField
    var showSearch = false

    init {
        title = ArrayList()
        subTitle = ArrayList()
        showSearch = true
    }

    fun getTitle(): List<String>? {
        return title
    }

    fun setTitle(title: List<String>?) {
        this.title = title
    }

    fun getSubTitle(): List<String>? {
        return subTitle
    }

    fun setSubTitle(subTitle: List<String>?) {
        this.subTitle = subTitle
    }

    fun isShowSearch(): Boolean {
        return showSearch
    }

    fun setShowSearch(showSearch: Boolean) {
        this.showSearch = showSearch
    }
}