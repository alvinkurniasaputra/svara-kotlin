package com.zamrud.radio.mobile.app.svara.login

import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.zamrud.radio.mobile.app.svara.CustomProject
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.apiclient.exception.HttpRequestException
import com.zamrud.radio.mobile.app.svara.apiclient.model.account.LoginAppResponse
import com.zamrud.radio.mobile.app.svara.databinding.FragmentLoginSocialBinding
import com.zamrud.radio.mobile.app.svara.main.LoginActivity
import com.zamrud.radio.mobile.app.svara.setting.localization.LanguageHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginOnlySocialFragment() : Fragment() {
    private lateinit var binding: FragmentLoginSocialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity !is LoginActivity)
            throw ClassCastException(LoginFragment::class.java.getSimpleName()
                    + " must be place in LoginActivity! ")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginSocialBinding.inflate(inflater, container, false)
        binding.btnFacebook.setOnClickListener { view ->
            if (isTermChecked())
                getActivityLogin().onLoginFacebookClick()
            else showCheck() }
        binding.btnGoogle.setOnClickListener { v ->
            if (isTermChecked())
                getActivityLogin().onLoginGoogleClick()
            else showCheck() }
        binding.btnPhone.setOnClickListener { v ->
            if (isTermChecked())
                getActivityLogin().onPhoneLoginClick()
            else showCheck() }
        binding.btnSkip.setOnClickListener { v -> onSkipClick() }

        if (!CustomProject.withSkip) {
            binding.btnSkip.visibility = View.GONE
        } else {
            binding.btnSkip.visibility = View.VISIBLE
        }

        setupTermText()
        return binding.root
    }

    private fun isTermChecked(): Boolean {
        return binding.checkTerm.isChecked
    }

    private fun showCheck() {
        if (context == null)
            return
        val message = requireContext().getString(R.string.inter_agree_privacy_policy)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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

    private fun setupTermText() {
        if (context == null)
            return
        //        "By creating an account you agree to our Term Of Services and conform that you have read our Privacy Policy"
        val byCreating = resources.getString(R.string.inter_agreement_by_creating)
        val term = resources.getString(R.string.inter_agreement_term_of_services)
        val confirm = resources.getString(R.string.inter_agreement_confirm)
        val policy = resources.getString(R.string.inter_agreement_privacy_policy)
        val span: Spannable = Spannable.Factory.getInstance().newSpannable(byCreating + term + confirm + policy)
        val langCode: String? = LanguageHelper.getCode(requireContext())
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
//         StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        val colorSpanTerm = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.svara_blue))
        val colorSpanPolicy = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.svara_blue))

//        span.setSpan(new RelativeSizeSpan(1.1f), byCreating.length(), byCreating.length()+term.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(registerClickTerm, byCreating.length, byCreating.length + term.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(colorSpanTerm, byCreating.length, byCreating.length + term.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        span.setSpan(registerClickPolicy, byCreating.length + term.length + confirm.length, byCreating.length + term.length + confirm.length + policy.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(colorSpanPolicy, byCreating.length + term.length + confirm.length, byCreating.length + term.length + confirm.length + policy.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.term.text = span
        binding.term.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun getActivityLogin(): LoginActivity {
        return activity as LoginActivity
    }
}