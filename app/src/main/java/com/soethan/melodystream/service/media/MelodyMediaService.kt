package com.soethan.melodystream.service.media

import android.content.Intent
import android.os.Build
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.soethan.melodystream.service.notification.MelodyMediaNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



@AndroidEntryPoint
class MelodyMediaService  : MediaSessionService(){

    @Inject
    lateinit var mediaSession: MediaSession

    @Inject
    lateinit var notificationManager:MelodyMediaNotificationManager

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return  mediaSession
    }


    @UnstableApi
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.startNotificationService(
                mediaSession = mediaSession,
                mediaSessionService = this
            )
        }
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaSession.apply {
            release()
            if (player.playbackState != Player.STATE_IDLE){
                player.seekTo(0)
                player.playWhenReady = false
                player.stop()
            }
        }
    }


}