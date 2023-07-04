package com.zamrud.radio.mobile.app.svara.helper.image

import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask

import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.zamrud.radio.mobile.app.svara.Realm.RealmMenu

import java.util.concurrent.ExecutionException

class DownloadMenuIconAsync(urls: List<String>, listener: DownloadImageListener) : AsyncTask<String?, Int?, Boolean>() {
    private var listener: DownloadImageListener
    private var urls: List<String>

    interface DownloadImageListener {
        fun onComplete()
        fun getContext(): Context
    }

    init {
        this.listener = listener
        this.urls = urls
    }

    override fun doInBackground(vararg strings: String?): Boolean {
        for (url in urls) {
            val futureTarget: FutureTarget<Bitmap> = Glide.with(listener.getContext()!!).asBitmap().load(url).submit()
            var bitmap: Bitmap? = null
            try {
                bitmap = futureTarget.get()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            if (bitmap != null)
                RealmMenu.saveImageMenu(url, bitmap)
        }
        return true
    }


    override fun onCancelled() {
        super.onCancelled()
        listener.onComplete()
    }

    override fun onPostExecute(aBoolean: Boolean?) {
        listener.onComplete()
    }

}