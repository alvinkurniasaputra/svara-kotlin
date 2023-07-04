package com.zamrud.radio.mobile.app.svara.apiclient.model.video

import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction

abstract class VideoModel : ModelWithAction() {
    companion object {
        const val VIDEO_OWNER_RADIO = "Radio"
        const val VIDEO_OWNER_ACCOUNT = "Account"
    }

    abstract fun getVideoUrl(): String?
}