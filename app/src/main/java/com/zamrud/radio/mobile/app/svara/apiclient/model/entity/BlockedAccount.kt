package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel
//import com.zamrud.radio.mobile.app.svara.userprofile.fragments.UserProfileFragment

class BlockedAccount {
    @SerializedName("contentType")
    @Expose
    private var contentType = ""

    @SerializedName("dataList")
    @Expose
    private var dataList: List<BlockedUser> = ArrayList()

    //has next, count, dan totalCOunt belum tentu semua model ada
    @SerializedName("hasNext")
    @Expose
    private var hasNext = false

    @SerializedName("count")
    @Expose
    private var count = 0

    @SerializedName("totalCount")
    @Expose
    private var totalCount = 0

    fun getContentType(): String {
        return contentType
    }

    fun setContentType(contentType: String) {
        this.contentType = contentType
    }

    fun getDataList(): List<BlockedUser> {
        return dataList
    }

    fun setDataList(dataList: List<BlockedUser>) {
        this.dataList = dataList
    }

    fun getHasNext(): Boolean {
        return hasNext
    }

    fun setHasNext(hasNext: Boolean) {
        this.hasNext = hasNext
    }

    fun getCount(): Int {
        return count
    }

    fun setCount(count: Int) {
        this.count = count
    }

    fun getTotalCount(): Int {
        return totalCount
    }

    fun setTotalCount(totalCount: Int) {
        this.totalCount = totalCount
    }

    class BlockedUser : BaseModel() {
        @SerializedName("accountId")
        @Expose
        @JvmField
        var accountId: String? = null

        @SerializedName("blockedAccountId")
        @Expose
        @JvmField
        var blockedAccountId: String? = null

        @SerializedName("createdAt")
        @Expose
        @JvmField
        var createdAt: String? = null

        @SerializedName("updatedAt")
        @Expose
        @JvmField
        var updatedAt: String? = null

        @SerializedName("isDeleted")
        @Expose
        var isDeleted: String? = null

        @SerializedName("account")
        @Expose
        @JvmField
        var account: UserAccount? = null

        @SerializedName("blockedAccount")
        @Expose
        @JvmField
        var blockedUserAccount: BlockedUserAccount? = null

        fun getAccountId(): String? {
            return accountId
        }

        fun setAccountId(accountId: String?) {
            this.accountId = accountId
        }

        fun getBlockedAccountId(): String? {
            return blockedAccountId
        }

        fun setBlockedAccountId(blockedAccountId: String?) {
            this.blockedAccountId = blockedAccountId
        }

        fun getCreatedAt(): String? {
            return createdAt
        }

        fun setCreatedAt(createdAt: String?) {
            this.createdAt = createdAt
        }

        fun getUpdatedAt(): String? {
            return updatedAt
        }

        fun setUpdatedAt(updatedAt: String?) {
            this.updatedAt = updatedAt
        }

        fun getIsDeleted(): String? {
            return isDeleted
        }

        fun setIsDeleted(isDeleted: String?) {
            this.isDeleted = isDeleted
        }

        fun getAccount(): UserAccount {
            return account!!
        }

        fun setAccount(account: UserAccount) {
            this.account = account
        }

        fun getBlockedUserAccount(): BlockedUserAccount {
            return blockedUserAccount!!
        }

        fun setBlockedUserAccount(blockedUserAccount: BlockedUserAccount) {
            this.blockedUserAccount = blockedUserAccount
        }

//        fun onClick(activity: BaseActivity, id: String?) {
//            activity.startFragment(UserProfileFragment.newInstance(id))
//        }
    }

    class UserAccount {
        @SerializedName("profilePicture")
        @Expose
        @JvmField
        var profilePicture: String? = null

        @SerializedName("firstName")
        @Expose
        @JvmField
        var firstName: String? = null

        @SerializedName("lastName")
        @Expose
        @JvmField
        var lastName: String? = null

        @SerializedName("username")
        @Expose
        @JvmField
        var username: String? = null

        @SerializedName("id")
        @Expose
        @JvmField
        var id: String? = null

        fun getProfilePicture(): String? {
            return profilePicture
        }

        fun setProfilePicture(profilePicture: String?) {
            this.profilePicture = profilePicture
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

        fun getUsername(): String? {
            return username
        }

        fun setUsername(username: String?) {
            this.username = username
        }

        fun getId(): String? {
            return id
        }

        fun setId(id: String?) {
            this.id = id
        }

    }

    class BlockedUserAccount {
        @SerializedName("profilePicture")
        @Expose
        @JvmField
        var profilePicture: String? = null

        @SerializedName("firstName")
        @Expose
        @JvmField
        var firstName: String? = null

        @SerializedName("lastName")
        @Expose
        @JvmField
        var lastName: String? = null

        @SerializedName("username")
        @Expose
        @JvmField
        var username: String? = null

        @SerializedName("id")
        @Expose
        @JvmField
        var id: String? = null

        fun getProfilePicture(): String? {
            return profilePicture
        }

        fun setProfilePicture(profilePicture: String?) {
            this.profilePicture = profilePicture
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

        fun getUsername(): String? {
            return username
        }

        fun setUsername(username: String?) {
            this.username = username
        }

        fun getId(): String? {
            return id
        }

        fun setId(id: String?) {
            this.id = id
        }
    }
}