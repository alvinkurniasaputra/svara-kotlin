package com.zamrud.radio.mobile.app.svara.apiclient.model

import com.google.gson.JsonObject
import com.zamrud.radio.mobile.app.svara.apiclient.model.menu.AppVersionFilter

class OpenAppDataBody(accountId: String?, locationData: JsonObject, appVersionFilter: AppVersionFilter) {

    @JvmField
    var accountId: String? = null
    @JvmField
    var locationData: JsonObject
    @JvmField
    var appVersionFilter: AppVersionFilter

    init{
        this.accountId = accountId
        this.locationData = locationData
        this.appVersionFilter = appVersionFilter
    }

    fun getAccountId(): String? {
        return accountId
    }

    fun setAccountId(accountId: String?) {
        this.accountId = accountId
    }

    fun getLocationData(): JsonObject {
        return locationData
    }

    fun setLocationData(locationData: JsonObject) {
        this.locationData = locationData
    }

    fun getAppVersionFilter(): AppVersionFilter {
        return appVersionFilter
    }

    fun setAppVersionFilter(appVersionFilter: AppVersionFilter) {
        this.appVersionFilter = appVersionFilter
    }

}
