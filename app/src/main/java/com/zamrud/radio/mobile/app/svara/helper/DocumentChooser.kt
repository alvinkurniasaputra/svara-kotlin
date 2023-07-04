package com.zamrud.radio.mobile.app.svara.helper

import android.Manifest
import android.app.Activity
import com.obsez.android.lib.filechooser.ChooserDialog
import pl.tajchert.nammu.Nammu
import pl.tajchert.nammu.PermissionCallback
import java.io.File


class DocumentChooser(private val activity: Activity, private val documentPicked: OnDocumentPicked) {

    companion object {
        const val DOC = "doc"
        const val DOCX = "docx"
        const val XLS = "xls"
        const val XLSX = "xlsx"
        const val PPT = "ppt"
        const val PPTX = "pptx"
        const val CSV = "csv"
        const val PDF = "pdf"
        const val TXT = "txt"
        const val JSON = "json"
        const val JPG = "jpg"
        const val JPEG = "jpeg"
        const val PNG = "png"
    }

    interface OnDocumentPicked {
        fun onPicked(file: File?)
    }

    fun launchDocumentChooser() {
        Nammu.askForPermission(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), object : PermissionCallback {
            override fun permissionGranted() {
                showDocumentChooser()
            }
            override fun permissionRefused() {}
        })
    }

    private fun showDocumentChooser() {
        ChooserDialog(activity)
            .withStartFile(null)
            .withFilter(false, DOC, DOCX, XLS, XLSX, PPT, PPTX, CSV, PDF, TXT, JSON, JPG, JPEG, PNG)
            .withChosenListener { path, pathFile -> documentPicked.onPicked(pathFile) }
            .build()
            .show()
    }

}