package com.zamrud.radio.mobile.app.svara

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator

/**
 * Created by fahziar on 11/05/2016.
 */
class UrlUtil {
    companion object {
        fun verifyUrl(url: String?): Boolean {
//        Log.e("Url Util", url);
            if (url != null && url.isNotEmpty() && url != " ") {
                return true
            } else {
                Log.w("Url Util", "URL not valid:$url")
                return false
            }
        }

        /**
         * Append api URL.
         *
         * @param url normalized url
         * @return URL with api url appended. Will return null if input is null
         */
        fun appendApiUrl(url: String?): String? {
            if (url == null) {
                return null
            }

            val apiUrl: String = ServiceGenerator.getActiveApiURL()
            val out: String

            // a condition which add apiUrl/Base URL to string that doesn't start with "http"
            // this if-else was disabled because this was no longer needed on the new method
//        if (!url.startsWith("http")){
//
//            String imageUrl = url;
//            if (imageUrl.startsWith("/")){
//                imageUrl = imageUrl.substring(1);
//            }
//
//            out = apiUrl + imageUrl;
//        } else {
//            out = url;
//        }
            out = url
            return out
        }

        /**
         * Append api URL.
         *
         * @param url normalized url
         * @return URL with api url appended. Will return null if input is null
         */
        fun appendApiUrlAudio(url: String?): String? {
            if (url == null) {
                return null
            }

            val apiUrl: String = ServiceGenerator.getActiveApiURL()
            val out: String

            // a condition which add apiUrl/Base URL to string that doesn't start with "http"
            if (!url.startsWith("http")) {
                var imageUrl: String = url
                if (imageUrl.startsWith("/")) {
                    imageUrl = imageUrl.substring(1)
                }
                out = apiUrl + imageUrl
            } else {
                out = url
            }
            return out
        }

        /**
         * @param contentUri
         */
        fun getRealUri(context: Context, contentUri: Uri): String? {
            var path: String? = null
            val proj = arrayOf(MediaStore.MediaColumns.DATA)
            val cursor: Cursor? = context.contentResolver.query(contentUri, proj, null, null, null)
            if (cursor!!.moveToFirst()) {
                val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                path = cursor.getString(column_index)
            }
            cursor.close()
            return path
        }
    }
}