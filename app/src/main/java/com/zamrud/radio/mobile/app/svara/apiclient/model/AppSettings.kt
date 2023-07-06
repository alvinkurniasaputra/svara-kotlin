package com.zamrud.radio.mobile.app.svara.apiclient.model

import com.zamrud.radio.mobile.app.svara.BuildConfig
import com.zamrud.radio.mobile.app.svara.admob.AdTestDevicePerson
import com.zamrud.radio.mobile.app.svara.apiclient.model.apps.ChannelActionButton

/**
 * Created by Hinata on 1/18/2018.
 */

class AppSettings : BaseModel() {
    @JvmField
    var features: Features = Features()
    @JvmField
    var androidInfo: AndroidInfo = AndroidInfo()
    @JvmField
    var contents: Contents = Contents()
    @JvmField
    var naming: Naming? = Naming()
    @JvmField
    var actionButtons: ArrayList<ChannelActionButton> = ArrayList()
    @JvmField
    var contentsText: ContentsText = ContentsText()

    fun getContentsText(): ContentsText {
        return contentsText
    }

    fun setContentsText(contentsText: ContentsText) {
        this.contentsText = contentsText
    }

    fun getFeatures(): Features {
        return features
    }

    fun setFeatures(features: Features) {
        this.features = features
    }

    fun getAndroidInfo(): AndroidInfo {
        return androidInfo
    }

    fun setAndroidInfo(androidInfo: AndroidInfo) {
        this.androidInfo = androidInfo
    }

    fun getContents(): Contents {
        return contents
    }

    fun setContents(contents: Contents) {
        this.contents = contents
    }

    fun getNaming(): Naming {
        return if (naming == null)
            Naming()
        else naming!!
    }

    fun setNaming(naming: Naming) {
        this.naming = naming
    }

    fun getActionButtons(): ArrayList<ChannelActionButton> {
        return actionButtons
    }

    fun setActionButtons(actionButtons: ArrayList<ChannelActionButton>) {
        this.actionButtons = actionButtons
    }

    class Features {
        @JvmField
        var preferences: ArrayList<Preference> = ArrayList()
        @JvmField
        var disableAdMob = true
        @JvmField
        var enablePayment = false
        @JvmField
        var enableReferral = false
        @JvmField
        var disableSearch = false
        @JvmField
        var enableProfileMenu = false
        @JvmField
        var enablePrivateDoc = false
        @JvmField
        var enableArticle = false
        @JvmField
        var disableFirstTimeAutoPlay = true
        @JvmField
        var addToUpNextOption = false
        @JvmField
        var enableTheme = true
        @JvmField
        var enableTutorialMenu = false
        @JvmField
        var showDrawer = true
        @JvmField
        var showNotificationDrawer = true
        @JvmField
        var enableShadowTabMenu = false
        @JvmField
        var allowUserToCreateContent = false
        @JvmField
        var tutorialUrl = ""
        @JvmField
        var withWatermark = false
        @JvmField
        var enablePreference = false
        @JvmField
        var disableTakeOffline = false
        @JvmField
        var copyright: String? = ""
        @JvmField
        var allowUserToCreatePlaylist = false
        @JvmField
        var allowUserToCreateSeries = false
        @JvmField
        var allowUserToCreateRSS = false
        @JvmField
        var disableChangeLanguage = false
        @JvmField
        var allowUserToCreatePodcast = false
        @JvmField
        var enableVideo = false
        @JvmField
        var enableStayTunedButton = false
        @JvmField
        var disableScreenCapture = false
        @JvmField
        var enableRundown = false

        @JvmField
        var adMobTestDeviceId: ArrayList<AdTestDevicePerson> = ArrayList()
        @JvmField
        var baseShareUrl: String? = ""
        @JvmField
        var preferencesCurationPage: ArrayList<PreferencesCurationPage> = ArrayList()

        fun getPreferencesCurationPage(): ArrayList<PreferencesCurationPage> {
            return preferencesCurationPage
        }

