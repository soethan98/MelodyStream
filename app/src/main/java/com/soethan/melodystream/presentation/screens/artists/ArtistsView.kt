package com.soethan.melodystream.presentation.screens.artists

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
import com.soethan.melodystream.presentation.components.ArtistTile
import com.soethan.melodystream.presentation.components.SongListTitle
import com.soethan.melodystream.presentation.data
import com.soethan.melodystream.presentation.viewmodel.ArtistViewModel
import com.soethan.melodystream.presentation.viewmodel.SongsViewModel

@Composable
fun ArtistsView(modifier: Modifier = Modifier,
                viewModel: ArtistViewModel = hiltViewModel()
) {
    val artists by viewModel.audioList.collectAsState()

    when (artists) {
        is UIState.Content -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(
                    horizontal = 16.dp, vertical = 16.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(artists.data()!!.size) {
                    ArtistTile(data = artists!!.data()!![it])

                }
            }
        }

        else -> {

        }
    }
}