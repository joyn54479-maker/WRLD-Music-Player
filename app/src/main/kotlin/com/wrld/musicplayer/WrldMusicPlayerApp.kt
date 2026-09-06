package com.wrld.musicplayer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WrldMusicPlayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
