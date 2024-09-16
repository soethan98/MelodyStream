package com.soethan.melodystream.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
object MainScreenRoute


@Serializable
data class PlayerScreenRoute(
    val uri: String
)