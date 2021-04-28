package ro.dekolor.filelist.network

import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query
import ro.dekolor.filelist.models.TrendingWeekResponse

interface TmdbService {
    @GET("trending/all/week")
    fun getTrendingWeek(
        @Query("api_key") api_key: String
    ): Call<TrendingWeekResponse>
}