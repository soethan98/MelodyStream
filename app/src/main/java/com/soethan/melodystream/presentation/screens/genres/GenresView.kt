package com.soethan.melodystream.presentation.screens.genres

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.soethan.melodystream.presentation.viewmodel.GenresViewModel
import com.soethan.melodystream.presentation.viewmodel.SongsViewModel

@Composable
fun GenresView(modifier: Modifier = Modifier,
               viewModel: GenresViewModel = hiltViewModel()
) {
    val genres by viewModel.audioList.collectAsState()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text ="GenresView")
    }
}