package com.zamrud.radio.mobile.app.svara.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.zamrud.radio.mobile.app.svara.BuildConfig
import com.zamrud.radio.mobile.app.svara.CustomProject
import com.zamrud.radio.mobile.app.svara.Player.Utils.ActionUtil
import com.zamrud.radio.mobile.app.svara.Player.Utils.Strings
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.Realm.RealmMenu
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import com.zamrud.radio.mobile.app.svara.apiclient.BaseCallback
import com.zamrud.radio.mobile.app.svara.apiclient.ResponseHelper
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.apiclient.countly.UserActivity
import com.zamrud.radio.mobile.app.svara.apiclient.exception.HttpRequestException
import com.zamrud.radio.mobile.app.svara.apiclient.model.AppSettings
import com.zamrud.radio.mobile.app.svara.apiclient.model.OpenAppData
import com.zamrud.radio.mobile.app.svara.apiclient.model.OpenAppDataBody
import com.zamrud.radio.mobile.app.svara.apiclient.model.account.LoginAppResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.account.LoginResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.AppResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Account
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Menu
import com.zamrud.radio.mobile.app.svara.apiclient.model.menu.AppVersionFilter
import com.zamrud.radio.mobile.app.svara.apiclient.services.AccountsService
import com.zamrud.radio.mobile.app.svara.apiclient.services.AppService
import com.zamrud.radio.mobile.app.svara.apiclient.services.CurationService
import com.zamrud.radio.mobile.app.svara.apiclient.services.TypeAccount
import com.zamrud.radio.mobile.app.svara.dialog.DialogShowFreePopup
import com.zamrud.radio.mobile.app.svara.helper.image.DownloadMenuIconAsync
import com.zamrud.radio.mobile.app.svara.login.*
import com.zamrud.radio.mobile.app.svara.setting.SettingUtil
import com.zamrud.radio.mobile.app.svara.setting.localization.LanguageHelper
import pl.tajchert.nammu.Nammu
import pl.tajchert.nammu.PermissionCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit


class LoginActivity : AppCompatActivity() {
    companion object {
        const val LOGIN_EVENT = "svara-login-event"
        private const val TAG = "LoginActivity"
        private const val APP_REQUEST_CODE = 99
        const val MODE_LOGIN = 1
        const val MODE_LOGOUT = 2
        const val MODE_FORCE_LOGOUT = 3
        const val MODE_REQUIRE_LOGIN = 4
        const val MODE_REQUIRE_PHONE = 5
        const val MODE_REQUIRE_GUEST = 6
        const val RC_SIGN_IN = 9001
        const val RC_PHONE_SIGN_IN = 9002
        private const val PHONE_CODE_TIME_OUT: Long = 60
    }
    private var phoneCodeToLeave = PHONE_CODE_TIME_OUT
    private var phoneVerificationId: String? = null
    private var phoneResendToken: PhoneAuthProvider.ForceResendingToken? = null
    private var phoneNumber: String? = ""

    private var mode = MODE_LOGIN

    var progressDialog: ProgressDialog? = null
    var container: View? = null
    lateinit var fbLogin: LoginButton
    var callbackManager: CallbackManager? = null
    var mGoogleSignInClient: GoogleSignInClient? = null
    private var mAuth: FirebaseAuth? = null
    private var isRegister = false

    override fun attachBaseContext(base: Context) {
        LanguageHelper.changeLanguage(base)
        super.attachBaseContext(base)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LanguageHelper.changeLanguage(this)
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)

//        Nammu.init(this)
        mode = intent.getIntExtra("mode", MODE_LOGIN)
        var action: String? = intent.action

        if (action == null)
            action = ""

        if (mode == MODE_REQUIRE_GUEST && action != ActionUtil.ACTION_REFERRAL_REDEEM) {
            forceGuest()
            return
        }

        setContentView(R.layout.activity_login2)
        container = findViewById<View>(R.id.container)
        fbLogin = findViewById<LoginButton>(R.id.fbLogin)
        makeStatusBarTransparent()
        callbackManager = CallbackManager.Factory.create()
        if (!needFragment())
            return

        fbLogin.setPermissions(arrayListOf("email", "public_profile"))
        registerFbLogin()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .requestProfile()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth = FirebaseAuth.getInstance()

