package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.BaseActivity
import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
//import com.zamrud.radio.mobile.app.svara.userprofile.fragments.UserProfileFragment

import org.parceler.Parcel

/**
 * Created by solusi247 on 30/09/16.
 */
@Parcel
class NotificationFrom : ModelWithAction() {
    @SerializedName("profilePicture")
    @Expose
    @JvmField
    var profilePicture: String? = null

    @SerializedName("username")
    @Expose
    @JvmField
    var username: String? = null

    @SerializedName("firstName")
    @Expose
    @JvmField
    var firstName: String? = null

    @SerializedName("lastName")
    @Expose
    @JvmField
    var lastName: String? = null

    @SerializedName("id")
    @Expose
    @JvmField
    var id: String? = null

    @SerializedName("images")
    @Expose
    @JvmField
    var images: Images? = null

    @SerializedName("followed")
    @Expose
    @JvmField
    var followed = false

    fun getProfilePicture(): String? {
        return profilePicture
    }

    fun setProfilePicture(profilePicture: String?) {
        this.profilePicture = profilePicture
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String?) {
        this.firstName = firstName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String?) {
        this.lastName = lastName
    }

    override fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    override fun getImages(): Images {
        return images!!
    }

    fun setImages(images: Images) {
        this.images = images
    }

    fun isFollowed(): Boolean {
        return followed
    }

    fun setFollowed(followed: Boolean) {
        this.followed = followed
    }

    override fun getCoverImageSmall(): String? {
        return getImages().getImage64()
    }

    override fun getCoverImageMedium(): String? {
        return getImages().getImage150()
    }

    override fun getCoverImageLarge(): String? {
        return getImages().getImage300()
    }

    override fun getCoverImageExtraLarge(): String? {
        return getImages().getImage640()
    }

    override fun getWideCoverImageSmall(): String? {
        return getImages().getCoverImages().coverImage300
    }

    override fun getWideCoverImageMedium(): String? {
        return getImages().getCoverImages().coverImage640
    }

    override fun getWideCoverImageLarge(): String? {
        return getImages().getCoverImages().coverImage1280
    }

    override fun getPlaceholder(): String? {
        return getImages().getPlaceholder()
    }

    override fun getCoverImageWide(): String? {
        return getImages().getImagew640()
    }

    override fun onClick(activity: BaseActivity) {
//        activity.startFragment(UserProfileFragment.newInstance(id))
    }

    fun getFullName(): String {
        return "$firstName $lastName"
    }
}