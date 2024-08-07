package com.soethan.melodystream.presentation.components

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.soethan.melodystream.SongMediaItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListTitle(song: SongMediaItem, modifier: Modifier = Modifier) {
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        AsyncImage(
            model = "https://images.unsplash.com/photo-1500462918059-b1a0cb512f1d?q=80&w=2187&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)){
            Text(text = song.title, overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
            ))

            Text(text = "${song.artist} | ${song.album}",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp)
        }

        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more_menu",modifier = Modifier
            .size(24.dp)
            .clickable {
                showBottomSheet = !showBottomSheet
            })
    }

    if (showBottomSheet){
        SortMenu()
    }
}