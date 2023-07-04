package com.zamrud.radio.mobile.app.svara

import android.content.Context
import android.graphics.drawable.Drawable

import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.makeramen.roundedimageview.RoundedImageView
import de.hdodenhof.circleimageview.CircleImageView

class PlaceholderUtil {
    companion object {
        const val ICON_DOC = "doc"
        const val ICON_WORD = "word"
        const val ICON_EXCEL = "excel"
        const val ICON_PPT = "ppt"
        const val ICON_PDF = "pdf"
        const val ICON_CSV = "csv"
        const val ICON_TXT = "txt"

        private val docList = ArrayList(arrayListOf(ICON_DOC, ICON_WORD, ICON_EXCEL, ICON_PPT, ICON_PDF, ICON_CSV, ICON_TXT))

        fun load(context: Context, imagePH: String?, callbackPH: CallbackPH) {
            if (UrlUtil.verifyUrl(imagePH)) {
                Glide.with(context).load(imagePH).listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                        callbackPH.onFailed()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        callbackPH.onPlaceholderReady(resource)
                        return true
                    }
                }).preload()
            } else callbackPH.onFailed()
        }

        fun into(imgePh: String?, imageUrl: String?, image: ImageView) {
            into(image.context, imgePh, imageUrl, image)
        }

        fun into(context: Context, imgePh: String?, imageUrl: String?, image: ImageView) {
            if (isLocalImage(imageUrl!!)) {
                loadLocalImage(context, imageUrl, image)
                return
            }

            load(context, imgePh, object : CallbackPH {
                override fun onFailed() {
                    if (UrlUtil.verifyUrl(imageUrl))
                        Glide.with(context).load(imageUrl).into(image)
                }

                override fun onPlaceholderReady(placeholder: Drawable?) {
                    if (UrlUtil.verifyUrl(imageUrl))
                        if (image is CircleImageView || image is RoundedImageView) {
                            val requestOptions: RequestOptions = RequestOptions().placeholder(placeholder).dontAnimate().dontTransform()
                            Glide.with(context).load(imageUrl).apply(requestOptions).into(image)
                    } else {
                        val requestOptions: RequestOptions = RequestOptions().placeholder(placeholder).dontTransform()
                        Glide.with(context).load(imageUrl).apply(requestOptions).into(image)
                    }
                }
            })
        }

        fun into(context: Context, imgePh: String?, drawable: Int, imageUrl: String?, image: ImageView) {
            if (isLocalImage(imageUrl!!)) {
                loadLocalImage(context, imageUrl, image)
                return
            }

            load(context, imgePh!!, object : CallbackPH {
                override fun onFailed() {
                    if (UrlUtil.verifyUrl(imageUrl))
                        Glide.with(context).load(imageUrl).error(drawable).into(image)
                }

                override fun onPlaceholderReady(placeholder: Drawable?) {
                    if (UrlUtil.verifyUrl(imageUrl))
                        if (image is CircleImageView || image is RoundedImageView) {
                            val requestOptions: RequestOptions = RequestOptions().placeholder(placeholder).dontAnimate().dontTransform()
                            Glide.with(context).load(imageUrl).apply(requestOptions).into(image)
                    } else {
                        val requestOptions: RequestOptions = RequestOptions().placeholder(placeholder).dontTransform()
                        Glide.with(context).load(imageUrl).apply(requestOptions).into(image)
                    }
                }
            })
        }

        private fun isLocalImage(type: String): Boolean {
            return docList.contains(type)
        }

        private fun loadLocalImage(context: Context, type: String, image: ImageView) {
            if (image is CircleImageView || image is RoundedImageView) {
                val requestOptions: RequestOptions = RequestOptions().dontAnimate().dontTransform()
                Glide.with(context).load(getLocalImage(type)).apply(requestOptions).into(image)
            } else {
                val requestOptions: RequestOptions = RequestOptions().dontTransform()
                Glide.with(context).load(getLocalImage(type)).apply(requestOptions).into(image)
            }
        }

        private fun getLocalImage(type: String): Int {
            return when (type) {
                ICON_WORD -> R.drawable.ic_microsoft_word
                ICON_EXCEL -> R.drawable.ic_microsoft_excel
                ICON_PPT -> R.drawable.ic_microsoft_powerpoint
                ICON_CSV -> R.drawable.ic_csv
                ICON_PDF -> R.drawable.ic_pdf
                ICON_TXT -> R.drawable.ic_txt_file
                ICON_DOC -> R.drawable.ic_document
                else -> R.drawable.ic_document
            }
        }
    }

    interface CallbackPH {
        fun onFailed()
        fun onPlaceholderReady(placeholder: Drawable?)
    }
}