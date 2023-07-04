package com.zamrud.radio.mobile.app.svara.helper

import android.content.Context
import android.telephony.TelephonyManager
import java.util.Locale

class LocaleHelper {
    companion object {
        fun getCountryId(context: Context): String {
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val countryCodeValue = tm.networkCountryIso
            val sim = tm.simCountryIso

            if (sim != null && sim.length == 2)
                return sim

            if (countryCodeValue != null && countryCodeValue.length == 2)
                return countryCodeValue

            return Locale.getDefault().country
        }
    }
}