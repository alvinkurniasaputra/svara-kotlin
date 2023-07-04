package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by irfan on 7/30/2018.
 */
@Parcel
class VerticalImage {
    @SerializedName("verticalImage720")
    @Expose
    @JvmField
    var verticalImage720 = ""

    fun getVerticalImage720(): String {
        return verticalImage720
    }

    fun setVerticalImage720(verticalImage720: String) {
        this.verticalImage720 = verticalImage720
    }
}