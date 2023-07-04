package com.zamrud.radio.mobile.app.svara

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.facebook.FacebookSdk.sdkInitialize
import com.zamrud.radio.mobile.app.svara.Player.Utils.Strings
import com.zamrud.radio.mobile.app.svara.Realm.InitConfiguration
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.helper.AppFlyerHelper
import com.zamrud.radio.mobile.app.svara.helper.LocaleHelper
import com.zamrud.radio.mobile.app.svara.setting.SettingUtil
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import ly.count.android.sdk.Countly
import ly.count.android.sdk.CountlyConfig
import ly.count.android.sdk.DeviceId
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class SvaraApplication : MultiDexApplication() {
    /* Up to 2 threads maximum, inactive threads are killed after 2 seconds */
    private val mThreadPool = ThreadPoolExecutor(0, 2, 2, TimeUnit.SECONDS, LinkedBlockingQueue())

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        sdkInitialize(applicationContext)
        initCountly()
        ViewPump.init(ViewPump.builder()
                .addInterceptor(CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/FontBody.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()))
                .build())

        InitConfiguration.initRealmConfig(this)

        var appFlyerHelper = AppFlyerHelper(object : SvaraAppListener {
            override fun getApp(): Application {
                return this@SvaraApplication
            }
        })
        instance = this

        if (SettingUtil.getAppSettings().getFeatures().isDisableScreenCapture())
            setupActivityListener()

//        // Initialize the database soon enough to avoid any race condition and crash
//        MediaDatabase.getInstance();
//        // Prepare cache folder constants
//        AudioUtil.prepareCacheFolder(this);
    }

    private fun setupActivityListener() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activity.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
            }
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }

    private fun initCountly() {
        val countlyConfig = CountlyConfig(this, "ecea142b0cc8bf54669755459d380cd1149d0375", ServiceGenerator.API_COUNTLY)
        countlyConfig.setIdMode(DeviceId.Type.OPEN_UDID)
        countlyConfig.enableCrashReporting()
        countlyConfig.setViewTracking(true)
        countlyConfig.setEventQueueSizeToSend(1)
        countlyConfig.setLocation(LocaleHelper.getCountryId(this), "Unknown",
            AuthenticationUtils.getLat(getAppContext()) + "," + AuthenticationUtils.getLong(getAppContext()),
            null)
        countlyConfig.setLoggingEnabled(true)
        Countly.sharedInstance().init(countlyConfig)
    }

    /**
     * Called when the overall system is running low on memory
     */
    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(TAG, "System is running low on memory")

//        BitmapCache.getInstance().clear();
    }

    companion object {
        const val TAG = "SvaraApplication"
        const val PACKAGE_NAME = "com.zamrud.radio.mobile.app.svara"
        private var instance: SvaraApplication? = null

        val SLEEP_INTENT: String = Strings.buildPkgString("SleepIntent")

        fun getVersionName(): String {
            return "0.25.7"
        }

        fun getInstance(): SvaraApplication {
            return instance!!
        }

        var sPlayerSleepTime: Calendar? = null

        /**
         * @return the main context of the Application
         */
        fun getAppContext(): Context? {
            return instance
        }

        /**
         * @return the main resources from the Application
         */
        fun getAppResources(): Resources {
            return instance!!.resources
        }

        fun runBackground(runnable: Runnable) {
            instance!!.mThreadPool.execute(runnable)
        }
    }
    interface SvaraAppListener {
        fun getApp(): Application
    }
}