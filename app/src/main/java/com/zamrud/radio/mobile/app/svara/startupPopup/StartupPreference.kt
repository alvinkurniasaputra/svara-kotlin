package com.zamrud.radio.mobile.app.svara.startupPopup

import android.content.Context
import android.content.SharedPreferences

class StartupPreference {
   companion object {
       private const val STARTUP = "svara.startup.showing"

       fun clear(context: Context?) {
           if (context == null) return
           val startup: SharedPreferences = context.getSharedPreferences(STARTUP, Context.MODE_PRIVATE)
           val editor = startup.edit()
           editor.clear()
           editor.apply()
       }

       fun setNeverShow(context: Context?, id: String?) {
           setNeverShow(context, id, true)
       }

       fun setNeverShow(context: Context?, id: String?, isNever: Boolean) {
           if (context == null) return
           val startup: SharedPreferences = context.getSharedPreferences(STARTUP, Context.MODE_PRIVATE)
           val editor = startup.edit()
           editor.putBoolean(id, isNever)
           editor.apply()
       }

       fun isShow(context: Context?, id: String): Boolean {
           return if (context == null) true
           else !isSaved(context, id)
       }

       private fun isSaved(context: Context, id: String): Boolean {
           val loginInfo = context.getSharedPreferences(STARTUP, Context.MODE_PRIVATE)
           return loginInfo.getBoolean(id, false)
       }
   }
}