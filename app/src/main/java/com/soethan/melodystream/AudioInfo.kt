package com.soethan.melodystream

import android.graphics.Bitmap
import android.net.Uri

data class AudioInfo(
    val uri: Uri,
    val displayName:String,
    val id:Long,
    val artist:String,
    val data:String,
    val duration:Int,
    val title:String,
    val albumArtCover:Bitmap?,
    val albumName:String

)