        fun setPreferencesCurationPage(preferencesCurationPage: ArrayList<PreferencesCurationPage>) {
            this.preferencesCurationPage = preferencesCurationPage
        }

        fun getPreferences(): ArrayList<Preference> {
            return preferences
        }

        fun setPreferences(preferences: ArrayList<Preference>) {
            this.preferences = preferences
        }

        fun isDisableAdMob(): Boolean {
            return disableAdMob
        }

        fun setDisableAdMob(disableAdMob: Boolean) {
            this.disableAdMob = disableAdMob
        }

        fun getAdMobTestDeviceId(): ArrayList<AdTestDevicePerson> {
            return adMobTestDeviceId
        }

        fun setAdMobTestDeviceId(adMobTestDeviceId: ArrayList<AdTestDevicePerson>) {
            this.adMobTestDeviceId = adMobTestDeviceId
        }

        fun isEnablePayment(): Boolean {
            return enablePayment
        }

        fun setEnablePayment(enablePayment: Boolean) {
            this.enablePayment = enablePayment
        }

        fun isEnableReferral(): Boolean {
            return enableReferral
        }

        fun isDisableSearch(): Boolean {
            return disableSearch
        }

        fun setDisableSearch(disableSearch: Boolean) {
            this.disableSearch = disableSearch
        }

        fun setEnableReferral(enableReferral: Boolean) {
            this.enableReferral = enableReferral
        }

        fun isEnableProfileMenu(): Boolean {
            return enableProfileMenu
        }

        fun setEnableProfileMenu(enableProfileMenu: Boolean) {
            this.enableProfileMenu = enableProfileMenu
        }

        fun isEnablePrivateDoc(): Boolean {
            return enablePrivateDoc
        }

        fun setEnablePrivateDoc(enablePrivateDoc: Boolean) {
            this.enablePrivateDoc = enablePrivateDoc
        }

        fun isEnableArticle(): Boolean {
            return enableArticle
        }

        fun setEnableArticle(enableArticle: Boolean) {
            this.enableArticle = enableArticle
        }

        fun getBaseShareUrl(): String? {
            return baseShareUrl
        }

        fun setBaseShareUrl(baseShareUrl: String?) {
            this.baseShareUrl = baseShareUrl
        }

        fun isDisableFirstTimeAutoPlay(): Boolean {
            return disableFirstTimeAutoPlay
        }

        fun setDisableFirstTimeAutoPlay(disableFirstTimeAutoPlay: Boolean) {
            this.disableFirstTimeAutoPlay = disableFirstTimeAutoPlay
        }

        fun isAddToUpNextOption(): Boolean {
            return addToUpNextOption
        }

        fun setAddToUpNextOption(addToUpNextOption: Boolean) {
            this.addToUpNextOption = addToUpNextOption
        }

        fun isEnableTheme(): Boolean {
            return enableTheme
        }

        fun setEnableTheme(enableTheme: Boolean) {
            this.enableTheme = enableTheme
        }

        fun isEnableTutorialMenu(): Boolean {
            return enableTutorialMenu
        }

        fun setEnableTutorialMenu(enableTutorialMenu: Boolean) {
            this.enableTutorialMenu = enableTutorialMenu
        }

        fun getTutorialUrl(): String {
            return tutorialUrl
        }

        fun setTutorialUrl(tutorialUrl: String) {
            this.tutorialUrl = tutorialUrl
        }

        fun isShowDrawer(): Boolean {
            return showDrawer
        }

        fun setShowDrawer(showDrawer: Boolean) {
            this.showDrawer = showDrawer
        }

        fun isShowNotificationDrawer(): Boolean {
            return showNotificationDrawer
        }

        fun setShowNotificationDrawer(showNotificationDrawer: Boolean) {
            this.showNotificationDrawer = showNotificationDrawer
        }

        fun isEnableShadowTabMenu(): Boolean {
            return enableShadowTabMenu
        }

