package com.zamrud.radio.mobile.app.svara.apiclient.countly

import com.zamrud.radio.mobile.app.svara.SvaraApplication
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import ly.count.android.sdk.Countly
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.ISODateTimeFormat


class UserActivity {
    companion object {
        private const val EVENT_OPEN_APP = "openApp"
        private const val EVENT_LOGIN = "login"
        private const val EVENT_SIGNUP = "signup"

        fun sendOpenApp() {
            val segmentation: HashMap<String, Any?> = java.util.HashMap()
            segmentation["appId"] = AuthenticationUtils.getLoggeInAppUserId(SvaraApplication.getAppContext())
            segmentation["platform"] = "android"
            segmentation["accountId"] = AuthenticationUtils.getLoggeInUserId(SvaraApplication.getAppContext())
            segmentation["date"] = ISODateTimeFormat.dateTime().print(DateTime().withZone(DateTimeZone.UTC))
            segmentation["username"] = AuthenticationUtils.getUsername()
            segmentation["fullName"] = AuthenticationUtils.getFullName()
            try {
                Countly.sharedInstance().events().recordEvent(EVENT_OPEN_APP, segmentation, 1)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun sendLogin(username: String?, fullName: String?) {
            val segmentation: HashMap<String, Any?> = HashMap()
            segmentation["appId"] = AuthenticationUtils.getLoggeInAppUserId(SvaraApplication.getAppContext())
            segmentation["platform"] = "android"
            segmentation["accountId"] = AuthenticationUtils.getLoggeInUserId(SvaraApplication.getAppContext())
            segmentation["date"] = ISODateTimeFormat.dateTime().print(DateTime().withZone(DateTimeZone.UTC))
            segmentation["username"] = username
            segmentation["fullName"] = fullName
            try {
                Countly.sharedInstance().events().recordEvent(EVENT_LOGIN, segmentation, 1)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun sendSignUp(username: String?, fullName: String?) {
            val segmentation: HashMap<String, Any?> = HashMap()
            segmentation["appId"] = AuthenticationUtils.getLoggeInAppUserId(SvaraApplication.getAppContext())
            segmentation["platform"] = "android"
            segmentation["accountId"] = AuthenticationUtils.getLoggeInUserId(SvaraApplication.getAppContext())
            segmentation["date"] = ISODateTimeFormat.dateTime().print(DateTime().withZone(DateTimeZone.UTC))
            segmentation["username"] = username
            segmentation["fullName"] = fullName
            try {
                Countly.sharedInstance().events().recordEvent(EVENT_SIGNUP, segmentation, 1)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
}