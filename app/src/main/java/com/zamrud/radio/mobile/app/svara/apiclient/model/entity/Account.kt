package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import android.content.Context
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.linkedin.android.spyglass.mentions.Mentionable
import com.zamrud.radio.mobile.app.svara.BaseActivity
import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
import org.parceler.Parcel
import org.parceler.Parcels


/**
 * Created by fahziar on 05/04/2016.
 */
@Parcel
class Account : ModelWithAction, Mentionable {
    @SerializedName("male")
    @Expose
    var mMale: String? = null

    @SerializedName("birthday")
    @Expose
    var mBirthday: String? = null

    @SerializedName("profilePicture")
    @Expose
    var mProfilePicture: String? = null

    @SerializedName("about")
    @Expose
    var mAbout: String? = null

    @SerializedName("radioOwner")
    @Expose
    var mRadioOwner: Boolean? = null

    @SerializedName("username")
    @Expose
    var mUsername: String? = null

    @SerializedName("firstName")
    @Expose
    var mFirstName: String? = null

    @SerializedName("lastName")
    @Expose
    var mLastName: String? = null

    @SerializedName("email")
    @Expose
    var mEmail: String? = null

    @SerializedName("emailVerified")
    @Expose
    var mEmailVerified: Boolean? = null

    @SerializedName("id")
    @Expose
    var mId: String? = null

    @SerializedName("followed")
    @Expose
    var mIsFollowed: Boolean? = null

    @SerializedName("images")
    @Expose
    @JvmField
    var images: Images? = null

    @SerializedName("gender")
    @Expose
    var mGender: String? = null

    @SerializedName("password")
    @Expose
    var mPassword: String? = null

    @SerializedName("coverImage")
    @Expose
    var mHeaderImage: String? = null
    @JvmField
    var isOver = false

    @SerializedName("isBlocked")
    @Expose
    @JvmField
    var isBlocked = false

    @SerializedName("isUnblockable")
    @Expose
    @JvmField
    var isUnblockable = false

    @SerializedName("status")
    @Expose
    @JvmField
    var defaultAccount: DefaultAccount? = null

    @SerializedName("isOnline")
    @Expose
    var isOnline: Boolean? = null

    fun getPhone(): String? {
        return mPhone
    }

    fun setPhone(mPhone: String?) {
        this.mPhone = mPhone
    }

    @SerializedName("phone")
    @Expose
    var mPhone: String? = null

    @SerializedName("accountTypeId")
    @Expose
    @JvmField
    var accountTypeId: String? = null

    @SerializedName("accountType")
    @Expose
    @JvmField
    var accountType: AccountType? = null

    @SerializedName("isMe")
    @Expose
    var isMe: Boolean? = null

    @SerializedName("linkWhatsapp")
    @Expose
    @JvmField
    var linkWhatsapp: String? = null

    @SerializedName("eCommerce")
    @Expose
    var eCommerce: ECommerce? = null

    constructor() {
        mMale = ""
        mBirthday = ""
        mProfilePicture = " "
        mAbout = ""
        mRadioOwner = false
        mUsername = ""
        mFirstName = ""
        mLastName = ""
        mEmail = ""
        mEmailVerified = false
        mId = ""
        mHeaderImage = ""
        mIsFollowed = false
        defaultAccount = nullify().defaultAccount
        isOnline = false
        accountTypeId = ""
        isMe = false
    }

    fun nullify(): Account {
        mMale = null
        mBirthday = null
        mProfilePicture = null
        mAbout = null
        mRadioOwner = null
        mUsername = null
        mFirstName = null
        mLastName = null
        mEmail = null
        mEmailVerified = null
        mId = null
        mIsFollowed = null
        mPassword = null
        mHeaderImage = null
        defaultAccount = null
        isOnline = null
        accountTypeId = null
        accountType = null
        isMe = null
        return this
    }

