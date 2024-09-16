package com.soethan.melodystream.presentation.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.soethan.melodystream.R
import com.soethan.melodystream.presentation.navigation.PlayerScreenRoute
import com.soethan.melodystream.presentation.screens.albums.AlbumsView
import com.soethan.melodystream.presentation.screens.artists.ArtistsView
import com.soethan.melodystream.presentation.screens.genres.GenresView
import com.soethan.melodystream.presentation.screens.playlist.PlayListView
import com.soethan.melodystream.presentation.screens.songs.SongsView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MainScreen(navController: NavController) {

    var selectedTabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState {
        tabs.size
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }


    Column(modifier = Modifier.fillMaxSize()) {

        ScrollableTabRow(selectedTabIndex = selectedTabIndex, edgePadding = 0.dp) {
            tabs.forEachIndexed { index, tabItem ->
                Tab(selected = index == selectedTabIndex, onClick = {
                    selectedTabIndex = index

                }, text = {
                    Text(text = stringResource(id = tabItem.stringRes))
                })
            }
        }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { _ ->
            when (selectedTabIndex) {
                0 -> SongsView()
                1 -> PlayListView()
                2 -> ArtistsView()
                3 -> AlbumsView()
                4 -> GenresView()
            }
        }
    }
}


val tabs = listOf<TabItem>(
    TabItem(R.string.songs),
    TabItem(R.string.playlist),
    TabItem(R.string.artists),
    TabItem(R.string.albums),
    TabItem(R.string.genres)
)

data class TabItem(
    @StringRes val stringRes: Int
)

