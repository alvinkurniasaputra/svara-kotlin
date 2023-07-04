package com.zamrud.radio.mobile.app.svara.apiclient.model.startupPopup

class StartupPopup {
    private var contentType = ""
    private var dataList: ArrayList<StartupPopupItem> = ArrayList()

    fun getContentType(): String {
        return contentType
    }

    fun setContentType(contentType: String) {
        this.contentType = contentType
    }

    fun getDataList(): ArrayList<StartupPopupItem> {
        return dataList
    }

    fun setDataList(dataList: ArrayList<StartupPopupItem>) {
        this.dataList = dataList
    }
}