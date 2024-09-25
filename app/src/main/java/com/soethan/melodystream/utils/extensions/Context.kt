package com.soethan.melodystream.utils.extensions

import android.content.Context
import android.database.Cursor
import android.net.Uri


fun Context.queryCursor(
    uri: Uri,
    projection:Array<String>,
    selection: String? = null,
    selectionArgs: Array<String>? = null,
    sortOrder: String? = null,
    showErrors: Boolean = false,
    callback: (cursor: Cursor) -> Unit
){
    try {
        val cursor = contentResolver.query(
            uri,projection,selection,selectionArgs,sortOrder
        )
        cursor?.use{
            if (cursor.moveToFirst()){
                do {
                    callback(cursor)
                }while (cursor.moveToNext())
            }

        }
    }catch (e:Exception){
        if (showErrors){
            /// TODO: display toast
        }
    }
}