package com.zamrud.radio.mobile.app.svara.apiclient.model.analytics

import java.util.*

class UserActivities {
    @JvmField
    var count = 0
    @JvmField
    var totalCount = 0
    @JvmField
    var hasNext = false
    @JvmField
    var startDate: Date? = Date()
    @JvmField
    var endDate: Date? = Date()
    @JvmField
    var data: ArrayList<UserActivitiesData>? = null

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

    fun isHasNext(): Boolean {
        return hasNext
    }

    fun setHasNext(hasNext: Boolean) {
        this.hasNext = hasNext
    }

    fun getStartDate(): Date? {
        return startDate
    }

    fun setStartDate(startDate: Date?) {
        this.startDate = startDate
    }

    fun getEndDate(): Date? {
        return endDate
    }

    fun setEndDate(endDate: Date?) {
        this.endDate = endDate
    }

    fun getData(): ArrayList<UserActivitiesData>? {
        return data
    }

    fun setData(data: ArrayList<UserActivitiesData>?) {
        this.data = data
    }

    fun addData(data: UserActivitiesData?) {
        if (this.data == null)
            this.data = ArrayList()
        this.data!!.add(data!!)
    }
}