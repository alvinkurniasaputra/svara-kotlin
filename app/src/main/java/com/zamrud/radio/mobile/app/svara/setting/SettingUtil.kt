package com.zamrud.radio.mobile.app.svara.setting

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zamrud.radio.mobile.app.svara.CustomProject
import com.zamrud.radio.mobile.app.svara.SvaraApplication
import com.zamrud.radio.mobile.app.svara.apiclient.model.AppSettings
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Account
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Menu
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.VersionSettings
import com.zamrud.radio.mobile.app.svara.helper.Report

import java.lang.reflect.Type

/**
 * Created by solusi247 on 17/10/16.
 */
class SettingUtil {
    companion object {
        private const val DEFAULT_OPEN_PLAYER_ON_AUTO = true
        private const val DEFAULT_OPEN_PLAYER = true

        private const val APP_SETTINGS = "svara.user.setting"
        private const val KEY_DEFAULT_HOME = "svara.default.home"
        private const val KEY_DEFAULT_NOTIF_PLAYER = "svara.default.notifplayer"
        private const val AUTO_PLAY = "app-auto-play"

        private var DEFAULT_AUTO_PLAY = true // default auto play on app open

        //menu settings
        private const val APP_MENU_VERSION_KEY = "menuVersion"
        private const val APP_FEATURE_VERSION_KEY = "appfeatureVersion"
        private const val APP_MENU_KEY = "appMenu"
        private const val APP_MENU_SIZE = "appMenuSize"
        private const val APP_REPORT_KEY = "appReport"

        const val STREAM_AUDIO_ORIGINAL = "original"
        const val STREAM_AUDIO_EXTRA_LOW = "extraLow"
        const val STREAM_AUDIO_LOW = "low"
        const val STREAM_AUDIO_NORMAL = "normal"
        const val STREAM_AUDIO_BEST = "best"
        const val STREAM_AUDIO_EXCELLENT = "excellent"

        //account setting
        private const val ACCOUNT_DETAIL_KEY = "accountDetail"

        //theme settings
        private const val THEME_KEY = "themeKey"

        fun setDefaultAutoPlay(autoPlay: Boolean) {
            DEFAULT_AUTO_PLAY = autoPlay
        }

        fun saveTheme(context: Context, info: String?) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putString(THEME_KEY, info)
            editor.apply()
        }

        fun getTheme(context: Context): String? {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            /*
            26 Oktober 2020 update default theme to light
            @Function isFirstUpdateToDefaultDarkV1 handle app lama yang default masih DARK
            kalo sudah tidak di butuhin hapus aja
            */
            val isFirstUpdateToDefaultDark = loginInfo.getBoolean("isFirstUpdateToDefaultDarkV1", true)
            if (isFirstUpdateToDefaultDark) {
                val editor = loginInfo.edit()
                editor.putBoolean("isFirstUpdateToDefaultDarkV1", false)
                editor.putString(THEME_KEY, CustomProject.THEME)
                editor.apply()
            }

            return loginInfo.getString(THEME_KEY, CustomProject.THEME)
        }

        fun saveAccountDetail(context: Context, info: Account?) {
            val json: String = Gson().toJson(info)
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putString(ACCOUNT_DETAIL_KEY, json)
            editor.apply()
        }

        fun getAccountDetail(context: Context): Account {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val m = loginInfo.getString(ACCOUNT_DETAIL_KEY, "")
            return Gson().fromJson(m, Account::class.java)
        }

        /**
         * save report that device can't send
         */
        fun saveReports(context: Context?, info: ArrayList<Report>?) {
            val loginInfo: SharedPreferences = context?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            if (info == null) {
                editor.putString(APP_REPORT_KEY, null)
                editor.apply()
                return
            }
            val listOfObjects: Type = object : TypeToken<ArrayList<Report>?>(){}.type
            val reports: String = Gson().toJson(info, listOfObjects)
            editor.putString(APP_REPORT_KEY, reports)
            editor.apply()
        }

        fun hasPendingReport(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val m = loginInfo.getString(APP_REPORT_KEY, null)
            return m != null
        }

        fun getReports(context: Context?): ArrayList<Report> {
            val loginInfo: SharedPreferences = context?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            val m = loginInfo.getString(APP_REPORT_KEY, "[]")
            var reports: ArrayList<Report> = ArrayList<Report>()
            val listOfObjects: Type = object : TypeToken<ArrayList<Report>>(){}.type
            reports = Gson().fromJson(m, listOfObjects)
            return reports
        }

        fun saveNewReport(context: Context?, info: Report) {
            val reports: ArrayList<Report> = getReports(context)
            reports.add(info)
            saveReports(context, reports)
        }

        fun saveNewReport(context: Context, email: String, device: String, message: String) {
            val report = Report()
            report.setEmail(email)
            report.setDevice(device)
            report.setMessage(message)

            saveNewReport(context, report)
        }

        fun saveMenu(context: Context, info: ArrayList<Menu>) {
            val listOfObjects: Type = object : TypeToken<ArrayList<Menu>>(){}.type
            val menus: String = Gson().toJson(info, listOfObjects)
            Log.d("saveMenu", menus)
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putString(APP_MENU_KEY, menus)
            editor.apply()
            saveMenuSize(context, info.size)
            Log.d("saveMenu", "berhasil" + loginInfo.getString(APP_MENU_KEY, "GAGAL"))
        //        Log.e("saveMenu", info);
//        SharedPreferences loginInfo = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = loginInfo.edit();
//        editor.putString(APP_MENU_KEY, info);
//        editor.apply();
//        Log.e("saveMenu", "berhasil"+loginInfo.getString(APP_MENU_KEY, "GAGAL"));
        }