    fun getAccountTypeId(): String? {
        return accountTypeId
    }

    fun setAccountTypeId(accountTypeId: String?) {
        this.accountTypeId = accountTypeId
    }

    fun getAccountType(): AccountType? {
        return accountType
    }

    fun setAccountType(accountType: AccountType?) {
        this.accountType = accountType
    }

    fun getDefaultAccount(): DefaultAccount? {
        return defaultAccount
    }

    fun setDefaultAccount(defaultAccount: DefaultAccount?) {
        this.defaultAccount = defaultAccount
    }

    fun getLinkWhatsapp(): String? {
        return linkWhatsapp
    }

    fun setLinkWhatsapp(linkWhatsapp: String?) {
        this.linkWhatsapp = linkWhatsapp
    }

    fun geteCommerce(): Account.ECommerce? {
        return eCommerce
    }

    fun seteCommerce(eCommerce: Account.ECommerce?) {
        this.eCommerce = eCommerce
    }

    fun getHeaderImage(): String? {
        return mHeaderImage
    }

    fun setHeaderImage(coverImage: String) {
        mHeaderImage = coverImage
    }

    fun getMe(): Boolean? {
        return isMe
    }

    fun setMe(me: Boolean) {
        isMe = me
    }

    fun getMale(): String? {
        return mMale
    }

    fun setMale(male: String) {
        mMale = male
    }

    fun getGender(): String? {
        return mGender
    }

    fun setGender(gender: String) {
        mGender = gender
    }

    fun getBirthday(): String? {
        return mBirthday
    }

    fun setBirthday(birthday: String) {
        mBirthday = birthday
    }

    fun getProfilePicture(): String? {
        return mProfilePicture
    }

    fun setProfilePicture(profilePicture: String) {
        mProfilePicture = profilePicture
    }

    fun getAbout(): String? {
        return mAbout
    }

    fun setAbout(about: String) {
        mAbout = about
    }

    fun getRadioOwner(): Boolean? {
        return mRadioOwner
    }

    fun setRadioOwner(radioOwner: Boolean) {
        mRadioOwner = radioOwner
    }

    fun getUsername(): String? {
        return mUsername
    }

    fun setUsername(username: String) {
        mUsername = username
    }

    fun getEmail(): String? {
        return mEmail
    }

    fun setEmail(email: String) {
        mEmail = email
    }

    fun getEmailVerified(): Boolean? {
        return mEmailVerified
    }

    fun setEmailVerified(emailVerified: Boolean) {
        mEmailVerified = emailVerified
    }

    override fun getId(): String? {
        return mId
    }

    fun setId(id: String) {
        mId = id
    }

    fun getFirstName(): String? {
        return mFirstName
    }

    fun setFirstName(firstName: String) {
        mFirstName = firstName
    }

    fun getLastName(): String? {
        return mLastName
    }

    fun setLastName(lastName: String) {
        mLastName = lastName
    }

    fun isFollowed(): Boolean {
        return mIsFollowed!!
    }

    fun setIsFollowed(IsFollowed: Boolean) {
        this.mIsFollowed = IsFollowed
    }

    override fun getImages(): Images {
        return if (images == null) Images() else images!!
    }

    fun setImages(images: Images?) {
        this.images = images
    }

    fun setPassword(password: String?) {
        mPassword = password
    }

    fun isBlocked(): Boolean {
        return isBlocked
    }

    fun setBlocked(blocked: Boolean) {
        isBlocked = blocked
    }

    fun isUnblockable(): Boolean {
        return isUnblockable
    }

    fun setUnblockable(unblockable: Boolean) {
        isUnblockable = unblockable
    }

