package com.soethan.melodystream

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
private val _isPermissionAllowed = mutableStateOf<Boolean?>(null)
    val isPermissionAllowed : State<Boolean?> get() = _isPermissionAllowed


    fun onPermissionChanged(value:Boolean){
        _isPermissionAllowed.value = value
    }
}