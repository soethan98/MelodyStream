package com.soethan.melodystream.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@ExperimentalMaterial3Api
@Composable
fun MenuBottomSheet(modifier: Modifier = Modifier) {
    ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
         {
            Row(
                modifier = Modifier.padding(vertical = 8.dp)

            ){
                Icon(imageVector = Icons.Default.Menu, contentDescription = "add_to_queue",modifier = Modifier.padding(end = 8.dp))

                Text(text = "Add to queue")
            }
            Row(
                modifier = Modifier.padding(vertical = 8.dp)

            ){
                Icon(imageVector = Icons.Default.Menu, contentDescription = "add_to_playlist",modifier = Modifier.padding(end = 8.dp))

                Text(text = "Add to playlist",)
            }
            Row(
                modifier = Modifier.padding(vertical = 8.dp)

            ){
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "delete_media",modifier = Modifier.padding(end = 8.dp))

                Text(text = "Delete")
            }
            Row(
                modifier = Modifier.padding(vertical = 8.dp)

            ) {
                Icon(imageVector = Icons.Outlined.Share, contentDescription = "share_media",modifier = Modifier.padding(end = 8.dp))
                Text(text = "Share")
            }
        }
    }
}