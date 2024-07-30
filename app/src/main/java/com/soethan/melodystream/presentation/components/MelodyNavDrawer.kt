package com.soethan.melodystream.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun MelodyNavDrawer(modifier: Modifier = Modifier) {
    ModalDrawerSheet {
        NavigationDrawerItem(
            icon = {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
            },
            label = {
                Text(text = "Settings")
            }, selected = false, onClick = { /*TODO*/ })
        NavigationDrawerItem(
            icon = {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "theme")
            },
            label = {
                Text(text = "Theme")
            }, selected = false, onClick = { /*TODO*/ })
    }
}

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)
