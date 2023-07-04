package com.zamrud.radio.mobile.app.svara.apiclient.model.curation

import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.TtxModel

/**
 * Created by Hinata on 1/25/2018.
 */

class TtxCurationModel : CurationPagesMetadata() {
    @JvmField
    var enable = true
    override var hasMore = false
    @JvmField
    var contentType: String? = ""
    @JvmField
    var dataList: ArrayList<TtxModel> = ArrayList()


    fun isEnable(): Boolean {
        return enable
    }

    fun setEnable(enable: Boolean) {
        this.enable = enable
    }

    override fun isHasMore(): Boolean {
        return hasMore
    }

    override fun setHasMore(hasMore: Boolean?) {
        this.hasMore = hasMore!!
    }

    fun getContentType(): String? {
        return contentType
    }

    fun setContentType(contentType: String?) {
        this.contentType = contentType
    }

    fun getDataList(): List<TtxModel> {
        return dataList
    }

    fun setDataList(dataList: ArrayList<TtxModel>) {
        this.dataList = dataList
    }

    override fun getData(): ArrayList<out BaseModel> {
        return dataList
    }


}