package com.zamrud.radio.mobile.app.svara.apiclient

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import com.facebook.login.LoginManager
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.zamrud.radio.mobile.app.svara.BuildConfig
import com.zamrud.radio.mobile.app.svara.Player.Utils.PlayerUtils
import com.zamrud.radio.mobile.app.svara.SvaraApplication
import com.zamrud.radio.mobile.app.svara.apiclient.accessibility.Accessibility
import com.zamrud.radio.mobile.app.svara.apiclient.exception.HttpRequestException
import com.zamrud.radio.mobile.app.svara.apiclient.model.AppSettings
import com.zamrud.radio.mobile.app.svara.apiclient.model.account.*
import com.zamrud.radio.mobile.app.svara.apiclient.model.apps.ChannelActionButton
import com.zamrud.radio.mobile.app.svara.apiclient.model.apps.ChannelActionButtonTypes
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.AppLoginOption
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.AppResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Account
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Instalation
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.InstallationCheck
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Membership
import com.zamrud.radio.mobile.app.svara.apiclient.services.AccountsService
import com.zamrud.radio.mobile.app.svara.apiclient.services.CurationService
import com.zamrud.radio.mobile.app.svara.apiclient.services.InstallationService
import com.zamrud.radio.mobile.app.svara.apiclient.services.LoginOther
import com.zamrud.radio.mobile.app.svara.main.LoginActivity
import com.zamrud.radio.mobile.app.svara.setting.SettingUtil
import com.zamrud.radio.mobile.app.svara.startupPopup.StartupPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

//import com.facebook.accountkit.AccountKit;
/**
 * Created by fahziar on 24/03/2016.
 */
class AuthenticationUtils {
    companion object {
        private const val APP_LOGIN_PREFERENCE_KEY = "appLoginPreference"
        private const val APP_INTRO_PREFERENCE_KEY = "appIntroPreference"
        private const val LOGIN_PREFERENCE_KEY = "loginPreference"
        private const val APP_SETTING_PREFERENCE_KEY = "appSettingPreference"
        private const val USERID_KEY = "userid"
        private const val USERACCOUNTTYPE_KEY = "userAccountType"
        private const val USEREMAIL_KEY = "userEmail"
        private const val TOKEN_KEY = "token"
        private const val DEVICE_TOKEN_KEY = "deviceToken"
        private const val EXPIRY_KEY = "expiry"
        private const val GUEST_KEY = "guest"
        private const val ISME_KEY = "isMe"
        private const val REALACCOUNT_KEY = "realAccount"
        private const val ADS_AUDIO_KEY = "adsaudio"
        private const val INTRO = "intro"

        //    private static final String APP_USER = "mari-radio";
        //    private static final String APP_PASSWORD = "m4r1r4d10$kudacepatberlari#";
        //    private static final String APP_USER = "radio-009927";
        //    private static final String APP_PASSWORD = "g3nl1t3r4d10";
        private const val APP_USER = "svara-debug"
        private const val APP_PASSWORD = "svara-debug"
        //    private static final String APP_PASSWORD = "qwlsidofjklsdjflkijlkjlsdf";
        //    private static final String USER_MEMBERSHIP = "user-membership";
        //    private static final String USER_TYPE = "user-type";
        private const val APP_LOCATION = "app-location"
        private const val APP_LAT = "app-lat"
        private const val APP_LONG = "app-long"

        //ROLES
        private const val USER_ROLES = "user-roles"
        private const val ROLES_KEY = "accountCurrentRolesAll"
        private const val ROLES_ROLE_GROUP = "roleGroup"
        private const val IS_ADMIN_KEY = "isAdmin"
        private const val IS_RADIO_MUSIC_MANAGER_KEY = "isRadioMusicManager"
        private const val IS_RADIO_ADMIN_KEY = "isRadioAdmin"
        private const val IS_APP_ADMIN_KEY = "isAppAdmin"
        private const val INSTALLATION_KEY = "installation.key"

        fun getAppId(): String {
            return APP_USER
        }

        fun saveAppInformation(context: Context, appResponse: LoginAppResponse) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.clear()
            editor.putString(USERID_KEY, appResponse.getAppId())
            editor.apply()
        }

