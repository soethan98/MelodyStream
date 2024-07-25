package com.soethan.melodystream

import android.net.Uri

data class Audio(
    val uri: Uri,
    val displayName:String,
    val id:Long,
    val artist:String,
    val data:String,
    val duration:Int,
    val title:String
)