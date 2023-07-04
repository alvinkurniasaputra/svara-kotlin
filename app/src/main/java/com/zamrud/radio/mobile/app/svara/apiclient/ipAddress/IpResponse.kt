package com.zamrud.radio.mobile.app.svara.apiclient.ipAddress

class IpResponse {
    @JvmField
    var ip: String? = null

    fun getIp(): String? {
        return ip
    }

    fun setIp(ip: String?) {
        this.ip = ip
    }
}