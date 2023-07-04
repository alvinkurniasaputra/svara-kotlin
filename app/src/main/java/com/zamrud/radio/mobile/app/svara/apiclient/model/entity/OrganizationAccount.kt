package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrganizationAccount {
    @SerializedName("contentType")
    @Expose
    private var contentType = ""

    @SerializedName("dataList")
    @Expose
    private var dataList: List<OrganizationMapping> = ArrayList<OrganizationMapping>()

    //has next, count, dan totalCOunt belum tentu semua model ada
    @SerializedName("hasNext")
    @Expose
    private var hasNext = false

    @SerializedName("count")
    @Expose
    private var count = 0

    @SerializedName("totalCount")
    @Expose
    private var totalCount = 0

    fun getContentType(): String {
        return contentType
    }

    fun setContentType(contentType: String) {
        this.contentType = contentType
    }

    fun getDataList(): List<OrganizationMapping> {
        return dataList
    }

    fun setDataList(dataList: List<OrganizationMapping>) {
        this.dataList = dataList
    }

    fun getHasNext(): Boolean {
        return hasNext
    }

    fun setHasNext(hasNext: Boolean) {
        this.hasNext = hasNext
    }

    fun getCount(): Int {
        return count
    }

    fun setCount(count: Int) {
        this.count = count
    }

    fun getTotalCount(): Int {
        return totalCount
    }

    fun setTotalCount(totalCount: Int) {
        this.totalCount = totalCount
    }
}