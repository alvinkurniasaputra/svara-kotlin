package com.zamrud.radio.mobile.app.svara.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast

import androidx.fragment.app.Fragment

import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.databinding.FragmentLoginPhoneBinding
import com.zamrud.radio.mobile.app.svara.helper.FontHelper
import com.zamrud.radio.mobile.app.svara.helper.LocaleHelper
import com.zamrud.radio.mobile.app.svara.helper.TextListener
import com.zamrud.radio.mobile.app.svara.main.LoginActivity

class LoginPhoneFragment() : Fragment() {
    lateinit var binding: FragmentLoginPhoneBinding
    var editTexts: ArrayList<EditText> = ArrayList()
    private var isCodeSend = false

    interface ProcessDone {
        fun doneSendNumber()
        fun doneSendCode()
        fun error(message: String?)
    }

    interface CodeTimer {
        fun progress(time: String?)
        fun onDone()
    }

    interface OnCodeCapture {
        fun onReceiveCode(code: String?)
    }

    companion object {
        fun newInstance(hasCode: Boolean): LoginPhoneFragment {
            val fragment = LoginPhoneFragment()
            fragment.isCodeSend = hasCode
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity !is LoginActivity)
            throw ClassCastException(LoginFragment::class.java.simpleName
                    + " must be place in LoginActivity! ")
    }

    private fun getActivityLogin(): LoginActivity {
        return activity as LoginActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginPhoneBinding.inflate(inflater, container, false)
        binding.ccp.setCountryForNameCode(LocaleHelper.getCountryId(requireActivity()))
        binding.ccp.registerCarrierNumberEditText(binding.etPhone)
        FontHelper.Bold(context, binding.tvChangeNumber)

        editTexts.add(binding.etCode0)
        editTexts.add(binding.etCode1)
        editTexts.add(binding.etCode2)
        editTexts.add(binding.etCode3)
        editTexts.add(binding.etCode4)
        editTexts.add(binding.etCode5)

        setupLayout()

        binding.ccp.setPhoneNumberValidityChangeListener { isValidNumber ->
            if (isValidNumber) phoneValid()
            else phoneInvalid()
        }

        binding.btnVerify.setOnClickListener { v ->
            if (!binding.ccp.isValidFullNumber)
                return@setOnClickListener

            isCodeSend = true
            binding.btnVerify.visibility = View.INVISIBLE
            binding.loadingVerify.visibility = View.VISIBLE
            getActivityLogin().sendPhoneNumber(binding.ccp.fullNumberWithPlus, object : ProcessDone {
                    override fun doneSendNumber() {
                        setupVerifyCode()
                        getActivityLogin().startCountDownPhoneCodeExpire()
                        setupLayout()
                        binding.loadingVerify.visibility = View.GONE
                        binding.btnVerify.visibility = View.VISIBLE
                    }

                    override fun doneSendCode() {
                        binding.btnSendCode.visibility = View.INVISIBLE
                        binding.loadingSendCode.visibility = View.GONE
                    }

                    override fun error(message: String?) {
                        binding.loadingVerify.visibility = View.GONE
                        binding.btnVerify.visibility = View.VISIBLE
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                })
            binding.etCode0.requestFocus()
        }

        binding.btnSendCode.setOnClickListener { v ->
            binding.btnSendCode.visibility = View.INVISIBLE
            binding.loadingSendCode.visibility = View.VISIBLE
            getActivityLogin().sendPhoneCode(captureCode(), object : ProcessDone {
                override fun doneSendNumber() {

                }
                override fun doneSendCode() {
                    binding.btnSendCode.visibility = View.INVISIBLE
                    binding.loadingSendCode.visibility = View.GONE
                }
                override fun error(message: String?) {
                    binding.btnSendCode.visibility = View.VISIBLE
                    binding.loadingSendCode.visibility = View.GONE
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.tvChangeNumber.setOnClickListener { v ->
            isCodeSend = false
            getActivityLogin().resetPhoneNumber()
            setupLayout()
            binding.etPhone.setText("")
        }

        syncButtonSendCode()
        setupFocusCode()

        getActivityLogin().setCodeTimerListener(codeTimer)
        getActivityLogin().setOnCodeCapture(onCodeCapture)
        return binding.root
    }

    override fun onDestroy() {
        getActivityLogin().deleteCodeTimerListener()
        getActivityLogin().setOnCodeCapture(null)
        super.onDestroy()
    }

    private val codeTimer: CodeTimer = object : CodeTimer {
        override fun progress(time: String?) {
            binding.tvResend.text = resources.getString(R.string.inter_resend_code_in, time)
            binding.tvResend.setOnClickListener(null)
            binding.tvResend.isClickable = false
        }

        override fun onDone() {
            binding.tvResend.text = resources.getString(R.string.inter_resend_code)
            setupResendButton()
        }
    }

    private val onCodeCapture: OnCodeCapture = object : OnCodeCapture {
        override fun onReceiveCode(code: String?) {
            for (i in code!!.indices) {
                if (i < editTexts.size) {
                    editTexts[i].setText(code[i].toString())
                }
            }
            binding.btnSendCode.visibility = View.INVISIBLE
            binding.loadingSendCode.visibility = View.VISIBLE
        }
    }

    private fun setupVerifyCode() {
        binding.tvInfo.text = requireContext().resources.getString(R.string.inter_code_info, binding.ccp.fullNumberWithPlus)
    }

    private fun setupLayout() {
        binding.tvResend.isClickable = false
        if (isCodeSend) {
            binding.etPhone.setText(getActivityLogin().getPhoneNumber())
            setupVerifyCode()
            if (getActivityLogin().canResendNumber())
                setupResendButton()
                binding.layoutPhone.visibility = View.GONE
                binding.layoutCode.visibility = View.VISIBLE
        } else {
            binding.layoutPhone.visibility = View.VISIBLE
            binding.layoutCode.visibility = View.GONE
        }
    }

    private fun phoneValid() {
        binding.btnVerify.isEnabled = true
        binding.btnVerify.alpha = 1.0f
    }

    private fun phoneInvalid() {
        binding.btnVerify.isEnabled = false
        binding.btnVerify.alpha = 0.7f
    }

    private fun syncButtonSendCode() {
        if (captureCode().length == 6) {
            binding.btnSendCode.isEnabled = true
            binding.btnSendCode.alpha = 1.0f
        } else {
            binding.btnSendCode.isEnabled = false
            binding.btnSendCode.alpha = 0.5f
        }
    }

    private fun captureCode(): String {
        val builder = StringBuilder()
        for (et: EditText in editTexts) {
            if (et.text != null)
                builder.append(et.text.toString())
        }
        return builder.toString()
    }

    private fun setupFocusCode() {
        for (i in editTexts.indices) {
            val et: EditText = editTexts[i]
            val finalI: Int = i
            et.addTextChangedListener(object : TextListener() {
                override fun afterTextChanged(editable: Editable) {
                    if (editable.length > 0 && finalI < editTexts.size - 1)
                        editTexts[finalI + 1].requestFocus()
                    syncButtonSendCode()
                }
            })
        }
    }

    private fun setupResendButton() {
        binding.tvResend.isClickable = true
        binding.tvResend.setOnClickListener { v -> getActivityLogin().resendPhoneCode(object : ProcessDone {
                override fun doneSendNumber() {
                    getActivityLogin().startCountDownPhoneCodeExpire()
                }

                override fun doneSendCode() {

                }
                override fun error(message: String?) {

                }
            })
        }
    }

}