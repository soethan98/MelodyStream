package com.soethan.melodystream.media.model

import android.graphics.Bitmap
import android.net.Uri

data class Track(
    val uri: Uri,
    val displayName:String,
    val id:Long,
    val artistId:Long,
    val artistName:String,
    val data:String,
    val duration:Int,
    val title:String,
    val albumArtCover: Bitmap?,
    val albumId:Long,
    val albumName:String,

)


