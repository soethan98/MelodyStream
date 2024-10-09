package com.soethan.melodystream.presentation.model.permission

data class PermissionState(
    val askPermission: Boolean,
    val showRational: Boolean = false,
    val rationals: List<String> = emptyList(),
    val permissions: List<String>,
    val navigateToSetting: Boolean = false
)