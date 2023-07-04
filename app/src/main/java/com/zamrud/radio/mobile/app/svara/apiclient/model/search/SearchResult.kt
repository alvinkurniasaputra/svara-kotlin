package com.zamrud.radio.mobile.app.svara.apiclient.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction

/**
 * Created by fahziar on 30/05/2016.
 */
class SearchResult : BaseModel() {
    @SerializedName("topResult")
    @Expose
    private var mTopResult: DataListModel? = null

    @SerializedName("contentType")
    private var mContentType: String? = null

    @SerializedName("dataList")
    @Expose
    private var mDataList: List<ModelWithAction>? = null

    fun getTopResult(): DataListModel? {
        return mTopResult
    }

    fun setTopResult(topResult: DataListModel?) {
        mTopResult = topResult
    }

    fun getContentType(): String? {
        return mContentType
    }

    fun setContentType(contentType: String?) {
        mContentType = contentType
    }

    fun getDataList(): List<ModelWithAction>? {
        return mDataList
    }

    fun setDataList(dataList: List<ModelWithAction>?) {
        mDataList = dataList
    }
}