package ro.dekolor.filelist.models

import java.io.Serializable

data class Movie (
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String,
    val original_language: String,
    val title: String?,
    val name: String?,
    val backdrop_path: String?,
    val popularity: Double,
    val vote_count: Long,
    val video: Boolean,
    val vote_average: Float
): Serializable