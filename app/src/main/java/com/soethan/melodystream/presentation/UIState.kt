package com.soethan.melodystream.presentation

sealed class UIState<out T : Any> {



    object Idle: UIState<Nothing>()
    object Loading : UIState<Nothing>()
    data class Content<out T : Any>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Content -> "Success with data:$data"
            is Error -> "Error : $message"
            Loading -> "Loading"
            Idle -> "Idle"
        }
    }
}


fun <T : Any> UIState<T>.data(): T? {
    if (this is UIState.Content<T>) {
        return data
    }
    return null
}