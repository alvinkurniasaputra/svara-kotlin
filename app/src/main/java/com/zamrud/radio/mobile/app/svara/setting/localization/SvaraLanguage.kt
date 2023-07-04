package com.zamrud.radio.mobile.app.svara.setting.localization

class SvaraLanguage(@JvmField var code: String?, @JvmField var name: String) {

    companion object {
        fun init(code: String?, name: String): SvaraLanguage {
            return SvaraLanguage(code, name)
        }
    }

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String) {
        this.code = code
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

}