        if (CustomProject.loginOnlySocial) {
            startFragment(LoginOnlySocialFragment(), addBackStack = false, animated = false)
            return
        }

        if (CustomProject.canSignUp)
            startFragment(SignUpFragment(), addBackStack = false, animated = false)
        else startFragment(LoginFragment(), addBackStack = false, animated = false)

        if (mode == MODE_LOGIN) {
            startFragment(LoginFragment(), addBackStack = false, animated = false)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun needFragment(): Boolean {
        if (mode == MODE_REQUIRE_PHONE)
            onPhoneLoginClick()
        if (mode == MODE_FORCE_LOGOUT) {
            val dialogShow: DialogShowFreePopup = DialogShowFreePopup.newInstance(this, DialogShowFreePopup.type.force_logout)
            dialogShow.isCancelable = true
            dialogShow.show(supportFragmentManager, "dialog")
        }
        if (mode == MODE_LOGOUT || mode == MODE_FORCE_LOGOUT) {
            LoginManager.getInstance().logOut()
            logoutGoogle()
            logoutPhone()
            AuthUI.getInstance().signOut(this)
            mode = MODE_LOGIN
        }
        return mode != MODE_REQUIRE_PHONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)

        //Google
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }

        if (requestCode == RC_PHONE_SIGN_IN) {
            val response: IdpResponse? = IdpResponse.fromResultIntent(data)

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                registerPhoneLogin(user!!)
            } else {
                // Sign in failed
                closeProgressDialog()
                if (response == null) {
                    // User pressed back button
                    return
                } else if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    return
                } else {
                    Toast.makeText(applicationContext, getString(R.string.inter_phone_login_failed), Toast.LENGTH_LONG).show()
                }
                Log.e(TAG, "Sign-in error: ", response.getError())
            }
        }
    }

    fun startFragment(fragment: Fragment, addBackStack: Boolean, animated: Boolean) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (animated)
            fragmentTransaction.setCustomAnimations(R.anim.login_in_slide_from_right,
            R.anim.login_out_slide_to_left,
            R.anim.login_in_slide_from_left,
            R.anim.login_out_slide_to_right)
        fragmentTransaction.replace(R.id.container, fragment)
        if (addBackStack)
            fragmentTransaction.addToBackStack("null")
        fragmentTransaction.commit()
    }

    private fun makeStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.white_25)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            container!!.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    fun showErrorMessage(t: Throwable?) {
        if (applicationContext == null)
            return
        if (t is UnknownHostException) {
            Toast.makeText(applicationContext, getString(R.string.inter_failed_communicate_to_server), Toast.LENGTH_LONG).show()
        } else if (t is HttpRequestException) {
            val e: HttpRequestException = t
            if (e.getStatusCode() == 401) {
                Toast.makeText(applicationContext, e.getmMessage(), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, getString(R.string.inter_failed_communicate_to_server), Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(applicationContext, getString(R.string.inter_failed_communicate_to_server), Toast.LENGTH_LONG).show()
        }
    }

    fun hideKeyboard() {
        val view: View? = currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showProgressDialog() {
        progressDialog = ProgressDialog(this, R.style.transparentProgesDialog)
        progressDialog!!.setCancelable(false)
        progressDialog!!.setProgressStyle(android.R.style.Widget_ProgressBar_Large_Inverse)
        progressDialog!!.show()
    }

    fun closeProgressDialog() {
        if (progressDialog != null)
            progressDialog!!.dismiss()
    }

    fun loginApp(callback: Callback<LoginAppResponse?>) {
        AuthenticationUtils.appLogin(applicationContext, callback)
    }

    private fun registerFbLogin() {
        fbLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(result: LoginResult?) {
                Log.d("OkHttp", result?.accessToken!!.token)
                loginApp(object : Callback<LoginAppResponse?> {
                    override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                        if (response.isSuccessful && response.body() != null)
                            loginFacebook(response.body()!!, result)
                        else {
                            showErrorMessage(HttpRequestException(400))
                            closeProgressDialog()
                        }
                    }

                    override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                        showErrorMessage(t)
                        closeProgressDialog()
                    }
                })
            }

            override fun onCancel() {
                closeProgressDialog()
            }

            override fun onError(e: FacebookException) {
                Toast.makeText(applicationContext, getString(R.string.inter_facebook_login_failed), Toast.LENGTH_LONG).show()
                closeProgressDialog()
            }
        })
    }

    fun onLoginFacebookClick() {
        showProgressDialog()
        fbLogin.performClick()
    }

    private fun loginFacebook(loginAppResponse: LoginAppResponse, loginResult: LoginResult) {
        AuthenticationUtils.loginFacebook(loginAppResponse.getAccessToken(),
            loginResult.accessToken.token,
            loginAppResponse.getAppId(),
            applicationContext,
            object : AuthenticationUtils.LoginOtherCallback {
                override fun onSuccess(loginResponse: LoginResponse?) {
                    getUserDetail(null)
                }

                override fun onFailure(t: Throwable) {
                    closeProgressDialog()
                    showErrorMessage(t)
                }
            })
    }

    fun onLoginGoogleClick() {
        showProgressDialog()
        GooglesignIn()
    }

    private fun GooglesignIn() {
        val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            loginApp(object : Callback<LoginAppResponse?> {
                override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                    if (response.isSuccessful && response.body() != null) {
                        loginGoogle(response.body()!!, account)
                    } else {
                        showErrorMessage(HttpRequestException(400))
                        closeProgressDialog()
                    }
                }

                override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                    showErrorMessage(t)
                    closeProgressDialog()
                }
            })
        } catch (e: ApiException) {
            closeProgressDialog()
            when (e.statusCode) {
                GoogleSignInStatusCodes.SIGN_IN_CANCELLED -> {}
                GoogleSignInStatusCodes.DEVELOPER_ERROR ->
                    Toast.makeText(applicationContext, getString(R.string.inter_google_login_failed), Toast.LENGTH_LONG).show()
                GoogleSignInStatusCodes.TIMEOUT ->
                    Toast.makeText(applicationContext, getString(R.string.inter_request_timeout), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loginGoogle(loginAppResponse: LoginAppResponse, loginResult: GoogleSignInAccount) {
        AuthenticationUtils.loginGoogle(loginAppResponse.getAccessToken(),
            loginResult.idToken,
            loginAppResponse.getAppId(),
            applicationContext,
            object : AuthenticationUtils.LoginOtherCallback {
                override fun onSuccess(loginResponse: LoginResponse?) {
                    getUserDetail(null)
                }

                override fun onFailure(t: Throwable) {
                    closeProgressDialog()
                    showErrorMessage(t)
                }
            })
    }

    private fun logoutGoogle() {
        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(applicationContext, gso)
        mGoogleSignInClient.signOut()
    }

    private fun logoutPhone() {
        FirebaseAuth.getInstance().signOut()
    }

    fun onPhoneLoginClick() {
        startFragment(LoginPhoneFragment.newInstance(phoneVerificationId != null), addBackStack = true, animated = true)
    }

    fun sendPhoneNumber(phone: String, processDone: LoginPhoneFragment.ProcessDone) {
        FirebaseAuth.getInstance().setLanguageCode(Locale.getDefault().country)
        phoneVerificationId = null
        phoneResendToken = null
        phoneNumber = phone
        val options: PhoneAuthOptions = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(phone)
            .setTimeout(PHONE_CODE_TIME_OUT, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    if (onCodeCapture != null)
                        onCodeCapture!!.onReceiveCode(phoneAuthCredential.smsCode)
                    sendPhoneCode(phoneAuthCredential, processDone)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    phoneVerificationId = null
                    phoneResendToken = null
                    phoneNumber = ""
                    processDone.error(e.message)
                }

                override fun onCodeSent(verificationId: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                    phoneVerificationId = verificationId
                    phoneResendToken = forceResendingToken
                    processDone.doneSendNumber()
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun sendPhoneCode(code: String?, processDone: LoginPhoneFragment.ProcessDone) {
        if (phoneVerificationId == null) {
//            Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show();
            processDone.error("Failure")
            return
        }
        val phoneAuthCredential: PhoneAuthCredential = PhoneAuthProvider.getCredential(phoneVerificationId!!, code!!)
        sendPhoneCode(phoneAuthCredential, processDone)
    }

    private fun sendPhoneCode(phoneAuthCredential: PhoneAuthCredential, processDone: LoginPhoneFragment.ProcessDone) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    processDone.doneSendCode()
                    showProgressDialog()
                    val firebaseUser: FirebaseUser? = task.result.user
                    registerPhoneLogin(firebaseUser!!)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        processDone.error(resources.getString(R.string.inter_phone_code_invalid))
                    } else processDone.error(task.exception!!.message)
                }
            }
    }

    // disable accountKit
    private fun registerPhoneLogin(loginResult: FirebaseUser) {
        Log.w(TAG, "signInResult: data=" + loginResult.phoneNumber)
        loginApp(object : Callback<LoginAppResponse?> {
            override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                if (response.isSuccessful && response.body() != null)
                    phoneLogin(response.body()!!, loginResult)
                else {
                    showErrorMessage(HttpRequestException(400))
                    closeProgressDialog()
                }
            }

            override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                showErrorMessage(t)
                closeProgressDialog()
            }
        })
    }

    private fun phoneLogin(loginAppResponse: LoginAppResponse, loginResult: FirebaseUser) {
        AuthenticationUtils.loginPhone(applicationContext,
            loginAppResponse.getAccessToken(),
            loginResult.phoneNumber,
            loginAppResponse.getAppId(),
            object : AuthenticationUtils.LoginOtherCallback {
                override fun onSuccess(loginResponse: LoginResponse?) {
                    getUserDetail(null)
                }

                override fun onFailure(t: Throwable) {
                    closeProgressDialog()
                    showErrorMessage(t)
                }
            })
    }

    private fun forceGuest() {
        loginApp(object : Callback<LoginAppResponse?> {
            override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                if (response.isSuccessful && response.body() != null)
                    loginGuest(response.body()!!)
                else {
                    closeProgressDialog()
                    showErrorMessage(HttpRequestException(400))
                }
            }

            override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                closeProgressDialog()
                showErrorMessage(t)
            }
        })
    }

    fun loginGuest(loginAppResponse: LoginAppResponse) {
        @SuppressLint("HardwareIds")
        val android_id: String = Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
        ServiceGenerator.createService(AccountsService::class.java).loginGuest(
            android_id, loginAppResponse.getAppId(),
            "android", null, loginAppResponse.getAccessToken())
            .enqueue(object : Callback<LoginResponse?> {
                override fun onResponse(call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
                    if (response.isSuccessful && response.body() != null) {
                        val curationService = ServiceGenerator.createServiceAppAuth(CurationService::class.java, response.body()!!.getAccessToken())
                        curationService.getApp(response.body()!!.getAppId())
                            .enqueue(object : ResponseHelper<AppResponse?>() {
                                override fun onSuccess(body: AppResponse?) {
                                    AuthenticationUtils.setAppLoginOption(applicationContext, body!!.getLoginOption())
                                    AuthenticationUtils.saveLoginInformation(response.body(), true, response.body()!!.getUserId(), applicationContext)
                                    UserActivity.sendLogin("guest", "guest")
                                    onAuthenticationFinish()
                                }

                                override fun onError(message: String?) {
                                    closeProgressDialog()
                                    showErrorMessage(HttpRequestException(400))
                                }
                            })
                    } else {
                        closeProgressDialog()
                        showErrorMessage(HttpRequestException(400))
                    }
                }

                override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                    closeProgressDialog()
                    showErrorMessage(t)
                }
            })
    }

    private fun onResultCanceled() {
        closeProgressDialog()
        if (mode == MODE_REQUIRE_PHONE || mode == MODE_REQUIRE_LOGIN)
            onBackPressed()
    }

    fun getUserDetail(callback: Callback<Account?>?) {
        val accountCall: Call<Account> = ServiceGenerator.createServiceWithAuth(AccountsService::class.java, applicationContext)
            .getProfile(AuthenticationUtils.getLoggeInUserId(applicationContext))
        if (callback != null) {
            accountCall.enqueue(callback)
            return
        }
        accountCall.enqueue(object : Callback<Account?> {
            override fun onResponse(call: Call<Account?>, response: Response<Account?>) {
                if (response.isSuccessful && response.body() != null) {
                    AuthenticationUtils.setAccount(call, response)
                    val account = response.body()
                    UserActivity.sendLogin(account!!.getUsername(), account.getFullName())
                    if ((account.getFirstName()!!.isEmpty()
                                || account.getImages().getImage150()!!.isEmpty()
                                || account.getBirthday()!!.isEmpty()
                                || account.getBirthday()!!
                            .startsWith("1900")) || account.getGender() == "undefined"
                    ) {
                        startFragment(RegisterFragment.newInstance(account, true), addBackStack = false, animated = true)
                        closeProgressDialog()
                        return
                    }
                }
                onAuthenticationFinish()
            }

            override fun onFailure(call: Call<Account?>, t: Throwable) {
                onAuthenticationFinish()
            }
        })
    }

    private fun checkMenuIconExist() {
        val menus: ArrayList<Menu> = SettingUtil.getMenu(applicationContext)
        val noIcon: ArrayList<String> = ArrayList()
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
                return this@LoginActivity
            }
        }).execute()
    }

    fun onAuthenticationFinish() {
        val deviceToken: String? = AuthenticationUtils.getDeviceTokenSaved()
        registerFirebaseMessaging()
        if (AuthenticationUtils.hasDeviceToken())
            AuthenticationUtils.sendInstallationIfNeeded(deviceToken)
        if (mode == MODE_REQUIRE_LOGIN) {
            getAllDataApp()
            sendLoginEvent()
            closeProgressDialog()
            finish()
            return
        }
        getAllDataApp()
    }

    private fun registerFirebaseMessaging() {
        if (AuthenticationUtils.isLoggedIn(applicationContext)) {
            FirebaseMessaging.getInstance().subscribeToTopic(AuthenticationUtils.getLoggeInAppUserId(applicationContext)!!)
        }
    }

    fun getMode(): Int {
        return mode
    }

    private fun startMainActivity() {
        getLocation(object : BaseCallback<Void?>() {
            override fun onSuccess(call: Call<Void?>, response: Response<Void?>) {

            }

            override fun onVoid(t: Throwable?) {
                val intent: Intent = this@LoginActivity.intent.clone() as Intent
                intent.setClass(this@LoginActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                if (getIntent().action != null && getIntent().extras != null) {
                    intent.action = getIntent().action
                    intent.putExtra(ActionUtil.KEY_TYPE, getIntent().extras!!.getString(ActionUtil.KEY_TYPE))
                    intent.putExtra(ActionUtil.KEY_ID, getIntent().extras!!.getString(ActionUtil.KEY_ID))
                }
                closeProgressDialog()
                startActivity(intent)
                finish()
            }
        })
    }

    private fun getLocation(callback: BaseCallback<Void?>) {
        Nammu.askForPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, object : PermissionCallback {
                override fun permissionGranted() {
                    AuthenticationUtils.getLocation(this@LoginActivity)
                    AuthenticationUtils.sendLocation(this@LoginActivity, object : Callback<Void> {
                        override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                            callback.onVoid(null)
                        }

                        override fun onFailure(call: Call<Void?>, t: Throwable) {
                            callback.onVoid(t)
                        }
                    })
                }

                override fun permissionRefused() {
                    callback.onVoid(null)
                }
            })
    }

    fun setRegister(isRegister: Boolean) {
        this.isRegister = isRegister
    }

    override fun onBackPressed() {
        if (isRegister) {
            showProgressDialog()
            onAuthenticationFinish()
            return
        }
        sendLoginEvent()
        super.onBackPressed()
    }

    private fun sendLoginEvent() {
        val intent: Intent = Intent(LOGIN_EVENT)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
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
            .enqueue(object : ResponseHelper<OpenAppData?>() {
                override fun onSuccess(body: OpenAppData?) {
                    //getApp
                    if (body!!.getApp().getAdsPlaceholder() != null && body.getApp().getAdsPlaceholder()!!.getZones() != null && body.getApp().getAdsPlaceholder()!!.getZones()!!.getAudio() != null) {
                        AuthenticationUtils.saveAudioAdsId(body.getApp().getAdsPlaceholder()!!.getZones()!!.getAudio())
                    }
                    AuthenticationUtils.setAppName(body.getApp().getName())
                    AuthenticationUtils.setAppRadioId(applicationContext, body.getApp().getAppParam().getRadioId())

                    //appSettings
                    val appSettings: AppSettings = body.getAppSettings()
                    AuthenticationUtils.setAdMobDisable(applicationContext, appSettings.getFeatures().isDisableAdMob())

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
                    if (body.getMenus() != null && body.getMenus()!!.isNotEmpty())
                        SettingUtil.saveMenu(applicationContext, body.getMenus()!!)
                    if (mode != MODE_REQUIRE_LOGIN) {
                        checkMenuIconExist()
                    }
                }

                override fun onError(message: String?) {
                    checkMenuIconExist()
                }
            })
    }

    private fun getCountAccountType() {
        val appService = ServiceGenerator.createServiceWithAuth(AppService::class.java, this)
        appService.getAccountType(AuthenticationUtils.getLoggeInAppUserId(this))
            .enqueue(object : ResponseHelper<TypeAccount?>() {
                override fun onSuccess(body: TypeAccount?) {
                    if (body == null) return
                    showMenuChangeAccount(body)
                }
            })
    }

    private fun showMenuChangeAccount(body: TypeAccount) {
        if (body.getCount() > 1) {
            AuthenticationUtils.setShowSwitchAccount(applicationContext, true)
        }
    }

    private val mExecutorServiceProgress: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    private var mScheduleFutureProgress: ScheduledFuture<*>? = null
    private var handler = Handler()
    private var codeTimer: LoginPhoneFragment.CodeTimer? = null
    private var onCodeCapture: LoginPhoneFragment.OnCodeCapture? = null
    private val runnable = Runnable {
        if (phoneCodeToLeave > 0) {
            if (codeTimer != null)
                codeTimer!!.progress(Strings.secondToTimeFormat(phoneCodeToLeave.toFloat()))

            phoneCodeToLeave -= 1
        } else {
            if (codeTimer != null)
                codeTimer!!.onDone()
            phoneCodeToLeave = PHONE_CODE_TIME_OUT
            stopCountDownPhoneCodeExpire()
        }
    }

    fun setCodeTimerListener(codeTimer: LoginPhoneFragment.CodeTimer?) {
        this.codeTimer = codeTimer
    }

    fun deleteCodeTimerListener() {
        codeTimer = null
    }

    fun startCountDownPhoneCodeExpire() {
        if (!mExecutorServiceProgress.isShutdown) {
            mScheduleFutureProgress = mExecutorServiceProgress.scheduleAtFixedRate(
                { handler.post(runnable) }, 1,
                1, TimeUnit.SECONDS)
        }
    }

    fun stopCountDownPhoneCodeExpire() {
        if (mScheduleFutureProgress != null) {
            mScheduleFutureProgress!!.cancel(false)
        }
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun canResendNumber(): Boolean {
        return phoneResendToken != null && mScheduleFutureProgress != null && mScheduleFutureProgress!!.isCancelled
    }

    fun resendPhoneCode(processDone: LoginPhoneFragment.ProcessDone) {
        val options: PhoneAuthOptions = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(phoneNumber!!)
            .setTimeout(PHONE_CODE_TIME_OUT, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    if (onCodeCapture != null)
                        onCodeCapture!!.onReceiveCode(phoneAuthCredential.smsCode)
                    sendPhoneCode(phoneAuthCredential, processDone)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    phoneVerificationId = null
                    phoneResendToken = null
                    phoneNumber = ""
                    processDone.error(e.message)
                }

                override fun onCodeSent(verificationId: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                    phoneVerificationId = verificationId
                    phoneResendToken = forceResendingToken
                    processDone.doneSendNumber()
                }
            })
            .setForceResendingToken(phoneResendToken!!)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun resetPhoneNumber() {
        stopCountDownPhoneCodeExpire()
        phoneVerificationId = null
        phoneResendToken = null
        phoneNumber = ""
        phoneCodeToLeave = PHONE_CODE_TIME_OUT
        mScheduleFutureProgress = null
    }

    fun setOnCodeCapture(onCodeCapture: LoginPhoneFragment.OnCodeCapture?) {
        this.onCodeCapture = onCodeCapture
    }

    fun setupUI(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { v, event ->
                hideKeyboard()
                false
            }
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView: View = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }


}