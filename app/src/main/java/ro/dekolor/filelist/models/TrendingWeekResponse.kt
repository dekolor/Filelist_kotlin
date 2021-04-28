package ro.dekolor.filelist.models

import java.io.Serializable

data class TrendingWeekResponse (
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
): Serializable