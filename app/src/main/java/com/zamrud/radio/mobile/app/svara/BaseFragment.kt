package com.zamrud.radio.mobile.app.svara
//
//import android.content.Context
//import android.graphics.Color
//import android.os.Build
//import android.os.Bundle
//import android.view.View
//import androidx.annotation.ColorInt
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import com.zamrud.radio.mobile.app.svara.album.AlbumFragment
//import com.zamrud.radio.mobile.app.svara.apiclient.countly.OpenPage
//import com.zamrud.radio.mobile.app.svara.artist.fragment.ArtistFragment
//import com.zamrud.radio.mobile.app.svara.helper.ThemeHelper
//import com.zamrud.radio.mobile.app.svara.interfaces.OnItemDeleted
//import com.zamrud.radio.mobile.app.svara.playlist.PlaylistFragment
//import com.zamrud.radio.mobile.app.svara.setting.SettingUtil
//
///**
// * Created by fahziar on 05/03/2016.
// */
//abstract class BaseFragment : Fragment(), OnItemDeleted {
//    protected var isDoSomethinkOnBack = false
//    protected var toolbarColor = 0
//    protected var textColor = 0
//    protected var textActiveColor = 0
//    protected var openPage: OpenPage? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        initOpenPage()
//    }
//
//    /**
//     * Get the activity that hold this fragment in form of BaseActivity
//     *
//     * @return activity that hold this fragment
//     */
//    open fun getBaseActivity(): BaseActivity? {
//        if (activity is BaseActivity)
//            return activity as BaseActivity?
//        return null
//    }
//
//    /**
//     * Set activity title
//     *
//     * @param title Activity title
//     */
//    open fun setActivityTitle(title: String?) {
//        getBaseActivity()!!.title = title
//    }
//
//    open fun setTextColor(color: Int) {
//        textColor = color
//        getBaseActivity()!!.titleColor = textColor
//        try {
//            getBaseActivity().setTextColor(color)
//        } catch (e: java.lang.Exception) {
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        CallCallbackFinishLoadRemove()
//    }
//
//    fun setTextActiveColor(color: Int) {
//        textActiveColor = color
//        try {
//            baseActivity.setTextActiveColor(color)
//        } catch (e: Exception) {
//        }
//    }
//
//    fun onPlayerChange() {}
//
//    /**
//     * Get fragment title, can be used as activity title
//     *
//     * @param context context used to retreive string resources
//     * @return Fragment title
//     */
//    open fun getFragmentTitle(context: Context?): String? {
//        return ""
//    }
//
//    /**
//     * Check whether this fragment require toolbar
//     *
//     * @return True if require toolbar
//     */
//    val isToolbarRequired: Boolean
//        get() = true
//
//    /**
//     * Check whether this fragment require drawer
//     *
//     * @return True if require drawer
//     */
//    open val isDrawerRequired: Boolean
//        get() = false
//
//    open fun showToolbarLogo(): Boolean {
//        return false
//    }
//
//    fun getToolbarColor(): Int {
//        if (context == null) return Color.BLACK
//        return if (this is ArtistFragment || this is AlbumFragment || this is PlaylistFragment) toolbarColor else ThemeHelper.getColorAttr(
//            context!!, R.attr.colorToolbarBackground
//        )
//    }
//
//    protected val isUseLightStatusBar: Boolean
//        protected get() = !SettingUtil.getTheme(SvaraApplication.getAppContext())
//            .equals(CustomProject.DARK_THEME) && CustomProject.blackTextSystemUiOnLightTheme
//    val titleColor: Int
//        get() {
//            if (context == null) return Color.WHITE
//            return if (this is ArtistFragment || this is AlbumFragment || this is PlaylistFragment) textColor else ThemeHelper.getColorAttr(
//                context!!, R.attr.colorToolbarText
//            )
//        }
//
//    fun setToolbarColor(color: Int) {
//        toolbarColor = color
//    }
//
//    fun getTextColor(): Int {
//        return textColor
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        //send open page to countly
//        if (openPage != null) openPage.send()
//        if (baseActivity != null) {
//            toolbarColor = baseActivity.getColors().getColor()
//            return
//        }
//        toolbarColor = ContextCompat.getColor(context!!, R.color.svara_yellow)
//
//
////        addStatusBarPadding(view);
//    }
//
//    protected fun addStatusBarPadding(view: View) {
//        val mainLayout = view.findViewById<View>(R.id.layout_main)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mainLayout?.setPadding(
//                mainLayout.paddingLeft,
//                resources.getDimensionPixelSize(R.dimen.status_bar_height),
//                mainLayout.paddingRight,
//                mainLayout.paddingBottom
//            )
//        }
//    }
//
//    /**
//     * Check if search menu is visible
//     *
//     * @return true if search menu is visible
//     */
//    open val isMenuSearchVisible: Boolean
//        get() = true
//
//    /**
//     * Show or hide profile menu
//     *
//     * @return true id profile menu visible
//     */
//    val isProfileMenuVisible: Boolean
//        get() = true
//
//    open fun deleteSelected(position: Int) {}
//    fun onBackPressed() {
//        isDoSomethinkOnBack = false
//    }
//
//    open fun CallCallbackFinishLoadRemove() {}
//    fun removeCallbackFinishLoad(listAdapter: ListAdapter?) {
//        if (listAdapter != null) listAdapter.removeOnLoadFinishState()
//    }
//
//    @get:ColorInt
//    val statusBarColor: Int
//        get() = if (context == null) Color.BLACK else ThemeHelper.getColorAttr(
//            context!!, R.attr.colorStatusBarBackground
//        )
//
//    fun setupStatusBarMode() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && view != null) {
//            if (isUseLightStatusBar) view!!.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else view!!.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
//        }
//    }
//
//    fun useToolbarShadow(): Boolean {
//        return true
//    }
//
//    fun showPlayerOnNewPlay(): Boolean {
//        return true
//    }
//
//    open val isShowMiniPlayer: Boolean
//        get() = true
//
//    protected abstract fun initOpenPage()
//    open fun showBottomNavigation(): Boolean {
//        return false
//    }
//}