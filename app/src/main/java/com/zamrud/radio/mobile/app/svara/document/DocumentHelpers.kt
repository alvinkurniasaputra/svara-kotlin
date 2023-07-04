package com.zamrud.radio.mobile.app.svara.document

import android.os.Environment
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Document
import com.zamrud.radio.mobile.app.svara.helper.DocumentChooser
import java.io.File

class DocumentHelpers {
    companion object {
        fun isFileExist(d: Document): Boolean {
            return getFile(d).exists()
        }

        fun isImage(d: Document): Boolean {
            return when (d.getContainer()!!.lowercase()) {
                DocumentChooser.JPG,
                DocumentChooser.JPEG,
                DocumentChooser.PNG -> true
                else -> false
            }
        }

        fun getDocIcon(ext: String): Int {
            return when (ext.lowercase()) {
                DocumentChooser.JPG,
                DocumentChooser.JPEG,
                DocumentChooser.PNG -> -1 // return -1 mean for display the image
                DocumentChooser.DOCX -> R.drawable.ic_microsoft_word
                DocumentChooser.XLS,
                DocumentChooser.XLSX -> R.drawable.ic_microsoft_excel
                DocumentChooser.PPT,
                DocumentChooser.PPTX -> R.drawable.ic_microsoft_powerpoint
                DocumentChooser.CSV -> R.drawable.ic_csv
                DocumentChooser.PDF -> R.drawable.ic_pdf
                DocumentChooser.TXT -> R.drawable.ic_txt_file
                DocumentChooser.JSON -> R.drawable.ic_document
                else -> R.drawable.ic_document
            }
        }

        fun getFile(d: Document): File {
            val dirCheck: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            return File(dirCheck, d.getName()
                        + "-"
                        + d.getOwner()!!.getUsername()
                        + "."
                        + d.getContainer()
            )
        }
    }
}