    override fun getLines(): ArrayList<CharSequence?>? {
        var out: ArrayList<CharSequence?>? = super.getLines()
        out?.set(0, getFirstName()+" "+getLastName())
       //TODO: Follower Count
        out?.set(1, getAbout())
        out?.set(2, "")
        return out
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

    override fun getImageOri(): String? {
        return getImages().getImageOri()
    }

    override fun getPlaceholder(): String? {
        return getImages().getPlaceholder()
    }

    override fun getCoverImageWide(): String? {
        return getImages().getImagew640()
    }

    fun isOnline(): Boolean {
        return isOnline!!
    }

    fun setOnline(online: Boolean) {
        isOnline = online
    }

    override fun onClick(activity: BaseActivity) {
//        activity.startFragment(UserProfileFragment.newInstance(mId))
    }

    override fun onLongClick(activity: BaseActivity) {
        val data = Parcels.wrap(Account::class.java, this)
//        val dialog: ActionDialog = ActionDialog.newInstance(ModelContract.CATEGORY_ACCOUNT, data)
//        dialog.show(activity.getSupportFragmentManager(), "dialog")
    }

    override fun getContentTypeString(): String {
        return ModelContract.getSearchString(ModelContract.CATEGORY_ACCOUNT)
    }

    override fun getSearchLines(context: Context): ArrayList<CharSequence?>? {
        val out: ArrayList<CharSequence?>? = getLines()
        out?.set(0, getFirstName() + " " + getLastName())
        out?.set(1, getAbout())
        return getLines()
    }

    fun isOver(): Boolean {
        return isOver
    }

    fun setOver(over: Boolean) {
        isOver = over
    }

    // --------------------------------------------------
    // Mentionable/Suggestible Implementation
    // --------------------------------------------------

    override fun getTextForDisplayMode(mode: Mentionable.MentionDisplayMode): String {
        return when (mode) {
            Mentionable.MentionDisplayMode.FULL -> "@" + getUsername() + " "
            Mentionable.MentionDisplayMode.PARTIAL -> getUsername()!!
            Mentionable.MentionDisplayMode.NONE -> ""
            else -> ""
        }
    }

    override fun getDeleteStyle(): Mentionable.MentionDeleteStyle {
        // People support partial deletion
        // i.e. "John Doe" -> DEL -> "John" -> DEL -> ""
        return Mentionable.MentionDeleteStyle.PARTIAL_NAME_DELETE
    }

    override fun getSuggestibleId(): Int {
        return getUsername().hashCode()
    }

    override fun getSuggestiblePrimaryText(): String {
        return getUsername()!!
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: android.os.Parcel, flags: Int) {
        dest.writeString(mFirstName)
        dest.writeString(mLastName)
        dest.writeString(mUsername)
    }

    fun getFullName(): String {
        return "$mFirstName $mLastName"
    }

    constructor(`in`: android.os.Parcel) {
        mFirstName = `in`.readString()
        mLastName = `in`.readString()
        mUsername = `in`.readString()
    }

    companion object{
        @JvmField
        val CREATOR: Parcelable.Creator<Account> = object : Parcelable.Creator<Account> {
            override fun createFromParcel(`in`: android.os.Parcel): Account {
                return Account(`in`)
            }

            override fun newArray(size: Int): Array<Account?> {
                return arrayOfNulls(size)
            }
        }

    }

    @Parcel
    class DefaultAccount {
        @SerializedName("default")
        @Expose
        @JvmField
        var statusAccount: StatusAccount? = null

        fun getStatusAccount(): StatusAccount? {
            return statusAccount
        }

        fun setStatusAccount(statusAccount: StatusAccount?) {
            this.statusAccount = statusAccount
        }
    }

    @Parcel
    class ECommerce {
        @SerializedName("url")
        @Expose
        @JvmField
        var url: String? = null

        @SerializedName("logo")
        @Expose
        @JvmField
        var logo: String? = null

        fun getUrl(): String? {
            return url
        }

        fun setUrl(url: String?) {
            this.url = url
        }

        fun getLogo(): String? {
            return logo
        }

        fun setLogo(logo: String?) {
            this.logo = logo
        }
    }

}
