package com.zamrud.radio.mobile.app.svara.artist.fragment.presenter
//
//import android.content.Context
//import android.graphics.Color
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//
//import androidx.annotation.ColorInt
//import androidx.core.content.ContextCompat
//
//import com.zamrud.radio.mobile.app.svara.BaseActivity
//import com.zamrud.radio.mobile.app.svara.R
//import com.zamrud.radio.mobile.app.svara.apiclient.ResponseHelper
//import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
//import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Artist
//import com.zamrud.radio.mobile.app.svara.apiclient.services.ArtistService
//import com.zamrud.radio.mobile.app.svara.artist.fragment.ArtistFragment
//import retrofit2.Call
//
//abstract class BaseArtistFragmentPresenter(artistListener: ArtistFragment.ArtistListener, context: Context, mArtistId: String, showBtnBack: Boolean) {
//    protected var mArtist: Artist? = null
//    protected var mArtistId: String? = null
//    protected var context: Context? = null
//    protected var view: View? = null
//    var artistListener: ArtistFragment.ArtistListener? = null
//
//    protected var mService: ArtistService? = null
//    private var dataCall: Call<Artist>? = null
//    var showBtnBack = false
//
//    init{
//        mService = ServiceGenerator.createServiceWithAuth(ArtistService::class.java, context)
//        //        this.mArtist = Artist();
//        this.showBtnBack = showBtnBack
//        this.mArtistId = mArtistId
//        this.context = context
//        this.artistListener = artistListener
//    }
//
//    protected open fun getBaseActivity(): BaseActivity? {
//        return artistListener!!.fragment.getBaseActivity()
//    }
//
//    abstract fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
//
//    abstract fun updateArtistView()
//
//    abstract fun loadContent()
//
//    abstract fun onDestroy()
//
//    @ColorInt
//    open fun getStatusBarColor(): Int {
//        return if (context == null) Color.BLACK
//        else ContextCompat.getColor(context!!, R.color.colorPrimaryDark)
//    }
//
//    open fun useLightStatusBar(): Boolean {
//        return false
//    }
//
//    open fun getData() {
//        dataCall = mService?.getArtistProfile(mArtistId)
//        dataCall?.enqueue(object : ResponseHelper<Artist?>() {
//            override fun onSuccess(body: Artist?) {
//                bindData(body)
//            }
//        })
//    }
//
//    protected abstract fun bindData(artist: Artist?)
//}