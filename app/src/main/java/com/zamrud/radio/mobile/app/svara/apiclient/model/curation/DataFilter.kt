package com.zamrud.radio.mobile.app.svara.apiclient.model.curation

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.parceler.Parcel
import org.parceler.ParcelPropertyConverter

/**
 * Created by fahziar on 13/04/2016.
 */
@Parcel
class DataFilter {
    @SerializedName("dataType")
    @Expose
    var mDataType = ""

    @SerializedName("dataId")
    @Expose
    var mDataId = ""

    @ParcelPropertyConverter(DataFilterGSONConverter::class)
    @SerializedName("dataProp")
    @Expose
    @JvmField
    var dataProp: JsonObject? = JsonObject()

    @SerializedName("searchParams")
    @Expose
    @JvmField
    var searchParams: DataFilterSearchParam? = null

    fun getDataType(): String {
        return mDataType
    }

    fun setDataType(dataType: String?) {
        mDataType = dataType!!
    }

    fun getDataId(): String {
        return mDataId
    }

    fun setDataId(dataId: String?) {
        mDataId = dataId!!
    }

    fun getDataProp(): JsonObject? {
        return dataProp
    }

    fun setOffset(offset: String?) {
        dataProp!!.addProperty("offset", offset)
    }

    fun setLimit(limit: String?) {
        dataProp!!.addProperty("limit", limit)
    }

    fun getSearchParams(): DataFilterSearchParam? {
        return searchParams
    }

    fun setSearchParams(searchParams: DataFilterSearchParam?) {
        this.searchParams = searchParams
    }

    /**
     * Return JSON representation of DataFilter object.
     *
     * This function is called by Retrofit
     * to serialize this object when querying web service. Currently Retrofit doesn't serialize
     * query using JSON, instead it call toString method.
     *
     * @return JSON representation of DataFilter object
     */
    override fun toString(): String {
        val gson: Gson = GsonBuilder().create()
        return gson.toJson(this)
    }
}