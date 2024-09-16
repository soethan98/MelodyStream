package com.soethan.melodystream.presentation.screens.artists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.soethan.melodystream.presentation.viewmodel.ArtistViewModel
import com.soethan.melodystream.presentation.viewmodel.SongsViewModel

@Composable
fun ArtistsView(modifier: Modifier = Modifier,
                viewModel: ArtistViewModel = hiltViewModel()
) {
    val artists by viewModel.audioList.collectAsState()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text ="ArtistsView")
    }
}