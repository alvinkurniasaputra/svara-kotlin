package com.zamrud.radio.mobile.app.svara.apiclient.model.video

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.BaseActivity
import com.zamrud.radio.mobile.app.svara.SvaraApplication
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Account
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Images
import org.parceler.Parcel
import org.parceler.Parcels
import java.util.*


@Parcel
class Video : VideoModel() {
    @SerializedName("name")
    @Expose
    @JvmField
    var name: String? = ""

    @SerializedName("duration")
    @Expose
    @JvmField
    var duration: Float = 0F

    @SerializedName("coverArt")
    @Expose
    @JvmField
    var coverArt: String? = ""

    @SerializedName("coverArtWide")
    @Expose
    @JvmField
    var coverArtWide: String? = ""

    @SerializedName("isPrivate")
    @Expose
    @JvmField
    var isPrivate = false

    @SerializedName("caption")
    @Expose
    @JvmField
    var caption: String? = ""

    @SerializedName("source")
    @Expose
    @JvmField
    var source: String? = ""

    @SerializedName("uploaderId")
    @Expose
    @JvmField
    var uploaderId: String? = ""

    @SerializedName("ownerId")
    @Expose
    @JvmField
    var ownerId: String? = ""

    @SerializedName("ownerType")
    @Expose
    @JvmField
    var ownerType: String? = ""

    @SerializedName("isDeleted")
    @Expose
    @JvmField
    var isDeleted = false

    @SerializedName("updatedAt")
    @Expose
    @JvmField
    var updatedAt: Date?

    @SerializedName("status")
    @Expose
    @JvmField
    var status: String? = ""

    @SerializedName("content")
    @Expose
    @JvmField
    var content: String? = ""

    @SerializedName("id")
    @Expose
    @JvmField
    var id: String? = ""

    @SerializedName("createdAt")
    @Expose
    @JvmField
    var createdAt: Date?

    @SerializedName("autoPublish")
    @Expose
    @JvmField
    var autoPublish = true

    @SerializedName("owner")
    @Expose
    var owner: String? = null

    @SerializedName("images")
    @Expose
    @JvmField
    var images: Images?

    @SerializedName("playable")
    @Expose
    @JvmField
    var playable: PlayableVideo?

    init {
        var a: Int
        createdAt = Date()
        updatedAt = Date()
        //        owner = Radio();
        images = Images()
        playable = PlayableVideo()
    }

    fun getPlayable(): PlayableVideo? {
        return playable
    }

    fun setPlayable(playable: PlayableVideo?) {
        this.playable = playable
    }

    override fun getImages(): Images? {
        return images
    }

    fun setImages(images: Images?) {
        this.images = images
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getDuration(): Float {
        return duration
    }

    fun setDuration(duration: Float) {
        this.duration = duration
    }

    fun getCoverArt(): String? {
        return coverArt
    }

    fun setCoverArt(coverArt: String?) {
        this.coverArt = coverArt
    }

    fun getCoverArtWide(): String? {
        return coverArtWide
    }

    fun setCoverArtWide(coverArtWide: String?) {
        this.coverArtWide = coverArtWide
    }

    fun isPrivate(): Boolean {
        return isPrivate
    }

    fun setPrivate(aPrivate: Boolean) {
        isPrivate = aPrivate
    }

    fun getCaption(): String? {
        return caption
    }

    fun setCaption(caption: String?) {
        this.caption = caption
    }

    fun getSource(): String? {
        return source
    }

    fun setSource(source: String?) {
        this.source = source
    }

    fun getUploaderId(): String? {
        return uploaderId
    }

    fun setUploaderId(uploaderId: String?) {
        this.uploaderId = uploaderId
    }

    override fun getOwnerId(): String? {
        return ownerId
    }

    fun setOwnerId(ownerId: String?) {
        this.ownerId = ownerId
    }

    fun getOwnerType(): String? {
        return ownerType
    }

    fun setOwnerType(ownerType: String?) {
        this.ownerType = ownerType
    }

    fun isDeleted(): Boolean {
        return isDeleted
    }

    fun setDeleted(deleted: Boolean) {
        isDeleted = deleted
    }

    fun getUpdatedAt(): Date? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: Date?) {
        this.updatedAt = updatedAt
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }

    override fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getCreatedAt(): Date? {
        return createdAt
    }

    fun setCreatedAt(createdAt: Date?) {
        this.createdAt = createdAt
    }

    fun isAutoPublish(): Boolean {
        return autoPublish
    }

    fun setAutoPublish(autoPublish: Boolean) {
        this.autoPublish = autoPublish
    }

    fun getOwnerString(): String? {
        return owner
    }

//    fun getOwner(): ModelWithAction? {
//        if (owner == null)
//            return Radio()
//        val gson = Gson()
//        return when (ownerType) {
//            VIDEO_OWNER_ACCOUNT -> gson.fromJson(owner, Account::class.java)
//            VIDEO_OWNER_RADIO -> gson.fromJson(owner, Radio::class.java)
//            else -> Radio()
//        }
//    }

    fun setOwner(owner: Account?) {
        val gson = Gson()
        this.owner = gson.toJson(owner)
    }

//    fun setOwner(owner: Radio?) {
//        val gson = Gson()
//        this.owner = gson.toJson(owner)
//    }

    fun setOwnerString(owner: String?) {
        this.owner = owner
    }

    override fun getContentTypeString(): String {
        return ModelContract.getSearchString(ModelContract.CATEGORY_VIDEO)
    }

    override fun onLongClick(activity: BaseActivity) {
        val data: Parcelable = Parcels.wrap(Video::class.java, this)
//        val dialog: ActionDialog = ActionDialog.newInstance(ModelContract.CATEGORY_VIDEO, data)
//        dialog.show(activity.getSupportFragmentManager(), "dialog")
    }

    override fun getLines(): ArrayList<CharSequence?>? {
        val out: ArrayList<CharSequence?>? = super.getLines()
        out?.set(0, getName())
//        out?.set(1, if (getOwner() != null) getOwner()!!.getLines()?.get(0) else "")
        out?.set(2, getCaption())
        return out
    }

    override fun getVideoUrl(): String {
        return ServiceGenerator.getActiveApiURL() + playable!!.getRaw() + "?jwt=" + AuthenticationUtils.getToken(SvaraApplication.getAppContext())
    }

    override fun getCoverImageMedium(): String? {
        return getImages()!!.getImage300()
    }

    override fun getCoverImageExtraLarge(): String? {
        return getImages()!!.getImage640()
    }

//    override fun play(activity: BaseActivity) {
//        activity.showVideoPlayer(this, false)
//    }
//
//    override fun onClick(activity: BaseActivity) {
//        activity.showVideoPlayer(this, false)
//    }
//
//    override fun onClick(activity: BaseActivity, position: Int) {
//        activity.showVideoPlayer(this, false)
//    }
//
//    override fun onClick(activity: BaseActivity, list: List<PlayableModel>?, position: Int, isFree: Boolean) {
//        activity.showVideoPlayer(this, false)
//    }

}