package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

/**
 * Created by irfan on 2/6/2018.
 */
class VersionSettings {
    private var menuVersion = 0
    private var featureVersion = 0

    fun getMenuVersion(): Int {
        return menuVersion
    }

    fun setMenuVersion(menuVersion: Int) {
        this.menuVersion = menuVersion
    }

    fun getFeatureVersion(): Int {
        return featureVersion
    }

    fun setFeatureVersion(featureVersion: Int) {
        this.featureVersion = featureVersion
    }
}