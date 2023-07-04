package com.zamrud.radio.mobile.app.svara.login

import android.graphics.Typeface
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
import android.widget.*
import androidx.fragment.app.Fragment
import com.zamrud.radio.mobile.app.svara.CustomProject
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.TextUtils
//import com.zamrud.radio.mobile.app.svara.WebView.WebviewFragment
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.apiclient.countly.UserActivity
import com.zamrud.radio.mobile.app.svara.apiclient.exception.HttpRequestException
import com.zamrud.radio.mobile.app.svara.apiclient.model.account.LoginAppResponse
import com.zamrud.radio.mobile.app.svara.apiclient.model.account.SignUpRequest
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Account
import com.zamrud.radio.mobile.app.svara.helper.ThemeHelper
import com.zamrud.radio.mobile.app.svara.login.TnCDialog.TnCListener
import com.zamrud.radio.mobile.app.svara.main.LoginActivity
import com.zamrud.radio.mobile.app.svara.setting.localization.LanguageHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {
    lateinit var btnRegister: TextView
    lateinit var tvTerm: TextView
    lateinit var btnEye: ImageView
    lateinit var etUsername: EditText
    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var btnSignUp: View
    lateinit var btnFacebook: View
    lateinit var btnGoogle: View
    lateinit var btnPhone: View
    lateinit var btnSkip: View
    lateinit var checkTerm: CheckBox
    lateinit var fragmentChild: View
    lateinit var lblSignUpWith: View

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity !is LoginActivity)
            throw ClassCastException(SignUpFragment::class.java.simpleName
                    + " must be place in LoginActivity! ")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_signup, container, false)
        btnRegister = view.findViewById(R.id.btnRegister)
        etUsername = view.findViewById(R.id.etUsername)
        etName = view.findViewById(R.id.etName)
        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        btnEye = view.findViewById(R.id.btnEye)
        btnSignUp = view.findViewById(R.id.btnSignUp)
        btnFacebook = view.findViewById(R.id.btnFacebook)
        btnGoogle = view.findViewById(R.id.btnGoogle)
        btnPhone = view.findViewById(R.id.btnPhone)
        btnSkip = view.findViewById(R.id.btnSkip)
        tvTerm = view.findViewById(R.id.term)
        checkTerm = view.findViewById(R.id.checkTerm)
        fragmentChild = view.findViewById(R.id.fragmentChild)
        lblSignUpWith = view.findViewById(R.id.lblSignUpWith)

        view.findViewById<View>(R.id.imgLogo).visibility = if (CustomProject.loginPageUseLogo) View.VISIBLE else View.INVISIBLE

        btnEye.setOnClickListener { v: View? -> onEyeClick() }
        btnSignUp.setOnClickListener { v: View? -> onSignUpClick() }
        btnFacebook.setOnClickListener { v: View ->
            if (!checkTerm.isChecked) {
                notifyToUser(v.context.getString(R.string.agree_privacy_policy))
                return@setOnClickListener
            }
            getActivityLogin().onLoginFacebookClick()
        }
        btnGoogle.setOnClickListener { v: View ->
            if (!checkTerm.isChecked) {
                notifyToUser(v.context.getString(R.string.agree_privacy_policy))
                return@setOnClickListener
            }
            getActivityLogin().onLoginGoogleClick()
        }
        btnPhone.setOnClickListener { v: View ->
            if (!checkTerm.isChecked) {
                notifyToUser(v.context.getString(R.string.agree_privacy_policy))
                return@setOnClickListener
            }
            getActivityLogin().onPhoneLoginClick()
        }
        btnSkip.setOnClickListener { v: View? -> onSkipClick() }

        if (!CustomProject.withSkip) {
            btnSkip.visibility = View.GONE
        } else {
            btnSkip.visibility = View.VISIBLE
        }

        setupLoginText()
        setupTermText()
        setupLoginOption()
        getActivityLogin().setupUI(view)
        return view
    }

    private fun setupLoginOption() {
        val appLoginOption = AuthenticationUtils.getAppLoginOption(getActivityLogin())
        if (appLoginOption.isDisableFacebookLogin())
            btnFacebook.visibility = View.GONE
        if (appLoginOption.isDisableGoogleLogin())
            btnGoogle.visibility = View.GONE
        if (appLoginOption.isDisablePhoneLogin())
            btnPhone.visibility = View.GONE
        if (appLoginOption.isDisableFacebookLogin() && appLoginOption.isDisableGoogleLogin() && appLoginOption.isDisablePhoneLogin())
            lblSignUpWith.visibility = View.GONE
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

    private fun onSignUpClick() {
        val isUser: Boolean = TextUtils.hasText(etUsername.text.toString())
        val isName: Boolean = TextUtils.hasText(etName.text.toString())
        val isEmail: Boolean = TextUtils.hasText(etEmail.text.toString())
        val isPassword: Boolean = TextUtils.hasText(etPassword.text.toString())
        if (!isUser) {
            etUsername.error = resources.getString(R.string.inter_username_cant_blank)
            return
        }
        if (!isName) {
            etName.error = resources.getString(R.string.inter_name_cant_blank)
            return
        }
        if (!isEmail) {
            etEmail.error = resources.getString(R.string.inter_email_cant_blank)
            return
        }
        if (!isPassword) {
            etPassword.error = resources.getString(R.string.inter_password_cant_blank)
            return
        }
        if (!TextUtils.isEmail(etEmail.text.toString())) {
            etEmail.error = resources.getString(R.string.inter_wrong_email)
            return
        }
        if (etPassword.length() < 3) {
            etPassword.error = resources.getString(R.string.inter_password_to_short)
            return
        }
        if (!checkTerm.isChecked) {
            notifyToUser(checkTerm.context.getString(R.string.inter_agree_privacy_policy))
            return
        }

        getActivityLogin().hideKeyboard()

        if (CustomProject.registerShowTnC) {
            fragmentChild.visibility = View.VISIBLE
            val tnCDialog = TnCDialog(object : TnCListener {
                override fun onDestroy() {
                    fragmentChild.visibility = View.GONE
                }

                override fun onAgree() {
                    fragmentChild.visibility = View.GONE
                    prepareSignUp()
                }
            })
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentChild, tnCDialog, "tncDialog").addToBackStack(null).commit()
        } else prepareSignUp()
    }

    private fun onSkipClick() {
        if (getActivityLogin().getMode() == LoginActivity.MODE_REQUIRE_LOGIN) {
            getActivityLogin().onBackPressed()
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

    private fun prepareSignUp() {
        getActivityLogin().showProgressDialog()
        val signUpRequest = SignUpRequest()
        signUpRequest.setFirstName(etName.text.toString())
        signUpRequest.setUsername(etUsername.text.toString())
        signUpRequest.setEmail(etEmail.text.toString())
        signUpRequest.setPassword(etPassword.text.toString())
        loginApp(signUpRequest)
    }

    private fun loginApp(signUpRequest: SignUpRequest) {
        getActivityLogin().hideKeyboard()
        AuthenticationUtils.appLogin(requireContext(), object : Callback<LoginAppResponse?> {
            override fun onResponse(call: Call<LoginAppResponse?>, response: Response<LoginAppResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    val userParam: SignUpRequest.UserParam = signUpRequest.UserParam()
                    userParam.setAppId(response.body()!!.getAppId())
                    signUpRequest.setUserParam(userParam)
                    signUp(signUpRequest, response.body()!!.getAccessToken())
                } else {
                    getActivityLogin().closeProgressDialog()
                    getActivityLogin().showErrorMessage(HttpRequestException(response.code()))
                }
            }

            override fun onFailure(call: Call<LoginAppResponse?>, t: Throwable) {
                getActivityLogin().closeProgressDialog()
                getActivityLogin().showErrorMessage(t)
            }
        })
    }

    private fun signUp(signUpRequest: SignUpRequest, appToken: String?) {
        if (context == null)
            return

        AuthenticationUtils.signUp(signUpRequest, requireContext(), appToken, object : AuthenticationUtils.AuthenticationCallback {
                override fun onSuccess() {
                    loginUser(appToken)
                }

                override fun onFailure(t: Throwable) {
                    getActivityLogin().closeProgressDialog()
                    notifyToUser(t.message)
                }
            })
    }

    private fun loginUser(appToken: String?) {
        AuthenticationUtils.login(etUsername.text.toString(),
            etPassword.text.toString(),
            requireContext(), appToken,
            object : AuthenticationUtils.AuthenticationCallback {
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
                    getActivityLogin().closeProgressDialog()
                    UserActivity.sendSignUp(response.body()!!.getUsername(), response.body()!!.getFullName())
                    getActivityLogin().startFragment(RegisterFragment.newInstance(response.body()), addBackStack = false, animated = true)

                } else
                    getActivityLogin().onAuthenticationFinish()
            }

            override fun onFailure(call: Call<Account?>, t: Throwable) {
                getActivityLogin().onAuthenticationFinish()
            }
        })
    }

    private fun notifyToUser(message: String?) {
        if (context == null)
            return
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun setupLoginText() {
        if (context == null)
            return
        val info = resources.getString(R.string.inter_have_an_account)
        val register = resources.getString(R.string.inter_have_an_account_sign_in)
        val span: Spannable = Spannable.Factory.getInstance().newSpannable(info + register)
        val registerClick: ClickableSpan = object : ClickableSpan() {
            override fun onClick(v: View) {
                getActivityLogin().startFragment(LoginFragment(), addBackStack = false, animated = true)
            }
        }
        val styleSpan = StyleSpan(Typeface.BOLD)
        val colorSpan = ForegroundColorSpan(ThemeHelper.getColorAttr(requireContext(), R.attr.colorBackground1))

        span.setSpan(registerClick, info.length, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(RelativeSizeSpan(1.1f), info.length, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(styleSpan, info.length, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(colorSpan, info.length, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        btnRegister.text = span
        btnRegister.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setupTermText() {
        if (context == null)
            return
        //        "By creating an account you agree to our Term Of Services and conform that you have read our Privacy Policy"
        val byCreating = resources.getString(R.string.inter_agreement_by_creating)
        val term = resources.getString(R.string.inter_agreement_term_of_services)
        val confirm = resources.getString(R.string.inter_agreement_confirm)
        val policy = resources.getString(R.string.inter_agreement_privacy_policy)
        val langCode: String? = LanguageHelper.getCode(requireContext())
        val span: Spannable = Spannable.Factory.getInstance().newSpannable(byCreating + term + confirm + policy)
        val registerClickTerm: ClickableSpan = object : ClickableSpan() {
            override fun onClick(v: View) {
//                getActivityLogin().startFragment(WebviewFragment.newInstance(ServiceGenerator.getTermServiceUrl(langCode!!), resources.getString(R.string.inter_term_of_services)), addBackStack = true, animated = true)
            }
        }

        val registerClickPolicy: ClickableSpan = object : ClickableSpan() {
            override fun onClick(v: View) {
//                getActivityLogin().startFragment(WebviewFragment.newInstance(ServiceGenerator.getPrivacyPolicyUrl(langCode!!), resources.getString(R.string.inter_privacy_policy)), addBackStack = true, animated = true)
            }
        }
        //        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        val colorSpanTerm = ForegroundColorSpan(ThemeHelper.getColorAttr(requireContext(), R.attr.colorBackground1))
        val colorSpanPolicy = ForegroundColorSpan(ThemeHelper.getColorAttr(requireContext(), R.attr.colorBackground1))

//        span.setSpan(new RelativeSizeSpan(1.1f), byCreating.length(), byCreating.length()+term.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(registerClickTerm, byCreating.length, byCreating.length + term.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(colorSpanTerm, byCreating.length, byCreating.length + term.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        span.setSpan(registerClickPolicy, byCreating.length + term.length + confirm.length, byCreating.length + term.length + confirm.length + policy.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(colorSpanPolicy, byCreating.length + term.length + confirm.length, byCreating.length + term.length + confirm.length + policy.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvTerm.text = span
        tvTerm.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun getActivityLogin(): LoginActivity {
        return activity as LoginActivity
    }
}