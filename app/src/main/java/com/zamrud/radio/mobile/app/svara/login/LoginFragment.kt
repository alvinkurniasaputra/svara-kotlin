package com.zamrud.radio.mobile.app.svara.login

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.text.Spannable
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

import androidx.fragment.app.Fragment

import com.zamrud.radio.mobile.app.svara.CustomProject
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.TextUtils
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import com.zamrud.radio.mobile.app.svara.apiclient.countly.UserActivity
import com.zamrud.radio.mobile.app.svara.apiclient.exception.HttpRequestException
import com.zamrud.radio.mobile.app.svara.apiclient.model.account.LoginAppResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Account
import com.zamrud.radio.mobile.app.svara.helper.ThemeHelper
import com.zamrud.radio.mobile.app.svara.main.LoginActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    lateinit var etUsername: EditText
    lateinit var etPassword:EditText
    var btnLogin: View? = null
    lateinit var btnFacebook: View
    lateinit var btnGoogle: View
    lateinit var btnPhone: View
    lateinit var btnSkip: View
    lateinit var btnRegister: TextView
    lateinit var btnEye: ImageView
    lateinit var lblSignInWith: View

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity !is LoginActivity)
            throw ClassCastException(LoginFragment::class.java.simpleName
                    + " must be place in LoginActivity! ")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        etUsername = view.findViewById(R.id.etUsername)
        etPassword = view.findViewById(R.id.etPassword)
        btnLogin = view.findViewById(R.id.btnSignUp)
        btnRegister = view.findViewById(R.id.btnRegister)
        btnEye = view.findViewById(R.id.btnEye)
        btnFacebook = view.findViewById(R.id.btnFacebook)
        btnGoogle = view.findViewById(R.id.btnGoogle)
        btnPhone = view.findViewById(R.id.btnPhone)
        btnSkip = view.findViewById(R.id.btnSkip)
        lblSignInWith = view.findViewById(R.id.lblSignInWith)
        val forgotPassword: View = view.findViewById(R.id.forgotPassword)
        view.findViewById<View>(R.id.imgLogo).visibility = if (CustomProject.loginPageUseLogo) View.VISIBLE else View.INVISIBLE

        btnLogin!!.setOnClickListener { v: View? -> onLoginClick() }
        btnFacebook.setOnClickListener { v: View? -> getActivityLogin().onLoginFacebookClick() }
        btnGoogle.setOnClickListener { v: View? -> getActivityLogin().onLoginGoogleClick() }
        btnPhone.setOnClickListener { v: View? -> getActivityLogin().onPhoneLoginClick() }
        btnEye.setOnClickListener { v: View? -> onEyeClick() }
        forgotPassword.setOnClickListener { v: View? -> onForgotClick() }
        btnSkip.setOnClickListener { v: View? -> onSkipClick() }

        if (!CustomProject.withSkip) {
            btnSkip.visibility = View.GONE
        } else {
            btnSkip.visibility = View.VISIBLE
        }
        if (CustomProject.canSignUp)
            setupRegisterText()
        else {
            btnFacebook.visibility = View.GONE
            btnGoogle.visibility = View.GONE
            btnPhone.visibility = View.GONE
            btnRegister.visibility = View.GONE
            lblSignInWith.visibility = View.GONE
        }
        setupLoginOption()
        getActivityLogin().setupUI(view)
        return view
    }

    private fun setupLoginOption() {
        val appLoginOption = AuthenticationUtils.getAppLoginOption((getActivityLogin()))
        if (appLoginOption.isDisableFacebookLogin()) btnFacebook.visibility = View.GONE
        if (appLoginOption.isDisableGoogleLogin()) btnGoogle.visibility = View.GONE
        if (appLoginOption.isDisablePhoneLogin()) btnPhone.visibility = View.GONE
        if (appLoginOption.isDisableFacebookLogin() && appLoginOption.isDisableGoogleLogin() && appLoginOption.isDisablePhoneLogin()) {
            lblSignInWith.visibility = View.GONE
        }
    }

    private fun onEyeClick() {
        if (isPasswordVisible) {
            isPasswordVisible = false
            btnEye.alpha = 0.25f
            etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            etPassword.setSelection(etPassword.length())
        } else {
            isPasswordVisible = true
            btnEye.alpha = 0.75f
            etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            etPassword.setSelection(etPassword.length())
        }
    }

    private fun onSkipClick() {
        if (getActivityLogin().getMode() == LoginActivity.MODE_REQUIRE_LOGIN) {
            getActivityLogin().finish()
            return
        }
        getActivityLogin().showProgressDialog()
        getActivityLogin().loginApp(object : Callback<LoginAppResponse?> {
            override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                if (response.isSuccessful && response.body() != null)
                    getActivityLogin().loginGuest(response.body()!!)
                else {
                    getActivityLogin().closeProgressDialog()
                    getActivityLogin().showErrorMessage(HttpRequestException(400))
                }
            }

            override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                getActivityLogin().closeProgressDialog()
                getActivityLogin().showErrorMessage(t)
            }
        })
    }

    private fun setupRegisterText() {
        if (context == null)
            return
        val info = resources.getString(R.string.inter_have_no_account_info)
        val register = resources.getString(R.string.inter_have_no_account_register)
        val span: Spannable = Spannable.Factory.getInstance().newSpannable(info + register)
        val registerClick: ClickableSpan = object : ClickableSpan() {
            override fun onClick(v: View) {
                getActivityLogin().startFragment(SignUpFragment(), addBackStack = false, animated = false)
            }
        }
        val styleSpan = StyleSpan(Typeface.BOLD)
        val colorSpan = ForegroundColorSpan(ThemeHelper.getColorAttr(requireContext(), R.attr.colorPrimary))

        span.setSpan(registerClick, info.length, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(RelativeSizeSpan(1.1f), info.length, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(styleSpan, info.length, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(colorSpan, info.length, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        btnRegister.text = span
        btnRegister.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun onLoginClick() {
        getActivityLogin().hideKeyboard()
        val isUser: Boolean = TextUtils.hasText(etUsername.text.toString())
        val isPassword: Boolean = TextUtils.hasText(etPassword.text.toString())
        if (!isUser) {
            etUsername.error = resources.getString(R.string.inter_username_cant_blank)
            return
        }
        if (!isPassword) {
            etPassword.error = resources.getString(R.string.inter_password_cant_blank)
            return
        }
        loginApp()
    }

    private fun onForgotClick() {
        val url = "https://app.svara.fm/reset-password"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun loginApp() {
        getActivityLogin().showProgressDialog()
        getActivityLogin().loginApp(object : Callback<LoginAppResponse?> {
            override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    loginUser(response.body()!!.getAccessToken())
                } else {
                    getActivityLogin().closeProgressDialog()
                    getActivityLogin().showErrorMessage(HttpRequestException(400))
                }
            }

            override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                getActivityLogin().closeProgressDialog()
                getActivityLogin().showErrorMessage(t)
            }
        })
    }

    private fun loginUser(appToken: String?) {
        val username: String = etUsername.text.toString()
        val password: String = etPassword.text.toString()
        AuthenticationUtils.login(username, password, requireContext(), appToken, object : AuthenticationUtils.AuthenticationCallback {
            override fun onSuccess() {
                getUserDetail()
            }

            override fun onFailure(t: Throwable) {
                getActivityLogin().closeProgressDialog()
                getActivityLogin().showErrorMessage(t)
            }
        })
    }

    private fun getUserDetail() {
        getActivityLogin().getUserDetail(object : Callback<Account?> {
            override fun onResponse(call: Call<Account?>, response: Response<Account?>) {
                if (response.isSuccessful && response.body() != null) {
                    AuthenticationUtils.setAccount(call, response)
                    UserActivity.sendLogin(response.body()!!.getUsername(), response.body()!!.getFullName())
                }
                getActivityLogin().onAuthenticationFinish()
            }

            override fun onFailure(call: Call<Account?>, t: Throwable) {
                getActivityLogin().onAuthenticationFinish()
            }
        })
    }

    private fun getActivityLogin(): LoginActivity {
        return activity as LoginActivity
    }
}