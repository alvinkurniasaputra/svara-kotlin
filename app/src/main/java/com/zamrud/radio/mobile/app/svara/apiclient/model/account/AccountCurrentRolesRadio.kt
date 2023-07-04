package com.zamrud.radio.mobile.app.svara.apiclient.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel

/**
 * Created by irfan on 4/2/2018.
 */
class AccountCurrentRolesRadio : BaseModel() {

    companion object {
        const val RADIO_ROLE_ADMIN = "radioadmin"
    }

    @SerializedName("isAdmin")
    @Expose
    @JvmField
    var isAdmin: Boolean? = null

    @SerializedName("rolesName")
    @Expose
    @JvmField
    var rolesName: List<String> = ArrayList()

    fun getAdmin(): Boolean? {
        return isAdmin
    }

    fun setAdmin(admin: Boolean?) {
        isAdmin = admin
    }

    fun getRolesName(): List<String> {
        return rolesName
    }

    fun setRolesName(rolesName: List<String>) {
        this.rolesName = rolesName
    }

}