        /**
         * ngambil menu dari sharedpreference, penting, method ini ga boleh di panggil di awal, karena kalau kosong pasti error
         * @param context
         * @return
         */
        fun getMenu(context: Context): ArrayList<Menu> {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val m = loginInfo.getString(APP_MENU_KEY, "[]")
            var menus: ArrayList<Menu> = ArrayList<Menu>()
            val listOfObjects: Type = object : TypeToken<ArrayList<Menu>>(){}.type
            menus = Gson().fromJson(m, listOfObjects)
            return menus
        }

        private fun saveMenuSize(context: Context, size: Int) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putInt(APP_MENU_SIZE, size)
            editor.apply()
        }

        fun getMenuSize(context: Context): Int {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            var size = loginInfo.getInt(APP_MENU_SIZE, 0)
            if (size == 0) {
                size = getMenu(context).size
            }
            return size
        }

        fun saveAppSettings(context: Context, info: VersionSettings) {
            val loginInfo = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putInt(APP_MENU_VERSION_KEY, info.getMenuVersion())
            editor.putInt(APP_FEATURE_VERSION_KEY, info.getFeatureVersion())
            editor.apply()
        }

        fun getVersionSettings(context: Context): VersionSettings {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val v = VersionSettings()
            v.setMenuVersion(loginInfo.getInt(APP_MENU_VERSION_KEY, 0))
            v.setFeatureVersion(loginInfo.getInt(APP_FEATURE_VERSION_KEY, 0))
            return v
        }

        fun getHomeTabPosition(context: Context): Int {
            val setting: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            return setting.getInt(KEY_DEFAULT_HOME, 0)
        }

        fun setHomeTabPosition(context: Context, pos: Int) {
            val setting: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val editor = setting.edit()
            editor.putInt(KEY_DEFAULT_HOME, pos)
            editor.apply()
        }

        fun setNotifPlayer(context: Context, enable: Boolean) {
            val setting: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val editor = setting.edit()
            editor.putBoolean(KEY_DEFAULT_NOTIF_PLAYER, enable)
            editor.apply()
        }

        fun getNotifPlayer(context: Context): Boolean {
            val setting: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            return setting.getBoolean(KEY_DEFAULT_NOTIF_PLAYER, true)
        }

        fun clearAll(context: Context) {
            val setting: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val editor = setting.edit()
            editor.clear()
            editor.apply()
        }

        /**
         * get setting auto play on app open
         * @param context Context to access shared preference
         * @return boolean
         */
        fun getAutoPlayOpen(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            return loginInfo.getBoolean(AUTO_PLAY, DEFAULT_AUTO_PLAY)
        }

        /**
         * set default auto play on app open
         * @param context Context to access shared preference
         * @param play boolean to set auto play
         */
        fun setAutoPlayOpen(context: Context, play: Boolean) {
            val autoPlay: SharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            val editor = autoPlay.edit()
            editor.putBoolean(AUTO_PLAY, play)
            editor.commit()
        }

        fun isDefaultOpenPlayerOnAuto(): Boolean {
            return DEFAULT_OPEN_PLAYER_ON_AUTO
        }

        fun isDefaultOpenPlayer(): Boolean {
            return DEFAULT_OPEN_PLAYER
        }

        fun setUseCompressAudio(useCompressAudio: Boolean) {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            val editor = preferences.edit()
            editor.putBoolean("useCompressAudio", useCompressAudio)
            editor.apply()
        }

        fun isUseCompressAudio(): Boolean {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            return preferences.getBoolean("useCompressAudio", false)
        }

        fun setDefaultStreamAudioQuality(quality: String?) {
            val preferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            val editor = preferences.edit()
            editor.putString("defaultStreamAudioQuality", quality)
            editor.apply()
        }

        fun getDefaultStreamAudioQuality(): String? {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            return preferences.getString("defaultStreamAudioQuality", STREAM_AUDIO_ORIGINAL)
        }

        fun setStreamAudioQuality(quality: String?) {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            val editor = preferences.edit()
            editor.putString("streamAudioQuality", quality)
            editor.apply()
        }

        fun getStreamAudioQuality(): String? {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            return preferences.getString("streamAudioQuality", getDefaultStreamAudioQuality())
        }

        fun setEnableTutorMenu(enable: Boolean) {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            val editor = preferences.edit()
            editor.putBoolean("enableTutorial", enable)
            editor.apply()
        }

        fun isEnableTutorMenu(): Boolean {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            return preferences.getBoolean("enableTutorial", false)
        }

        fun setTutorUrl(url: String?) {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            val editor = preferences.edit()
            editor.putString("tutorUrl", url)
            editor.apply()
        }

        fun getTutorUrl(): String? {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)!!
            return preferences.getString("tutorUrl", null)
        }

        private var appSettings: AppSettings = AppSettings()

        fun getAppSettings(): AppSettings {
            return appSettings
        }

        fun setAppSettings(appSettings: AppSettings) {
            SettingUtil.appSettings = appSettings
        }
    }
}