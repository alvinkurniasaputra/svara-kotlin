package com.zamrud.radio.mobile.app.svara.apiclient.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.AccountStats

/**
 * Created by fahziar on 06/04/2016.
 */
class AccountStatsResponse : BaseModel() {
    @SerializedName("message")
    @Expose
    private lateinit var mAccountStats: AccountStats

    fun getAccountStats(): AccountStats {
        return mAccountStats
    }

    fun setAccountStats(accountStats: AccountStats) {
        mAccountStats = accountStats
    }
}