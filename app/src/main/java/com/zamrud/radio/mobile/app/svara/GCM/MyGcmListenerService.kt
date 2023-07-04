package com.zamrud.radio.mobile.app.svara.GCM

import android.content.Intent
import android.net.Uri
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.application.isradeleon.notify.Notify
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import com.zamrud.radio.mobile.app.svara.apiclient.ResponseHelper
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Account
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.GcmMessage
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.GcmNotif
import com.zamrud.radio.mobile.app.svara.apiclient.services.AccountsService
import com.zamrud.radio.mobile.app.svara.helper.NotificationHelpers
import java.lang.reflect.Type

class MyGcmListenerService : FirebaseMessagingService() {
    companion object {
        private const val TAG = "MyGcmListenerService"
        const val KEY_NOTIFICATION_GROUP = "svara.notification.group"
        const val CHANNEL_ID = "svara.channel.id"

        const val KEY_FROM_NOTIFICATION = "actionFromNotification"
        const val KEY_CONTENT_TYPE = "contentType"
        const val KEY_EVENT_TYPE = "eventType"
        const val KEY_MESSAGE = "message"
        const val KEY_CONTENT_ID = "contentId"
    }

    private lateinit var broadcaster: LocalBroadcastManager

    override fun onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this)
    }

    override fun onNewToken(token: String) {
        AuthenticationUtils.sendInstallationIfNeeded(token)
    }

    // [START receive_message]
    override fun onMessageReceived(message: RemoteMessage) {
        showNotification(message)
        /*
          TODO: handle if need specific with topics
        String from = message.getFrom();

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal.
        }
        */
    }

    private fun showNotification(message: RemoteMessage) {
        val notification:RemoteMessage.Notification? = message.notification
        if (notification == null || notification.title == null || notification.title!!.length < 1) {
            sendNotification(message)
            return
        }
        val channelId = getString(R.string.app_name) + ".information"
        val title: String? = notification.title
        val body: String? = notification.body
        val icon: String? = notification.icon
        val image: Uri? = notification.imageUrl

        val notify: Notify = Notify.build(this)

        notify.setChannelId(channelId)
        notify.setSmallIcon(R.drawable.notif_icon)
        notify.setColor(R.color.svara_blue)

        notify.setTitle(title ?: getString(R.string.app_name))
        if (body != null)
            notify.setContent(body)

        if (icon != null) {
            notify.setLargeIcon(icon)
            notify.largeCircularIcon()
        }

        if (image != null)
            notify.setPicture(image.toString())
        notify.setAction(NotificationHelpers.create().createIntent(message.data, this))
        notify.setAutoCancel(true)
        notify.show()

        //intent to add notif count
        val intent = Intent("MyData")
        broadcaster.sendBroadcast(intent)
    }

    /**
     * handle old notification data
     * in another word the RemoteMessage data without Notification field
     *
     * @param message
     */
    private fun sendNotification(message: RemoteMessage) {
        val data: Map<String?, String?> = message.data

        val gcmNotif = GcmNotif()
        gcmNotif.setSent_time(message.sentTime.toInt())
        gcmNotif.setCollapse_key(message.collapseKey)
        gcmNotif.setMessage_id(message.collapseKey)

        val gson = Gson()
        val listType: Type = object : TypeToken<ArrayList<GcmMessage?>?>() {}.type
        val messages: ArrayList<GcmMessage> = gson.fromJson(data[GcmNotif.KEY_MESSAGE] as String, listType)
        gcmNotif.setMessage(messages)

        val messageList: ArrayList<GcmMessage>? = gcmNotif.getMessage()
        for (gcmMessage in messageList!!) {
            val channelId = getString(R.string.app_name) + ".information"
            val title = gcmMessage.getUsername() + " " + gcmMessage.getTitle()

            val notify: Notify = Notify.build(this)

            notify.setChannelId(channelId)
            notify.setSmallIcon(R.drawable.notif_icon)
            notify.setColor(R.color.svara_blue)
            notify.setTitle(title)
            notify.setAction(NotificationHelpers.create().createIntentGcmMessage(gcmMessage, this)!!)
            notify.setAutoCancel(true)

            if (AuthenticationUtils.isLoggedIn(applicationContext)) {
                val accountsService: AccountsService = ServiceGenerator.createServiceWithAuth(AccountsService::class.java, applicationContext)
                accountsService.getProfile(gcmMessage.getAccountId())
                    .enqueue(object : ResponseHelper<Account?>() {
                        override fun onSuccess(body: Account?) {
                            notify.largeCircularIcon()
                            notify.setLargeIcon(body?.getImages()?.getImage150()!!)
                            notify.show()
                        }

                        override fun onError(message: String?) {
                            notify.show()
                        }
                    })
            }
        }


        //intent to add notif count
        val intent = Intent("MyData")
        broadcaster.sendBroadcast(intent)
    }
}