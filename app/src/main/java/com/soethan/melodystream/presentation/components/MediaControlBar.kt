package com.soethan.melodystream.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.soethan.melodystream.presentation.components.icons.NextIcon
import com.soethan.melodystream.presentation.components.icons.PlayIcon
import com.soethan.melodystream.presentation.components.icons.PreviousIcon
import com.soethan.melodystream.presentation.components.icons.RepeatIcon
import com.soethan.melodystream.presentation.components.icons.ShuffleIcon

@Composable
fun MediaControlBar(modifier: Modifier = Modifier) {

    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        IconButton(onClick = {},modifier = Modifier.size(30.dp)) {
            Icon(imageVector = ShuffleIcon(), tint = Color.White, contentDescription = "shuffle_icon")
        }
        IconButton(onClick = {},modifier = Modifier.size(30.dp)) {
            Icon(imageVector = PreviousIcon(), tint = Color.White, contentDescription = "shuffle_icon")
        }
        IconButton(onClick = {},modifier = Modifier.size(30.dp)) {
            Icon(imageVector = PlayIcon(), tint = Color.White, contentDescription = "shuffle_icon")
        }
        IconButton(onClick = {},modifier = Modifier.size(30.dp)) {
            Icon(imageVector = NextIcon(), tint = Color.White, contentDescription = "shuffle_icon")
        }
        IconButton(onClick = {},modifier = Modifier.size(30.dp)) {
            Icon(imageVector = RepeatIcon(), tint = Color.White, contentDescription = "shuffle_icon")
        }
    }
}