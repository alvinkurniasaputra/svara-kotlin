package com.zamrud.radio.mobile.app.svara.setting.localization

import android.content.Context
import com.zamrud.radio.mobile.app.svara.CustomProject
import com.zamrud.radio.mobile.app.svara.SvaraApplication
import java.util.*

class LanguageHelper {

    companion object {
        private const val LANGUAGE = "setting.language"
        private val languages: ArrayList<SvaraLanguage?> = arrayListOf(
            SvaraLanguage.init(null, "Default"),
            SvaraLanguage.init("en", "English"),
            SvaraLanguage.init("in", "Indonesia")
        )

        fun getLanguages(): ArrayList<SvaraLanguage?> {
            return languages
        }

        fun getCurrentLanguage(context: Context): String? {
            val loginInfo = context.getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE)
            return loginInfo.getString("language", CustomProject.defaultLanguage)
        }

        fun saveLanguage(svaraLanguage: SvaraLanguage) {
            val loginInfo = SvaraApplication.getAppContext()?.getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putString("language", svaraLanguage.code)
            editor.apply()
        }

        fun getSvaraLanguage(context: Context): SvaraLanguage {
            val language = getCurrentLanguage(context)
            if (language != null)
                for (svaraLanguage in languages) {
                    if (language == svaraLanguage?.getCode())
                        return svaraLanguage
            }
            return SvaraLanguage.init(null, "Default")
        }

        fun changeLanguage(context: Context) {
            val langCode: String? = getCurrentLanguage(context)
            val locale: Locale = if (langCode == null) Locale.getDefault() else Locale(langCode)
            context.resources.configuration.setLocale(locale)
            context.resources.updateConfiguration(context.resources.configuration, context.resources.displayMetrics)
        }

        fun getCode(context: Context): String? {
            val svaraLanguage: SvaraLanguage = getSvaraLanguage(context)
            return if (svaraLanguage.getCode() != null) svaraLanguage.getCode() else Locale.getDefault().language
        }
    }

}
