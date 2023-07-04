package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

//import com.zamrud.radio.mobile.app.svara.WebView.WebviewFragment
//import com.zamrud.radio.mobile.app.svara.dialog.ActionDialog
//import com.zamrud.radio.mobile.app.svara.dialog.DialogShowImage
//import com.zamrud.radio.mobile.app.svara.directMessage.DirectMessageFragment

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.BaseActivity
import com.zamrud.radio.mobile.app.svara.NetworkUtil
import com.zamrud.radio.mobile.app.svara.Player.Model.PlayableModel
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
import java.util.*

class Link : ModelWithAction() {
    companion object {
        const val EXTRA_CONTENT_TYPE = "CONTENTTYPE"
        const val EXTRA_CONTENT_ID = "CONTENTID"
        const val EXTRA_IMAGE = "IMAGE"
        const val EXTRA_FIRST_LINE = "FIRSTLINE"
        const val EXTRA_SECOND_LINE = "SECONDLINE"
    }

    @SerializedName("id")
    @Expose
    private var id: String? = ""

    @SerializedName("useExternalBrowser")
    @Expose
    @JvmField
    var useExternalBrowser = true

    @SerializedName("forceDownloadChrome")
    @Expose
    @JvmField
    var forceDownloadChrome = false

    @SerializedName("name")
    @Expose
    @JvmField
    var name: String? = ""

    @SerializedName("order")
    @Expose
    @JvmField
    var order = 0.0f

    @SerializedName("image")
    @Expose
    @JvmField
    var image: String? = ""

    @SerializedName("coverImage")
    @Expose
    @JvmField
    var coverImage: String? = ""

    @SerializedName("primaryText")
    @Expose
    @JvmField
    var primaryText: String? = ""

    @SerializedName("secondaryText")
    @Expose
    @JvmField
    var secondaryText: String? = ""

    @SerializedName("contentType")
    @Expose
    @JvmField
    var contentType: String? = ""

    @SerializedName("contentId")
    @Expose
    @JvmField
    var contentId: String? = ""

    @SerializedName("enable")
    @Expose
    @JvmField
    var enable = true

    @SerializedName("images")
    @Expose
    private var images: Images?

    @SerializedName("url")
    @Expose
    @JvmField
    var url: String? = ""

    @SerializedName("linkGroupId")
    @Expose
    @JvmField
    var linkGroupId: String? = ""

    @SerializedName("appId")
    @Expose
    @JvmField
    var appId: String? = ""

    @SerializedName("createdAt")
    @Expose
    @JvmField
    var createdAt: Date?

    @SerializedName("updatedAt")
    @Expose
    @JvmField
    var updatedAt: Date?

    @SerializedName("content")
    @Expose
    @JvmField
    var content: ModelWithAction? = null

    @SerializedName("withToken")
    @Expose
    @JvmField
    var withToken = false

    @SerializedName("withParamAccount")
    @Expose
    @JvmField
    var withParamAccount = false

    @SerializedName("showMiniPlayer")
    @Expose
    @JvmField
    var showMiniPlayer = false

    init {
        images = Images()
        createdAt = Date()
        updatedAt = Date()
    }

    override fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun isUseExternalBrowser(): Boolean {
        return useExternalBrowser
    }

    fun setUseExternalBrowser(useExternalBrowser: Boolean) {
        this.useExternalBrowser = useExternalBrowser
    }

    fun isForceDownloadChrome(): Boolean {
        return forceDownloadChrome
    }

