package com.zamrud.radio.mobile.app.svara.Player.listener

import android.support.v4.media.session.MediaControllerCompat
import com.zamrud.radio.mobile.app.svara.BaseActivity
import com.zamrud.radio.mobile.app.svara.Player.Model.PlayableModel
//import com.zamrud.radio.mobile.app.svara.Player.Svara.SvaraMediaPlayer

interface PlayerFragmentRequest {
//    fun getSvaraMediaPlayer(): SvaraMediaPlayer?
    fun getController(): MediaControllerCompat?
    fun requestBaseActivity(): BaseActivity
    fun getPlayingType(): Int
    fun getPlayingId(): String?
    fun canSeek(): Boolean
    fun requestPosition(position: Int)
    fun requestPlay(position: Int)
    fun requestPlay(id: String)
    fun requestPlayerState()
    fun addSvaraPlayerListener(svaraPlayerListener: SvaraPlayerListener)
    fun removeSvaraPlayerListener(svaraPlayerListener: SvaraPlayerListener)
    fun getPlaying(): PlayableModel?
    fun getQueue(): ArrayList<PlayableModel>
}