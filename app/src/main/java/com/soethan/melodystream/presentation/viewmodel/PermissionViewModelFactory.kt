package com.soethan.melodystream.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soethan.melodystream.presentation.model.permission.PermissionModel

class PermissionViewModelFactory(
    private val permissions: List<PermissionModel>,
    private val askPermission:Boolean
):ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PermissionViewModel(permissions = permissions, askPermission = askPermission) as T
    }
}