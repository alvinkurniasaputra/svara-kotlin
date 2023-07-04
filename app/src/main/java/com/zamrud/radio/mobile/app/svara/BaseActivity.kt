package com.zamrud.radio.mobile.app.svara
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.ComponentName
//import android.content.Context
//import android.content.Intent
//import android.content.ServiceConnection
//import android.content.pm.PackageManager
//import android.graphics.Color
//import android.graphics.PorterDuff
//import android.os.Build
//import android.os.Bundle
//import android.os.Handler
//import android.os.IBinder
//import android.support.v4.media.MediaBrowserCompat
//import android.support.v4.media.MediaMetadataCompat
//import android.support.v4.media.session.MediaControllerCompat
//import android.support.v4.media.session.MediaSessionCompat
//import android.support.v4.media.session.PlaybackStateCompat
//import android.text.Spannable
//import android.util.Log
//import android.view.Gravity
//import android.view.Menu
//import android.view.View
//import android.view.WindowManager
//import android.view.inputmethod.InputMethodManager
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.annotation.ColorInt
//import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import androidx.drawerlayout.widget.DrawerLayout
//import androidx.fragment.app.Fragment
//import com.bumptech.glide.Glide
//import com.google.android.material.snackbar.Snackbar
//import com.google.gson.JsonArray
//import com.google.gson.JsonObject
//import com.zamrud.radio.mobile.app.svara.Player.Model.PlayableModel
//import com.zamrud.radio.mobile.app.svara.Player.Svara.SvaraMediaPlayer
//import com.zamrud.radio.mobile.app.svara.Player.Svara.SvaraMediaService
//import com.zamrud.radio.mobile.app.svara.Player.Svara.SvaraNotificationManager
//import com.zamrud.radio.mobile.app.svara.Player.Svara.Video.VideoPlayer
//import com.zamrud.radio.mobile.app.svara.Player.Utils.ActionUtil
//import com.zamrud.radio.mobile.app.svara.Player.Utils.Strings
//import com.zamrud.radio.mobile.app.svara.Player.Video.VideoPlayerContent
import com.zamrud.radio.mobile.app.svara.Player.listener.PlayerFragmentRequest
import com.zamrud.radio.mobile.app.svara.Player.listener.SvaraPlayerListener
//import com.zamrud.radio.mobile.app.svara.Realm.RealmInstance
//import com.zamrud.radio.mobile.app.svara.WebView.WebviewFragment
//import com.zamrud.radio.mobile.app.svara.WebView.payment.ActivityWebViewPayment
//import com.zamrud.radio.mobile.app.svara.album.AlbumFragment
//import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
//import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
//import com.zamrud.radio.mobile.app.svara.apiclient.ResponseHelper
//import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
//import com.zamrud.radio.mobile.app.svara.apiclient.countly.EventHelper
//import com.zamrud.radio.mobile.app.svara.apiclient.countly.PlayerEvent
//import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel
//import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.*
//import com.zamrud.radio.mobile.app.svara.apiclient.model.video.Video
//import com.zamrud.radio.mobile.app.svara.apiclient.model.video.VideoModel
//import com.zamrud.radio.mobile.app.svara.apiclient.services.AccountsService
//import com.zamrud.radio.mobile.app.svara.apiclient.services.PlaylistService
//import com.zamrud.radio.mobile.app.svara.apiclient.services.VideoService
//import com.zamrud.radio.mobile.app.svara.article.ArticleView
//import com.zamrud.radio.mobile.app.svara.artist.fragment.ArtistFragment
//import com.zamrud.radio.mobile.app.svara.document.DownloadDocument
//import com.zamrud.radio.mobile.app.svara.document.FragmentDocument
//import com.zamrud.radio.mobile.app.svara.helper.ExceptionHandler
//import com.zamrud.radio.mobile.app.svara.helper.ThemeHelper
//import com.zamrud.radio.mobile.app.svara.main.LoginActivity
//import com.zamrud.radio.mobile.app.svara.membership.GooglePayHelper
//import com.zamrud.radio.mobile.app.svara.menu.BadgeDrawerArrowDrawable
//import com.zamrud.radio.mobile.app.svara.music.MusicFragment
//import com.zamrud.radio.mobile.app.svara.notification.NotificationFragment
//import com.zamrud.radio.mobile.app.svara.playlist.PlaylistFragment
//import com.zamrud.radio.mobile.app.svara.playlist.PlaylistKind
//import com.zamrud.radio.mobile.app.svara.podcast.PodcastFragment
//import com.zamrud.radio.mobile.app.svara.podcastRadio.PodcastRadioFragment
//import com.zamrud.radio.mobile.app.svara.radioprofile.RadioProfileFragment
//import com.zamrud.radio.mobile.app.svara.referral.ReferralFragment
//import com.zamrud.radio.mobile.app.svara.search.fragment.SearchHistoryFragment
//import com.zamrud.radio.mobile.app.svara.series.fragment.SeriesFragment
//import com.zamrud.radio.mobile.app.svara.services.DownloadService
//import com.zamrud.radio.mobile.app.svara.setting.SettingUtil
//import com.zamrud.radio.mobile.app.svara.social.SocialCommentFragment
//import com.zamrud.radio.mobile.app.svara.termsPolicies.TermsPoliciesFragment
//import com.zamrud.radio.mobile.app.svara.userprofile.fragments.UserProfileFragment
//import io.github.inflationx.viewpump.ViewPumpContextWrapper
//import ly.count.android.sdk.Countly
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import timber.log.Timber
//import timber.log.Timber.DebugTree
//import java.util.*
//
abstract class BaseActivity : AppCompatActivity(),
    SvaraPlayerListener, PlayerFragmentRequest {
//    protected var isFirstAppOpen = true
//    protected var isPlayerShowing = false
//    protected var mediaBrowser: MediaBrowserCompat? = null
//    protected var svaraMediaPlayer: SvaraMediaPlayer? = null
//    protected var svaraPlayerListener: MutableList<SvaraPlayerListener>? = null
//    protected var toolbar: Toolbar? = null
//    protected var title: TextView? = null
//    protected var btnProfile: ImageView? = null
//    protected var btnSearch: ImageView? = null
//    protected var toolbarLogo: ImageView? = null
//    protected var drawerContainer: View? = null
//    protected var btnDrawer: ImageView? = null
//    protected var badgeNotification: TextView? = null
//    protected var drawer: DrawerLayout? = null
//    protected var drawerToggle: ActionBarDrawerToggle? = null
//    private var badgeDrawable: BadgeDrawerArrowDrawable? = null
//    var textColor = Color.WHITE
//    var textActiveColor = Color.BLACK
//    protected var fragment: BaseFragment? = null
//    protected var caller: Intent? = null
//    protected var noContentView: View? = null
//    protected var imgEmpty: ImageView? = null
//    protected var txtEmpty: TextView? = null
//    protected var btnEmpty: Button? = null
//    var notifyCount = 0 //unread notification count
//    abstract val context: Context?
//    abstract val activity: Activity?
//
//    abstract fun showPlayer()
//    abstract fun showPlayer(typeShow: Int, videoUrl: String?)
//    abstract fun showVideoPlayer(video: VideoModel?, fullscreen: Boolean)
//    abstract fun releaseVideoContent()
//    abstract fun showVideoPlayerPIP()
//    abstract fun addMarginPIP(bottom: Int)
//    abstract fun resetMarginPIP()
//    abstract fun closeVideoPlayerPIP()
//    abstract fun hidePlayer()
//    abstract fun forceHidePlayer()
//    abstract fun checkIsFavorite()
//    abstract fun hideBottomNavigation()
//    abstract fun showBottomNavigation()
//    abstract fun releaseMiniPlayer()
//    fun syncBottomNavigation(show: Boolean) {
//        if (show) showBottomNavigation() else hideBottomNavigation()
//    }
//
//    protected var colors: Colors = Colors()
//    fun getColors(): Colors {
//        return colors
//    }
//
//    fun playRadioWithMessage(model: PlayableModel?, message: Spannable?) {
//        Play(model)
//    }
//
//    abstract fun closeDrawer()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        if (SettingUtil.getTheme(context!!)
//                .equals(CustomProject.DARK_THEME)
//        ) setTheme(R.style.Dark) else setTheme(R.style.Light)
//        super.onCreate(savedInstanceState)
//        caller = intent
//        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler(this))
//        Timber.plant(DebugTree())
//        Log.d("login1", "1")
//        sendLocation()
//        Log.d("login1", "2")
//        svaraPlayerListener = ArrayList<SvaraPlayerListener>()
//        svaraPlayerListener!!.add(this)
//    }
//
//    override fun onDestroy() {
//        doUnbindService()
//        releaseVideoContent()
//        releaseVideo()
//        if (mediaControllerCompat != null) {
//            if (mediaControllerCompat!!.playbackState.state != PlaybackStateCompat.STATE_PLAYING) mediaControllerCompat!!.transportControls.stop()
//        }
//
//        // TODO: 6/25/2020 kenapa tutup app (back back terus) FC?
//        try {
//            super.onDestroy()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Countly.sharedInstance().onStart(this)
//        if (mediaBrowser == null) mediaBrowser = MediaBrowserCompat(
//            this,
//            ComponentName(context, SvaraMediaService::class.java),
//            mConnectionCallbacks,
//            null
//        )
//        if (!mediaBrowser!!.isConnected) {
//            try {
//                mediaBrowser!!.connect()
//            } catch (e: Exception) {
//                println(e.message)
//            }
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//    }
//
//    override fun onStop() {
//        Countly.sharedInstance().onStop()
//        super.onStop()
//        if (mediaControllerCompat != null) {
//            mediaControllerCompat!!.unregisterCallback(controllerCallback)
//        }
//        if (mediaBrowser != null && mediaBrowser!!.isConnected) mediaBrowser!!.disconnect()
//    }
//
//    override fun setContentView(layoutResID: Int) {
//        super.setContentView(layoutResID)
//        toolbar = findViewById<Toolbar>(R.id.toolbar)
//        btnProfile = findViewById<ImageView>(R.id.btnProfile)
//        btnSearch = findViewById<ImageView>(R.id.btnSearch)
//        toolbarLogo = findViewById<ImageView>(R.id.toolbarLogo)
//        title = findViewById<TextView>(R.id.tv_title)
//        drawer = findViewById<DrawerLayout>(R.id.navigation_drawer)
//        drawerContainer = findViewById<View>(R.id.drawerContainer)
//        btnDrawer = findViewById<ImageView>(R.id.btnDrawer)
//        badgeNotification = findViewById<TextView>(R.id.badgeNotification)
//        noContentView = findViewById<View>(R.id.view_no_content)
//        imgEmpty = findViewById<ImageView>(R.id.img_empty)
//        txtEmpty = findViewById<TextView>(R.id.text_empty)
//        btnEmpty = findViewById<Button>(R.id.btn_empty)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        if (toolbar != null) setSupportActionBar(toolbar)
//        if (supportActionBar != null) supportActionBar!!.setDisplayShowTitleEnabled(false)
//        title.setSelected(true)
//        title.setTextColor(ThemeHelper.getColorAttr(this, R.attr.colorToolbarText))
//        drawerToggle = ActionBarDrawerToggle(
//            this,
//            drawer,
//            toolbar,
//            R.string.navigation_drawer_open,
//            R.string.navigation_drawer_close
//        )
//        drawerToggle!!.isDrawerIndicatorEnabled = false
//        //add badge on burger menu
//        badgeDrawable = BadgeDrawerArrowDrawable(supportActionBar!!.themedContext)
//        drawerToggle!!.drawerArrowDrawable = badgeDrawable
//        notificationCount
//        //Listener for back button in toolbar
//        drawerToggle!!.toolbarNavigationClickListener =
//            View.OnClickListener { v: View? -> onBackPressed() }
//        drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
//            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
//            override fun onDrawerOpened(drawerView: View) {}
//            override fun onDrawerClosed(drawerView: View) {
//                syncFragmentState()
//            }
//
//            override fun onDrawerStateChanged(newState: Int) {}
//        })
//        btnEmpty.setOnClickListener(View.OnClickListener { v: View? ->
//            val intent = Intent(baseActivity, LoginActivity::class.java)
//            intent.putExtra("mode", LoginActivity.MODE_LOGIN)
//            baseActivity.startActivity(intent)
//        })
//        btnSearch.setOnClickListener(View.OnClickListener { v: View? ->
//            startFragment(
//                SearchHistoryFragment()
//            )
//        })
//        bindUserProfile()
//        makeTransparentStatusBar()
//        setupLastPlayed()
//    }
//
//    private fun bindUserProfile() {
//        if (AuthenticationUtils.isGuest(context!!)) {
//            btnProfile!!.setOnClickListener { v: View? ->
//                AuthenticationUtils.goLogin(
//                    this
//                )
//            }
//        } else {
//            btnProfile!!.setOnClickListener { v: View? ->
//                startFragment(
//                    UserProfileFragment.newInstance(
//                        AuthenticationUtils.getLoggeInUserId(
//                            context!!
//                        )
//                    )
//                )
//            }
//            AuthenticationUtils.fetchAccount(context!!, object : ResponseHelper<Account?>() {
//                override fun onSuccess(body: Account?) {
//                    Glide.with(btnProfile!!).load(body!!.getImages().getImage150())
//                        .into(btnProfile!!)
//                }
//            })
//        }
//    }
//
//    fun setSearchMenuVisibility(visible: Boolean) {
//        if (btnSearch != null) {
//            btnSearch!!.visibility =
//                if (!AuthenticationUtils.isDisableSearch(context!!) && visible) View.VISIBLE else View.GONE
//        }
//    }
//
//    fun setProfileMenuVisibility(visible: Boolean) {
//        if (btnProfile != null) {
//            btnProfile!!.visibility =
//                if (AuthenticationUtils.isEnableProfileMenu(context!!) && visible) View.VISIBLE else View.GONE
//        }
//    }
//
//    fun setToolbarLogoVisibility(visible: Boolean) {
//        if (toolbarLogo != null) toolbarLogo!!.visibility =
//            if (CustomProject.enableToolbarLogo && visible) View.VISIBLE else View.GONE
//    }
//
//    private fun sendLocation() {
//        val accountsService = ServiceGenerator.createServiceWithAuth(
//            AccountsService::class.java, this
//        )
//        val jsonObject = JsonObject()
//        val jsonArray = JsonArray()
//        Log.d("login1", "getlong: " + AuthenticationUtils.getLong(this))
//        Log.d("login1", "getlat: " + AuthenticationUtils.getLat(this))
//        jsonArray.add(AuthenticationUtils.getLong(this))
//        jsonArray.add(AuthenticationUtils.getLat(this))
//        jsonObject.add("location", jsonArray)
//        accountsService.SET_LOCATION(AuthenticationUtils.getLoggeInUserId(this), jsonObject)
//            .enqueue(object : Callback<Void?> {
//                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
//                    Timber.tag("Send Location").i(response.code().toString())
//                }
//
//                override fun onFailure(call: Call<Void?>, t: Throwable) {
//                    Timber.tag("Send Location").e(t)
//                }
//            })
//    }
//
//    fun showEmptyStateWithButton() {
//        Glide.with(this).load(R.drawable.no_connection).into(imgEmpty!!)
//        txtEmpty!!.text = getString(R.string.inter_poor_connection)
//        noContentView!!.visibility = View.VISIBLE
//    }
//
//    fun hideEmptyStateWithButton() {
//        noContentView!!.visibility = View.GONE
//    }
//
//    @JvmOverloads
//    fun syncFragmentState(requestPlayerState: Boolean = true) {
//    }
//
//    fun syncFragmentState(requestPlayerState: Boolean, showSearch: Boolean, showProfile: Boolean) {}
//    protected val notificationCount: Unit
//        protected get() {
//            ServiceGenerator.createServiceWithAuth(AccountsService::class.java, this)
//                .getNotificationCount(false)
//                .enqueue(object : Callback<Int?> {
//                    override fun onResponse(call: Call<Int?>, response: Response<Int?>) {
//                        if (response.isSuccessful && response.body() != null) {
//                            notifyCount = response.body()
//                            if (notifyCount > 0) {
//                                setNotifyBadgeCount()
//                                badgeDrawable.setEnabled(false)
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Int?>, t: Throwable) {
//                        badgeDrawable.setEnabled(false)
//                    }
//                })
//        }
//
//    fun setNotifyBadgeCount() {}
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == AuthenticationUtils.LOCATION_REQUEST_CODE) {
//            if (grantResults.size > 0
//                && grantResults[0] == PackageManager.PERMISSION_GRANTED
//            ) {
//                Log.d("login1", "454")
//                AuthenticationUtils.getLocation(this)
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
//
//    override fun onPostCreate(savedInstanceState: Bundle?) {
//        super.onPostCreate(savedInstanceState)
//        if (drawerToggle != null) {
//            drawerToggle!!.syncState()
//        }
//    }
//
//    fun setTitle(title: String?) {
//        if (this.title != null) {
//            this.title!!.text = title
//            this.title!!.isSelected = true
//        }
//    }
//
//    /**
//     * Set padding to compensate transparent status bar on android Lollipop and up.
//     * Default only set padding to toolbar
//     */
//    protected fun setPadding() {
//        if (toolbar != null) {
//            toolbar!!.setPadding(
//                toolbar!!.paddingLeft,
//                statusBarHeight,
//                toolbar!!.paddingRight,
//                toolbar!!.paddingBottom
//            )
//        }
//    }
//
//    /**
//     * Get status bar height
//     * Source: https://stackoverflow.com/questions/3407256/height-of-status-bar-in-android
//     *
//     * @return status bar height
//     */
//    val statusBarHeight: Int
//        get() {
//            var result = 0
//            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
//            if (resourceId > 0) {
//                result = resources.getDimensionPixelSize(resourceId)
//            }
//            return result
//        }
//
//    /**
//     * Make status bar in Android Lollipop and newer transparent
//     */
//    protected fun makeTransparentStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val window = window
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            setPadding()
//        }
//    }
//
//    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
//    }
//
//    fun startFragment(fragment: Fragment?) {
//        drawer!!.closeDrawers()
//        setDrawerState(false)
//        if (isPlayerShowing) forceHidePlayer()
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_frame, fragment!!)
//            .addToBackStack(null)
//            .commit()
//    }
//
//    fun replaceLastStack(fragment: Fragment?) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_frame, fragment!!)
//            .commit()
//    }
//
//    fun setDrawerState(isEnabled: Boolean) {
//        if (drawerContainer == null) return
//        if (isEnabled && AuthenticationUtils.isShowDrawer(context!!)) {
//            drawerContainer!!.visibility = View.VISIBLE
//            btnDrawer!!.setImageResource(R.drawable.ic_burger)
//            btnDrawer!!.setOnClickListener { v: View? -> openDrawer() }
//        } else if (supportFragmentManager.backStackEntryCount > 0) {
//            drawerContainer!!.visibility = View.VISIBLE
//            btnDrawer!!.setImageResource(R.drawable.ic_back)
//            btnDrawer!!.setOnClickListener { v: View? -> onBackPressed() }
//        } else drawerContainer!!.visibility = View.GONE
//        setupLastPlayed()
//    }
//
//    fun setToolbarVisibility(visible: Boolean) {
//        if (supportActionBar == null) return
//        if (visible) {
//            supportActionBar!!.show()
//        } else {
//            supportActionBar!!.hide()
//        }
//    }
//
//    fun setupLastPlayed() {}
//    protected fun tintMenuIcons(menu: Menu) {
//        for (i in 0 until menu.size()) {
//            val favoriteItem = menu.getItem(i)
//            val newIcon = favoriteItem.icon
//            newIcon!!.mutate().setColorFilter(
//                ThemeHelper.getColorAttr(context!!, R.attr.colorToolbarIcon),
//                PorterDuff.Mode.SRC_IN
//            )
//            favoriteItem.icon = newIcon
//        }
//    }
//
//    fun finishFragment(fragment: Fragment?) {
//        supportFragmentManager.beginTransaction()
//            .remove(fragment!!)
//            .commit()
//        supportFragmentManager.popBackStack()
//    }
//
//    fun setToolbarColor(color: Int) {
//        if (toolbar != null) {
//            toolbar!!.setBackgroundColor(color)
//        }
//    }
//
//    override fun setTitleColor(color: Int) {
//        if (title != null) {
//            title!!.setTextColor(color)
//        }
//    }
//
//    fun setToolbarShadow(visible: Boolean) {
//        findViewById<View>(R.id.toolbarShadow).visibility = if (visible) View.VISIBLE else View.GONE
//        //        ViewCompat.setElevation(toolbar, elevation);
//    }
//
//    override fun onResume() {
//        super.onResume()
//        GooglePayHelper.getInstance().setListener(null)
//        GooglePayHelper.getInstance().queryPurchasesAsync()
//        if (AuthenticationUtils.isGuest(context!!)) {
//            btnProfile!!.setOnClickListener { v: View? ->
//                AuthenticationUtils.goLogin(
//                    baseActivity
//                )
//            }
//        } else {
//            btnProfile!!.setOnClickListener { v: View? ->
//                baseActivity.startFragment(
//                    UserProfileFragment.newInstance(
//                        AuthenticationUtils.getLoggeInUserId(
//                            context!!
//                        )
//                    )
//                )
//            }
//            AuthenticationUtils.getCurrentAccount(context!!, object : ResponseHelper<Account?>() {
//                override fun onSuccess(body: Account?) {
//                    Glide.with(btnProfile!!).load(body!!.getImages().getImage150())
//                        .into(btnProfile!!)
//                }
//            })
//        }
//        if (AuthenticationUtils.getLong(baseActivity).equals("0.0")
//            && AuthenticationUtils.getLat(baseActivity).equals("0.0")
//        ) {
//            Log.d("login1", "655")
//            AuthenticationUtils.getLocation(baseActivity)
//        }
//        sendLocation()
//        syncFragmentState()
//    }
//
//    fun showSnackBar(text: String?) {
//        if (noContentView != null) Snackbar.make(noContentView!!, text!!, Snackbar.LENGTH_SHORT)
//            .show()
//    }
//
//    fun showSnackBar(text: String?, duration: Int) {
//        if (noContentView != null) Snackbar.make(noContentView!!, text!!, duration).show()
//    }
//
//    fun hideKeyBoard() {
//        val view = currentFocus
//        if (view != null) {
//            val imm = (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
//            imm.hideSoftInputFromWindow(view.windowToken, 0)
//        }
//    }
//
//    fun hideKeyboardFrom(context: Context, view: View) {
//        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(view.windowToken, 0)
//    }
//
//    fun setFragment(baseActivity: BaseFragment?) {
//        fragment = baseActivity
//    }
//
//    fun getFragment(): Fragment? {
//        return fragment
//    }
//
//    fun initHeader() {}
//    protected val baseActivity: BaseActivity
//        protected get() = this
//    private var mShouldUnbind = false
//    private var downloadService: DownloadService? = null
//    private val serviceConnection: ServiceConnection = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName, service: IBinder) {
//            downloadService = (service as DownloadService.LocalBinder).getService()
//        }
//
//        override fun onServiceDisconnected(name: ComponentName) {
//            downloadService = null
//        }
//    }
//
//    fun doUnbindService() {
//        if (mShouldUnbind) {
//            // Release information about the service's state.
//            unbindService(serviceConnection)
//            mShouldUnbind = false
//        }
//    }
//
//    fun takeOffline(model: BaseModel) {
//        if (!AuthenticationUtils.getAccessibility(this).getGlobalAccess()!!.isCanTakeOffline()) {
//            showSnackBar(
//                SettingUtil.getAppSettings().getNaming().getPlayer().getLimitFeaturePremium()
//            )
//            return
//        }
//        if (downloadService == null) {
//            if (bindService(
//                    Intent(this@BaseActivity, DownloadService::class.java),
//                    serviceConnection, BIND_AUTO_CREATE
//                )
//            ) {
//                mShouldUnbind = true
//            }
//            findViewById<View>(android.R.id.content).rootView.postDelayed(
//                { doDownload(model) },
//                1000
//            )
//        } else {
//            doDownload(model)
//        }
//    }
//
//    private fun doDownload(model: BaseModel) {
//        val eventHelper = EventHelper()
//        eventHelper.init(PlayerEvent.STATUS_TAKE_OFFLINE, 0)
//        eventHelper.sendReport(context, model)
//        if (model is Music) downloadService.getRealmInstance()
//            .getMusicFile(model as Music) else if (model is Upload) downloadService.getRealmInstance()
//            .getUploadFile(model as Upload) else if (model is RadioContent) downloadService.getRealmInstance()
//            .getRadioContentFile(model as RadioContent) else if (model is Video) downloadService.getRealmInstance()
//            .getVideoFile(
//                model
//            )
//    }
//
//    fun removeOffline(model: BaseModel?) {
//        val eventHelper = EventHelper()
//        eventHelper.init(PlayerEvent.STATUS_REMOVE_OFFLINE, 0)
//        eventHelper.sendReport(context, model)
//        if (model is Music) RealmInstance(context).removeMusic(model as Music?) else if (model is Upload) RealmInstance(
//            context
//        ).removeUpload(model as Upload?) else if (model is RadioContent) RealmInstance(context).removeRadioContent(
//            model as RadioContent?
//        ) else if (model is Video) RealmInstance(
//            context
//        ).removeVideo(model as Video?)
//    }
//
//    fun downloadDocument(
//        document: Document?,
//        downloadDocListener: DownloadDocument.DownloadDocListener?
//    ) {
//        if (downloadService == null) {
//            if (bindService(
//                    Intent(this@BaseActivity, DownloadService::class.java),
//                    serviceConnection, BIND_AUTO_CREATE
//                )
//            ) {
//                mShouldUnbind = true
//            }
//            findViewById<View>(android.R.id.content).rootView.postDelayed({
//                downloadService.getDownloadDocument(
//                    downloadDocListener
//                ).download(document)
//            }, 1000)
//        } else {
//            downloadService.getDownloadDocument(downloadDocListener).download(document)
//        }
//    }
//
//    val isOnline: Boolean
//        get() = NetworkUtil.isOnline()
//
//    fun checkIsOffline() {
//        /* if (!isOnline() && fragment instanceof HomeFragment) {
//            ((HomeFragment) fragment).goToLibrary();
//        }*/
//    }
//
//    fun updateNavData() {}
//    private var canCloseApp = false
//
//    @SuppressLint("RtlHardcoded")
//    override fun onBackPressed() {
//        if (isPlayerShowing) {
//            hidePlayer()
//            return
//        }
//        if (fragment.isDoSomethinkOnBack) {
//            fragment.onBackPressed()
//            return
//        } else if (drawer!!.isDrawerOpen(Gravity.LEFT)) {
//            drawer!!.closeDrawers()
//            return
//        }
//        if (supportFragmentManager.backStackEntryCount < 1 && !canCloseApp) {
//            Toast.makeText(
//                context,
//                resources.getString(R.string.inter_close_app_notification),
//                Toast.LENGTH_SHORT
//            ).show()
//            canCloseApp = true
//            Handler().postDelayed({ canCloseApp = false }, 2000)
//            return
//        }
//        super.onBackPressed()
//    }
//
//    private val mConnectionCallbacks: MediaBrowserCompat.ConnectionCallback =
//        object : MediaBrowserCompat.ConnectionCallback() {
//            override fun onConnected() {
//                val token = mediaBrowser!!.sessionToken
//                val mediaController: MediaControllerCompat
//                mediaController = MediaControllerCompat(context, token)
//                MediaControllerCompat.setMediaController(activity, mediaController)
//                mediaController.registerCallback(controllerCallback)
//                svaraMediaPlayer = SvaraMediaPlayer.getInstance()
//                if (caller != null && caller!!.action != null) {
//                    setupAction(caller!!.action)
//                }
//                if (svaraMediaPlayer != null && mediaControllerCompat != null) {
//                    val state: Int = mediaControllerCompat.getPlaybackState().getState()
//                    if (state != PlaybackStateCompat.STATE_PLAYING && state != PlaybackStateCompat.STATE_BUFFERING && SettingUtil.getAutoPlayOpen(
//                            context
//                        )
//                    ) {
//                        svaraMediaPlayer.restoreSavedState()
//                        releaseVideoContent()
//                        closeVideoPlayerPIP()
//                        releaseVideo()
//                        isFirstAppOpen = false
//                        return
//                    } else for (listener in svaraPlayerListener) {
//                        listener.onMetadataChange(mediaController.metadata)
//                        listener.onPlaybackStateChange(mediaController.playbackState)
//                    }
//                }
//                if (!isFirstAppOpen) return else showHideMiniPlayerFirstOpen()
//                if (mediaController.playbackState.state == PlaybackStateCompat.STATE_PLAYING ||
//                    mediaController.playbackState.state == PlaybackStateCompat.STATE_PAUSED
//                ) {
//                    for (listener in svaraPlayerListener) {
//                        listener.onMetadataChange(mediaController.metadata)
//                        listener.onPlaybackStateChange(mediaController.playbackState)
//                    }
//                } else autoPlay()
//                if (intent.action != null) {
//                    if (intent.action == SvaraNotificationManager.ACTION_SHOW_PLAYER) showPlayer()
//                }
//                isFirstAppOpen = false
//                svaraMediaPlayer.requestPlayerState()
//            }
//        }
//
//    protected abstract fun autoPlay()
//    protected abstract fun showHideMiniPlayerFirstOpen()
//    var controllerCallback: MediaControllerCompat.Callback =
//        object : MediaControllerCompat.Callback() {
//            override fun onMetadataChanged(metadata: MediaMetadataCompat) {
//                if (metadata == null) return
//                for (listener in svaraPlayerListener) listener.onMetadataChange(metadata)
//            }
//
//            override fun onPlaybackStateChanged(state: PlaybackStateCompat) {
//                for (listener in svaraPlayerListener) listener.onPlaybackStateChange(state)
//            }
//
//            override fun onSessionEvent(event: String, extras: Bundle) {
//                when (event) {
//                    SvaraMediaService.ACTION_PROGRESS -> for (listener in svaraPlayerListener) listener.onProgress(
//                        extras.getLong("progress")
//                    )
//                    SvaraMediaService.ACTION_INFO -> showSnackBar(extras.getString("message"))
//                    SvaraMediaService.ACTION_QUEUE_CHANGE -> if (svaraMediaPlayer != null) for (listener in svaraPlayerListener) listener.onQueueChange(
//                        svaraMediaPlayer.getQueue()
//                    )
//                    SvaraMediaService.ACTION_DURATION -> for (listener in svaraPlayerListener) listener.onDurationChange(
//                        extras.getLong("duration", 0)
//                    )
//                    SvaraMediaService.ACTION_ICY_METADATA -> for (listener in svaraPlayerListener) if (extras.getBoolean(
//                            "useDefault",
//                            false
//                        )
//                    ) listener.onTitleChange(null) else listener.onTitleChange(extras.getString("title"))
//                    SvaraMediaService.ACTION_ADS_METADATA -> for (listener in svaraPlayerListener) {
//                        listener.onTitleChange(extras.getString("title", null))
//                        listener.onCoverChange(extras.getString("banner", null))
//                    }
//                    SvaraMediaService.ACTION_SHOW_PLAYER -> showPlayer()
//                }
//            }
//
//            override fun onQueueChanged(queue: List<MediaSessionCompat.QueueItem>) {
//                for (listener in svaraPlayerListener) listener.onQueueChange(svaraMediaPlayer.getQueue())
//            }
//        }
//
//    fun Play(model: PlayableModel?) {
//        if (VideoPlayer.isVideoAvailable()) releaseVideo()
//        if (mediaBrowser!!.isConnected && svaraMediaPlayer != null) svaraMediaPlayer.play(model)
//        showPlayerIfNeed()
//        releaseVideoContent()
//        closeVideoPlayerPIP()
//    }
//
//    fun Play(models: List<PlayableModel?>?, position: Int) {
//        if (VideoPlayer.isVideoAvailable()) releaseVideo()
//        if (mediaBrowser!!.isConnected && svaraMediaPlayer != null) svaraMediaPlayer.play(
//            models,
//            position
//        )
//        showPlayerIfNeed()
//        releaseVideoContent()
//        closeVideoPlayerPIP()
//    }
//
//    fun Play(id: String?) {
//        if (VideoPlayer.isVideoAvailable()) releaseVideo()
//        if (!mediaBrowser!!.isConnected) return
//        val mediaControllerCompat = MediaControllerCompat.getMediaController(
//            activity!!
//        )
//        mediaControllerCompat.transportControls.playFromMediaId(id, null)
//        showPlayerIfNeed()
//        releaseVideoContent()
//        closeVideoPlayerPIP()
//    }
//
//    fun PlayVideo(video: VideoModel?) {
//        showVideoPlayer(video, false)
//    }
//
//    @JvmOverloads
//    fun add(model: PlayableModel?, upNext: Boolean = false) {
//        if (svaraMediaPlayer != null) svaraMediaPlayer.add(model, upNext)
//    }
//
//    private fun showPlayerIfNeed() {
//        if (fragment.showPlayerOnNewPlay()) showPlayer()
//    }
//
//    fun add(models: List<PlayableModel?>?) {
//        if (svaraMediaPlayer != null) svaraMediaPlayer.add(models)
//    }
//
//    fun stop() {
//        if (!mediaBrowser!!.isConnected) return
//        val mediaControllerCompat = MediaControllerCompat.getMediaController(
//            activity!!
//        )
//        mediaControllerCompat.transportControls.stop()
//    }
//
//    fun replacePlay() {
//        if (VideoPlayer.isVideoAvailable()) releaseVideo()
//        if (!mediaBrowser!!.isConnected) return
//        val mediaControllerCompat = MediaControllerCompat.getMediaController(
//            activity!!
//        )
//        mediaControllerCompat.transportControls.sendCustomAction(
//            SvaraMediaService.ACTION_REPLACE_PLAY,
//            null
//        )
//    }
//
//    fun replaceQueue(playableModel: PlayableModel?) {
//        if (VideoPlayer.isVideoAvailable()) releaseVideo()
//        if (mediaBrowser!!.isConnected && svaraMediaPlayer != null) svaraMediaPlayer.replaceQueue(
//            playableModel
//        )
//        showPlayerIfNeed()
//    }
//
//    val mediaControllerCompat: MediaControllerCompat?
//        get() = MediaControllerCompat.getMediaController(activity!!)
//
//    fun setupActionPlay(type: String?, id: String?) {
//        if (id == null || type == null) return
//        if (type.lowercase(Locale.getDefault()) == ActionUtil.TYPE_VIDEO) {
//            playVideoWithId(id)
//            return
//        }
//        if (svaraMediaPlayer == null) return
//        svaraMediaPlayer.newPlay(id, ModelContract.getCategoryInt(type))
//    }
//
//    fun hideSystemUI() {
//        val decorView = window.decorView
//        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // Hide the nav bar and status bar
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_FULLSCREEN)
//    }
//
//    fun showSystemUI() {
//        val decorView = window.decorView
//        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
//    }
//
//    fun changeStatusBarColor(@ColorInt color: Int) {
//        val window = window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.statusBarColor = color
//    }
//
//    fun openWeb(url: String?, title: String?) {
//        startFragment(WebviewFragment.newInstance(url, title))
//    }
//
//    fun openDrawer() {
//        if (drawer != null) drawer!!.openDrawer(Gravity.LEFT)
//    }
//
//    /**
//     * handle do open page or play come content from notification or sharing
//     * AND eventType is NOT "None"
//     */
//    private fun setupAction(action: String?) {
//        val bundle = caller!!.extras
//        if (!isFirstAppOpen && action == null || action!!.isEmpty() || bundle == null || action == GcmNotif.ACTION_NONE || !bundle.containsKey(
//                ActionUtil.KEY_TYPE
//            )
//        ) return
//        val type = caller!!.extras!!.getString(ActionUtil.KEY_TYPE, "")
//        val id = caller!!.extras!!.getString(ActionUtil.KEY_ID, "")
//        when (action) {
//            GcmNotif.ACTION_OPEN_PAGE -> actionOpenPage(type, id)
//            GcmNotif.ACTION_PLAY -> {
//                setupActionPlay(type, id)
//                caller!!.removeExtra(ActionUtil.KEY_TYPE)
//            }
//            ActionUtil.ACTION_REFERRAL_REDEEM -> {
//                val dataReferral = Bundle()
//                dataReferral.putString("code", id)
//                val referralFragment = ReferralFragment()
//                referralFragment.setArguments(dataReferral)
//                startFragment(referralFragment)
//            }
//        }
//    }
//
//    fun actionOpenPage(type: String?, id: String) {
//        when (type) {
//            GcmNotif.TYPE_NOTIFICATION -> startFragment(NotificationFragment())
//            GcmNotif.TYPE_PAYMENT -> {
//                val intent = Intent(this, ActivityWebViewPayment::class.java)
//                startActivity(intent)
//            }
//            GcmNotif.TYPE_REFERRAL -> startFragment(ReferralFragment())
//            GcmNotif.TYPE_TAP -> startFragment(TermsPoliciesFragment())
//            GcmNotif.TYPE_ACCOUNT -> startFragment(UserProfileFragment.newInstance(id))
//            GcmNotif.TYPE_RADIO_CONTENT -> startFragment(PodcastRadioFragment.newInstance(id, ""))
//            GcmNotif.TYPE_DOCUMENT -> startFragment(FragmentDocument.newInstance(id))
//            GcmNotif.TYPE_VIDEO -> ServiceGenerator.createServiceWithAuth(
//                VideoService::class.java,
//                this
//            )
//                .getVideo(id)
//                .enqueue(object : ResponseHelper<Video?>() {
//                    override fun onSuccess(body: Video?) {
//                        showVideoPlayer(body, false)
//                    }
//                })
//            GcmNotif.TYPE_UPLOAD -> startFragment(PodcastFragment.newInstance(id, ""))
//            GcmNotif.TYPE_MUSIC -> startFragment(MusicFragment.newInstance(id, ""))
//            GcmNotif.TYPE_ARTIST, ActionUtil.TYPE_ARTIST -> startFragment(
//                ArtistFragment.newInstance(
//                    id
//                )
//            )
//            GcmNotif.TYPE_ALBUM, ActionUtil.TYPE_ALBUM -> startFragment(AlbumFragment.newInstance(id))
//            GcmNotif.TYPE_PLAYLIST, ActionUtil.TYPE_PLAYLIST -> openPlaylistPageByKind(id)
//            GcmNotif.TYPE_RADIO, ActionUtil.TYPE_RADIO -> startFragment(
//                RadioProfileFragment.newInstance(
//                    id,
//                    null
//                )
//            )
//            GcmNotif.TYPE_ARTICLE, ActionUtil.TYPE_ARTICLE -> startFragment(
//                ArticleView.newInstance(
//                    getString(R.string.article),
//                    id
//                )
//            )
//            GcmNotif.TYPE_FEED, ActionUtil.TYPE_FEED -> startFragment(
//                SocialCommentFragment.newInstance(
//                    id
//                )
//            )
//        }
//    }
//
//    private fun openPlaylistPageByKind(id: String) {
//        ServiceGenerator.createServiceWithAuth(PlaylistService::class.java, context!!)
//            .getPlaylistInfo(id)
//            .enqueue(object : ResponseHelper<Playlist?>() {
//                override fun onSuccess(body: Playlist?) {
//                    if (context == null) return
//                    when (body.getKind().toLowerCase()) {
//                        PlaylistKind.PLAYLIST -> startFragment(PlaylistFragment.newInstance(body.getId()))
//                        PlaylistKind.USER_SERIES, PlaylistKind.RADIO_SERIES -> startFragment(
//                            SeriesFragment.newInstance(body.getId(), body.getName())
//                        )
//                    }
//                }
//            })
//    }
//
//    // Override this
//    val videoPlayerContent: VideoPlayerContent?
//        get() = null
//
//    fun playVideoWithId(videoId: String?) {
//        val videoService: VideoService = ServiceGenerator.createServiceWithAuth(
//            VideoService::class.java,
//            context!!
//        )
//        videoService.getVideo(videoId).enqueue(object : ResponseHelper<Video?>() {
//            override fun onSuccess(body: Video?) {
//                showVideoPlayer(body, false)
//            }
//        })
//    }
//
//    companion object {
//        val ACTION_PLAY = Strings.buildPkgString("action.play")
//        val KEY_TYPE = Strings.buildPkgString("action.key.type")
//        val KEY_ID = Strings.buildPkgString("action.key.id")
//    }
}