package ro.dekolor.filelist.network

import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query
import ro.dekolor.filelist.models.Torrent

interface FilelistService {

    @GET("/api.php")
    fun getLatest(
        @Query("username") username: String,
        @Query("passkey") passkey: String,
        @Query("action") action: String
    ): Call<List<Torrent>>

    @GET("/api.php")
    fun search(
        @Query("username") username: String,
        @Query("passkey") passkey: String,
        @Query("action") action: String,
        @Query("type") type: String,
        @Query("query") query: String
    ): Call<List<Torrent>>
}