package com.zamrud.radio.mobile.app.svara.helper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zamrud.radio.mobile.app.svara.GCM.MyGcmListenerService
import com.zamrud.radio.mobile.app.svara.Player.Utils.ActionUtil
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.GcmMessage
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.GcmNotif
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Notification
import com.zamrud.radio.mobile.app.svara.main.MainActivity
import java.lang.reflect.Type
import java.util.*


class NotificationHelpers {
    companion object {
        fun create(): NotificationHelpers {
            return NotificationHelpers()
        }
    }

    fun createIntent(data: Map<String?, String?>, context: Context): Intent {
        val intent = Intent()
        val bundle = Bundle()

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        if (!data.containsKey(MyGcmListenerService.KEY_CONTENT_TYPE) || data[MyGcmListenerService.KEY_EVENT_TYPE] == GcmNotif.ACTION_NONE)
            return intent

        intent.setClass(context, MainActivity::class.java)
        intent.putExtra(MyGcmListenerService.KEY_FROM_NOTIFICATION, true)

        if (data.containsKey("action")) {
            intent.action = GcmNotif.ACTION_OPEN_PAGE
            bundle.putString(ActionUtil.KEY_TYPE, GcmNotif.TYPE_NOTIFICATION)
        } else {
            intent.action = data[GcmNotif.ACTION_OPEN_PAGE]
            bundle.putString(ActionUtil.KEY_TYPE, data[MyGcmListenerService.KEY_CONTENT_TYPE])
        }

        bundle.putString(ActionUtil.KEY_ID, data[MyGcmListenerService.KEY_CONTENT_ID])
        intent.putExtras(bundle)

        return intent
    }

    /**
     * create intent action for GcmMessage notification data
     *
     * @return Intent
     */
    fun createIntentGcmMessage(gcmMessage: GcmMessage, context: Context): Intent? {
        val intent = Intent()
        val bundle = Bundle()

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.setClass(context, MainActivity::class.java)
        intent.putExtra(MyGcmListenerService.KEY_FROM_NOTIFICATION, true)

        if (gcmMessage.getContentType() == null
            || gcmMessage.getContentType()!!.length < 1
            || gcmMessage.getAction() == GcmNotif.ACTION_NONE)
            return intent

        val action: String= gcmMessage.getAction()!!.lowercase(Locale.getDefault())
        val actions: Set<String> = HashSet(arrayListOf(
                Notification.EVENT_LIKE,
                Notification.EVENT_COMMENT,
                Notification.EVENT_FOLLOW,
                Notification.EVENT_DIRECT))
        if (actions.contains(action)) {
            intent.action = GcmNotif.ACTION_OPEN_PAGE
        } else {
            intent.action = gcmMessage.getAction()
        }

        bundle.putString(ActionUtil.KEY_TYPE, gcmMessage.getContentType())
        bundle.putString(ActionUtil.KEY_ID, gcmMessage.getContentId())

        if (action == Notification.EVENT_FOLLOW)
            bundle.putString(ActionUtil.KEY_ID, gcmMessage.getAccountId())

        intent.putExtras(bundle)

        return intent
    }

    /**
     * handle data when app open from notification
     *
     * @param intent intent to next activity
     * @param bundle bundle from current activity
     */
    fun handleDataNotification(intent: Intent, bundle: Bundle) {
        val gson = Gson()
        val listType: Type = object : TypeToken<List<GcmMessage>>() {}.type
        val messages: List<GcmMessage> = gson.fromJson(bundle.get(GcmNotif.KEY_MESSAGE) as String, listType)
                ?: return

        intent.action = GcmNotif.ACTION_OPEN_PAGE
        if (messages.size > 1) {
            bundle.putString(ActionUtil.KEY_TYPE, GcmNotif.TYPE_NOTIFICATION)
            intent.putExtras(bundle)
            return
        }

        if (messages.size == 1) {
            val gcmMessage: GcmMessage = messages[0]
            val action = gcmMessage.getAction()!!.lowercase()
            val actions: Set<String> = HashSet(arrayListOf(
                    Notification.EVENT_LIKE,
                    Notification.EVENT_COMMENT,
                    Notification.EVENT_FOLLOW,
                    Notification.EVENT_DIRECT))
            if (actions.contains(action)) {
                intent.action = GcmNotif.ACTION_OPEN_PAGE
            } else {
                intent.action = gcmMessage.getAction()
            }

            bundle.putString(ActionUtil.KEY_TYPE, gcmMessage.getContentType())
            bundle.putString(ActionUtil.KEY_ID, gcmMessage.getContentId())


            if (action == Notification.EVENT_FOLLOW)
                bundle.putString(ActionUtil.KEY_ID, gcmMessage.getAccountId())

            intent.putExtras(bundle)
        }
    }

    fun handleNotificationAction(intent: Intent, bundle: Bundle) {
        bundle.putString(ActionUtil.KEY_TYPE, bundle.getString(MyGcmListenerService.KEY_CONTENT_TYPE))
        bundle.putString(ActionUtil.KEY_ID, bundle.getString(MyGcmListenerService.KEY_CONTENT_ID))
        intent.putExtras(bundle)

        intent.action = bundle.getString(MyGcmListenerService.KEY_EVENT_TYPE)
    }
}