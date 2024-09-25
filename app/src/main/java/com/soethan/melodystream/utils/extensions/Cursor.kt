package com.soethan.melodystream.utils.extensions

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull

fun Cursor.getStringValue(key: String) = getString(getColumnIndexOrThrow(key))


fun Cursor.getStringValueOrNull(key: String) = getStringOrNull(getColumnIndexOrThrow(key))


fun Cursor.getIntValue(key: String) = getInt(getColumnIndexOrThrow(key))

fun Cursor.getIntValueOrNull(key: String) = getIntOrNull(getColumnIndexOrThrow(key))


fun Cursor.getLongValue(key: String) = getLong(getColumnIndexOrThrow(key))

fun Cursor.getLongValueOrNull(key: String) = getLongOrNull(getColumnIndexOrThrow(key))

fun Cursor.getBlobValue(key: String) = getBlob(getColumnIndexOrThrow(key))