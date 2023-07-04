package com.zamrud.radio.mobile.app.svara.apiclient.model.entity
//
//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName
//import com.zamrud.radio.mobile.app.svara.BaseActivity
//import com.zamrud.radio.mobile.app.svara.Player.Model.PlayableModel
//import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
//import com.zamrud.radio.mobile.app.svara.apiclient.BaseCallback
//import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
//import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
//import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
//import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
//import com.zamrud.radio.mobile.app.svara.apiclient.services.ArtistService
//import com.zamrud.radio.mobile.app.svara.artist.fragment.ArtistFragment
//import com.zamrud.radio.mobile.app.svara.dialog.ActionDialog
//import org.parceler.Parcel
//import org.parceler.Parcels
//import retrofit2.Call
//import retrofit2.Response
//
///**
// * Created by fahziar on 24/04/2016.
// */
//@Parcel
//class Artist : ModelWithAction() {
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
//    var mFavoriteCount = 0
//
//    @SerializedName("source")
//    @Expose
//    var mSource: String? = null
//
//    @SerializedName("id")
//    @Expose
//    var mId: String? = null
//
//    @SerializedName("accountId")
//    @Expose
//    var mAccountId: String? = null
//
//    @SerializedName("description")
//    @Expose
//    var mDescription: String? = null
//
//    @SerializedName("images")
//    @Expose
//    var images: Images? = null
//
//    init {
//        mName = ""
//        mGenre = ""
//        mFavoriteCount = 0
//        mSource = ""
//        mId = ""
//        mAccountId = ""
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
//    fun getGenre(): String? {
//        return mGenre
//    }
//
//    fun setGenre(genre: String?) {
//        mGenre = genre
//    }
//
//    fun getFavoriteCount(): Int {
//        return mFavoriteCount
//    }
//
//    fun setFavoriteCount(favoriteCount: Int) {
//        mFavoriteCount = favoriteCount
//    }
//
//    fun getSource(): String? {
//        return mSource
//    }
//
//    fun setSource(source: String?) {
//        mSource = source
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
//    fun getDescription(): String? {
//        return mDescription
//    }
//
//    fun setDescription(description: String?) {
//        mDescription = description
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
//        out?.set(1, getGenre())
//        out?.set(2, "")
//        return out
//    }
//
//    override fun getCoverImageSmall(): String? {
//        return getImages().getImage64()
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
//        return getImages().getImage640()
//    }
//
//    override fun getWideCoverImageSmall(): String? {
//        return getImages().getCoverImages().coverImage300
//    }
//
//    override fun getWideCoverImageMedium(): String? {
//        return getImages().getCoverImages().coverImage640
//    }
//
//    override fun getCoverImageWide(): String? {
////        return getImages().getCoverImages().coverImage640; ga ada imagenya
//        return getImages().getImage640()
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
//    override fun toString(): String {
//        return getName()!!
//    }
//
//    override fun onClick(activity: BaseActivity) {
//        activity.startFragment(ArtistFragment.newInstance(getId()))
//    }
//
//    override fun play(activity: BaseActivity) {
//        if (AuthenticationUtils.isGuest(activity)) {
//            AuthenticationUtils.goLogin(activity)
//        } else {
//            activity.replacePlay()
//            val mService: ArtistService = ServiceGenerator.createServiceWithAuth(ArtistService::class.java, activity)
//            val apiCall: Call<DataListModel> = mService.getTopMusics(getId(), 0, 50)
//            apiCall.enqueue(object : BaseCallback<DataListModel?>() {
//                override fun onSuccess(call: Call<DataListModel?>, response: Response<DataListModel?>) {
//                    val modelWithActions: ArrayList<ModelWithAction>? = response.body()?.getDataList() as ArrayList<ModelWithAction>?
//                    val playableModels: ArrayList<PlayableModel> = ArrayList()
//                    for (m in modelWithActions!!) {
//                        m.setSourceContentType(SOURCE_CONTENT_ARTIST)
//                        m.setSourceContentId(getId())
//                        playableModels.add(m as PlayableModel)
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
//            })
//        }
//    }
//
//    override fun onLongClick(activity: BaseActivity) {
//        val data = Parcels.wrap(Artist::class.java, this)
//        val dialog: ActionDialog = ActionDialog.newInstance(ModelContract.CATEGORY_ARTIST, data)
//        dialog.show(activity.supportFragmentManager, "dialog")
//    }
//
//    override fun getContentTypeString(): String? {
//        return ModelContract.getSearchString(ModelContract.CATEGORY_ARTIST)
//    }
//}