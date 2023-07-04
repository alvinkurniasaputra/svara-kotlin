package com.zamrud.radio.mobile.app.svara.artist.fragment
//
//import android.content.Context
//import android.content.res.Configuration
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.FragmentManager
//import com.zamrud.radio.mobile.app.svara.BaseFragment
//import com.zamrud.radio.mobile.app.svara.CustomProject
//import com.zamrud.radio.mobile.app.svara.apiclient.countly.OpenPage
//import com.zamrud.radio.mobile.app.svara.artist.fragment.presenter.BaseArtistFragmentPresenter
//import com.zamrud.radio.mobile.app.svara.artist.fragment.presenter.LandscapeArtistFragmentPresenter
//import com.zamrud.radio.mobile.app.svara.artist.fragment.presenter.OnePageArtistFragmentPresenter
//import com.zamrud.radio.mobile.app.svara.artist.fragment.presenter.ViewPagerArtistFragmentPresenter
//import com.zamrud.radio.mobile.app.svara.setting.SettingUtil
//
//class ArtistFragment : BaseFragment(), View.OnClickListener {
//    companion object {
//        val EXTRA_ID = "id"
//    }
//
//    private var presenter: BaseArtistFragmentPresenter? = null
//
//    var container: ViewGroup? = null
//
//    var showMiniPlayer = false
//    var isDrawerRequired = false
//    var showBottomNavigation = false
//    var showBtnBack = true
//    var mArtistId: String? = null
//
//    private val artistListener: ArtistListener = object : ArtistListener {
//        override fun fragmentManager(): FragmentManager? {
//            return getChildFragmentManager()
//        }
//        override val fragment: ArtistFragment?
//            get() = this@ArtistFragment
//
//        override fun back() {
//            getBaseActivity().onBackPressed()
//        }
//
//        override fun setToolbarColor(color: Int) {
//            toolbarColor = color
//        }
//    }
//
//    override fun getStatusBarColor(): Int {
//        return if (presenter == null) super.getStatusBarColor() else presenter!!.getStatusBarColor()
//    }
//
//    protected fun initOpenPage() {
//        var id = "-"
//        if (getArguments() != null) id = getArguments().getString(EXTRA_ID)
//        openPage = OpenPage(OpenPage.PAGE_ARTIST, id)
//    }
//
//    protected fun isUseLightStatusBar(): Boolean {
//        return SettingUtil.getTheme(getContext()).equals(CustomProject.LIGHT_THEME)
//    }
//
//    fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (getArguments() != null) {
//            mArtistId = getArguments().getString(EXTRA_ID)
//            showBottomNavigation = getArguments().getBoolean("showBottomNavigation", false)
//            isDrawerRequired = getArguments().getBoolean("isDrawerRequired", false)
//            showMiniPlayer = getArguments().getBoolean("showMiniPlayer", false)
//            showBtnBack = getArguments().getBoolean("showBtnBack", true)
//        }
//        if (CustomProject.pageArtist == CustomProject.PAGE_ARTIST_VIEW_PAGER) {
//            presenter = ViewPagerArtistFragmentPresenter(
//                artistListener,
//                getContext(),
//                mArtistId,
//                showBtnBack
//            )
//        } else if (CustomProject.pageArtist == CustomProject.PAGE_ARTIST_ONE_PAGE) {
//            val orientation: Int = getResources().getConfiguration().orientation
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                // In landscape
//                presenter = LandscapeArtistFragmentPresenter(
//                    artistListener,
//                    getContext(),
//                    mArtistId,
//                    showBtnBack
//                )
//            } else {
//                // In portrait
//                presenter = OnePageArtistFragmentPresenter(
//                    artistListener,
//                    getContext(),
//                    mArtistId,
//                    showBtnBack
//                )
//            }
//        } else {
//            presenter =
//                OnePageArtistFragmentPresenter(artistListener, getContext(), mArtistId, showBtnBack)
//        }
//    }
//
//    fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        this.container = container
//        return presenter!!.onCreateView(inflater, container, savedInstanceState)
//    }
//
//    fun isToolbarRequired(): Boolean {
//        return if (CustomProject.pageArtist == CustomProject.PAGE_ARTIST_VIEW_PAGER) {
//            false
//        } else if (CustomProject.pageArtist == CustomProject.PAGE_ARTIST_ONE_PAGE) {
//            false
//        } else {
//            false
//        }
//    }
//
//    fun getFragmentTitle(context: Context?): String? {
//        return "ARTIST"
//    }
//
//    fun CallCallbackFinishLoadRemove() {
////        removeCallbackFinishLoad(mAdapter);
//    }
//
//    fun deleteSelected(position: Int) {}
//
//    fun newInstance(accountId: String?): ArtistFragment? {
//        val args = Bundle()
//        args.putString(EXTRA_ID, accountId)
//        val fragment = ArtistFragment()
//        fragment.setArguments(args)
//        return fragment
//    }
//
//    fun newInstance(
//        accountId: String?,
//        showBottomNavigation: Boolean,
//        showMiniPlayer: Boolean,
//        isDrawerRequired: Boolean,
//        showBtnBack: Boolean
//    ): ArtistFragment? {
//        val args = Bundle()
//        args.putString(EXTRA_ID, accountId)
//        args.putBoolean("showBottomNavigation", showBottomNavigation)
//        args.putBoolean("showMiniPlayer", showMiniPlayer)
//        args.putBoolean("isDrawerRequired", isDrawerRequired)
//        args.putBoolean("showBtnBack", showBtnBack)
//        val fragment = ArtistFragment()
//        fragment.setArguments(args)
//        return fragment
//    }
//
//    fun isShowMiniPlayer(): Boolean {
//        return showMiniPlayer
//    }
//
//    fun isDrawerRequired(): Boolean {
//        return isDrawerRequired
//    }
//
//    fun showBottomNavigation(): Boolean {
//        return showBottomNavigation
//    }
//
//    override fun onClick(v: View?) {
//        getBaseActivity().onBackPressed()
//    }
//
//    interface ArtistListener {
//        val fragmentManager: FragmentManager?
//        val fragment: ArtistFragment?
//
//        fun back()
//        fun setToolbarColor(color: Int)
//    }
//
//    fun onDestroy() {
//        super.onDestroy()
//        presenter!!.onDestroy()
//    }
//
//    fun onConfigurationChanged(newConfig: Configuration?) {
//        super.onConfigurationChanged(newConfig)
//        presenter!!.onDestroy()
//        if (getArguments() != null) {
//            mArtistId = getArguments().getString(EXTRA_ID)
//            showBottomNavigation = getArguments().getBoolean("showBottomNavigation", false)
//            isDrawerRequired = getArguments().getBoolean("isDrawerRequired", false)
//            showMiniPlayer = getArguments().getBoolean("showMiniPlayer", false)
//            showBtnBack = getArguments().getBoolean("showBtnBack", true)
//        }
//        if (CustomProject.pageArtist == CustomProject.PAGE_ARTIST_VIEW_PAGER) {
//            presenter = ViewPagerArtistFragmentPresenter(
//                artistListener,
//                getContext(),
//                mArtistId,
//                showBtnBack
//            )
//        } else if (CustomProject.pageArtist == CustomProject.PAGE_ARTIST_ONE_PAGE) {
//            val orientation: Int = getResources().getConfiguration().orientation
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                // In landscape
//                presenter = LandscapeArtistFragmentPresenter(
//                    artistListener,
//                    getContext(),
//                    mArtistId,
//                    showBtnBack
//                )
//            } else {
//                // In portrait
//                presenter = OnePageArtistFragmentPresenter(
//                    artistListener,
//                    getContext(),
//                    mArtistId,
//                    showBtnBack
//                )
//            }
//        } else {
//            presenter =
//                OnePageArtistFragmentPresenter(artistListener, getContext(), mArtistId, showBtnBack)
//        }
//        if (container == null) return
//        if (container!!.childCount > 0) container!!.removeAllViews()
//        val view = presenter!!.onCreateView(getLayoutInflater(), container, getArguments())
//        container!!.addView(view)
//    }
//
//    fun getData() {
//        presenter!!.getData()
//    }
//}