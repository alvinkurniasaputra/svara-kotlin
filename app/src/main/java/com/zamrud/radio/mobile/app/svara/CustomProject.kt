package com.zamrud.radio.mobile.app.svara

/**
 * Created by irfan on 4/4/2018.
 * This file is created for parameters that become a refrence in custom projects.
 */


class CustomProject {

    companion object {
        var withSkip = true
        var autoSkip = false

        var canSignUp = true
        var loginOnlySocial = false

        /**
         * an option to enable or disable dynamic color which is an option for color to change dynamically based on radio that user play
         */
        var isDynamicColor = false

        /**
         * an option to enable or disable choosing from library when user create new post
         */
        var isPostFromLibrary = true

        /**
         * an option to hide or show item menu on navigation drawer
         */
        var isShowMembershipMenu = true
        var isShowAppSettingMenu = false
        var isMenuThemes = true

        /**
         * an option to hide or show button play on every curation section
         */
        var isShowButtonPlay = false

        /**
         * an option to determine the background that apps use is transparent or not
         */
        var isBackgroundTransparent = false

        /**
         * an option to force play radio streaming when open radio profile
         */
        var isRadioProfileForcePlay = false

        var isNoShuffle = false

        /**
         * if true, background chat will be blank, if false, the default will be radio image
         */
        var isDefBgChatBlank = false

        var isCurationHeaderClickable = false

        /**
         * if true, layout for player will use layout_chat2
         */
        var isChatPlayer2 = true

        /**
         * an option to make selectedTab has background Color
         */
        var isSelectedTabHasColor = true

        /**
         * an option to know list is transparent or not (if transparent will change the color of the text to black)
         */
        var isListThreeTransparent = false

        /**
         * an option for radio profile page
         */
        var PAGE_RADIO_PROFILE_1 = 1
        var PAGE_RADIO_PROFILE_2 = 2
        var PAGE_RADIO_PROFILE_3 = 3
        var PAGE_RADIO_PROFILE_4 = 4
        var pageRadioProfile = PAGE_RADIO_PROFILE_3

        /**
         * an option for artist page
         */
        var PAGE_ARTIST_ONE_PAGE = 1
        var PAGE_ARTIST_VIEW_PAGER = 2
        var pageArtist = PAGE_ARTIST_ONE_PAGE

        /**
         * an option for social page
         */
        var PAGE_SOCIAL_1 = 1
        var PAGE_SOCIAL_2 = 2
        var pageSocial = PAGE_SOCIAL_1

        /**
         * an option for theme
         */
        var LIGHT_THEME = "light"
        var DARK_THEME = "dark"
        var THEME = DARK_THEME
        var isMultiTheme = true
        var blackTextSystemUiOnLightTheme = true

        /**
         * an option for player
         */
        var PLAYER_CONTENT_CLEAN = 1
        var PLAYER_CONTENT_PROMINENT = 2
        var PLAYER_RADIO_CHAT = 3
        var PLAYER_RADIO_CLEAN = 4
        var playerContent = PLAYER_CONTENT_CLEAN
        var playerRadio = PLAYER_RADIO_CLEAN

        /**
         * option untuk tampilah halama User profile
         */
        var USER_PROFILE_SVARA = 1
        var USER_PROFILE_MARI = 2
        var USER_PROFILE_PAGE = USER_PROFILE_SVARA

        /**
         * option untuk fitur follow
         */
        var withFollowFeature = true

        /**
         * show Term Condition and Privacy Policy on Register
         */
        var registerShowTnC = false

        var loginPageUseLogo = true

        var useIntroStart = true

        var showVideoProgress = false

        /**
         * for animatino splashscreen
         * if true use animation in activity_prepare.xml with @drawable/sv_anim_1.xml
         */
        var useAnimateSplashScreen = false

        /**
         * show toolbar logo on left or not.
         * this use @drawable/toolbar_logo_on_dark or @drawable/toolbar_logo_on_dark
         * depend by theme with attr ?attr/toolbarLogo
         */
        var enableToolbarLogo = false

        /**
         * determine app to use bottom navigation or not
         */
        var useBottomNavigation = false

        /**
         * determine app to use default language
         * currently supported: null | "en" | "in"
         */
        var defaultLanguage: String? = null
    }

}
