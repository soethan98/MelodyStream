package com.soethan.melodystream

data class SongMediaItem(
    val id:String,
    val title:String,
    val album:String,
    val artist:String,
)

fun generateSampleSongs(): List<SongMediaItem> {
    return listOf(
        SongMediaItem(id = "1", title = "Song One", album = "Album A", artist = "Artist X"),
        SongMediaItem(id = "2", title = "Song Two", album = "Album B", artist = "Artist Y"),
        SongMediaItem(id = "3", title = "Song Three", album = "Album C", artist = "Artist Z"),
        SongMediaItem(id = "4", title = "Song Four", album = "Album D", artist = "Artist X"),
        SongMediaItem(id = "5", title = "Song Five", album = "Album E", artist = "Artist Y"),
        SongMediaItem(id = "6", title = "Song Six", album = "Album F", artist = "Artist Z"),
        SongMediaItem(id = "7", title = "Song Seven", album = "Album G", artist = "Artist X"),
        SongMediaItem(id = "8", title = "Song Eight", album = "Album H", artist = "Artist Y"),
        SongMediaItem(id = "9", title = "Song Nine", album = "Album I", artist = "Artist Z"),
        SongMediaItem(id = "10", title = "Song Ten", album = "Album J", artist = "Artist X")
    )
}