        fun setEnableShadowTabMenu(enableShadowTabMenu: Boolean) {
            this.enableShadowTabMenu = enableShadowTabMenu
        }

        fun isAllowUserToCreateContent(): Boolean {
            return allowUserToCreateContent
        }

        fun setAllowUserToCreateContent(allowUserToCreateContent: Boolean) {
            this.allowUserToCreateContent = allowUserToCreateContent
        }

        fun isAllowUserToCreateRSS(): Boolean {
            return allowUserToCreateRSS
        }

        fun setAllowUserToCreateRSS(allowUserToCreateRSS: Boolean) {
            this.allowUserToCreateRSS = allowUserToCreateRSS
        }

        fun isWithWatermark(): Boolean {
            return withWatermark
        }

        fun setWithWatermark(withWatermark: Boolean) {
            this.withWatermark = withWatermark
        }

        fun isEnablePreference(): Boolean {
            return enablePreference
        }

        fun setEnablePreference(enablePreference: Boolean) {
            this.enablePreference = enablePreference
        }

        fun isDisableTakeOffline(): Boolean {
            return disableTakeOffline
        }

        fun setDisableTakeOffline(disableTakeOffline: Boolean) {
            this.disableTakeOffline = disableTakeOffline
        }

        fun getCopyright(): String? {
            return copyright
        }

        fun setCopyright(copyright: String?) {
            this.copyright = copyright
        }

        fun isAllowUserToCreatePlaylist(): Boolean {
            return allowUserToCreatePlaylist
        }

        fun setAllowUserToCreatePlaylist(allowUserToCreatePlaylist: Boolean) {
            this.allowUserToCreatePlaylist = allowUserToCreatePlaylist
        }

        fun isAllowUserToCreateSeries(): Boolean {
            return allowUserToCreateSeries
        }

        fun setAllowUserToCreateSeries(allowUserToCreateSeries: Boolean) {
            this.allowUserToCreateSeries = allowUserToCreateSeries
        }

        fun isDisableChangeLanguage(): Boolean {
            return disableChangeLanguage
        }

        fun setDisableChangeLanguage(disableChangeLanguage: Boolean) {
            this.disableChangeLanguage = disableChangeLanguage
        }

        fun isAllowUserToCreatePodcast(): Boolean {
            return allowUserToCreatePodcast
        }

        fun setAllowUserToCreatePodcast(allowUserToCreatePodcast: Boolean) {
            this.allowUserToCreatePodcast = allowUserToCreatePodcast
        }

        fun isEnableVideo(): Boolean {
            return enableVideo
        }

        fun setEnableVideo(enableVideo: Boolean) {
            this.enableVideo = enableVideo
        }

        fun isEnableStayTunedButton(): Boolean {
            return enableStayTunedButton
        }

        fun setEnableStayTunedButton(enableStayTunedButton: Boolean) {
            this.enableStayTunedButton = enableStayTunedButton
        }

        fun isDisableScreenCapture(): Boolean {
            return disableScreenCapture
        }

        fun setDisableScreenCapture(disableScreenCapture: Boolean) {
            this.disableScreenCapture = disableScreenCapture
        }

        fun isEnableRundown(): Boolean {
            return enableRundown
        }

        fun setEnableRundown(enableRundown: Boolean) {
            this.enableRundown = enableRundown
        }
    }

    class AndroidInfo {
        @JvmField
        var appVersionName: String
        @JvmField
        var playStoreUrl: String
        @JvmField
        var forceUpdate: Boolean

        init {
            appVersionName = BuildConfig.VERSION_NAME
            forceUpdate = false
            playStoreUrl = ""
        }

        fun getAppVersionName(): String {
            return appVersionName
        }

        fun setAppVersionName(appVersionName: String) {
            this.appVersionName = appVersionName
        }

        fun getPlayStoreUrl(): String {
            return playStoreUrl
        }

