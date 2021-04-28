package ro.dekolor.filelist.models

import java.io.Serializable

data class Torrent (
    val id: Long,
    val name: String,
    val imdb: String?,
    val freeleech: Int,
    val doubleup: Int,
    val upload_date: String,
    val download_link: String,
    val size: Long,
    val internal: Int,
    val moderated: Int,
    val category: String,
    val seeders: Int,
    val leechers: Int,
    val times_completed: Int,
    val comments: Int,
    val files: Int,
    val small_description: String
): Serializable