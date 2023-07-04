package com.zamrud.radio.mobile.app.svara.helper.adsId

import android.os.AsyncTask
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.zamrud.radio.mobile.app.svara.SvaraApplication
import java.io.IOException

class AdsHelper(adsCallback: AdsCallback) : AsyncTask<Void?, Void?, String?>() {
    companion object {
        private var aId: String? = null
    }
    private var adsCallback: AdsCallback? = adsCallback

    interface AdsCallback {
        fun onFinish()
    }

    override fun doInBackground(vararg voids: Void?): String? {
        return try {
            val info: AdvertisingIdClient.Info = AdvertisingIdClient.getAdvertisingIdInfo(SvaraApplication.getAppContext()!!)
            info.id
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
            null
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
            null
        }
    }

    override fun onPostExecute(s: String?) {
        aId = s
        adsCallback?.onFinish()
    }

    fun getAdsId(): String? {
        return aId
    }

}