        fun setPlayStoreUrl(playStoreUrl: String) {
            this.playStoreUrl = playStoreUrl
        }

        fun isForceUpdate(): Boolean {
            return forceUpdate
        }

        fun setForceUpdate(forceUpdate: Boolean) {
            this.forceUpdate = forceUpdate
        }
    }

    class Contents {
        @JvmField
        var document = false

        fun isDocument(): Boolean {
            return document
        }

        fun setDocument(document: Boolean) {
            this.document = document
        }
    }

    class PreferencesCurationPage {
        @JvmField
        var curationPageId = ""

        fun getCurationPageId(): String {
            return curationPageId
        }

        fun setCurationPageId(curationPageId: String) {
            this.curationPageId = curationPageId
        }
    }

     class Preference {
         @JvmField
         var title = ""
         @JvmField
         var type = ""
         @JvmField
         var viewType = ""
         @JvmField
         var filterBy = ""
         @JvmField
         var referTo: ArrayList<Preference> = ArrayList()

         fun getTitle(): String {
             return title
         }

         fun setTitle(title: String) {
             this.title = title
         }

         fun getType(): String {
             return type
         }

         fun setType(type: String) {
             this.type = type
         }

         fun getViewType(): String {
             return viewType
         }

         fun setViewType(viewType: String) {
             this.viewType = viewType
         }

         fun getFilterBy(): String {
             return filterBy
         }

         fun setFilterBy(filterBy: String) {
             this.filterBy = filterBy
         }

         fun getReferTo(): ArrayList<Preference> {
             return referTo
         }

         fun setReferTo(referTo: ArrayList<Preference>) {
             this.referTo = referTo
         }
    }

    class Naming {
        @JvmField
        var radioPage = RadioPage()
        @JvmField
        var shareText = ShareText()
        @JvmField
        var player: PlayerText? = PlayerText()
        @JvmField
        var libraryPage: LibraryPage? = LibraryPage()

        init {

        }

        fun getRadioPage(): RadioPage {
            return radioPage
        }

        fun setRadioPage(radioPage: RadioPage) {
            this.radioPage = radioPage
        }

        fun getShareText(): ShareText {
            return shareText
        }

        fun setShareText(shareText: ShareText) {
            this.shareText = shareText
        }

        fun getPlayer(): PlayerText {
            return if (player == null)
                PlayerText()
            else player!!
        }

        fun setPlayer(player: PlayerText) {
            this.player = player
        }

        fun getLibraryPage(): LibraryPage {
            return if (libraryPage == null)
                LibraryPage()
            else libraryPage!!
        }

        fun setLibraryPage(libraryPage: LibraryPage) {
            this.libraryPage = libraryPage
        }
    }

    class RadioPage {
        @JvmField
        var stayTune = "STAY TUNE"
        @JvmField
        var stayTuned = "STAY TUNED"

        fun getStayTune(): String {
            return stayTune
        }

        fun setStayTune(stayTune: String) {
            this.stayTune = stayTune
        }

        fun getStayTuned(): String {
            return stayTuned
        }

        fun setStayTuned(stayTuned: String) {
            this.stayTuned = stayTuned
        }
    }

    class LibraryPage {
        @JvmField
        var playlist = "Playlist"
        @JvmField
        var music = "Music"
        @JvmField
        var artist = "Artist"
        @JvmField
        var album = "Album"
        @JvmField
        var channel = "Channel"
        @JvmField
        var podcast = "Podcast"
        @JvmField
        var series = "Series"
        @JvmField
        var video = "Video"
        @JvmField
        var privateDoc = "Private Doc"
        @JvmField
        var offlineData = "Offline Data"
        @JvmField
        var privateRoom = "Private Room"

        fun getPlaylist(): String {
            return playlist
        }

        fun setPlaylist(playlist: String) {
            this.playlist = playlist
        }

        fun getMusic(): String {
            return music
        }

        fun setMusic(music: String) {
            this.music = music
        }

