package com.zamrud.radio.mobile.app.svara.Realm

import android.content.Context

import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream

import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by solusi247 on 20/03/17.
 */

class InitConfiguration(context: Context) {

    private val realmName = "svara_data.realm"
    private val schemaVersion = 1.9.toLong()

    companion object {
        fun initRealmConfig(context: Context): InitConfiguration {
            return InitConfiguration(context)
        }
    }

    init {
        Realm.init(context)
        val key = ByteArray(64)
        val input: InputStream = ByteArrayInputStream("SVARA".toByteArray())
        try {
            input.read(key)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val config: RealmConfiguration = RealmConfiguration.Builder()
            .name(realmName)
            .encryptionKey(key)
            .schemaVersion(schemaVersion)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

}
