package com.soethan.melodystream.presentation.screens.playlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.soethan.melodystream.presentation.viewmodel.PlaylistViewModel
import com.soethan.melodystream.presentation.viewmodel.SongsViewModel

@Composable
fun PlayListView(modifier: Modifier = Modifier,
                   viewModel: PlaylistViewModel = hiltViewModel()
) {
    val playlistItems by viewModel.audioList.collectAsState()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text ="PlayListScreen")
    }
}