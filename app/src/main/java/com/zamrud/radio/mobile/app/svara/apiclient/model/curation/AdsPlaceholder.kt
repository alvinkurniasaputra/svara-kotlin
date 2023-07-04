package com.zamrud.radio.mobile.app.svara.apiclient.model.curation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by solusi247 on 28/02/17.
 */
class AdsPlaceholder {
    @SerializedName("id")
    @Expose
    private var mId: String? = null

    @SerializedName("zones")
    @Expose
    private var mZones: Zones? = null

    fun getId(): String? {
        return mId
    }

    fun setId(mId: String?) {
        this.mId = mId
    }

    fun getZones(): AdsPlaceholder.Zones? {
        return mZones
    }

    fun setmones(mZones: AdsPlaceholder.Zones?) {
        this.mZones = mZones
    }

    class Zones {
        @SerializedName("audio")
        @Expose
        private var mAudio: String? = null

        @SerializedName("banner")
        @Expose
        private var mBanner: String? = null

        fun getAudio(): String? {
            return mAudio
        }

        fun setAudio(mAudio: String?) {
            this.mAudio = mAudio
        }

        fun getBanner(): String? {
            return mBanner
        }

        fun setBanner(mBanner: String?) {
            this.mBanner = mBanner
        }
    }

}