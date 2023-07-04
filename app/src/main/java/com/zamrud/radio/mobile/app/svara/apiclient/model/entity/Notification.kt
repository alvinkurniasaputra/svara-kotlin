package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.BaseActivity
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
import com.zamrud.radio.mobile.app.svara.apiclient.services.AccountsService
import org.parceler.Parcel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*



/**
 * Created by solusi247 on 30/09/16.
 */
@Parcel
class Notification : ModelWithAction() {
    companion object {
        const val TYPE_FEED = "Feed"
        const val TYPE_ACCOUNT = "Account"
        const val TYPE_CHAT = "Chat"

        const val EVENT_FOLLOW = "follow"
        const val EVENT_COMMENT = "comment"
        const val EVENT_LIKE = "like"
        const val EVENT_MENTION = "mention"
        const val EVENT_REPLY = "reply"
        const val EVENT_GOTO = "goto"
        const val EVENT_NO_ACTION = "noaction"
        const val EVENT_DIRECT = "direct"
    }

    @SerializedName("eventType")
    @Expose
    @JvmField
    var eventType: String? = null

    @SerializedName("contentType")
    @Expose
    @JvmField
    var contentType: String? = null

    @SerializedName("read")
    @Expose
    @JvmField
    var read = false

    @SerializedName("created")
    @Expose
    @JvmField
    var created: Date? = null

    @SerializedName("modified")
    @Expose
    @JvmField
    var modified: Date? = null

    @SerializedName("id")
    @Expose
    @JvmField
    var id: String? = null

    @SerializedName("contentId")
    @Expose
    @JvmField
    var contentId: String? = null

    @SerializedName("images")
    @Expose
    @JvmField
    var images: Images? = null

    @SerializedName("message")
    @Expose
    var mMessage: NotificationMessage = NotificationMessage()

    @SerializedName("from")
    @Expose
    var mFrom: List<NotificationFrom>? = null

    fun getEventType(): String? {
        return eventType
    }

    fun setEventType(eventType: String?) {
        this.eventType = eventType
    }

    fun getContentType(): String? {
        return contentType
    }

    fun setContentType(contentType: String?) {
        this.contentType = contentType
    }

    fun isRead(): Boolean {
        return read
    }

    fun setRead(read: Boolean) {
        this.read = read
    }

    fun getCreated(): Date {
        return created!!
    }

    fun setCreated(created: Date) {
        this.created = created
    }

    fun getModified(): Date {
        return modified!!
    }

    fun setModified(modified: Date) {
        this.modified = modified
    }

     override fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getContentId(): String? {
        return contentId
    }

    fun setContentId(contentId: String?) {
        this.contentId = contentId
    }

    override fun getImages(): Images? {
        return images
    }

    fun setImages(images: Images?) {
        this.images = images
    }

    fun getmMessage(): NotificationMessage {
        return mMessage
    }

    fun setmMessage(mMessage: NotificationMessage) {
        this.mMessage = mMessage
    }

    fun getmFrom(): List<NotificationFrom>? {
        return mFrom
    }

    fun setmFrom(mFrom: List<NotificationFrom>?) {
        this.mFrom = mFrom
    }

    fun getListUsername(): ArrayList<String?> {
        val s: ArrayList<String?> = ArrayList()
        for (n: NotificationFrom in mFrom!!) {
            s.add(n.getUsername())
        }
        return s
    }

    override fun getCoverImageSmall(): String? {
        return if (getImages() != null) {
            getImages()!!.getImage64()
        } else {
            ""
        }
    }

    override fun getCoverImageMedium(): String? {
        return getImages()!!.getImage150()
    }

    override fun getCoverImageLarge(): String? {
        return getImages()!!.getImage300()
    }

    override fun getCoverImageExtraLarge(): String? {
        return getImages()!!.getImage640()
    }

    override fun getWideCoverImageSmall(): String? {
        return getImages()!!.getCoverImages().coverImage300
    }

    override fun getWideCoverImageMedium(): String? {
        return getImages()!!.getCoverImages().coverImage640
    }

    override fun getWideCoverImageLarge(): String? {
        return getImages()!!.getCoverImages().coverImage1280
    }

    override fun getPlaceholder(): String? {
        return getImages()!!.getPlaceholder()
    }

    override fun getCoverImageWide(): String? {
        return getImages()!!.getImagew640()
    }

    fun getFromFormated(): String {
        val stringBuilder = StringBuilder()
        for (f: NotificationFrom in mFrom!!) {
            stringBuilder.append(f.getUsername())
            stringBuilder.append(" ")
        }
        return stringBuilder.toString()
    }

    fun getFullName(): String {
        return mFrom!![0].getFirstName() +
                " " +
                mFrom!![0].getLastName()
    }

    override fun onClick(activity: BaseActivity) {
        read = true
        val accountsService: AccountsService = ServiceGenerator.createServiceWithAuth(AccountsService::class.java, activity)
        val call: Call<Void> = accountsService.readNotification(getId(), "true")
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
//                    activity.setNotifyCount(activity.getNotifyCount() - 1)
                    try {
//                        (activity as MainActivity).setNotifyBadgeCount()
                    } catch (e: Exception) {
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }
        })
        when (getContentType()) {
//            TYPE_FEED -> {
//                val fragment: CommentFragment = CommentFragment.newInstance(contentId)
//                activity.startFragment(fragment)
//            }
//            TYPE_ACCOUNT -> activity.startFragment(UserProfileFragment.newInstance(contentId))
            TYPE_CHAT -> {}
        }
    }
}