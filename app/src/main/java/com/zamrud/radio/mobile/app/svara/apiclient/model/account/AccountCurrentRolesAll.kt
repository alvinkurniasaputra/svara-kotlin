package com.zamrud.radio.mobile.app.svara.apiclient.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel

class AccountCurrentRolesAll : BaseModel() {
    companion object {
        const val CONTENT_TYPE_RADIO = "Radio"
        const val CONTENT_TYPE_APP = "App"
        const val ROLES_RADIO_ADMIN = "radioadmin"
        const val ROLES_RADIO_DJ = "dj"
        const val ROLES_RADIO_MUSIC_MANAGER = "musicmanager"
        const val ROLES_RADIO_CONTENT_UPLOADER = "contentuploader"
        const val ROLES_RADIO_CONTENT_EDITOR = "contenteditor"
        const val ROLES_APP_ADMIN = "appadmin"
        const val ROLES_APP_AUTO_FOLLOW = "autofollow"
        const val ROLES_APP_CURATOR = "curator"
    }

    @SerializedName("isAdmin")
    @Expose
    private var isAdmin: Boolean? = null

    @SerializedName("roleGroups")
    @Expose
    private var roleGroups: ArrayList<RoleGroup> = ArrayList()

    fun isAdmin(): Boolean? {
        return isAdmin
    }

    fun setAdmin(admin: Boolean?) {
        isAdmin = admin
    }

    fun getRoleGroups(): ArrayList<RoleGroup> {
        return roleGroups
    }

    fun setRoleGroups(roleGroups: ArrayList<RoleGroup>) {
        this.roleGroups = roleGroups
    }

    class RoleGroup {
        @SerializedName("contentType")
        @Expose
        @JvmField
        var contentType: String? = null

        @SerializedName("dataList")
        @Expose
        private var dataList: ArrayList<BaseRole> = ArrayList()

        fun getContentType(): String? {
            return contentType
        }

        fun setContentType(contentType: String?) {
            this.contentType = contentType
        }

        fun getDataList(): ArrayList<BaseRole> {
            return dataList
        }

        fun setDataList(dataList: ArrayList<BaseRole>) {
            this.dataList = dataList
        }

        class BaseRole {
            @JvmField
            var name: String? = null
            @JvmField
            var id: String? = null

            @SerializedName("rolesName")
            @Expose
            private var rolesName: ArrayList<String> = ArrayList()

            fun getName(): String? {
                return name
            }

            fun setName(name: String?) {
                this.name = name
            }

            fun getId(): String? {
                return id
            }

            fun setId(id: String?) {
                this.id = id
            }

            fun getRolesName(): ArrayList<String> {
                return rolesName
            }

            fun setRolesName(rolesName: ArrayList<String>) {
                this.rolesName = rolesName
            }
        }
    }

}