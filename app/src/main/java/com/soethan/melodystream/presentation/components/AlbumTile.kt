package com.soethan.melodystream.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.soethan.melodystream.SongMediaItem
import com.soethan.melodystream.data.model.AlbumInfo

@Composable
fun AlbumTile(
    modifier: Modifier = Modifier,
    data: AlbumInfo
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        AsyncImage(
            model = data.coverArt,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(96.dp)
                .height(96.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = data.albumName, overflow = TextOverflow.Ellipsis,style = TextStyle(
            fontWeight = FontWeight.Bold,
        ))
        Text(text = "${data.numberOfSongs} Tracks", overflow = TextOverflow.Ellipsis)
    }
}