    fun setForceDownloadChrome(forceDownloadChrome: Boolean) {
        this.forceDownloadChrome = forceDownloadChrome
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getOrder(): Float {
        return order
    }

    fun setOrder(order: Float) {
        this.order = order
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String?) {
        this.image = image
    }

    fun getCoverImage(): String? {
        return coverImage
    }

    fun setCoverImage(coverImage: String?) {
        this.coverImage = coverImage
    }

    fun getPrimaryText(): String? {
        return primaryText
    }

    fun setPrimaryText(primaryText: String?) {
        this.primaryText = primaryText
    }

    fun getSecondaryText(): String? {
        return secondaryText
    }

    fun setSecondaryText(secondaryText: String?) {
        this.secondaryText = secondaryText
    }

    fun getContentType(): String? {
        return contentType
    }

    fun setContentType(contentType: String?) {
        this.contentType = contentType
    }

    fun getContentId(): String? {
        return contentId
    }

    fun setContentId(contentId: String?) {
        this.contentId = contentId
    }

    fun isEnable(): Boolean {
        return enable
    }

    fun setEnable(enable: Boolean) {
        this.enable = enable
    }

    override fun getImages(): Images? {
        return images
    }

    fun setImages(images: Images?) {
        this.images = images
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getLinkGroupId(): String? {
        return linkGroupId
    }

    fun setLinkGroupId(linkGroupId: String?) {
        this.linkGroupId = linkGroupId
    }

    fun getAppId(): String? {
        return appId
    }

    fun setAppId(appId: String?) {
        this.appId = appId
    }

    fun getCreatedAt(): Date? {
        return createdAt
    }

    fun setCreatedAt(createdAt: Date?) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): Date? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: Date?) {
        this.updatedAt = updatedAt
    }

    fun getContent(): ModelWithAction? {
        return content
    }

    fun setContent(content: ModelWithAction?) {
        this.content = content
    }

    fun isWithToken(): Boolean {
        return withToken
    }

    fun setWithToken(withToken: Boolean) {
        this.withToken = withToken
    }

    fun isWithParamAccount(): Boolean {
        return withParamAccount
    }

    fun setWithParamAccount(withParamAccount: Boolean) {
        this.withParamAccount = withParamAccount
    }

    fun isShowMiniPlayer(): Boolean {
        return showMiniPlayer
    }

    fun setShowMiniPlayer(showMiniPlayer: Boolean) {
        this.showMiniPlayer = showMiniPlayer
    }

    override fun getCoverImageSmall(): String? {
        return if (!NetworkUtil.isOnline())
            ServiceGenerator.getLocalImage(getId()!!)
        else getImages()!!.getImage64()
    }

    override fun getCoverImageMedium(): String? {
        return getImages()!!.getImage150()
    }

    override fun getCoverImageLarge(): String? {
        return getImages()!!.getImage300()
    }

    override fun getCoverImageExtraLarge(): String? {
        return if (!NetworkUtil.isOnline())
            ServiceGenerator.getLocalImage(getId()!!)
        else getImages()!!.getImage640()
    }

    override fun getWideCoverImageSmall(): String? {
        return if (!NetworkUtil.isOnline())
            ServiceGenerator.getLocalImage(getId()!!)
        else getImages()!!.getCoverImages().coverImage300
    }

    override fun getWideCoverImageMedium(): String? {
        return if (!NetworkUtil.isOnline())
            ServiceGenerator.getLocalImage(getId()!!)
        else getImages()!!.getCoverImages().coverImage640
    }

    override fun getWideCoverImageLarge(): String? {
        return if (!NetworkUtil.isOnline())
            ServiceGenerator.getLocalImage(getId()!!)
        else getImages()!!.getCoverImages().coverImage1280
    }

    override fun getCoverImageWide(): String? {
        return if (!NetworkUtil.isOnline())
            ServiceGenerator.getLocalImage(getId()!!)
        else getImages()!!.getImagew640()
    }

    override fun getImageOri(): String? {
        return getImages()!!.getImageOri()
    }

    override fun getPlaceholder(): String? {
        return getImages()!!.getPlaceholder()
    }

    override fun getLines(): ArrayList<CharSequence?>? {
        val list: ArrayList<CharSequence?> = ArrayList()
        list.add(primaryText)
        list.add(secondaryText)
        list.add(name)
        return list
    }

    override fun onClick(activity: BaseActivity) {
        if (ModelContract.getCategoryInt(contentType) == ModelContract.CATEGORY_URL || contentType!!.lowercase() == "imageonly") {
            openLink(activity)
            return
        }

        if (ModelContract.getCategoryInt(contentType) == ModelContract.CATEGORY_DIAL) {
            val number: Uri = Uri.parse("tel:$contentId")
            val callIntent = Intent(Intent.ACTION_DIAL, number)
            activity.startActivity(callIntent)
        }

//        if (ModelContract.getCategoryInt(contentType) == ModelContract.CATEGORY_DM) {
//            activity.startFragment(DirectMessageFragment.newInstancce(contentId,
//                    false,
//                    if (content == null) "Private" else content!!.getLines()?.get(0).toString()
//            ))
//            return
//        }

        if (content != null) {
            if (ModelContract.getCategoryInt(content!!.getContentTypeString()) == ModelContract.CATEGORY_RADIO) // Radio instance Playable model tapi buka page
                content!!.onClick(activity)
            else if (content is PlayableModel)
                content!!.play(activity)
            else content!!.onClick(activity)
        }
    }