        fun getArtist(): String {
            return artist
        }

        fun setArtist(artist: String) {
            this.artist = artist
        }

        fun getAlbum(): String {
            return album
        }

        fun setAlbum(album: String) {
            this.album = album
        }

        fun getChannel(): String {
            return channel
        }

        fun setChannel(channel: String) {
            this.channel = channel
        }

        fun getPodcast(): String {
            return podcast
        }

        fun setPodcast(podcast: String) {
            this.podcast = podcast
        }

        fun getSeries(): String {
            return series
        }

        fun setSeries(series: String) {
            this.series = series
        }

        fun getVideo(): String {
            return video
        }

        fun setVideo(video: String) {
            this.video = video
        }

        fun getPrivateDoc(): String {
            return privateDoc
        }

        fun setPrivateDoc(privateDoc: String) {
            this.privateDoc = privateDoc
        }

        fun getOfflineData(): String {
            return offlineData
        }

        fun setOfflineData(offlineData: String) {
            this.offlineData = offlineData
        }

        fun getPrivateRoom(): String {
            return privateRoom
        }

        fun setPrivateRoom(privateRoom: String) {
            this.privateRoom = privateRoom
        }
    }

     class ShareText {
         @JvmField
         var music: String? = "This Music is for you.."
         @JvmField
         var account: String? = "This Account is for you.."
         @JvmField
         var album: String? = "This Album is for you.."
         @JvmField
         var artist: String? = "This Artist is for you.."
         @JvmField
         var radio: String? = "This Radio is for you.."
         @JvmField
         var radioContent: String? = "This Radio Content is for you.."
         @JvmField
         var playlist: String? = "This Playlist is for you.."
         @JvmField
         var upload: String? = "This Podcast is for you.."
         @JvmField
         var video: String? = "This Video is for you.."
         @JvmField
         var series: String? = "This Series is for you.."

         fun getMusic(): String {
             return if (music == null)
                 "This Music is for you.."
             else music!!
         }

         fun setMusic(music: String) {
             this.music = music
         }

         fun getAccount(): String {
             return if (account == null)
                 "This Account is for you.."
             else account!!
         }

         fun setAccount(account: String) {
             this.account = account
         }

         fun getAlbum(): String {
             return if (album == null)
                 "This Album is for you.."
             else album!!
         }

         fun setAlbum(album: String) {
             this.album = album
         }

         fun getArtist(): String {
             return if (artist == null)
                 "This Artist is for you.."
             else artist!!
         }

         fun setArtist(artist: String?) {
             this.artist = artist
         }

         fun getRadio(): String {
             return if (radio == null)
                 "This Radio is for you.."
             else radio!!
         }

         fun setRadio(radio: String) {
             this.radio = radio
         }

         fun getRadioContent(): String {
             return if (radioContent == null)
                 "This Radio Content is for you.."
             else radioContent!!
         }

         fun setRadioContent(radioContent: String) {
             this.radioContent = radioContent
         }

         fun getPlaylist(): String {
             return if (playlist == null)
                 "This Playlist is for you.."
             else playlist!!
         }

         fun setPlaylist(playlist: String) {
             this.playlist = playlist
         }

         fun getUpload(): String {
             return if (upload == null)
                 "This Podcast is for you.."
             else upload!!
         }

         fun setUpload(upload: String) {
             this.upload = upload
         }

         fun getVideo(): String {
             return if (video == null)
                 "This Video is for you.."
             else video!!
         }

         fun setVideo(video: String) {
             this.video = video
         }

         fun getSeries(): String {
             return if (series == null)
                 "This Series is for you.."
             else series!!
         }

         fun setSeries(series: String) {
             this.series = series
         }
    }

