package com.zamrud.radio.mobile.app.svara.apiclient.countly

import com.zamrud.radio.mobile.app.svara.SvaraApplication
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import ly.count.android.sdk.Countly

class OpenPage {
    companion object {
        const val PAGE_ACTIVITIES = "activities"
        const val PAGE_VIDEO_PROGRESS = "video progress"
        const val PAGE_ALBUM = "album"
        const val PAGE_ALBUM_EDIT = "edit album"
        const val PAGE_ARTIST = "artist"
        const val PAGE_ARTIST_EDIT = "edit artist"
        const val PAGE_SOCIAL_COMMENT = "social comment"
        const val PAGE_SOCIAL = "social"
        const val PAGE_CONTENT_COMMENT = "content comment"
        const val PAGE_DIRECT_MESSAGE = "direct message"
        const val PAGE_CREATE_DOCUMENT = "create document"
        const val PAGE_CREATE_ARTICLE = "create article"
        const val PAGE_EDIT_DOCUMENT = "edit document"
        const val PAGE_EDIT_ARTICLE = "edit article"
        const val PAGE_DOCUMENT = "document"
        const val PAGE_CURATION = "curation"
        const val PAGE_CURATION_MORE = "curation more"
        const val PAGE_HOME = "home"
        const val PAGE_LIBRARY_DETAIL = "library detail"
        const val PAGE_LIBRARY = "library"
        const val PAGE_MEMBERSHIP = "membership"
        const val PAGE_MUSIC = "music"
        const val PAGE_NOTIFICATION = "notification"
        const val PAGE_OFFLINE_DATA = "offline data"
        const val PAGE_PLAYER = "player"
        const val PAGE_VIDEO_FULL_SCREEN = "video player full screen"
        const val PAGE_VIDEO_PLAYER = "video player"
        const val PAGE_PLAYLIST_CREATE = "create playlist"
        const val PAGE_PLAYLIST_EDIT = "edit playlist"
        const val PAGE_PLAYLIST = "playlist"
        const val PAGE_PODCAST = "podcast"
        const val PAGE_RADIO = "radio"
        const val PAGE_RSS = "rss"
        const val PAGE_SEARCH = "search"
        const val PAGE_SEARCH_DETAIL = "search page"
        const val PAGE_TAB_MENU = "tab menu"
        const val PAGE_SERIES = "series"
        const val PAGE_SETTING = "setting app"
        const val PAGE_SETTING_USER = "setting user"
        const val PAGE_SHARE = "share"
        const val PAGE_TnP = "terms and policies"
        const val PAGE_THEME = "theme"
        const val PAGE_FOLLOWING = "following"
        const val PAGE_USER_DOCUMENT = "user document"
        const val PAGE_USER_PROFILE = "user profile"
        const val PAGE_WEB_VIEW = "web view"
        const val PAGE_ARTICLE = "article"
        const val PAGE_DRAWER = "drawer"
        const val PAGE_SWITCH_ACCOUNT = "switch account"
    }

    var pageName: String? = null
    var isSent = false
    var segmentation: HashMap<String, Any>

    constructor(pageName: String): this(pageName, "-", "-") {

    }
    constructor(pageName: String, pageId: String): this(pageName, pageId, "-") {

    }
    constructor(pageName: String, pageId: String, pageAlias: String) {
        isSent = false
        this.pageName = pageName
        segmentation = HashMap()
        segmentation["appId"] = AuthenticationUtils.getLoggeInAppUserId(SvaraApplication.getAppContext())!!
        segmentation["platform"] = "android"
        segmentation["accountId"] = AuthenticationUtils.getLoggeInUserId(SvaraApplication.getAppContext())!!
        segmentation["page"] = pageName
        segmentation["pageId"] = pageId
        segmentation["pageAlias"] = pageAlias
    }

    fun send() {
        if (isSent)
            return

        try {
            Countly.sharedInstance().views().recordView(pageName, segmentation)
            isSent = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}