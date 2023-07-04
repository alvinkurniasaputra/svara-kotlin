package com.zamrud.radio.mobile.app.svara.apiclient.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by solusi247 on 04/08/16.
 */
class UpdateRequest {
    @SerializedName("firstName")
    @Expose
    private var mFirstName: String? = null

    @SerializedName("lastName")
    @Expose
    private var mLastName: String? = null

    @SerializedName("username")
    @Expose
    private var mUsername: String? = null

    @SerializedName("about")
    @Expose
    private var mAbout: String? = null

    @SerializedName("profilePicture")
    @Expose
    private var mProfilePicture: String? = null

    @SerializedName("coverImage")
    @Expose
    private var mCoverImage: String? = null

    @SerializedName("email")
    @Expose
    private var mEmail: String? = null

    @SerializedName("gender")
    @Expose
    private var mGender: String? = null

    @SerializedName("birthday")
    @Expose
    private var mBirthday: String? = null

    init {
        mFirstName = " "
        mLastName = " "
        mUsername = " "
        mAbout = " "
        mProfilePicture = " "
        mCoverImage = " "
        mEmail = " "
        mGender = " "
    }

    fun getFirstName(): String? {
        return mFirstName
    }

    fun setFirstName(FirstName: String?) {
        this.mFirstName = FirstName
    }

    fun getLastName(): String? {
        return mLastName
    }

    fun setLastName(LastName: String?) {
        this.mLastName = LastName
    }

    fun getUsername(): String? {
        return mUsername
    }

    fun setUsername(Username: String?) {
        this.mUsername = Username
    }

    fun getAbout(): String? {
        return mAbout
    }

    fun setAbout(About: String?) {
        this.mAbout = About
    }

    fun getProfilePicture(): String? {
        return mProfilePicture
    }

    fun setProfilePicture(ProfilePicture: String?) {
        this.mProfilePicture = ProfilePicture
    }

    fun getCoverImage(): String? {
        return mCoverImage
    }

    fun setCoverImage(CoverImage: String?) {
        this.mCoverImage = CoverImage
    }

    fun getEmail(): String? {
        return mEmail
    }

    fun setEmail(Email: String?) {
        this.mEmail = Email
    }

    fun getGender(): String? {
        return mGender
    }

    fun setGender(Gender: String?) {
        this.mGender = Gender
    }

    fun getBirthday(): String? {
        return mBirthday
    }

    fun setBirthday(Birthday: String?) {
        this.mBirthday = Birthday
    }

}