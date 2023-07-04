package com.zamrud.radio.mobile.app.svara.helper
//
//import android.Manifest
//import android.content.Context
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Build
//import android.os.Environment
//import android.provider.OpenableColumns
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.PickVisualMediaRequest
//import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
//import androidx.core.content.ContextCompat
//import androidx.core.content.FileProvider
//import androidx.fragment.app.Fragment
//import com.zamrud.radio.mobile.app.svara.BaseActivity
//import java.io.File
//import java.io.FileOutputStream
//import java.io.IOException
//import java.io.InputStream
//import java.text.SimpleDateFormat
//import java.util.*
//
//class ImageChooser @JvmOverloads constructor(
//    private val fragment: Fragment,
//    private var source: Source = Source.Both
//) {
//    private var reqCode = 0
//    protected var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>? = null
//    protected var takePicture: ActivityResultLauncher<Uri?>? = null
//    var requestPermissionLauncher: ActivityResultLauncher<Array<String>>? = null
//    protected var mCoverArtData: File? = null
//    protected var mCoverArtUri: Uri? = null
//
//    enum class Source {
//        Both, Gallery, Camera
//    }
//
//    interface OnImagePicked {
//        fun onPicked(file: File?, reqCode: Int)
//    }
//
//    fun launchImageChooser(baseActivity: BaseActivity, ImagePicked: OnImagePicked) {
//        launchImageChooser(baseActivity, 0, ImagePicked)
//    }
//
//    fun launchImageChooser(baseActivity: BaseActivity, reqCode: Int, ImagePicked: OnImagePicked) {
//        launchImageChooser(baseActivity, reqCode, source, ImagePicked)
//    }
//
//    fun launchImageChooser(
//        baseActivity: BaseActivity,
//        reqCode: Int,
//        source: Source,
//        ImagePicked: OnImagePicked
//    ) {
//        this.reqCode = reqCode
//        this.source = source
//        when (source) {
//            Source.Gallery -> pickMedia =
//                baseActivity.registerForActivityResult(PickVisualMedia()) { uri ->
//                    if (uri != null) {
//                        mCoverArtUri = uri
//                        try {
//                            mCoverArtData = getFile(baseActivity, mCoverArtUri)
//                        } catch (e: IOException) {
//                            e.printStackTrace()
//                        }
//                        ImagePicked.onPicked(mCoverArtData, reqCode)
//                    }
//                }
//            Source.Camera -> takePicture =
//                baseActivity.registerForActivityResult(TakePicture()) { isSuccess ->
//                    if (isSuccess) {
//                        try {
//                            mCoverArtData = getFile(baseActivity, mCoverArtUri)
//                        } catch (e: IOException) {
//                            e.printStackTrace()
//                        }
//                        ImagePicked.onPicked(mCoverArtData, reqCode)
//                    }
//                }
//            Source.Both -> {
//                pickMedia = baseActivity.registerForActivityResult(PickVisualMedia()) { uri ->
//                    if (uri != null) {
//                        mCoverArtUri = uri
//                        try {
//                            mCoverArtData =
//                                getFile(baseActivity, mCoverArtUri)
//                        } catch (e: IOException) {
//                            e.printStackTrace()
//                        }
//                        ImagePicked.onPicked(mCoverArtData, reqCode)
//                    }
//                }
//                takePicture = baseActivity.registerForActivityResult(TakePicture()) { isSuccess ->
//                    if (isSuccess) {
//                        try {
//                            mCoverArtData =
//                                getFile(baseActivity, mCoverArtUri)
//                        } catch (e: IOException) {
//                            e.printStackTrace()
//                        }
//                        ImagePicked.onPicked(mCoverArtData, reqCode)
//                    }
//                }
//            }
//            else -> {}
//        }
//        if ((ContextCompat.checkSelfPermission(
//                baseActivity,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            )
//                    != PackageManager.PERMISSION_GRANTED)
//            || (ContextCompat.checkSelfPermission(
//                baseActivity,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//            )
//                    != PackageManager.PERMISSION_GRANTED)
//        ) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requestPermissionLauncher!!.launch(
//                    arrayOf(
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                    )
//                )
//            } else showImageChooser()
//        } else {
//            showImageChooser()
//        }
//    }
//
//    fun requestPermissionsResult(grantResults: IntArray) {
//        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//            showImageChooser()
//        }
//    }
//
//    fun showImageChooser() {
//        when (source) {
//            Source.Gallery -> pickMedia!!.launch(
//                Builder()
//                    .setMediaType(ImageOnly)
//                    .build()
//            )
//            Source.Camera -> {
//                try {
//                    mCoverArtUri = FileProvider.getUriForFile(
//                        fragment.activity!!,
//                        "com.zamrud.radio.mobile.app.svara.provider",
//                        createImageFile()
//                    )
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//                takePicture!!.launch(mCoverArtUri)
//            }
//        }
//    }
//
//    @Throws(IOException::class)
//    fun createImageFile(): File {
//        val timeStamp =
//            SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        val imageFileName = "JPEG_" + timeStamp + "_"
//        val storageDir = Environment.getExternalStoragePublicDirectory(
//            Environment.DIRECTORY_PICTURES
//        )
//        return File.createTempFile(
//            imageFileName,  // prefix
//            ".jpg",  // suffix
//            storageDir // directory
//        )
//    }
//
//    companion object {
//        const val IMAGE_REQUEST_CODE = 8
//        @Throws(IOException::class)
//        fun getFile(context: Context, uri: Uri?): File {
//            val destinationFilename =
//                File(context.filesDir.path + File.separatorChar + queryName(context, uri))
//            try {
//                context.contentResolver.openInputStream(uri!!).use { ins ->
//                    createFileFromStream(
//                        ins!!, destinationFilename
//                    )
//                }
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//            return destinationFilename
//        }
//
//        fun createFileFromStream(ins: InputStream, destination: File?) {
//            try {
//                FileOutputStream(destination).use { os ->
//                    val buffer = ByteArray(4096)
//                    var length: Int
//                    while (ins.read(buffer).also { length = it } > 0) {
//                        os.write(buffer, 0, length)
//                    }
//                    os.flush()
//                }
//            } catch (ex: Exception) {
//                ex.printStackTrace()
//            }
//        }
//
//        private fun queryName(context: Context, uri: Uri?): String {
//            val returnCursor = context.contentResolver.query(uri!!, null, null, null, null)!!
//            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//            returnCursor.moveToFirst()
//            val name = returnCursor.getString(nameIndex)
//            returnCursor.close()
//            return name
//        }
//    }
//}