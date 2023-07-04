package com.zamrud.radio.mobile.app.svara.WebView
//
//import android.annotation.SuppressLint
//import android.app.Dialog
//import android.content.Context
//import android.content.DialogInterface
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.os.Handler
//import android.os.Message
//import android.view.*
//import android.webkit.*
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.PickVisualMediaRequest
//import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
//import androidx.appcompat.app.AlertDialog
//import androidx.core.content.FileProvider
//import com.github.ybq.android.spinkit.SpinKitView
//import com.zamrud.radio.mobile.app.svara.BaseFragment
//import com.zamrud.radio.mobile.app.svara.R
//import com.zamrud.radio.mobile.app.svara.apiclient.countly.OpenPage
//import com.zamrud.radio.mobile.app.svara.helper.ImageChooser
//import java.io.File
//import java.io.IOException
//import java.util.*
//
//class WebviewFragment : BaseFragment() {
//    private var mWebView: WebView? = null
//    private var id: String? = null
//    private var title: String? = null
//    private var showBottomNavigation = false
//    private var canShowToolbarIcon = false
//    var isShowMiniPlayer = true
//    var spin_kit: SpinKitView? = null
//    private var imageChooser: ImageChooser? = null
//    private var fileCallback: ValueCallback<Array<Uri>>? = null
//    protected var takePicture: ActivityResultLauncher<Uri?>? = null
//    private var mCoverArtUri: Uri? = null
//    private var mCoverArtData: File? = null
//    protected var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>? = null
//    var contentType: String? = null
//    protected fun initOpenPage() {
//        openPage = OpenPage(OpenPage.PAGE_WEB_VIEW, id, title)
//    }
//
//    fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//        imageChooser = ImageChooser(this, ImageChooser.Source.Camera)
//        takePicture = registerForActivityResult(TakePicture()) { isSuccess ->
//            if (isSuccess) {
//                try {
//                    mCoverArtData = ImageChooser.getFile(getBaseActivity(), mCoverArtUri)
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//                fileCallback!!.onReceiveValue(
//                    arrayOf(
//                        Uri.fromFile(
//                            mCoverArtData
//                        )
//                    )
//                )
//                fileCallback = null
//            }
//        }
//        pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
//            if (uri != null) {
//                mCoverArtUri = uri
//                try {
//                    mCoverArtData = ImageChooser.getFile(getBaseActivity(), mCoverArtUri)
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//                fileCallback!!.onReceiveValue(
//                    arrayOf(
//                        Uri.fromFile(
//                            mCoverArtData
//                        )
//                    )
//                )
//                fileCallback = null
//            }
//        }
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view: View = inflater.inflate(R.layout.fragment_web_view, container, false)
//        mWebView = view.findViewById(R.id.webview)
//        spin_kit = view.findViewById<SpinKitView>(R.id.spin_kit)
//
////        mWebView.setWebViewClient(new WebViewClient() {
////            @Override
////            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                Log.d("notwa", "first "+url);
////                if (url.startsWith("whatsapp")) {
////                    try {
////                        Log.d("notwa", "wa udh terinstall");
////                        Intent intent = new Intent(Intent.ACTION_VIEW);
////                        intent.setData(Uri.parse(url));
////                        startActivity(intent);
////                        return true;
////                    } catch (ActivityNotFoundException e) {
////                        // Aplikasi WhatsApp belum terinstal.
////                        Log.d("notwa", "wa blm terinstall");
////                    }
////                }
////                view.loadUrl(url);
////                return true;
////            }
////            @Override
////            public void onPageStarted(WebView view, String url, Bitmap favicon) {
////                Log.d("notwa", "aaa "+url);
////                spin_kit.setVisibility(View.VISIBLE);
////            }
////
////            @Override
////            public void onPageFinished(WebView view, String url) {
////                Log.d("notwa", "bbb "+url);
////                spin_kit.setVisibility(View.GONE);
////            }
////        });
//        mWebView.setWebChromeClient(object : WebChromeClient() {
//            override fun getDefaultVideoPoster(): Bitmap? {
//                return BitmapFactory.decodeResource(
//                    getBaseActivity().getResources(),
//                    R.drawable.bg_blur
//                )
//            }
//
//            override fun onProgressChanged(view: WebView, newProgress: Int) {
//                if (newProgress >= 75 && spin_kit.getVisibility() == View.VISIBLE) spin_kit.setVisibility(
//                    View.GONE
//                )
//                if (newProgress < 75 && spin_kit.getVisibility() == View.GONE) spin_kit.setVisibility(
//                    View.VISIBLE
//                )
//            }
//
//            override fun onJsAlert(
//                view: WebView,
//                url: String,
//                message: String,
//                result: JsResult
//            ): Boolean {
//                return super.onJsAlert(view, url, message, result)
//            }
//
//            override fun onJsConfirm(
//                view: WebView,
//                url: String,
//                message: String,
//                result: JsResult
//            ): Boolean {
//                val builder = AlertDialog.Builder(getBaseActivity())
//                builder.setMessage(message)
//                builder.setNegativeButton(getString(R.string.cancel)) { dialog, which ->
//                    result.cancel()
//                    dialog.dismiss()
//                }
//                builder.setPositiveButton("OK") { dialog: DialogInterface, which: Int ->
//                    result.confirm()
//                    dialog.dismiss()
//                }
//                builder.setOnCancelListener { dialog: DialogInterface? -> result.cancel() }
//                builder.show()
//                return true
//            }
//
//            override fun onGeolocationPermissionsShowPrompt(
//                origin: String,
//                callback: GeolocationPermissions.Callback
//            ) {
//                callback.invoke(origin, true, false)
//            }
//
//            override fun onPermissionRequest(request: PermissionRequest) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    request.grant(request.resources)
//                }
//            }
//
//            override fun onShowFileChooser(
//                webView: WebView,
//                filePathCallback: ValueCallback<Array<Uri>>,
//                fileChooserParams: FileChooserParams
//            ): Boolean {
//                fileCallback = filePathCallback
//                val acceptTypes = fileChooserParams.acceptTypes
//                val useCamera = Arrays.binarySearch(acceptTypes, "image/camera") >= 0
//                if (useCamera && acceptTypes.size == 1) {
//                    try {
//                        mCoverArtUri = FileProvider.getUriForFile(
//                            getBaseActivity(),
//                            "com.zamrud.radio.mobile.app.svara.provider",
//                            imageChooser.createImageFile()
//                        )
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//                    takePicture!!.launch(mCoverArtUri)
//                } else {
//                    onCreateDialog()
//                }
//                return true
//            }
//        })
//        mWebView.setOnKeyListener(View.OnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
//            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == MotionEvent.ACTION_UP && mWebView.canGoBack()
//            ) {
//                handler.sendEmptyMessage(1)
//                return@setOnKeyListener true
//            }
//            false
//        })
//        mWebView.getSettings().javaScriptEnabled = true
//        mWebView.getSettings().saveFormData = false
//        mWebView.getSettings().cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
//        mWebView.getSettings().databaseEnabled = true
//        mWebView.getSettings().domStorageEnabled = true
//        mWebView.getSettings().setGeolocationEnabled(true)
//        mWebView.getSettings().allowFileAccess = false
//        mWebView.getSettings().allowContentAccess = true
//        mWebView.getSettings().allowFileAccessFromFileURLs = false
//        mWebView.getSettings().allowUniversalAccessFromFileURLs = false
//        mWebView.getSettings().javaScriptCanOpenWindowsAutomatically = true
//        mWebView.getSettings().builtInZoomControls = true
//        mWebView.loadUrl(id!!)
//        if (getBaseActivity() != null) getBaseActivity().forceHidePlayer()
//        return view
//    }
//
//    fun getFragmentTitle(context: Context?): String? {
//        return title
//    }
//
//    fun CallCallbackFinishLoadRemove() {}
//    val isMenuSearchVisible: Boolean
//        get() = false
//
//    fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {}
//    fun onDestroy() {
//        super.onDestroy()
//        mWebView!!.clearCache(true)
//        mWebView!!.clearFormData()
//    }
//
//    @SuppressLint("HandlerLeak")
//    private val handler: Handler = object : Handler() {
//        override fun handleMessage(message: Message) {
//            if (message.what == 1) {
//                mWebView!!.goBack()
//            }
//        }
//    }
//
//    fun deleteSelected(position: Int) {}
//    fun onCreateDialog(): Dialog {
//        val builder = AlertDialog.Builder(getBaseActivity())
//        builder.setTitle(R.string.pick_resource)
//            .setItems(
//                R.array.colors_array
//            ) { dialog, which ->
//                when (which) {
//                    0 -> {
//                        try {
//                            mCoverArtUri = FileProvider.getUriForFile(
//                                getBaseActivity(),
//                                "com.zamrud.radio.mobile.app.svara.provider",
//                                imageChooser.createImageFile()
//                            )
//                        } catch (e: IOException) {
//                            e.printStackTrace()
//                        }
//                        takePicture!!.launch(mCoverArtUri)
//                    }
//                    1 -> pickMedia!!.launch(
//                        Builder()
//                            .setMediaType(ImageOnly)
//                            .build()
//                    )
//                }
//            }
//        builder.create()
//        return builder.show()
//    }
//
//    fun showToolbarLogo(): Boolean {
//        return canShowToolbarIcon
//    }
//
//    val isDrawerRequired: Boolean
//        get() = true
//
//    fun showBottomNavigation(): Boolean {
//        return showBottomNavigation
//    }
//
//    companion object {
//        fun newInstance(
//            id: String?,
//            title: String?,
//            showMiniPlayer: Boolean,
//            showBottomNavigation: Boolean,
//            canShowToolbarIcon: Boolean
//        ): WebviewFragment {
//            val fragment = WebviewFragment()
//            fragment.id = id
//            fragment.title = title
//            fragment.isShowMiniPlayer = showMiniPlayer
//            fragment.showBottomNavigation = showBottomNavigation
//            fragment.canShowToolbarIcon = canShowToolbarIcon
//            return fragment
//        }
//
//        @JvmOverloads
//        fun newInstance(
//            url: String?,
//            title: String?,
//            showMiniPlayer: Boolean = true
//        ): WebviewFragment {
//            val fragment = WebviewFragment()
//            fragment.id = url
//            fragment.title = title
//            fragment.isShowMiniPlayer = showMiniPlayer
//            return fragment
//        }
//    }
//}