package com.zamrud.radio.mobile.app.svara.apiclient.model.curation

import android.os.Parcel
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.parceler.ParcelConverter

/**
 * Created by fahziar on 08/10/2016.
 */
class DataFilterGSONConverter : ParcelConverter<JsonObject?> {
    override fun toParcel(input: JsonObject?, parcel: Parcel?) {
        if (input != null) {
            parcel?.writeString(input.toString())
        } else {
            parcel?.writeString("")
        }
    }

    override fun fromParcel(parcel: Parcel): JsonObject? {
        val json: String? = parcel.readString()
        return if (json!!.length > 0) {
            val parser = JsonParser()
            parser.parse(json) as JsonObject
        } else {
            null
        }
    }
}