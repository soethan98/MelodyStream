package com.soethan.melodystream.presentation.components

import android.content.ContentUris
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.soethan.melodystream.AudioInfo
import com.soethan.melodystream.R
import com.soethan.melodystream.presentation.model.UiSongInfo

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun SongListTitle(song: UiSongInfo, modifier: Modifier = Modifier) {
    var showBottomSheet by remember {
        mutableStateOf(false)
    }


    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        GlideImage(
//            model = Uri.parse("content://media/external/audio/albumart/15"),
//            contentDescription = null,
//            modifier = Modifier
//                .width(50.dp)
//                .height(50.dp)
//                .clip(RoundedCornerShape(10.dp))
//        )
//        Image(
//            painter = rememberImagePainter(Uri.parse("content://media/external/audio/albumart/3"), builder = {
//                // Optional: Set Coil options as needed
//                placeholder(R.drawable.heart) // Set a placeholder image
//                error(R.drawable.earphones) // Set an error image
//                crossfade(true) // Enable crossfading for smoother transitions
//            }),
//            contentDescription = "Album cover art",
//            contentScale = ContentScale.Crop, // Adjust the content scale as needed
//        )
//
        AsyncImage(

            model =
            ImageRequest.Builder(LocalContext.current)
                .data(song.songInfo.albumArtCover)
                .error(R.drawable.heart)
                .placeholder(R.drawable.heart)

                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
        )



        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = song.songInfo.title, overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
                ),
                maxLines = 1
            )

            Text(
                text = "${song.songInfo.artist} | ${song.songInfo.albumName}",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(imageVector = Icons.Default.MoreVert,
            contentDescription = "more_menu",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    showBottomSheet = !showBottomSheet
                })
    }

    if (showBottomSheet) {
        SortMenu()
    }
}