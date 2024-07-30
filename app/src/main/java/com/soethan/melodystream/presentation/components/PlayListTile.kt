package com.soethan.melodystream.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.soethan.melodystream.R


@Composable
fun FavoritePlayListTile(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(150.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))) {
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentScale = ContentScale.Crop,
                contentDescription = "favorite_playlist"
            )

            Row(modifier = Modifier.fillMaxWidth().background(Color.Magenta.copy(alpha = 0.5f)).align(Alignment.BottomCenter).padding(8.dp), verticalAlignment = Alignment.Bottom){
                Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "favorite_border")
                Text(text = "Favorites")
            }
        }
        // Content inside the Box
    }
}