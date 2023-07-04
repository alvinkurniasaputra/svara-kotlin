package com.zamrud.radio.mobile.app.svara

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


/**
 * Created by solusi247 on 28/03/17.
 */
class NetworkUtil {
    companion object {
        fun isOnline(): Boolean {
            val connMgr: ConnectivityManager = SvaraApplication.getAppContext()
                ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        fun isNetworkSupport(): Boolean {
            return isOnline()
        }
    }
}