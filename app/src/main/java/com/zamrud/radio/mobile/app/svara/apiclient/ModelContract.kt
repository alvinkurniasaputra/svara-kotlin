package com.zamrud.radio.mobile.app.svara.apiclient

/**
 * Created by fahziar on 05/05/2016.
 */
class ModelContract {
    companion object {
        const val CATEGORY_MUSIC = 1
        const val CATEGORY_ARTIST = 2
        const val CATEGORY_ALBUM = 3
        const val CATEGORY_RADIO = 4
        const val CATEGORY_ACCOUNT = 5
        const val CATEGORY_UPLOAD = 6
        const val CATEGORY_PLAYLIST = 7
        const val CATEGORY_RADIO_CONTENT = 8
        const val CATEGORY_MEDIA = 9
        const val ACTION_ADD_TO_PLAYLIST = 10
        const val CATEGORY_TTX = 11
        const val CATEGORY_CURATION_PAGE = 12
        const val CATEGORY_TTX_TREE = 13
        const val CATEGORY_COMMENT = 14
        const val CATEGORY_SEARCH = 99
        const val CATEGORY_FEED = 20
        const val CATEGORY_URL = 21
        const val CATEGORY_FEED_COMMENT = 22
        const val CATEGORY_VIDEO = 23
        const val CATEGORY_DOCUMENT = 25
        const val CATEGORY_DM = 26
        const val CATEGORY_ARTICLE = 27
        const val CATEGORY_CLASS_ROOM = 28
        const val CATEGORY_LINK = 29
        const val CATEGORY_DIAL = 30

        // for offline data
        const val CATEGORY_OFFLINE = 24

        fun getSearchString(category: Int): String {
            return when (category) {
                CATEGORY_MUSIC -> "Music"
                CATEGORY_ARTIST -> "Artist"
                CATEGORY_ALBUM -> "Album"
                CATEGORY_RADIO -> "Radio"
                CATEGORY_ACCOUNT -> "Account"
                CATEGORY_UPLOAD -> "Upload"
                CATEGORY_PLAYLIST -> "Playlist"
                CATEGORY_RADIO_CONTENT -> "RadioContent"
                CATEGORY_TTX -> "TTX"
                CATEGORY_TTX_TREE -> "ttxtree"
                CATEGORY_FEED -> "Feed"
                CATEGORY_CURATION_PAGE -> "CurationPage"
                CATEGORY_COMMENT -> "Comment"
                CATEGORY_URL -> "URL"
                CATEGORY_FEED_COMMENT -> "FeedComment"
                CATEGORY_VIDEO -> "Video"
                CATEGORY_DOCUMENT -> "Document"
                CATEGORY_DM -> "DM"
                CATEGORY_ARTICLE -> "Article"
                CATEGORY_CLASS_ROOM -> "ClassRoom"
                CATEGORY_LINK -> "Link"
                CATEGORY_DIAL -> "Dial"
                else -> ""
            }
        }

        fun getCategoryInt(category: String?): Int {
            return when (category!!.lowercase()) {
                "music" -> CATEGORY_MUSIC
                "artist" -> CATEGORY_ARTIST
                "album" -> CATEGORY_ALBUM
                "radio" -> CATEGORY_RADIO
                "account" -> CATEGORY_ACCOUNT
                "upload" -> CATEGORY_UPLOAD
                "playlist" -> CATEGORY_PLAYLIST
                "radiocontent" -> CATEGORY_RADIO_CONTENT
                "ttxtree" -> CATEGORY_TTX_TREE
                "ttx" -> CATEGORY_TTX
                "feed" -> CATEGORY_FEED
                "curationpage" -> CATEGORY_CURATION_PAGE
                "comment" -> CATEGORY_COMMENT
                "url" -> CATEGORY_URL
                "feedcomment" -> CATEGORY_FEED_COMMENT
                "videoradio", "video" -> CATEGORY_VIDEO
                "document" -> CATEGORY_DOCUMENT
                "dm" -> CATEGORY_DM
                "article" -> CATEGORY_ARTICLE
                "classroom" -> CATEGORY_CLASS_ROOM
                "link" -> CATEGORY_LINK
                "dial" -> CATEGORY_DIAL
                else -> 0
            }
        }
    }
}