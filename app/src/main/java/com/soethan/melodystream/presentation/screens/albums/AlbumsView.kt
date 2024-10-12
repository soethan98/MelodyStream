package com.soethan.melodystream.presentation.screens.albums

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soethan.melodystream.presentation.UIState
import com.soethan.melodystream.presentation.components.AlbumTile
import com.soethan.melodystream.presentation.components.SongListTitle
import com.soethan.melodystream.presentation.data
import com.soethan.melodystream.presentation.viewmodel.AlbumsViewModel
import com.soethan.melodystream.presentation.viewmodel.SongsViewModel

@Composable
fun AlbumsView(
    modifier: Modifier = Modifier,
    viewModel: AlbumsViewModel = hiltViewModel()
) {
    val albums by viewModel.audioList.collectAsState()
    when (albums) {
        is UIState.Content -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(
                    horizontal = 16.dp, vertical = 16.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(albums.data()!!.size) {
                    AlbumTile(data = albums!!.data()!![it])
                }
            }
        }

        else -> {

        }
    }
}