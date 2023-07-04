package com.zamrud.radio.mobile.app.svara.apiclient.model

import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.AppResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Menu
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.VersionSettings

class OpenAppData {
    @JvmField
    var app: AppResponse = AppResponse()
    @JvmField
    var appSettings = AppSettings()
    @JvmField
    var versionSettings = VersionSettings()
    @JvmField
    var menus: ArrayList<Menu>? = ArrayList()

    fun getApp(): AppResponse {
        return app
    }

    fun setApp(app: AppResponse) {
        this.app = app
    }

    fun getAppSettings(): AppSettings {
        return appSettings
    }

    fun setAppSettings(appSettings: AppSettings) {
        this.appSettings = appSettings
    }

    fun getVersionSettings(): VersionSettings {
        return versionSettings
    }

    fun setVersionSettings(versionSettings: VersionSettings) {
        this.versionSettings = versionSettings
    }

    fun getMenus(): ArrayList<Menu>? {
        return menus
    }

    fun setMenus(menus: ArrayList<Menu>?) {
        this.menus = menus
    }
}