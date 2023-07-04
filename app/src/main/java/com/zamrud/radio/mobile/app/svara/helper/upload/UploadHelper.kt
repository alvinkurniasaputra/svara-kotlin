package com.zamrud.radio.mobile.app.svara.helper.upload

abstract class UploadHelper {
//    var file: File? = null
//    var uploadResult: UploadResult? = null
//    private var disposable: Disposable? = null
//    var context: Context? = null
//    abstract fun start()
//    fun file(file: File?): UploadHelper {
//        this.file = file
//        return this
//    }
//
//    fun setUploadResult(uploadResult: UploadResult?): UploadHelper {
//        this.uploadResult = uploadResult
//        return this
//    }
//
//    abstract class UploadResult : BaseUploadResult {
//        override fun onSuccess(storageResponse: StorageResponse?) {}
//    }
//
//    interface BaseUploadResult {
//        fun onSuccess(fileUrl: String?)
//        fun onFailed()
//        fun onSuccess(storageResponse: StorageResponse?)
//    }
//
//    private val mimeType: String
//        private get() {
//            val fileNameMap = URLConnection.getFileNameMap()
//            return fileNameMap.getContentTypeFor(file!!.name)
//        }
//
//    fun checkFile() {
//        if (file == null) throw UnsupportedOperationException("ImageHelper trying call start() before call file()")
//    }
//
//    fun createProgressBody(emitter: FlowableEmitter<Double?>): ProgressUploadingRequest {
//        return ProgressUploadingRequest(
//            file,
//            parse.parse(mimeType)
//        ) { percentage, uploaded, total ->
//            val intent = Intent(EVENT_NAME)
//            intent.putExtra("progress", percentage)
//            if (percentage >= 100) intent.putExtra("message", "Processing")
//            LocalBroadcastManager.getInstance(context!!)
//                .sendBroadcast(intent)
//            emitter.onNext(1.0 * uploaded / total)
//        }
//    }
//
//    fun setDisposable(fileObservable: Flowable<Double?>) {
//        disposable = fileObservable.subscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ progress -> }, { throwable -> }) {}
//    }
//
//    val cleanFileName: String
//        get() = if (file == null) "" else file!!.name.replace("[^A-Za-z0-9].".toRegex(), "_")
//
//    companion object {
//        const val EVENT_NAME = "svara-upload-progress"
//        fun toImagePlaylist(context: Context?): ImagePlaylist {
//            return ImagePlaylist(context)
//        }
//
//        fun toImagePodcast(context: Context?): ImagePodcast {
//            return ImagePodcast(context)
//        }
//
//        fun toAudioPodcast(context: Context?): AudioPodcast {
//            return AudioPodcast(context)
//        }
//
//        fun toAlbum(context: Context?): ImageAlbum {
//            return ImageAlbum(context)
//        }
//
//        fun toArtist(context: Context?): ImageArtist {
//            return ImageArtist(context)
//        }
//
//        fun toProfile(context: Context?): ImageProfile {
//            return ImageProfile(context)
//        }
//
//        fun toImageFeed(context: Context?): ImageFeed {
//            return ImageFeed(context)
//        }
//
//        fun toCover(context: Context?): ImageCover {
//            return ImageCover(context)
//        }
//
//        fun toVideoCover(context: Context?, kind: String?): ImageVideo {
//            return ImageVideo(context, kind)
//        }
//
//        fun toVideoUploader(context: Context?): VideoFile {
//            return VideoFile(context)
//        }
//
//        fun toDocumentUploader(context: Context?): DocumentFile {
//            return DocumentFile(context)
//        }
//
//        fun toImageArticle(context: Context?): ImageArticle {
//            return ImageArticle(context)
//        }
//    }
}