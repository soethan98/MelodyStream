package com.soethan.melodystream.utils.extensions

import android.content.res.Resources
import com.soethan.melodystream.R

private var coverArtHeight: Int = 0
fun Resources.getCoverArtHeight(): Int {
    return if (coverArtHeight == 0) {
        getDimension(R.dimen.top_art_height).toInt()
    } else {
        coverArtHeight
    }
}