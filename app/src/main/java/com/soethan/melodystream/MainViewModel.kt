package com.soethan.melodystream

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.melodystream.media.ContentResolverHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contentResolverHelper: ContentResolverHelper
):ViewModel() {
private val _isPermissionAllowed = mutableStateOf<Boolean?>(null)
    val isPermissionAllowed : State<Boolean?> get() = _isPermissionAllowed


    fun onPermissionChanged(value:Boolean){
        _isPermissionAllowed.value = value
    }

    fun getAudioFiles(){
        viewModelScope.launch {
           val results =  contentResolverHelper.getAudioData()
            Log.i("MainViewModel", "getAudioFiles: ${results.size}")
        }
    }
}