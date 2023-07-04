package com.zamrud.radio.mobile.app.svara.Player.listener

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.zamrud.radio.mobile.app.svara.Player.Model.PlayableModel

interface SvaraPlayerListener {
    fun onMetadataChange(metadata: MediaMetadataCompat)
    fun onPlaybackStateChange(state: PlaybackStateCompat)
    fun onProgress(current: Long)
    fun onQueueChange(playableModels: ArrayList<PlayableModel>)
    fun onTitleChange(title: String?)
    fun onCoverChange(cover: String?)
    fun onDurationChange(duration: Long)
    fun releaseVideo()
}