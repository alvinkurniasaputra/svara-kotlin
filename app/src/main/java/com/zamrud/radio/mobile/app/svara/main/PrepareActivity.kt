package com.zamrud.radio.mobile.app.svara.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.zamrud.radio.mobile.app.svara.BuildConfig
import com.zamrud.radio.mobile.app.svara.CustomProject
import com.zamrud.radio.mobile.app.svara.GCM.MyGcmListenerService
import com.zamrud.radio.mobile.app.svara.Player.Utils.ActionUtil
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.Realm.RealmMenu
import com.zamrud.radio.mobile.app.svara.SvaraApplication
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import com.zamrud.radio.mobile.app.svara.apiclient.ResponseHelper
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.apiclient.countly.UserActivity
import com.zamrud.radio.mobile.app.svara.apiclient.exception.HttpRequestException
import com.zamrud.radio.mobile.app.svara.apiclient.ipAddress.IpResponse
import com.zamrud.radio.mobile.app.svara.apiclient.ipAddress.IpService
import com.zamrud.radio.mobile.app.svara.apiclient.model.AppSettings
import com.zamrud.radio.mobile.app.svara.apiclient.model.OpenAppData
import com.zamrud.radio.mobile.app.svara.apiclient.model.OpenAppDataBody
import com.zamrud.radio.mobile.app.svara.apiclient.model.account.LoginAppResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.account.LoginResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.AppLoginOption
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.AppParam
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.AppResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.GcmNotif
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Menu
import com.zamrud.radio.mobile.app.svara.apiclient.model.menu.AppVersionFilter
import com.zamrud.radio.mobile.app.svara.apiclient.services.AccountsService
import com.zamrud.radio.mobile.app.svara.apiclient.services.AppService
import com.zamrud.radio.mobile.app.svara.apiclient.services.CurationService
import com.zamrud.radio.mobile.app.svara.apiclient.services.TypeAccount
import com.zamrud.radio.mobile.app.svara.databinding.ActivityPrepareBinding
import com.zamrud.radio.mobile.app.svara.helper.NotificationHelpers
import com.zamrud.radio.mobile.app.svara.helper.Report
import com.zamrud.radio.mobile.app.svara.helper.adsId.AdsHelper
import com.zamrud.radio.mobile.app.svara.helper.adsId.AdsHelper.AdsCallback
import com.zamrud.radio.mobile.app.svara.helper.image.DownloadMenuIconAsync
import com.zamrud.radio.mobile.app.svara.introCard.IntroActivity
import com.zamrud.radio.mobile.app.svara.setting.SettingUtil
import com.zamrud.radio.mobile.app.svara.setting.localization.LanguageHelper
import pl.tajchert.nammu.Nammu
import pl.tajchert.nammu.PermissionCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.net.UnknownHostException
import java.util.*

class PrepareActivity : AppCompatActivity() {

