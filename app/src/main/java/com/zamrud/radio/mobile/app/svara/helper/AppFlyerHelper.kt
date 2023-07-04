package com.zamrud.radio.mobile.app.svara.helper

import com.zamrud.radio.mobile.app.svara.SvaraApplication

class AppFlyerHelper(listener: SvaraApplication.SvaraAppListener) {
    private lateinit var appListener: SvaraApplication.SvaraAppListener

    companion object {
        private const val TAG = "AppFlyerHelper"
        private const val AF_DEV_KEY = "Bw7ZE7RtfGSq5iDqpi5nj5"
        private lateinit var instance: AppFlyerHelper

        fun getInstance(): AppFlyerHelper {
            return instance
        }
    }

    init {
        instance = this
    }

    fun setCustomerUserId(id: String) {}
    fun sendPageReport(pageId: String, pageName: String) {}
}