    override fun play(activity: BaseActivity) {
        if (ModelContract.getCategoryInt(contentType) == ModelContract.CATEGORY_URL || contentType!!.lowercase() == "imageonly") {
            openLink(activity)
            return
        }

//        if (ModelContract.getCategoryInt(contentType) == ModelContract.CATEGORY_DM) {
//            activity.startFragment(DirectMessageFragment.newInstancce(contentId,
//                    false,
//                    if (content == null) "Private" else content!!.getLines()?.get(0).toString()))
//            return
//        }

        if (content != null) {
            if (content is PlayableModel)
                content!!.play(activity)
            else
                content!!.onClick(activity)
        }
    }

    override fun onLongClick(baseActivity: BaseActivity) {
        val bundle = Bundle()
        bundle.putInt(EXTRA_CONTENT_TYPE, ModelContract.getCategoryInt(contentType))
        bundle.putString(EXTRA_CONTENT_ID, contentId)
        bundle.putString(EXTRA_IMAGE, getCoverImageMedium())
        bundle.putString(EXTRA_FIRST_LINE, getPrimaryText())
        bundle.putString(EXTRA_SECOND_LINE, getSecondaryText())

//        val dialog: ActionDialog = ActionDialog.newInstance(ModelContract.CATEGORY_LINK, bundle)
//        dialog.show(baseActivity.supportFragmentManager, "dialog")
    }

    private fun openLink(activity: BaseActivity) {
        var link: String? = if (contentId!!.startsWith("http")) contentId else "http://$contentId"
        if (contentType!!.lowercase() == "imageonly") {
            link = getImages()!!.getCoverImages().getCoverImage640()
            if (link == null || link.isEmpty() || link.length < 5)
                link = getImages()!!.getImage640()
            link = link!!.replace("/640x640/", "/0x0/")
        }
        if (withToken)
            link = addJwtParam(activity, link)

        if (withParamAccount)
            link = addUseParam(link)

        if (isUseExternalBrowser()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            intent.setPackage("com.android.chrome") // force open with chrome
            try {
                activity.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // Chrome is probably not installed
                if (isForceDownloadChrome()) {
                    try {
                        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.android.chrome")))
                    } catch (ex: ActivityNotFoundException) {
                        // Try with the default browser
                        openDefaultBrowser(intent, activity)
                    }
                } else {
                    // Try with the default browser
                    openDefaultBrowser(intent, activity)
                }
            }
        } else {
//            if (contentType!!.lowercase() == "imageonly") {
//                val dialogShowImage: DialogShowImage = DialogShowImage.newInstance(activity.baseContext, link, getImages()!!.getCoverImages().getPlaceholder())
//                dialogShowImage.show(activity.supportFragmentManager, "dialog")
//            } else
//                activity.startFragment(WebviewFragment.newInstance(link, primaryText, isShowMiniPlayer()))
        }
    }

    private fun openDefaultBrowser(intent: Intent, activity: BaseActivity) {
        try {
            intent.setPackage(null)
            activity.startActivity(intent)
        } catch (exc: ActivityNotFoundException) {
//            Toast.makeText(activity.getContext(), "No Browser Found.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addJwtParam(context: Context, link: String?): String {
        return link + (if (link!!.contains("?")) "&jwt=" else "?jwt=") + AuthenticationUtils.getToken(context)
    }

    private fun addUseParam(link: String?): String {
        val account: Account? = AuthenticationUtils.getCurrentAccount()

        val builder = StringBuilder()
        builder.append(link)

        if (link!!.contains("?"))
            builder.append("&")
        else builder.append("?")

        builder.append("sId=")
        builder.append(account!!.getId())

        builder.append("&")
        builder.append("email=")
        builder.append(account.getEmail())

        builder.append("&")
        builder.append("fullname=")
        builder.append(account.getFirstName())
        builder.append(" ")
        builder.append(account.getLastName())

        builder.append("&")
        builder.append("username=")
        builder.append(account.getUsername())

        return builder.toString()
    }
}