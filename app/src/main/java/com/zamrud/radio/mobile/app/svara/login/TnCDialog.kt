package com.zamrud.radio.mobile.app.svara.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView

import androidx.fragment.app.Fragment

import com.skydoves.expandablelayout.ExpandableLayout
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.databinding.TncDialogBinding
import com.zamrud.radio.mobile.app.svara.setting.localization.LanguageHelper

class TnCDialog(var listener: TnCListener?) : Fragment() {

    lateinit var binding: TncDialogBinding
    var expandHeight = HashMap<Int?, Int?>()

    interface TnCListener {
        fun onDestroy()
        fun onAgree()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TncDialogBinding.inflate(inflater, container, false)
        binding.root.setOnClickListener { v -> }

        expandHeight[1] = null
        expandHeight[2] = null

        toggleExpand(binding.expandable, 1)
        toggleExpand(binding.expandable2, 2)
        val langCode: String? = LanguageHelper.getCode(requireContext())
        setupWeb(binding.expandable, getString(R.string.terms_of_services), ServiceGenerator.getTermServiceUrl(langCode!!), object : WebViewRequest {
            override fun getWebView(): WebView {
                return binding.expandable.findViewById(R.id.webview)
            }
            override fun changeHeight(height: Int) {
                expandHeight[1] = height
                getWebView().layoutParams.height = height
                binding.expandable.expand(height)
            }
        })

        setupWeb(binding.expandable2, getString(R.string.privacy_policy), ServiceGenerator.getPrivacyPolicyUrl(langCode), object : WebViewRequest {
            override fun getWebView(): WebView {
                return binding.expandable2.findViewById(R.id.webview)
            }
            override fun changeHeight(height: Int) {
                expandHeight[2] = height
                getWebView().layoutParams.height = height
                binding.expandable2.expand(height)
            }
        })

        binding.btnAgree.setOnClickListener { v -> listener!!.onAgree() }

        return binding.root
    }

    private fun toggleExpand(expandableLayout: ExpandableLayout, key: Int) {
        expandableLayout.parentLayout.setOnClickListener { v: View? ->
            if (expandHeight.containsKey(key) && expandHeight[key] == null)
                return@setOnClickListener

            if (expandableLayout.isExpanded)
                expandableLayout.toggleLayout()
            else
                try {
                    expandableLayout.expand(expandHeight[key]!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWeb(expandableLayout: ExpandableLayout, name: String, url: String, webViewRequest: WebViewRequest) {
        val textView: TextView = expandableLayout.parentLayout.findViewById(R.id.tvTitle)
        textView.text = name

        val webView: WebView = webViewRequest.getWebView()

        val client: WebViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl("javascript:AndroidFunction.resize(document.body.scrollHeight)")
            }
        }

        webView.webViewClient = client
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(WebAppInterface(webViewRequest), "AndroidFunction")
        webView.loadUrl(url)
    }

    interface WebViewRequest {
        fun getWebView(): WebView

        fun changeHeight(height: Int)
    }

    inner class WebAppInterface(var webViewRequest: WebViewRequest) {
        @JavascriptInterface
        fun resize(height: Float) {
            val webViewHeight: Float = (height * resources.displayMetrics.density)
            webViewRequest.changeHeight(webViewHeight.toInt() + 100)
        }
    }

    override fun onDestroy() {
        if (listener != null)
            listener!!.onDestroy()
        super.onDestroy()
    }
}