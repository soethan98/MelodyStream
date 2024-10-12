package com.soethan.melodystream.presentation.components

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.soethan.melodystream.SongMediaItem
import com.soethan.melodystream.data.model.ArtistInfo

@Composable
fun ArtistTile(data: ArtistInfo, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        SubcomposeAsyncImage(
            model = data.coverArt ?: data.defaultAlbumArtRes,
            contentDescription = null,
            contentScale = ContentScale.Crop, error = {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(
                            RoundedCornerShape(22.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "wmkl",
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                    )

                }

            },
            modifier = Modifier
                .width(96.dp)
                .height(96.dp)
                .clip(RoundedCornerShape(22.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = data.artistName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )
    }
}