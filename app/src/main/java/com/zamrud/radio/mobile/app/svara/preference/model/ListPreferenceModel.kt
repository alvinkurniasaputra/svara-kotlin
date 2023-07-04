package com.zamrud.radio.mobile.app.svara.preference.model

class ListPreferenceModel {
    @JvmField
    var contentType = ""
    @JvmField
    var dataList: List<BasePreferenceModel> = ArrayList()
    @JvmField
    var currentPreferences: List<CurrentPreferences> = ArrayList()

    fun getContentType(): String {
        return contentType
    }

    fun setContentType(contentType: String) {
        this.contentType = contentType
    }

    fun getDataList(): List<BasePreferenceModel> {
        return dataList
    }

    fun setDataList(dataList: List<BasePreferenceModel>) {
        this.dataList = dataList
    }

    fun getCurrentPreferences(): List<CurrentPreferences> {
        return currentPreferences
    }

    fun setCurrentPreferences(currentPreferences: List<CurrentPreferences>) {
        this.currentPreferences = currentPreferences
    }

    class CurrentPreferences {
        @JvmField
        var type = ""
        @JvmField
        var value = ""
        @JvmField
        var id = ""
        @JvmField
        var appId = ""
        @JvmField
        var accountId = ""

        fun getType(): String {
            return type
        }

        fun setType(type: String) {
            this.type = type
        }

        fun getValue(): String {
            return value
        }

        fun setValue(value: String) {
            this.value = value
        }

        fun getId(): String {
            return id
        }

        fun setId(id: String) {
            this.id = id
        }

        fun getAppId(): String {
            return appId
        }

        fun setAppId(appId: String) {
            this.appId = appId
        }

        fun getAccountId(): String {
            return accountId
        }

        fun setAccountId(accountId: String) {
            this.accountId = accountId
        }
    }
}