    companion object {
        private const val PLAY_SERVICES_RESOLUTION_REQUEST = 9000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LanguageHelper.changeLanguage(this)
        if (CustomProject.useAnimateSplashScreen) {
            val binding = ActivityPrepareBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.svTag.alpha = 0f
            // TODO: 3/18/2021 handling this for white labeling
            if (binding.svAnim.drawable is AnimatedVectorDrawable) {
                val animator = binding.svAnim.drawable as AnimatedVectorDrawable
                animator.start()
            }
            if (binding.svText.drawable is AnimatedVectorDrawable) {
                val animator = binding.svText.drawable as AnimatedVectorDrawable
                animator.start()
            }
            binding.svTag.animate().setDuration(1000).setStartDelay(1000).alpha(1.0f).start()
        }
        Nammu.init(applicationContext)
        FirebaseApp.initializeApp(applicationContext)
        if (!AuthenticationUtils.hasDeviceToken() || !AuthenticationUtils.isInstalationSuccess()) {
            getDeviceToken()
            return
        }
        onDeviceTokenReady()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun isPlayServiceAvailable(): Boolean {
        val apiAvailability: GoogleApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode: Int = apiAvailability.isGooglePlayServicesAvailable(this)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                Objects.requireNonNull(
                    apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST))
                    ?.show()
            } else {
                Timber.i("This device is not supported.")
                finish()
            }
            return false
        }
        return true
    }

    private fun getDeviceToken() {
        if (isPlayServiceAvailable())
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task: Task<String?> ->
            if (!task.isSuccessful) {
                Toast.makeText(applicationContext, getString(R.string.failure_getting_GCM), Toast.LENGTH_SHORT).show()
            } else if (task.result != null) {
                val token: String? = task.result
                AuthenticationUtils.saveDeviceToken(token)
                AuthenticationUtils.sendInstallationIfNeeded(token)
            }
            onDeviceTokenReady()
        }
    }

    private fun getLocation() {
        Nammu.askForPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, object : PermissionCallback {
                override fun permissionGranted() {
                    AuthenticationUtils.getLocation(this@PrepareActivity)
                    getAllDataApp()
                }

                override fun permissionRefused() {
                    getAllDataApp()
                }
            })
    }

    private fun getAllDataApp() {
        val jsonObject = JsonObject()
        val jsonArray = JsonArray()
        jsonArray.add(AuthenticationUtils.getLong(applicationContext))
        jsonArray.add(AuthenticationUtils.getLat(applicationContext))
        jsonObject.add("location", jsonArray)

        val openAppDataBody = OpenAppDataBody(
            AuthenticationUtils.getLoggeInUserId(applicationContext),
            jsonObject,
            AppVersionFilter(BuildConfig.VERSION_NAME, "android"))
        ServiceGenerator.createServiceWithAuth(AppService::class.java, applicationContext)
            .getAllDataOpenApp(AuthenticationUtils.getLoggeInAppUserId(applicationContext), openAppDataBody)
            .enqueue(object : ResponseHelper<OpenAppData>() {
                override fun onSuccess(body: OpenAppData?) {
                    //getApp
                    if (body?.getApp()?.getAdsPlaceholder() != null && body.getApp().getAdsPlaceholder()!!.getZones() != null && body.getApp().getAdsPlaceholder()!!.getZones()?.getAudio() != null) {
                        AuthenticationUtils.saveAudioAdsId(body.getApp().getAdsPlaceholder()!!.getZones()?.getAudio())
                    }

                    AuthenticationUtils.setAppName(body?.getApp()?.getName())
                    AuthenticationUtils.setAppRadioId(applicationContext, body?.getApp()?.getAppParam()?.getRadioId())

                    //appSettings
                    val appSettings: AppSettings? = body?.getAppSettings()
                    AuthenticationUtils.setAdMobDisable(applicationContext, appSettings?.getFeatures()!!.isDisableAdMob())

                    AuthenticationUtils.setEnablepayment(applicationContext, appSettings.getFeatures().isEnablePayment())
                    AuthenticationUtils.setEnableReferral(applicationContext, appSettings.getFeatures().isEnableReferral())
                    AuthenticationUtils.setEnablePreference(applicationContext, appSettings.getFeatures().isEnablePreference())
                    AuthenticationUtils.setEnableTheme(applicationContext, appSettings.getFeatures().isEnableTheme())
                    AuthenticationUtils.setDisableSearch(applicationContext, appSettings.getFeatures().isDisableSearch())
                    AuthenticationUtils.setShowDrawer(applicationContext, appSettings.getFeatures().isShowDrawer())
                    AuthenticationUtils.setShowNotificationDrawer(applicationContext, appSettings.getFeatures().isShowNotificationDrawer())
                    AuthenticationUtils.setEnableProfileMenu(applicationContext, appSettings.getFeatures().isEnableProfileMenu())
                    AuthenticationUtils.setEnablePrivateDoc(applicationContext, appSettings.getFeatures().isEnablePrivateDoc())
                    AuthenticationUtils.setEnableArticle(applicationContext, appSettings.getFeatures().isEnableArticle())
                    AuthenticationUtils.setEnableVideo(applicationContext, appSettings.getFeatures().isEnableVideo())
                    SettingUtil.setDefaultAutoPlay(!appSettings.getFeatures().isDisableFirstTimeAutoPlay())
                    AuthenticationUtils.setForceUpdate(applicationContext, appSettings.getAndroidInfo().isForceUpdate())
                    AuthenticationUtils.setAppContent(appSettings.getContents())
                    AuthenticationUtils.setBaseShareUrl(appSettings.getFeatures().getBaseShareUrl())
                    AuthenticationUtils.saveShareText(appSettings.getNaming().getShareText())
                    AuthenticationUtils.saveChannelActionButton(appSettings.getActionButtons())
                    SettingUtil.setUseCompressAudio(body.getApp().getCompressSettings().isUseCompressedContent())
                    SettingUtil.setDefaultStreamAudioQuality(body.getApp().getCompressSettings().getDefaultStreamAudioQuality())
                    SettingUtil.setEnableTutorMenu(appSettings.getFeatures().isEnableTutorialMenu())
                    SettingUtil.setTutorUrl(appSettings.getFeatures().getTutorialUrl())
                    SettingUtil.setAppSettings(appSettings)

                    getCountAccountType()

                    // settings version
                    SettingUtil.saveAppSettings(applicationContext, body.getVersionSettings())
                    if (body.getMenus() != null && !body.getMenus()!!.isEmpty())
                        SettingUtil.saveMenu(applicationContext, body.getMenus()!!)
                    checkMenuIconExist()
                }

                override fun onError(message: String?) {
                    checkMenuIconExist()
                }
            })
    }

    private fun getCountAccountType() {
        val appService: AppService = ServiceGenerator.createServiceWithAuth(AppService::class.java, this)
        appService.getAccountType(AuthenticationUtils.getLoggeInAppUserId(this))
            .enqueue(object : ResponseHelper<TypeAccount>() {
                override fun onSuccess(body: TypeAccount?) {
                    if (body == null) return
                    showMenuChangeAccount(body.getCount())
                }
            })
    }

    private fun showMenuChangeAccount(count: Int) {
        if (count > 1) {
            AuthenticationUtils.setShowSwitchAccount(applicationContext, true)
        }
    }

    private fun onDeviceTokenReady() {
        registerFirebaseMessaging()
        ServiceGenerator.createService(IpService::class.java).getIp()
            .enqueue(object : Callback<IpResponse> {
                override fun onResponse(call: Call<IpResponse>, response: Response<IpResponse>) {
                    if (response.body() != null)
                        AuthenticationUtils.setPublicIp(
                        response.body()!!.getIp()
                    )
                    getAdsId()
                }

                override fun onFailure(call: Call<IpResponse>, t: Throwable) {
                    getAdsId()
                }
            })
    }

    private fun registerFirebaseMessaging() {
        if (AuthenticationUtils.isLoggedIn(applicationContext)) {
            FirebaseMessaging.getInstance().subscribeToTopic(AuthenticationUtils.getLoggeInAppUserId(applicationContext)!!)
        }
    }

    private fun getAdsId() {
        AdsHelper(object : AdsCallback {
            override fun onFinish() {
                if (AuthenticationUtils.isLoggedIn(applicationContext)) {
                    Log.d("log1", "udh login")
                    UserActivity.sendOpenApp()
                    if (SettingUtil.hasPendingReport(applicationContext)) sendUnsentReport()
                    getAppLoginOption()
                    getLocation()
                    return
                }
                if (AuthenticationUtils.showIntro()) {
                    Log.d("log1", "intro")
                    prepareIntro()
                    return
                }
                if (CustomProject.autoSkip) {
                    Log.d("log1", "skiplogin")
                    forceGuest()
                } else {
                    Log.d("log1", "gaskiplogin")
                    prepareLoginPage()
                }
            }
        }).execute()
    }

    fun loginApp(callback: Callback<LoginAppResponse?>) {
        AuthenticationUtils.appLogin(applicationContext, callback)
    }

    private fun forceGuest() {
        loginApp(object : Callback<LoginAppResponse?> {
            override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    loginGuest(response.body())
                } else {
                    showErrorMessage(HttpRequestException(400))
                }
            }

            override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                showErrorMessage(t)
            }
        })
    }

    fun loginGuest(loginAppResponse: LoginAppResponse?) {
        @SuppressLint("HardwareIds")
        val android_id = Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
        ServiceGenerator.createService(AccountsService::class.java).loginGuest(android_id, loginAppResponse!!.getAppId(),
            "android", null, loginAppResponse.getAccessToken())
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        AuthenticationUtils.saveLoginInformation(response.body()!!, true, response.body()!!.getUserId(), applicationContext)
                        UserActivity.sendLogin("guest", "guest")
                        getAllDataApp()
                        getAppLoginOption()
                    } else {
                        showErrorMessage(HttpRequestException(400))
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    showErrorMessage(t)
                }
            })
    }

    fun showErrorMessage(t: Throwable) {
        if (applicationContext == null)
            return
        if (t is UnknownHostException) {
            Toast.makeText(applicationContext, getString(R.string.inter_failed_communicate_to_server), Toast.LENGTH_LONG).show()
        } else if (t is HttpRequestException) {
            val e: HttpRequestException = t as HttpRequestException
            if (e.getStatusCode() == 401) {
                Toast.makeText(applicationContext, e.getmMessage(), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, getString(R.string.inter_failed_communicate_to_server), Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(applicationContext, getString(R.string.inter_failed_communicate_to_server), Toast.LENGTH_LONG).show()
        }
    }

    private fun sendUnsentReport() {
        val reports: List<Report> = SettingUtil.getReports(this)
        SettingUtil.saveReports(this, null)
        for (report in reports)
            sendReport(report)
    }

    private fun sendReport(report: Report) {
        val service: com.zamrud.radio.mobile.app.svara.apiclient.services.Report = ServiceGenerator
            .createServiceWithAuth(com.zamrud.radio.mobile.app.svara.apiclient.services.Report::class.java, this)
        val reportCall: Call<Report> = service.REPORT(report)
        reportCall.enqueue(object : Callback<Report> {
            override fun onResponse(call: Call<Report>, response: Response<Report>) {
                if (!response.isSuccessful)
                    SettingUtil.saveNewReport(SvaraApplication.getAppContext(), report)
            }

            override fun onFailure(call: Call<Report>, t: Throwable) {
                SettingUtil.saveNewReport(SvaraApplication.getAppContext(), report)
            }
        })
    }

    private fun checkMenuIconExist() {
        val menus: List<Menu> = SettingUtil.getMenu(applicationContext)
        val noIcon: MutableList<String> = ArrayList()
        for (menu in menus) {
            if (!RealmMenu.isIconExist(menu.getmIcon()))
                noIcon.add(menu.getmIcon())
        }
        if (noIcon.isEmpty()) {
            startMainActivity()
            return
        }

        DownloadMenuIconAsync(noIcon, object : DownloadMenuIconAsync.DownloadImageListener {
            override fun onComplete() {
                startMainActivity()
            }

            override fun getContext(): Context {
                return this@PrepareActivity
            }
        }).execute()
    }

    private fun prepareIntro() {
        AuthenticationUtils.appLogin(this, object : Callback<LoginAppResponse?> {
            override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    val curationService: CurationService = ServiceGenerator.createServiceAppAuth(CurationService::class.java, response.body()!!.getAccessToken())
                    curationService.getApp(response.body()!!.getAppId())
                        .enqueue(object : Callback<AppResponse> {
                            override fun onResponse(call: Call<AppResponse>, response: Response<AppResponse>) {
                                if (response.isSuccessful && response.body() != null) {
                                    val appResponse: AppResponse = response.body()!!
                                    //save audioZoneAdsId if not null
                                    if (appResponse.getAdsPlaceholder() != null && appResponse.getAdsPlaceholder()!!.getZones() != null && appResponse.getAdsPlaceholder()!!.getZones()!!.getAudio() != null) {
                                        AuthenticationUtils.saveAudioAdsId(appResponse.getAdsPlaceholder()!!.getZones()!!.getAudio())
                                    }
                                    SettingUtil.setUseCompressAudio(response.body()!!.getCompressSettings().isUseCompressedContent())
                                    SettingUtil.setDefaultStreamAudioQuality(response.body()!!.getCompressSettings().getDefaultStreamAudioQuality())
                                    AuthenticationUtils.setAppLoginOption(applicationContext, appResponse.getLoginOption())
                                    startIntro(appResponse.getAppParam())
                                } else startIntro(null)
                            }

                            override fun onFailure(call: Call<AppResponse>, t: Throwable) {
                                disableLoginOption()
                                startIntro(null)
                            }
                        })
                } else {
                    disableLoginOption()
                    startIntro(null)
                }
            }

            override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                startIntro(null)
            }
        })
    }

    private fun getAppLoginOption() {
        AuthenticationUtils.appLogin(this, object : Callback<LoginAppResponse?> {
            override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    val curationService: CurationService = ServiceGenerator.createServiceAppAuth(CurationService::class.java, response.body()!!.getAccessToken())
                    curationService.getApp(response.body()!!.getAppId())
                        .enqueue(object : Callback<AppResponse> {
                            override fun onResponse(call: Call<AppResponse>, response: Response<AppResponse>) {
                                if (response.isSuccessful && response.body() != null) {
                                    val appResponse: AppResponse = response.body()!!
                                    //save audioZoneAdsId if not null
                                    if (appResponse.getAdsPlaceholder() != null && appResponse.getAdsPlaceholder()!!.getZones() != null && appResponse.getAdsPlaceholder()!!.getZones()!!.getAudio() != null) {
                                        AuthenticationUtils.saveAudioAdsId(appResponse.getAdsPlaceholder()!!.getZones()!!.getAudio())
                                    }
                                    SettingUtil.setUseCompressAudio(response.body()!!.getCompressSettings().isUseCompressedContent())
                                    SettingUtil.setDefaultStreamAudioQuality(response.body()!!.getCompressSettings().getDefaultStreamAudioQuality())
                                    AuthenticationUtils.setAppLoginOption(applicationContext, appResponse.getLoginOption())
                                }
                            }

                            override fun onFailure(call: Call<AppResponse>, t: Throwable) {
                                disableLoginOption()
                            }
                        })
                } else {
                    disableLoginOption()
                }
            }

            override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {}
        })
    }

    fun startIntro(param: AppParam?) {
        val i = Intent(this, IntroActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        i.putExtra("AppParam", Gson().toJson(param))
        startActivity(i)
        finish()
    }

    private fun createAction(type: String): String {
        return when (type.lowercase(Locale.getDefault())) {
            ActionUtil.TYPE_RADIO_CONTENT, ActionUtil.TYPE_UPLOAD, ActionUtil.TYPE_MUSIC, ActionUtil.TYPE_VIDEO -> GcmNotif.ACTION_PLAY
            ActionUtil.TYPE_ARTIST, ActionUtil.TYPE_ALBUM, ActionUtil.TYPE_PLAYLIST, ActionUtil.TYPE_RADIO, ActionUtil.TYPE_ARTICLE, ActionUtil.TYPE_FEED -> GcmNotif.ACTION_OPEN_PAGE
            ActionUtil.TYPE_REFERRAL -> ActionUtil.ACTION_REFERRAL_REDEEM
            else -> ""
        }
    }

    /**
     * handle opened from sharing link
     *
     * @param intent intent to next activity
     * @param bundle bundle from current activity
     */
    private  fun handleSharingLink(intent: Intent, bdl: Bundle?) {
        val data: Uri = intent.data ?: return

        var bundle = bdl
        if (bundle == null) {
            bundle = Bundle()
        }

        val segments: List<String> = data.pathSegments
        if (segments.size == 3 && segments.contains("sharing")) {
            val key: String = segments[1]
            intent.action = createAction(key)
            bundle.putString(ActionUtil.KEY_TYPE, key)
            bundle.putString(ActionUtil.KEY_ID, segments[2])
            intent.putExtras(bundle)
        } else if (segments.size == 2) {
            val key: String = segments[0]
            intent.action = createAction(key)
            bundle.putString(ActionUtil.KEY_TYPE, key)
            bundle.putString(ActionUtil.KEY_ID, segments[1])
            intent.putExtras(bundle)
        }
    }

    private fun createIntent(cls: Class<*>): Intent {
        val intent: Intent = intent.clone() as Intent
        intent.setClass(this, cls)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

        val bundle: Bundle? = intent.extras

        if (bundle != null && bundle.containsKey(GcmNotif.KEY_MESSAGE_ID)
            && bundle.containsKey(MyGcmListenerService.KEY_MESSAGE)) {

            NotificationHelpers.create().handleDataNotification(intent, bundle)
        }

        if (bundle != null && bundle.containsKey(GcmNotif.KEY_MESSAGE_ID)
            && bundle.containsKey(MyGcmListenerService.KEY_CONTENT_TYPE)) {
            NotificationHelpers.create().handleNotificationAction(intent, bundle)
        }

        handleSharingLink(intent, bundle)

//        intent.putExtra("mode", LoginActivity.MODE_REQUIRE_GUEST);
        return intent
    }

    private fun startMainActivity() {
        AuthenticationUtils.checkAccessibility(this, object : ResponseHelper<Boolean>() {
            override fun onSuccess(body: Boolean?) {
                startActivity(createIntent(MainActivity::class.java))
                finish()
            }
        })
    }

    private fun prepareLoginPage() {
        AuthenticationUtils.appLogin(this, object : Callback<LoginAppResponse?> {
            override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    val curationService: CurationService = ServiceGenerator.createServiceAppAuth(CurationService::class.java, response.body()!!.getAccessToken())
                    curationService.getApp(response.body()!!.getAppId())
                        .enqueue(object : ResponseHelper<AppResponse>() {
                            override fun onSuccess(body: AppResponse?) {
                                AuthenticationUtils.setAppLoginOption(applicationContext, body!!.getLoginOption())
                                startLoginActivity()
                            }

                            override fun onError(message: String?) {
                                disableLoginOption()
                                startLoginActivity()
                            }
                        })
                } else {
                    disableLoginOption()
                    startLoginActivity()
                }
            }

            override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                disableLoginOption()
            }
        })
    }

    private fun disableLoginOption() {
        val appLoginOption = AppLoginOption()
        appLoginOption.setDisableFacebookLogin(true)
        appLoginOption.setDisableGoogleLogin(true)
        appLoginOption.setDisablePhoneLogin(true)
        AuthenticationUtils.setAppLoginOption(applicationContext, appLoginOption)
    }

    private fun startLoginActivity() {
        startActivity(createIntent(LoginActivity::class.java))
        finish()
    }

}