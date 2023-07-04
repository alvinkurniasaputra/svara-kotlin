package com.zamrud.radio.mobile.app.svara.apiclient.model.entity
//
//import android.util.Log
//import com.google.gson.Gson
//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName
//import com.zamrud.radio.mobile.app.svara.BaseActivity
//import com.zamrud.radio.mobile.app.svara.NetworkUtil
//import com.zamrud.radio.mobile.app.svara.Player.Model.PlayableModel
//import com.zamrud.radio.mobile.app.svara.Realm.model.OfflineData
//import com.zamrud.radio.mobile.app.svara.album.AlbumFragment
//import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
//import com.zamrud.radio.mobile.app.svara.apiclient.BaseCallback
//import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
//import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
//import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
//import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
//import com.zamrud.radio.mobile.app.svara.apiclient.services.AlbumService
//import com.zamrud.radio.mobile.app.svara.dialog.ActionDialog
//import io.realm.Realm
//import io.realm.RealmResults
//import org.parceler.Parcel
//import org.parceler.Parcels
//import retrofit2.Call
//import retrofit2.Response
//
///*
//* Created by fahziar on 31/03/2016.
//*/
//@Parcel
//class Album : ModelWithAction() {
//    @SerializedName("name")
//    @Expose
//    var mName: String? = null
//
//    @SerializedName("genre")
//    @Expose
//    var mGenre: String? = null
//
//    @SerializedName("favoriteCount")
//    @Expose
//    var mFavoriteCount: Int? = null
//
//    @SerializedName("id")
//    @Expose
//    var mId: String? = null
//
//    @SerializedName("accountId")
//    @Expose
//    var mAccountId: String? = null
//
//    @SerializedName("artists")
//    @Expose
//    var mArtists: ArrayList<Artist>? = null
//
//    @SerializedName("images")
//    @Expose
//    var images: Images? = null
//
//    init {
//        mName = ""
//        mGenre = ""
//        mFavoriteCount = 0
//        mId = ""
//        mAccountId = ""
//        mArtists = ArrayList()
//    }
//
//    fun getName(): String? {
//        return mName
//    }
//
//    fun setName(name: String?) {
//        mName = name
//    }
//
//    fun getFavoriteCount(): Int? {
//        return mFavoriteCount
//    }
//
//    fun setFavoriteCount(favoriteCount: Int?) {
//        mFavoriteCount = favoriteCount
//    }
//
//    override fun getId(): String? {
//        return mId
//    }
//
//    fun setId(id: String?) {
//        mId = id
//    }
//
//    fun getAccountId(): String? {
//        return mAccountId
//    }
//
//    fun setAccountId(accountId: String?) {
//        mAccountId = accountId
//    }
//
//    fun getArtists(): ArrayList<Artist>? {
//        return mArtists
//    }
//
//    fun setArtists(artists: ArrayList<Artist>?) {
//        mArtists = artists
//    }
//
//    fun getGenre(): String? {
//        return mGenre
//    }
//
//    fun setGenre(genre: String?) {
//        mGenre = genre
//    }
//
//    override fun getImages(): Images {
//        return if (images == null) Images() else images!!
//    }
//
//    fun setImages(images: Images?) {
//        this.images = images
//    }
//
//    override fun getLines(): ArrayList<CharSequence?>? {
//        val out: ArrayList<CharSequence?>? = super.getLines()
//        out?.set(0, getName())
//        //TODO Shouldn't be empty. Fix this
//        out?.set(1, getArtistFormatted())
//        out?.set(2, "")
//        return out
//    }
//
//    override fun getCoverImageSmall(): String? {
//        return if (!NetworkUtil.isOnline())
//            ServiceGenerator.getLocalImage(getId()!!)
//        else getImages().getImage64()
//    }
//
//    override fun getCoverImageMedium(): String? {
//        return getImages().getImage150()
//    }
//
//    override fun getCoverImageLarge(): String? {
//        return getImages().getImage300()
//    }
//
//    override fun getCoverImageExtraLarge(): String? {
//        return if (!NetworkUtil.isOnline())
//            ServiceGenerator.getLocalImage(getId()!!)
//        else getImages().getImage640()
//    }
//
//    override fun getWideCoverImageSmall(): String? {
//        return if (!NetworkUtil.isOnline())
//            ServiceGenerator.getLocalImage(getId()!!)
//        else getImages().getCoverImages().coverImage300
//    }
//
//    override fun getWideCoverImageMedium(): String? {
//        return if (!NetworkUtil.isOnline())
//            ServiceGenerator.getLocalImage(getId()!!)
//        else getImages().getCoverImages().coverImage640
//    }
//
//    override fun getWideCoverImageLarge(): String? {
//        return if (!NetworkUtil.isOnline())
//            ServiceGenerator.getLocalImage(getId()!!)
//        else getImages().getCoverImages().coverImage1280
//    }
//
//    override fun getCoverImageWide(): String? {
//        return if (!NetworkUtil.isOnline())
//            ServiceGenerator.getLocalImage(getId()!!)
//        else getImages().getImagew640()
//    }
//
//    override fun getImageOri(): String? {
//        return getImages().getImageOri()
//    }
//
//    override fun getPlaceholder(): String? {
//        return getImages().getPlaceholder()
//    }
//
//    fun getArtistFormatted(): String? {
//        val sb = StringBuilder("")
//        if (mArtists!!.size > 0) {
//            sb.append(mArtists!![0])
//            for (i in 1 until mArtists!!.size - 2) {
//                sb.append(", ")
//                sb.append(mArtists!![i])
//            }
//        }
//        return sb.toString()
//    }
//
//    override fun onLongClick(activity: BaseActivity) {
//        val data = Parcels.wrap(Album::class.java, this)
//        val dialog: ActionDialog = ActionDialog.newInstance(ModelContract.CATEGORY_ALBUM, data)
//        dialog.show(activity.supportFragmentManager, "dialog")
//    }
//
//    override fun onClick(activity: BaseActivity) {
//        activity.startFragment(AlbumFragment.newInstance(mId))
//    }
//
//    override fun play(activity: BaseActivity) {
//        if (AuthenticationUtils.isGuest(activity)) {
//            AuthenticationUtils.goLogin(activity)
//        } else {
//            activity.replacePlay()
//            val mService: AlbumService =
//                ServiceGenerator.createServiceWithAuth(AlbumService::class.java, activity)
//            val apiCall: Call<DataListModel> = mService.getMusics(getId(), 0, 50)
//            apiCall.enqueue(object : BaseCallback<DataListModel?>() {
//                override fun onSuccess(
//                    call: Call<DataListModel?>,
//                    response: Response<DataListModel?>
//                ) {
//                    Log.e("album", Gson().toJson(response))
//                    val modelWithActions = response.body()!!
//                        .getDataList() as List<ModelWithAction>?
//                    val playableModels: MutableList<PlayableModel> = ArrayList()
//                    for (m in modelWithActions!!) {
//                        if (m is PlayableModel) {
//                            m.setSourceContentType(SOURCE_CONTENT_ALBUM)
//                            m.setSourceContentId(getId())
//                            playableModels.add(m)
//                        }
//                    }
//                    if (playableModels.size > 0) {
//                        activity.Play(playableModels, 0)
//                    } else {
//                        activity.stop()
//                        activity.showSnackBar("No Content to Play")
//                    }
//                }
//
//                override fun onError() {
//                    activity.stop()
//                    activity.showSnackBar("No Content to Play")
//                }
//            }
//            )
//        }
//    }
//
//    override fun getAuthor(): String? {
//        return getArtistFormatted()
//    }
//
//    override fun getContentTypeString(): String? {
//        return ModelContract.getSearchString(ModelContract.CATEGORY_ALBUM)
//    }
//
//    override fun onOffline(): Boolean {
//        val offlineData: RealmResults<OfflineData> = Realm.getDefaultInstance().where(
//            OfflineData::class.java
//        ).equalTo("id", mId).findAll()
//        Log.e("isOffline", "on Album: " + offlineData.size)
//        return offlineData.size > 0
//    }
//}