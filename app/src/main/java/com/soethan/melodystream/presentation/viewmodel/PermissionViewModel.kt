package com.soethan.melodystream.presentation.viewmodel

import android.os.Build
import androidx.lifecycle.ViewModel
import com.soethan.melodystream.presentation.model.permission.PermissionModel
import com.soethan.melodystream.presentation.model.permission.PermissionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PermissionViewModel(private val permissions:List<PermissionModel>,askPermission:Boolean):ViewModel() {
    private var startPermissionRequest = 0L
    private var endPermissionRequest = 0L


    private val _state = MutableStateFlow(PermissionState(permissions = permissions.filter {
        it.maxSDKVersion >= Build.VERSION.SDK_INT && it.minSDKVersion <= Build.VERSION.SDK_INT
    }.map { it.permission }, askPermission = askPermission, showRational = false))

    val state = _state.asStateFlow()

    fun onResult(result:Map<String,Boolean>){
        _state.update { it.copy(askPermission = false) }
        endPermissionRequest = System.currentTimeMillis()
        val notGrantedPermissions = permissions.filter { it.permission in result.filter { permission -> permission.value.not() } }
        if (notGrantedPermissions.isEmpty()){
            onConsumeRational()
            _state.update {
                it.copy(allPermissionsGranted = true)
            }
        }else {
            _state.update {state-> state.copy(permissions = notGrantedPermissions.map { it.permission }, allPermissionsGranted = false) }

            if (endPermissionRequest - startPermissionRequest < 200) {
                _state.update {
                    it.copy(navigateToSetting = true)
                }
            } else
                _state.update { state ->
                    state.copy(
                        showRational = notGrantedPermissions.isNotEmpty(),
                        rationals = notGrantedPermissions.map { it.rational }
                    )
                }
        }
    }


    fun onGrantPermissionClicked(){
        _state.update { it.copy(askPermission = true) }
        startPermissionRequest = System.currentTimeMillis()
    }

    fun onPermissionRequested() {
        _state.update {
            it.copy(
                askPermission = false,
                navigateToSetting = false
            )
        }
    }

    private fun onConsumeRational() {
        _state.update {
            it.copy(showRational = false)
        }
    }

}