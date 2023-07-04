package com.zamrud.radio.mobile.app.svara.apiclient.model.entity
//
//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName
//import com.zamrud.radio.mobile.app.svara.BaseActivity
//import com.zamrud.radio.mobile.app.svara.Player.Model.PlayableModel
//import com.zamrud.radio.mobile.app.svara.apiclient.BaseCallback
//import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
//import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
//import com.zamrud.radio.mobile.app.svara.apiclient.model.DataListModel
//import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
//import com.zamrud.radio.mobile.app.svara.apiclient.model.playlist.PlaylistsContentsResponse
//import com.zamrud.radio.mobile.app.svara.apiclient.model.video.Video
//import com.zamrud.radio.mobile.app.svara.apiclient.services.AlbumService
//import com.zamrud.radio.mobile.app.svara.apiclient.services.ArtistService
//import com.zamrud.radio.mobile.app.svara.apiclient.services.PlaylistService
//import com.zamrud.radio.mobile.app.svara.playlist.PlaylistKind
//import org.parceler.Parcel
//import retrofit2.Call
//import retrofit2.Response
//import timber.log.Timber
//import java.util.*
//
///**
// * Created by fahziar on 10/05/2016.
// */
//@Parcel
//class Feed : ModelWithAction() {
//    @SerializedName("caption")
//    @Expose
//    var caption: String? = ""
//
//    @SerializedName("reason")
//    @Expose
//    var reason: String? = ""
//
//    @SerializedName("image")
//    @Expose
//    var image: String? = ""
//
//    @SerializedName("type")
//    @Expose
//    var type: String? = ""
//
//    @SerializedName("isPrivate")
//    @Expose
//    var private = false
//
//    @SerializedName("contentType")
//    @Expose
//    var contentType: String? = ""
//
//    @SerializedName("id")
//    @Expose
//    override var id: String? = ""
//
//    @SerializedName("feedContentId")
//    @Expose
//    var feedContentId: String? = ""
//
//    @SerializedName("createdAt")
//    @Expose
//    var createdAt: Date?
//
//    @SerializedName("updatedAt")
//    @Expose
//    var updatedAt: String?
//
//    @SerializedName("accountId")
//    @Expose
//    var accountId: String?
//
//    @SerializedName("likesCount")
//    @Expose
//    var likesCount: Int
//
//    @SerializedName("commentsCount")
//    @Expose
//    var commentsCount: Int
//
//    @SerializedName("isLiked")
//    @Expose
//    var liked: Boolean
//
//    @SerializedName("playlist")
//    @Expose
//    var mPlaylist: Playlist?
//
//    @SerializedName("owner")
//    @Expose
//    var mOwner: Owner?
//
//    @SerializedName("music")
//    @Expose
//    var mMusic: Music?
//
//    @SerializedName("album")
//    @Expose
//    var mAlbum: Album?
//
//    @SerializedName("artist")
//    @Expose
//    var mArtist: Artist?
//
//    @SerializedName("radio")
//    @Expose
//    var mRadio: Radio?
//
//    @SerializedName("upload")
//    @Expose
//    var mUpload: Upload?
//
//    @SerializedName("account")
//    @Expose
//    var account: Account
//
//    @SerializedName("radioContent")
//    @Expose
//    var mRadioContent: RadioContent?
//
//    @SerializedName("video")
//    @Expose
//    var video: Video? = null
//
//    @SerializedName("article")
//    @Expose
//    var article: Article?
//
//    @SerializedName("document")
//    @Expose
//    var document: Document?
//
//    @SerializedName("images")
//    @Expose
//    override var images: Images? = null
//
//    init {
//        createdAt = Date()
//        updatedAt = ""
//        accountId = ""
//        likesCount = 0
//        commentsCount = 0
//        liked = false
//        mPlaylist = Playlist()
//        mOwner = Owner()
//        mMusic = Music()
//        mAlbum = Album()
//        mArtist = Artist()
//        mRadio = Radio()
//        mUpload = Upload()
//        mRadioContent = RadioContent()
//        account = Account()
//        article = Article()
//        document = Document()
//    }
//
//    fun nullify() {
//        caption = null
//        reason = null
//        image = null
//        type = null
//        contentType = null
//        id = null
//        feedContentId = null
//        createdAt = null
//        updatedAt = null
//        accountId = null
//        mPlaylist = null
//        mOwner = null
//        mMusic = null
//        mAlbum = null
//        mArtist = null
//        mRadio = null
//        mUpload = null
//        mRadioContent = null
//        images = null
//        article = null
//        document = null
//        baseNullify()
//    }
//
//    var playlist: Playlist?
//        get() = mPlaylist
//        set(playlist) {
//            mPlaylist = playlist
//        }
//
//    //        mMusic.setFree(true);
//    var music: Music?
//        get() =//        mMusic.setFree(true);
//            mMusic
//        set(music) {
//            mMusic = music
//        }
//
//    var owner: Owner?
//        get() = mOwner
//        set(owner) {
//            mOwner = owner
//        }
//    var album: Album?
//        get() = mAlbum
//        set(album) {
//            mAlbum = album
//        }
//    var artist: Artist?
//        get() = mArtist
//        set(artist) {
//            mArtist = artist
//        }
//    var radio: Radio?
//        get() = mRadio
//        set(radio) {
//            mRadio = radio
//        }
//    var upload: Upload?
//        get() = mUpload
//        set(mUpload) {
//            var mUpload: Upload? = mUpload
//            mUpload = mUpload
//        }
//    var radioContent: RadioContent?
//        get() = mRadioContent
//        set(radioContent) {
//            mRadioContent = radioContent
//        }
//
//    fun getVideo(): Video? {
//        return video
//    }
//
//    fun setVideo(video: Video?) {
//        this.video = video
//    }
//
//    fun getArticle(): Article? {
//        return article
//    }
//
//    fun setArticle(article: Article?) {
//        this.article = article
//    }
//
//    fun getDocument(): Document? {
//        return document
//    }
//
//    fun setDocument(document: Document?) {
//        this.document = document
//    }
//
//    override fun getImages(): Images {
//        return if (images == null) Images() else images
//    }
//
//    fun setImages(images: Images?) {
//        this.images = images
//    }
//
//    override val coverImageSmall: String
//        get() = getImages().getImage64()
//    override val coverImageMedium: String
//        get() = getImages().getImage150()
//    override val coverImageLarge: String
//        get() = getImages().getImage300()
//    override val coverImageExtraLarge: String
//        get() = getImages().getImage640()
//    override val wideCoverImageSmall: String
//        get() = getImages().getCoverImages().coverImage300
//    override val wideCoverImageMedium: String
//        get() = getImages().getCoverImages().coverImage640
//    override val wideCoverImageLarge: String
//        get() = getImages().getCoverImages().coverImage1280
//    override val coverImageWide: String
//        get() = getImages().getImagew640()
//    override val imageOri: String
//        get() = getImages().getImageOri()
//    override val placeholder: String
//        get() = getImages().getPlaceholder()
//
//    override fun onClick(activity: BaseActivity) {
//        when (ModelContract.getCategoryInt(contentType)) {
//            ModelContract.CATEGORY_PLAYLIST -> {
//                val mPlaylistService: PlaylistService = ServiceGenerator.createServiceWithAuth(
//                    PlaylistService::class.java, activity
//                )
//                val call: Call<List<PlaylistsContentsResponse>> =
//                    mPlaylistService.getPlaylistContents(
//                        playlist.getId(), 0, 50
//                    )
//                call.enqueue(object : BaseCallback<List<PlaylistsContentsResponse?>?>() {
//                    fun onSuccess(
//                        call: Call<List<PlaylistsContentsResponse?>?>?,
//                        response: Response<List<PlaylistsContentsResponse>>
//                    ) {
//                        if (response.body()!!.size > 0) {
//                            val playlistContent: List<PlaylistsContentsResponse> =
//                                response.body()!!
//                            var i = 0
//                            while (i < playlistContent.size) {
//                                var entry: ModelWithAction? = null
//                                when (ModelContract.getCategoryInt(playlistContent[i].getContentType())) {
//                                    ModelContract.CATEGORY_MUSIC -> entry =
//                                        playlistContent[i].getMusic()
//                                    ModelContract.CATEGORY_RADIO_CONTENT -> entry =
//                                        playlistContent[i].getRadioContent()
//                                    ModelContract.CATEGORY_UPLOAD -> entry =
//                                        playlistContent[i].getUpload()
//                                }
//                                entry!!.sourceContentType = SOURCE_CONTENT_PLAYLIST
//                                entry.sourceContentId = playlist.getId()
//                                i++
//                            }
//                        }
//                    }
//                })
//            }
//            ModelContract.CATEGORY_MUSIC -> {
//                val m: Music? = music
//                //                m.setFree(false);
//                m.setSourceContentType(SOURCE_CONTENT_FEED)
//                m.setSourceContentId("")
//                music.onClick(activity)
//                activity.Play(m)
//            }
//            ModelContract.CATEGORY_ALBUM -> {
//                val mService: AlbumService =
//                    ServiceGenerator.createServiceWithAuth(AlbumService::class.java, activity)
//                val apiCall: Call<DataListModel> = mService.getMusics(album.getId(), 0, 50)
//                apiCall.enqueue(object : BaseCallback<DataListModel?>() {
//                    fun onSuccess(call: Call<DataListModel?>?, response: Response<DataListModel>) {
//                        wrapPlay(activity, response.body().getDataList() as List<ModelWithAction>)
//                    }
//                })
//            }
//            ModelContract.CATEGORY_ARTIST -> {
//                val artistService: ArtistService = ServiceGenerator.createServiceWithAuth(
//                    ArtistService::class.java, activity
//                )
//                val artistCall: Call<DataListModel> =
//                    artistService.getTopMusics(artist.getId(), 0, 10)
//                artistCall.enqueue(object : BaseCallback<DataListModel?>() {
//                    fun onSuccess(call: Call<DataListModel?>?, response: Response<DataListModel>) {
//                        wrapPlay(activity, response.body().getDataList() as List<ModelWithAction>)
//                    }
//                })
//            }
//            ModelContract.CATEGORY_RADIO -> activity.Play(radio)
//            ModelContract.CATEGORY_UPLOAD -> activity.Play(upload)
//            ModelContract.CATEGORY_RADIO_CONTENT -> activity.Play(radioContent)
//            ModelContract.CATEGORY_ARTICLE -> article.onClick(activity)
//            ModelContract.CATEGORY_DOCUMENT -> if (document != null) document.onClick(activity)
//            else -> Timber.w("Content type not defined: %s", contentType)
//        }
//    }
//
//    private fun wrapPlay(activity: BaseActivity, datas: List<ModelWithAction>) {
//        val models: MutableList<PlayableModel> = ArrayList<PlayableModel>()
//        for (m in datas) {
//            m.sourceContentType = SOURCE_CONTENT_ARTIST
//            m.sourceContentId = artist.getId()
//            if (m is PlayableModel) models.add(m as PlayableModel)
//        }
//        activity.Play(models, 0)
//    }
//
//    val content: ModelWithAction?
//        get() = when (ModelContract.getCategoryInt(contentType)) {
//            ModelContract.CATEGORY_PLAYLIST -> playlist
//            ModelContract.CATEGORY_MUSIC -> music
//            ModelContract.CATEGORY_ALBUM -> album
//            ModelContract.CATEGORY_ARTIST -> artist
//            ModelContract.CATEGORY_RADIO -> radio
//            ModelContract.CATEGORY_UPLOAD -> upload
//            ModelContract.CATEGORY_RADIO_CONTENT -> radioContent
//            ModelContract.CATEGORY_VIDEO -> getVideo()
//            ModelContract.CATEGORY_ARTICLE -> getArticle()
//            ModelContract.CATEGORY_DOCUMENT -> getDocument()
//            else -> Loading()
//        }
//    val userContentType: String?
//        get() {
//            return when (ModelContract.getCategoryInt(contentType)) {
//                ModelContract.CATEGORY_RADIO_CONTENT, ModelContract.CATEGORY_UPLOAD -> "Podcast"
//                ModelContract.CATEGORY_PLAYLIST -> {
//                    if (playlist == null || playlist.getKind() == null) {
//                        return contentType
//                    }
//                    if (playlist.getKind().equals(PlaylistKind.PLAYLIST)) return "Playlist"
//                    if (playlist.getKind().equals(PlaylistKind.USER_SERIES)) return "Series"
//                    if (playlist.getKind().equals(PlaylistKind.RADIO_SERIES)) return "Series"
//                    if (playlist.getKind().equals(PlaylistKind.PROGRAM)) "Program" else contentType
//                }
//                else -> contentType
//            }
//        }
//}