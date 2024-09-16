package com.soethan.melodystream.data.mapper

import com.soethan.melodystream.AudioInfo
import com.soethan.melodystream.data.model.SongInfo
import javax.inject.Inject

class AudioMapper @Inject constructor() {


    fun toSong(audio: AudioInfo): SongInfo {
        return with(audio) {
            SongInfo(
                uri = this.uri,
                title = this.title,
                albumName = this.albumName,
                albumArtCover = this.albumArtCover,
                duration = this.duration,
                artist = this.artist,
                id = this.id,
                displayName = this.displayName,
                data = this.data

            )
        }
    }
}