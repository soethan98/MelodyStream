package com.soethan.melodystream.utils.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings

private fun Activity.openAppSetting() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