        fun saveLoginInformation(info: LoginResponse?, isMe: Boolean, realAccountId: String?, context: Context) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.clear()
            editor.putString(TOKEN_KEY, info?.getAccessToken())
            editor.putString(USERID_KEY, info?.getUserId())
            editor.putString(USERACCOUNTTYPE_KEY, info?.getAccountTypeId())
            editor.putLong(EXPIRY_KEY, System.currentTimeMillis() + ((info?.getTtl()!! - 10L) * 1000L))
            editor.putBoolean(GUEST_KEY, info.isGuest())
            editor.putBoolean(ISME_KEY, isMe)
            editor.putString(REALACCOUNT_KEY, realAccountId)
            editor.apply()
        }

        fun getCurrentRoles(context: Context) {
            ServiceGenerator.createServiceWithAuth(AccountsService::class.java, context)
                .getCurrentRoles(getLoggeInUserId(context))
                .enqueue(object : Callback<AccountCurrentRolesAll?> {
                    override fun onResponse(call: Call<AccountCurrentRolesAll?>, response: Response<AccountCurrentRolesAll?>) {
                        if (response.isSuccessful) {
//                            Log.e("currentRoles", new Gson().toJson(response.body()));
                            saveRolesInformation(response.body(), context)
                        } else {
                            Log.e("currentRoles", "notsuccess: " + response.message())
                        }
                    }

                    override fun onFailure(call: Call<AccountCurrentRolesAll?>, t: Throwable) {
                        Log.e("currentRoles", "fail: " + t.message)
                    }
                })
        }

        /**
         * save all current user roles to sharedPreferencce
         *
         * @param accountCurrentRolesAll all roles that user has
         * @param context                Contex used for reading shared preference
         */
        fun saveRolesInformation(accountCurrentRolesAll: AccountCurrentRolesAll?, context: Context) {
            if (accountCurrentRolesAll != null) {
                val loginInfo: SharedPreferences = context.getSharedPreferences(USER_ROLES, Context.MODE_PRIVATE)
                val editor = loginInfo.edit()
                editor.clear()
                editor.putBoolean(IS_ADMIN_KEY, accountCurrentRolesAll.isAdmin()!!)
                val roles: String = Gson().toJson(accountCurrentRolesAll)
                editor.putString(ROLES_KEY, roles)

                if (accountCurrentRolesAll.getRoleGroups().isNotEmpty()) {
                    for (i in 0 until accountCurrentRolesAll.getRoleGroups().size) {
                        val roleGroup: AccountCurrentRolesAll.RoleGroup = accountCurrentRolesAll.getRoleGroups()[i]
                        if (roleGroup.getContentType().equals(AccountCurrentRolesAll.CONTENT_TYPE_RADIO)) {
                            editor.putString(ROLES_ROLE_GROUP, Gson().toJson(roleGroup))
                            for (j in 0 until roleGroup.getDataList().size) {
                                val rolesName: ArrayList<String> = roleGroup.getDataList()[j].getRolesName()
                                for (k in 0 until rolesName.size) {
                                    val name = rolesName[k]
                                    if (name == AccountCurrentRolesAll.ROLES_RADIO_ADMIN) {
                                        editor.putBoolean(IS_RADIO_ADMIN_KEY, true)
                                    } else if (name == AccountCurrentRolesAll.ROLES_RADIO_MUSIC_MANAGER) {
                                        editor.putBoolean(IS_RADIO_MUSIC_MANAGER_KEY, true)
                                    }
                                }
                            }
                        } else if (roleGroup.getContentType().equals(AccountCurrentRolesAll.CONTENT_TYPE_APP)) {
                            for (j in 0 until roleGroup.getDataList().size) {
                                val role: AccountCurrentRolesAll.RoleGroup.BaseRole = roleGroup.getDataList()[j]
                                if (role.getId() == getLoggeInAppUserId(context)) {
                                    val rolesName: ArrayList<String> = role.getRolesName()
                                    for (k in rolesName.indices) {
                                        if (rolesName[k] == AccountCurrentRolesAll.ROLES_APP_ADMIN) {
                                            editor.putBoolean(IS_APP_ADMIN_KEY, true)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                editor.apply()
            }
        }

        fun getRolesData(context: Context): AccountCurrentRolesAll.RoleGroup {
            val loginInfo: SharedPreferences = context.getSharedPreferences(USER_ROLES, Context.MODE_PRIVATE)
            return Gson().fromJson(loginInfo.getString(ROLES_ROLE_GROUP, null), AccountCurrentRolesAll.RoleGroup::class.java)
        }

        fun isAdmin(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(USER_ROLES, Context.MODE_PRIVATE)
            return loginInfo.getBoolean(IS_ADMIN_KEY, false)
        }

        fun isRadioAdmin(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(USER_ROLES, Context.MODE_PRIVATE)
            return loginInfo.getBoolean(IS_RADIO_ADMIN_KEY, false)
        }

        fun isRadioMusicManager(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(USER_ROLES, Context.MODE_PRIVATE)
            return loginInfo.getBoolean(IS_RADIO_MUSIC_MANAGER_KEY, false)
        }

        fun isAppAdmin(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(USER_ROLES, Context.MODE_PRIVATE)
            return loginInfo.getBoolean(IS_APP_ADMIN_KEY, false)
        }

        /**
         * Get saved token. Doesnt handle expired token.
         *
         * @param context Contex used for reading shared preference
         * @return Saved token
         */
        fun getToken(context: Context?): String? {
            val loginInfo: SharedPreferences =
                if (context != null)
                    context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
                else SvaraApplication.getAppContext()?.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val expiry = loginInfo.getLong(EXPIRY_KEY, System.currentTimeMillis())
            if (expiry <= System.currentTimeMillis()) {
                Log.w("client api", "Login expired")
            }
            return loginInfo.getString(TOKEN_KEY, "")
        }

        /**
         * Check if user logged in
         *
         * @param context Context to access shared preference
         * @return True if user logged in
         */
        fun isLoggedIn(context: Context?): Boolean {
            val loginInfo: SharedPreferences = context!!.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return !loginInfo.getString(TOKEN_KEY, "")!!.isEmpty() &&
                    loginInfo.getLong(EXPIRY_KEY, System.currentTimeMillis()) > System.currentTimeMillis()
        }

        /**
         * Check if user login guest
         *
         * @param context Context to access shared preference
         * @return true if guest
         */
        fun isGuest(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean(GUEST_KEY, false)
        }

        /**
         * Log in to server and save the api token if successful
         *
         * @param signUpRequest SignUpRequest
         * @param context       Activity context used for saving shared preference
         * @param callback      Called when login process is done
         */
        fun signUp(signUpRequest: SignUpRequest, context: Context, appToken: String?, callback: AuthenticationCallback) {
            val accountsService: AccountsService = ServiceGenerator.createServiceAppAuth(AccountsService::class.java, appToken)
            val apiCall: Call<LoginResponse> = accountsService.SIGNUP(signUpRequest)

            apiCall.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse?>) {
                    if (response.isSuccessful) {
                        callback.onSuccess()
                    } else {
                        //TODO: handle com.google.gson.JsonSyntaxException
                        val e: com.zamrud.radio.mobile.app.svara.apiclient.model.Error? = ErrorUtils.parseError(response)
                        callback.onFailure(Throwable(e?.getMessage()))
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(javaClass.name, "Error:" + t.message)
                    callback.onFailure(t)
                }
            })
        }

        /**
         * Log in to server and save the api token if successful
         *
         * @param username Username
         * @param password Password
         * @param context  Activity context used for saving shared preference
         * @param callback Called when login process is done
         */
        fun login(username: String?, password: String?, context: Context, appToken: String?, callback: AuthenticationCallback) {
            val accountsService: AccountsService = ServiceGenerator.createServiceAppAuth(AccountsService::class.java, appToken)
            val apiCall: Call<LoginResponse> = accountsService.login(LoginRequest(username, password, false))

            apiCall.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        if (response.body()!!.getTtl() > 0) {
                            val loginResponse: LoginResponse? = response.body()
                            saveLoginInformation(loginResponse, true, loginResponse?.getUserId(), context)
//                            setInstallation(context);
                            callback.onSuccess()
                        } else {
                            callback.onFailure(HttpRequestException(401))
                        }
                    } else {
                        //TODO: handle com.google.gson.JsonSyntaxException
                        val e: com.zamrud.radio.mobile.app.svara.apiclient.model.Error? = ErrorUtils.parseError(response)
                        callback.onFailure(HttpRequestException(response.code(), e?.getMessage()))
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(javaClass.name, "Error:" + t.message)
                    callback.onFailure(t)
                }
            })
        }

        fun setInstallation(context: Context?, token: String?, callback: Callback<Instalation>) {
            val installationService: InstallationService = ServiceGenerator.createServiceWithAuth(InstallationService::class.java, context)
            val instalation: Instalation = Instalation.BUILD(context, getLoggeInAppUserId(context), getLoggeInUserId(context), token)
            instalation.setAppVersion(BuildConfig.VERSION_NAME)
            instalation.setPlatform("android")
            instalation.setBrand(Build.BRAND)
            instalation.setDeviceType("android")
            instalation.setPlatform("android")
            instalation.setDevice(Build.DEVICE)
            instalation.setDeviceId(Build.ID)
            instalation.setModel(Build.MODEL)
            instalation.setSdk(Build.VERSION.SDK_INT.toString() + "")
            instalation.setRelease(Build.SERIAL)
            instalation.setIncremental(Build.MANUFACTURER)

            val call: Call<Instalation> = installationService.instalation(instalation)
            call.enqueue(callback)
        }

        fun sendInstallationIfNeeded(deviceToken: String?) {
            saveDeviceToken(deviceToken)
            if (!isLoggedIn(SvaraApplication.getAppContext()))
                return
            val installationService: InstallationService = ServiceGenerator.createServiceWithAuth(InstallationService::class.java, SvaraApplication.getAppContext())
            installationService.getInstallationCheck(getLoggeInUserId(SvaraApplication.getAppContext()), deviceToken)
                .enqueue(object : Callback<InstallationCheck?> {
                    override fun onResponse(call: Call<InstallationCheck?>, response: Response<InstallationCheck?>) {
                        if (response.isSuccessful && response.body() != null) {
                            val installationCheck: InstallationCheck? = response.body()
                            if (installationCheck!!.isShouldAddInstallation()) {
                                createInstallation(deviceToken)
                            } else updateInstallation(deviceToken)
                        }
                    }

                    override fun onFailure(call: Call<InstallationCheck?>, t: Throwable) {}
                })
        }

        private fun createInstallation(deviceToken: String?) {
            setInstallation(SvaraApplication.getAppContext(), deviceToken, object : Callback<Instalation> {
                    override fun onResponse(call: Call<Instalation?>, response: Response<Instalation?>) {
                        if (response.isSuccessful)
                            setInstallationSuccess(true)
                        else setInstallationSuccess(false)
                    }

                    override fun onFailure(call: Call<Instalation?>, t: Throwable) {
                        setInstallationSuccess(false)
                    }
                })
        }

        private fun updateInstallation(deviceToken: String?) {
            ServiceGenerator.createServiceWithAuth(InstallationService::class.java, SvaraApplication.getAppContext())
                .putInstallationSession(deviceToken)
                .enqueue(object : Callback<Void?> {
                    override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                        if (response.isSuccessful)
                            setInstallationSuccess(true)
                        else setInstallationSuccess(false)
                    }

                    override fun onFailure(call: Call<Void?>, t: Throwable) {
                        t.printStackTrace()
                        setInstallationSuccess(false)
                    }
                })
        }

        fun saveDeviceToken(token: String?) {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences("DEVICE_TOKEN", Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putString(DEVICE_TOKEN_KEY, token)
            editor.apply()
        }

        fun getDeviceTokenSaved(): String? {
            val savedToken: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences("DEVICE_TOKEN", Context.MODE_PRIVATE)!!
            return savedToken.getString(DEVICE_TOKEN_KEY, "")
        }

        fun hasDeviceToken(): Boolean {
            return getDeviceTokenSaved() != ""
        }

        /**
         * check installation to server state
         *
         * @return
         */
        open fun isInstalationSuccess(): Boolean {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            return loginInfo.getBoolean(INSTALLATION_KEY, false)
        }

        /**
         * save installation to server state
         *
         * @param success success or not
         */
        private fun setInstallationSuccess(success: Boolean) {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putBoolean(INSTALLATION_KEY, success)
            editor.apply()
        }

        /**
         * login with facebook
         *
         * @param access_token Access token facebook
         * @param app_id       Aplication id
         * @param context      Activity context used for saving shared preference
         * @param callback     Called when login process is done
         */
        fun loginFacebook(appToken: String?, access_token: String?, app_id: String?, context: Context, callback: LoginOtherCallback) {
            val loginOther: LoginOther = ServiceGenerator.connectFacebook(LoginOther::class.java, context, appToken)
            val apiCall: Call<LoginResponse> = loginOther.GET_TOKEN(access_token, app_id)
            apiCall.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse: LoginResponse? = response.body()
                    Log.e("....", Gson().toJson(response.body()))
                    saveLoginInformation(loginResponse, true, loginResponse?.getUserId(), context)
                    callback.onSuccess(loginResponse)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(javaClass.name, "Error:" + t.message)
                    callback.onFailure(t)
                }
            })
        }

        /**
         * login with google
         *
         * @param id_token Id token google
         * @param app_id   Aplication id
         * @param context  Activity context used for saving shared preference
         * @param callback Called when login process is done
         */
        fun loginGoogle(appToken: String?, id_token: String?, app_id: String?, context: Context, callback: LoginOtherCallback) {
            val loginOther: LoginOther = ServiceGenerator.connectFacebook(LoginOther::class.java, context, appToken)
            val apiCall: Call<LoginResponse> = loginOther.LOGIN_GOOGLE(id_token, app_id)
            apiCall.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse: LoginResponse? = response.body()
                    Log.e("....", Gson().toJson(response.body()))
                    saveLoginInformation(loginResponse, true, loginResponse?.getUserId(), context)
                    callback.onSuccess(loginResponse)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(javaClass.name, "Error:" + t.message)
                    callback.onFailure(t)
                }
            })
        }

        /**
         * login with phone number
         *
         * @param phone    Id token google
         * @param appId    Aplication id
         * @param context  Activity context used for saving shared preference
         * @param callback Called when login process is done
         */
        fun loginPhone(context: Context, appToken: String?, phone: String?, appId: String?, callback: LoginOtherCallback) {
            val loginOther: LoginOther = ServiceGenerator.connectFacebook(LoginOther::class.java, context, appToken)
            val apiCall: Call<LoginResponse> = loginOther.LOGIN_PHONE(phone, appId)
            apiCall.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val loginResponse: LoginResponse? = response.body()
                        saveLoginInformation(loginResponse, true, loginResponse?.getUserId(), context)
                        callback.onSuccess(loginResponse)
                    } else callback.onFailure(Throwable(response.message()))
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    callback.onFailure(t)
                }
            })
        }

        fun logout(context: Activity, mode: Int) {
            logout(context, null, mode)
        }

        fun logout(context: Activity, cb: Callback<Void>) {
            logout(context, cb, LoginActivity.MODE_LOGOUT)
        }

        fun logout(context: Activity, cb: Callback<Void>?, mode: Int) {
            val deviceToken: String? = getDeviceTokenSaved()
            FirebaseMessaging.getInstance().unsubscribeFromTopic(getLoggeInAppUserId(context)!!)
            /**
             * delete installation id
             */
            ServiceGenerator.createServiceWithAuth(InstallationService::class.java, context)
                .deleteInstallation(deviceToken)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        /**
                         * logout
                         */
                        LoginManager.getInstance().logOut()
                        //AccountKit.logOut();
                        callAccount = null
                        accountResponse = null
                        //Remove shared preference
                        var loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
                        var editor = loginInfo.edit()
                        editor.clear()
                        editor.apply()
                        //Toast.makeText(context, context.getString(R.string.toast_logout), Toast.LENGTH_SHORT).show();
                        loginInfo = context.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
                        editor = loginInfo.edit()
                        editor.clear()
                        editor.apply()
                        StartupPreference.clear(context)

                        SettingUtil.clearAll(context)
                        PlayerUtils.clearAll(context)
                        //SavedMediaUtil.clearAll();
                        val logoutCall: Call<Void> = ServiceGenerator.createServiceWithAuth(AccountsService::class.java, context).logout()
                        logoutCall.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (cb != null) cb.onResponse(call, response)
                                appLogin(context, object : ResponseHelper<LoginAppResponse?>() {
                                    override fun onSuccess(body: LoginAppResponse?) {
                                        val curationService: CurationService = ServiceGenerator.createServiceAppAuth(CurationService::class.java, body!!.getAccessToken())
                                        curationService.getApp(body.getAppId())
                                            .enqueue(object : ResponseHelper<AppResponse>() {
                                                override fun onSuccess(body: AppResponse?) {
                                                    setAppLoginOption(context, body?.getLoginOption())
                                                    goLoginWhenLogout(context, mode)
                                                }

                                                override fun onError(message: String?) {
                                                    goLoginWhenLogout(context, mode)
                                                }
                                            })
                                    }
                                })
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                goLoginWhenLogout(context, mode)
                            }
                        })
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        goLoginWhenLogout(context, mode)
                    }
                })
        }

        private fun goLoginWhenLogout(context: Activity, mode: Int) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("mode", mode)
            context.startActivity(intent)
            context.finish()
        }

        /**
         * force require login
         * show login view
         *
         * @param activity Context to intent
         */
        fun goLogin(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.putExtra("mode", LoginActivity.MODE_REQUIRE_LOGIN)
            activity.startActivity(intent)
        }

        /**
         * call to show phone verification
         *
         * @param context Context to intent
         */
        fun showPhoneVerification(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra("mode", LoginActivity.MODE_REQUIRE_PHONE)
            context.startActivity(intent)
        }

        /**
         * Get logged in user id
         *
         * @param context
         * @return logged in user id
         */
        fun getLoggeInUserId(context: Context?): String? {
            val loginInfo: SharedPreferences = context?.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            return loginInfo.getString(USERID_KEY, " ")
        }

        fun getRealAccountId(context: Context): String? {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getString(REALACCOUNT_KEY, " ")
        }

        fun isMe(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean(ISME_KEY, true)
        }

        /**
         * App login for curation
         *
         * @param context
         * @param callback
         */
        fun appLogin(context: Context, callback: Callback<LoginAppResponse?>) {
            val accountsService: AccountsService = ServiceGenerator.createService(AccountsService::class.java)
            val apiCall: Call<LoginAppResponse> = accountsService.appLogin(LoginRequest(APP_USER, APP_PASSWORD, true))
            apiCall.enqueue(object : Callback<LoginAppResponse?> {
                override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                    if (response.isSuccessful && response.body() != null) {
                        saveAppLoginInformation(response.body(), context)
                        callback.onResponse(call, response)
                    } else {
                        callback.onFailure(call, Throwable(response.message()))
                    }
                }

                override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                    callback.onFailure(call, t)
                }
            })
        }

        private fun saveAppLoginInformation(loginResponse: LoginAppResponse?, context: Context) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.clear()
            editor.putString(TOKEN_KEY, loginResponse?.getAccessToken())
            editor.putString(USERID_KEY, loginResponse?.getAppId())
            editor.putLong(EXPIRY_KEY, System.currentTimeMillis() + (loginResponse!!.getTtl() - 10L) * 1000L)
            editor.apply()
        }

        fun setRealAccountId(realAccountId: String?, context: Context) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.clear()
            editor.putString("realAccountId", realAccountId)
            editor.apply()
        }

        /**
         * Get logged in app user id
         *
         * @param context
         * @return logged in user id
         */
        fun getLoggeInAppUserId(context: Context?): String? {
            val loginInfo: SharedPreferences = context?.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            return loginInfo.getString(USERID_KEY, " ")
        }

        fun getLoggedInUserAccountTypeId(context: Context): String? {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getString(USERACCOUNTTYPE_KEY, " ")
        }

        /**
         * Get saved app token. Doesnt handle expired token.
         *
         * @param context Contex used for reading shared preference
         * @return Saved token
         */
        fun getAppToken(context: Context): String? {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val expiry = loginInfo.getLong(EXPIRY_KEY, System.currentTimeMillis())
            if (expiry <= System.currentTimeMillis()) {
                Log.w("client api", "Login expired")
            }
            return loginInfo.getString(TOKEN_KEY, "")
        }

        /**
         * Check if app logged in
         *
         * @param context Context to access shared preference
         * @return True if user logged in
         */
        fun isAppLoggedIn(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getString(TOKEN_KEY, "")!!.isNotEmpty() &&
                    loginInfo.getLong(EXPIRY_KEY, System.currentTimeMillis()) > System.currentTimeMillis()
        }

        private var callAccount: Call<Account?>? = null
        private var accountResponse: Response<Account?>? = null

        fun setAccount(ca: Call<Account?>?, ra: Response<Account?>?) {
            callAccount = ca
            accountResponse = ra
            if (ra!!.isSuccessful && ra.body() != null) {
                setUserName(ra.body()!!.getUsername())
                setFullName(ra.body()!!.getFirstName())
            }
        }

        private fun setUserName(username: String?) {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putString("username", username)
            editor.apply()
        }

        fun getUsername(): String? {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            return loginInfo.getString("username", " ")
        }

        private fun setFullName(fullName: String?) {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putString("fullname", fullName)
            editor.apply()
        }

        fun getFullName(): String? {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            return loginInfo.getString("fullname", " ")
        }

        fun getCurrentAccount(): Account? {
            if (accountResponse == null || accountResponse!!.body() == null) {
                val account = Account()
                account.setId(getLoggeInUserId(SvaraApplication.getAppContext())!!)
                account.setUsername(getUsername()!!)
                return account
            }
            return accountResponse!!.body()
        }

        fun getCurrentAccount(context: Context, callback: Callback<Account>) {
            if (accountResponse == null || accountResponse!!.body() == null) {
                fetchAccount(context, callback)
            } else callback.onResponse(callAccount!!, accountResponse!!)
        }

        fun fetchAccount(context: Context, callback: Callback<Account>) {
            val accountCall: Call<Account> = ServiceGenerator.createServiceWithAuth(AccountsService::class.java, context)
                .getProfile(getLoggeInUserId(context))

            accountCall.enqueue(object : Callback<Account> {
                override fun onResponse(call: Call<Account?>?, response: Response<Account?>?) {
                    if (response!!.isSuccessful && response.body() != null) {
                        accountResponse = response
                        callAccount = call
                        setUserName(response.body()!!.getUsername())
                        setFullName(response.body()!!.getFullName())
                        callback.onResponse(call!!, response)
                        SettingUtil.saveAccountDetail(context, response.body())
                    }
                }

                override fun onFailure(call: Call<Account>, t: Throwable) {
                    callback.onFailure(call, t)
                }
            })
        }

        private var accessibility: Accessibility? = null

        fun getAccessibility(context: Context?): Accessibility {
            return if (accessibility == null) Accessibility() else accessibility!!
        }

        //    public static Membership getMembership(final Context context) {
        ////        CheckMembership(context);
        //        if (mMembership == null) {
        //            mMembership = new Membership();
        //            SharedPreferences loginInfo = context.getSharedPreferences(USER_MEMBERSHIP, Context.MODE_PRIVATE);
        //            mMembership.setPackageType(loginInfo.getString(USER_TYPE, Membership.MEMBER_TYPE_FREE));
        //            return mMembership;
        //        } else return mMembership;
        //
        //    }

        //    public static Membership getMembership(final Context context) {
        ////        CheckMembership(context);
        //        if (mMembership == null) {
        //            mMembership = new Membership();
        //            SharedPreferences loginInfo = context.getSharedPreferences(USER_MEMBERSHIP, Context.MODE_PRIVATE);
        //            mMembership.setPackageType(loginInfo.getString(USER_TYPE, Membership.MEMBER_TYPE_FREE));
        //            return mMembership;
        //        } else return mMembership;
        //
        //    }
        fun checkAccessibility(context: Activity) {
            checkAccessibility(context, null)
        }

        fun checkAccessibility(context: Activity, cb: ResponseHelper<Boolean>?) {
            CheckMembership(context, cb)
        }

        fun CheckMembership(context: Activity, cb: ResponseHelper<Boolean>?) {
            val membershipCall: Call<Membership> = ServiceGenerator.createServiceWithAuth(AccountsService::class.java, context)
                .GET_MEMBERSHIP(getLoggeInUserId(context))

            membershipCall.enqueue(object : Callback<Membership> {
                override fun onResponse(call: Call<Membership>, response: Response<Membership?>) {
                    if (response.isSuccessful && response.body() != null) {
                        val membership: Membership? = response.body()
                        accessibility = Accessibility(membership?.getPackageType())
                    }
                    if (response.code() == HTTP_UNAUTHORIZED || response.code() == HTTP_FORBIDDEN) {
                        logout(context, LoginActivity.MODE_FORCE_LOGOUT)
                    }
                    if (cb != null)
                        cb.onSuccess(true)
                }

                override fun onFailure(call: Call<Membership>, t: Throwable) {
                    if (cb != null)
                        cb.onSuccess(false)
                }
            })
        }

        const val LOCATION_REQUEST_CODE = 37

        fun getLocation(activity: Activity) {
            val lm: LocationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                var location: Location? = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (location == null) {
                    location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                }
                if (location != null) {
                    val loginInfo: SharedPreferences = activity.getSharedPreferences(APP_LOCATION, Context.MODE_PRIVATE)
                    val editor = loginInfo.edit()
                    editor.putString(APP_LAT, "" + location.latitude)
                    editor.putString(APP_LONG, "" + location.longitude)
                    editor.apply()
                }
            } else println("no permision")
        }

        fun sendLocation(activity: Activity, callback: Callback<Void>) {
            val accountsService: AccountsService = ServiceGenerator.createServiceWithAuth(AccountsService::class.java, activity)
            val jsonObject = JsonObject()
            val jsonArray = JsonArray()
            jsonArray.add(getLong(activity))
            jsonArray.add(getLat(activity))
            jsonObject.add("location", jsonArray)
            accountsService.SET_LOCATION(getLoggeInUserId(activity), jsonObject).enqueue(callback)
        }

        fun getCurationOption(activity: Activity?): String {
            if (activity == null) return ""
            var opt = ""
            if (!isGuest(activity)) {
                val loginInfo: SharedPreferences = activity.getSharedPreferences(APP_LOCATION, Context.MODE_PRIVATE)
                opt = "{\"location\":[" + loginInfo.getString(APP_LAT, "0.0") + ", " + loginInfo.getString(APP_LONG, "0.0") + "]}"
            }
            return opt
        }

        fun getLat(activity: Context?): String? {
            if (activity == null) return ""
            val loginInfo: SharedPreferences = activity.getSharedPreferences(APP_LOCATION, Context.MODE_PRIVATE)
            return loginInfo.getString(APP_LAT, "0.0")
        }

        fun getLong(activity: Context?): String? {
            if (activity == null) return ""
            val loginInfo: SharedPreferences = activity.getSharedPreferences(APP_LOCATION, Context.MODE_PRIVATE)
            return loginInfo.getString(APP_LONG, "0.0")
        }

        fun getLongLat(activity: Context?): String {
            return if (activity == null) ""
            else getLong(activity) + ", " + getLat(activity)
        }

        /**
         * Get audio ads id
         *
         * @return audio ads id
         */
        fun getAudioAdsId(): String? {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            return loginInfo.getString(ADS_AUDIO_KEY, " ")
        }

        fun saveAudioAdsId(id: String?) {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putString(ADS_AUDIO_KEY, id)
            editor.commit()
        }

        fun showIntro(): Boolean {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_INTRO_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            return loginInfo.getBoolean(INTRO, true)
        }

        fun setIntro(show: Boolean) {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_INTRO_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putBoolean(INTRO, show)
            editor.commit()
        }

        fun setPreferenceShow() {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putBoolean("preference", false)
            editor.apply()
        }

        fun isPreferenceSouldShow(): Boolean {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            return loginInfo.getBoolean("preference", true)
        }

        fun isAdMobDisable(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("admob", true)
        }

        fun setAdMobDisable(context: Context, disable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("admob", disable)
            editor.apply()
        }

        fun isPaymentEnable(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("payment", false)
        }

        fun setEnablepayment(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("payment", enable)
            editor.apply()
        }

        fun isShowSwitchAccount(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("showSwitchAccount", false)
        }

        fun setShowSwitchAccount(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("showSwitchAccount", enable)
            editor.apply()
        }

        fun getAppRadioId(context: Context): String? {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getString("appRadioId", "")
        }

        fun setAppRadioId(context: Context, radioId: String?) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putString("appRadioId", radioId)
            editor.apply()
        }

        fun isReferralEnable(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("referral", false)
        }

        fun setEnableReferral(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("referral", enable)
            editor.apply()
        }

        fun isPreferenceEnable(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("preference", false)
        }

        fun setEnablePreference(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("preference", enable)
            editor.apply()
        }

        fun isEnableTheme(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("themeMenu", true)
        }

        fun setEnableTheme(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("themeMenu", enable)
            editor.apply()
        }

        fun setDisableSearch(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("search", enable)
            editor.apply()
        }

        fun isDisableSearch(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("search", false)
        }

        fun setShowDrawer(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("showDrawer", enable)
            editor.apply()
        }

        fun isShowDrawer(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("showDrawer", true)
        }

        fun setShowNotificationDrawer(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("showNotificationDrawer", enable)
            editor.apply()
        }

        fun isShowNotificationDrawer(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("showNotificationDrawer", true)
        }

        fun setEnableProfileMenu(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("profileMenu", enable)
            editor.apply()
        }

        fun isEnableProfileMenu(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("profileMenu", false)
        }

        fun setAppLoginOption(context: Context, appLoginOption: AppLoginOption?) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putString("loginOption", Gson().toJson(appLoginOption))
            editor.apply()
        }

        fun getAppLoginOption(context: Context): AppLoginOption {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val loginOption: String? = loginInfo.getString("loginOption", null)

            return if (loginOption != null)
                Gson().fromJson(loginOption, AppLoginOption::class.java)
            else AppLoginOption()
        }

        fun setEnablePrivateDoc(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("privateDoc", enable)
            editor.apply()
        }

        fun isEnablePrivateDoc(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("privateDoc", false)
        }

        fun setEnableArticle(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("article", enable)
            editor.apply()
        }

        fun isEnableArticle(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("article", false)
        }

        fun setForceUpdate(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("forceUpdate", enable)
            editor.apply()
        }

        fun isForceUpdate(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("forceUpdate", false)
        }

        private var appContents: AppSettings.Contents = AppSettings.Contents()

        fun setAppContent(contents: AppSettings.Contents) {
            appContents = contents
        }

        fun getAppContents(): AppSettings.Contents {
            return appContents
        }

        private var publicIp: String? = null

        fun getPublicIp(): String? {
            return publicIp
        }

        fun setPublicIp(ip: String?) {
            publicIp = ip
        }

        fun setBaseShareUrl(baseShareUrl: String?) {
            if (baseShareUrl == null || !baseShareUrl.startsWith("http"))
                return
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTING_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putString("baseShareUrl", if (baseShareUrl.endsWith("/")) baseShareUrl else "$baseShareUrl/")
            editor.apply()
        }

        fun getBaseShareUrl(): String? {
            val loginInfo = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTING_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            return loginInfo.getString("baseShareUrl", "https://svara.id/sharing/")
        }

        fun setAppName(appName: String?) {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTING_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putString("appName", appName)
            editor.apply()
        }

        fun getAppName(): String? {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTING_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            return loginInfo.getString("appName", "SVARA")
        }

        fun saveShareText(shareText: AppSettings.ShareText) {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTING_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putString("SHARE_TEXT", Gson().toJson(shareText))
            editor.apply()
        }

        fun getShareText(): AppSettings.ShareText {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTING_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val share: String = preferences.getString("SHARE_TEXT", null)
                ?: return AppSettings.ShareText()

            val shareText: AppSettings.ShareText = Gson().fromJson(share, AppSettings.ShareText::class.java)
                ?: return AppSettings.ShareText()

            return shareText
        }

        fun saveChannelActionButton(channelActionButtons: List<ChannelActionButton?>?) {
            val loginInfo: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTING_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val editor = loginInfo.edit()
            editor.putString("CHANNEL_ACTION_BUTTON", Gson().toJson(channelActionButtons))
            editor.apply()
        }

        fun getChannelActionButton(): List<ChannelActionButton>? {
            val preferences: SharedPreferences = SvaraApplication.getAppContext()?.getSharedPreferences(APP_SETTING_PREFERENCE_KEY, Context.MODE_PRIVATE)!!
            val channelActionButton: String? = preferences.getString("CHANNEL_ACTION_BUTTON", "[]")

//            val channelActionButtons = Gson().fromJson<List<ChannelActionButton>>(
//                channelActionButton,
//                object : TypeToken<List<ChannelActionButton?>?>() {}.type
//            )

            val channelActionButtons: List<ChannelActionButton>? = Gson().fromJson(channelActionButton, object: TypeToken<List<ChannelActionButton>>(){
            }.type)

            if (channelActionButtons == null || channelActionButtons.size < 1) {
                val actionButtons: MutableList<ChannelActionButton> = ArrayList()
                actionButtons.add(ChannelActionButton(ChannelActionButtonTypes.TYPE_SHARE, true))
                actionButtons.add(ChannelActionButton(ChannelActionButtonTypes.TYPE_CHAT, true))
                actionButtons.add(ChannelActionButton(ChannelActionButtonTypes.TYPE_PLAY_AUDIO, true))
                actionButtons.add(ChannelActionButton(ChannelActionButtonTypes.TYPE_PLAY_VIDEO, true))
                actionButtons.add(ChannelActionButton(ChannelActionButtonTypes.TYPE_ADD_TO_LIBRARY, true))
                return actionButtons
            }
            return channelActionButtons
        }

        fun setEnableVideo(context: Context, enable: Boolean) {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val editor = loginInfo.edit()
            editor.putBoolean("video", enable)
            editor.apply()
        }

        fun isEnableVideo(context: Context): Boolean {
            val loginInfo: SharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return loginInfo.getBoolean("video", false)
        }

    }

    interface AuthenticationCallback {
        fun onSuccess()
        fun onFailure(t: Throwable)
    }
    interface LoginOtherCallback {
        fun onSuccess(loginResponse: LoginResponse?)
        fun onFailure(t: Throwable)
    }
}