    class PlayerText {
        @JvmField
        var limitFeaturePlatinum = "This feature is limited to Platinum member only."
        @JvmField
        var limitFeaturePremium = "This feature is for Premium member."
        @JvmField
        var freePopupPlaylist = "You are currently playing this playlist in Shuffle mode. Avoid any restrictions with Platinum. Coming Soon."
        @JvmField
        var freePopupAlbum = "You are currently playing this album in Shuffle mode. Avoid any restrictions with Platinum. Coming Soon."
        @JvmField
        var freePopupArtist = "You are currently playing this Artist in Shuffle mode. avoid any restrictions with Platinum. Coming Soon."
        @JvmField
        var freePopupSingle = "You are currently playing this track mix. Avoid any restrictions with Platinum. Coming Soon."
        @JvmField
        var freePopupCuration = "You are currently playing curated list in Shuffle mode. Avoid any restriction with Platinum. Coming Soon."
        @JvmField
        var freePopupDefault = "You are currently playing in Shuffle mode. Avoid any restriction with Platinum. Coming Soon."

        fun getLimitFeaturePlatinum(): String {
            return limitFeaturePlatinum
        }

        fun setLimitFeaturePlatinum(limitFeaturePlatinum: String) {
            this.limitFeaturePlatinum = limitFeaturePlatinum
        }

        fun getLimitFeaturePremium(): String {
            return limitFeaturePremium
        }

        fun setLimitFeaturePremium(limitFeaturePremium: String) {
            this.limitFeaturePremium = limitFeaturePremium
        }

        fun getFreePopupPlaylist(): String {
            return freePopupPlaylist
        }

        fun setFreePopupPlaylist(freePopupPlaylist: String) {
            this.freePopupPlaylist = freePopupPlaylist
        }

        fun getFreePopupAlbum(): String {
            return freePopupAlbum
        }

        fun setFreePopupAlbum(freePopupAlbum: String) {
            this.freePopupAlbum = freePopupAlbum
        }

        fun getFreePopupArtist(): String {
            return freePopupArtist
        }

        fun setFreePopupArtist(freePopupArtist: String) {
            this.freePopupArtist = freePopupArtist
        }

        fun getFreePopupSingle(): String {
            return freePopupSingle
        }

        fun setFreePopupSingle(freePopupSingle: String) {
            this.freePopupSingle = freePopupSingle
        }

        fun getFreePopupCuration(): String {
            return freePopupCuration
        }

        fun setFreePopupCuration(freePopupCuration: String) {
            this.freePopupCuration = freePopupCuration
        }

        fun getFreePopupDefault(): String {
            return freePopupDefault
        }

        fun setFreePopupDefault(freePopupDefault: String) {
            this.freePopupDefault = freePopupDefault
        }
    }

    class ContentsText {
        @JvmField
        var music = "Music"
        @JvmField
        var radio = "Radio"
        @JvmField
        var radioContent = "Radio Content"
        @JvmField
        var playlist = "Playlist"
        @JvmField
        var artist = "Artist"
        @JvmField
        var album = "Album"
        @JvmField
        var upload = "Podcast"
        @JvmField
        var account = "Account"

        init {
        }

        fun getMusic(): String {
            return music
        }

        fun setMusic(music: String) {
            this.music = music
        }

        fun getRadio(): String {
            return radio
        }

        fun setRadio(radio: String) {
            this.radio = radio
        }

        fun getRadioContent(): String {
            return radioContent
        }

        fun setRadioContent(radioContent: String) {
            this.radioContent = radioContent
        }

        fun getPlaylist(): String {
            return playlist
        }

        fun setPlaylist(playlist: String) {
            this.playlist = playlist
        }

        fun getArtist(): String {
            return artist
        }

        fun setArtist(artist: String) {
            this.artist = artist
        }

        fun getAlbum(): String {
            return album
        }

        fun setAlbum(album: String) {
            this.album = album
        }

        fun getUpload(): String {
            return upload
        }

        fun setUpload(upload: String) {
            this.upload = upload
        }

        fun getAccount(): String {
            return account
        }

        fun setAccount(account: String) {
            this.account = account
        }
    }
}