package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Membership {
    companion object {
        const val MEMBER_TYPE_PREMIUM = "premium"
        const val MEMBER_TYPE_FREE = "free"
        const val MEMBER_TYPE_BASIC = "basic"
    }

    @SerializedName("packageId")
    @Expose
    var mPackageId = ""

    @SerializedName("packageType")
    @Expose
    var mPackageType = MEMBER_TYPE_FREE

    @SerializedName("startDate")
    @Expose
    var mStartDate = ""

    @SerializedName("endDate")
    @Expose
    var mEndDate = ""

    @SerializedName("membershipParam")
    @Expose
    var mMembershipParam: Any = ""

    @SerializedName("appId")
    @Expose
    var mAppId = ""

    @SerializedName("id")
    @Expose
    var mId = ""

    @SerializedName("accountId")
    @Expose
    var mAccountId = ""

    fun getPackageId(): String {
        return mPackageId
    }

    fun setPackageId(packageId: String) {
        mPackageId = packageId
    }

    fun getPackageType(): String {
        return mPackageType
    }

    fun setPackageType(packageType: String) {
        mPackageType = packageType
    }

    fun getStartDate(): String {
        return mStartDate
    }

    fun setStartDate(startDate: String) {
        mStartDate = startDate
    }

    fun getEndDate(): String {
        return mEndDate
    }

    fun setEndDate(endDate: String) {
        mEndDate = endDate
    }

    fun getMembershipParam(): Any {
        return mMembershipParam
    }

    fun setMembershipParam(membershipParam: Any) {
        mMembershipParam = membershipParam
    }

    fun getAppId(): String {
        return mAppId
    }

    fun setAppId(appId: String) {
        mAppId = appId
    }

    fun getId(): String {
        return mId
    }

    fun setId(id: String) {
        mId = id
    }

    fun getAccountId(): String {
        return mAccountId
    }

    fun setAccountId(accountId: String) {
        mAccountId = accountId
    }

}