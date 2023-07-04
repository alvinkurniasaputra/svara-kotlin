package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by irfan on 6/27/2018.
 */
class InstallationCheck {
    @JvmField
    var shouldAddInstallation = false

    @SerializedName("installation")
    @JvmField
    var instalation: Instalation? = null

    fun isShouldAddInstallation(): Boolean {
        return shouldAddInstallation
    }

    fun setShouldAddInstallation(shouldAddInstallation: Boolean) {
        this.shouldAddInstallation = shouldAddInstallation
    }

    fun getInstalation(): Instalation? {
        return instalation
    }

    fun setInstalation(instalation: Instalation?) {
        this.instalation = instalation
    }
}