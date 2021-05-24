import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_imdb.*
import retrofit.*
import ro.dekolor.filelist.Constants
import ro.dekolor.filelist.R
import ro.dekolor.filelist.adapters.MovieAdapter
import ro.dekolor.filelist.adapters.TorrentListAdapter
import ro.dekolor.filelist.models.Movie
import ro.dekolor.filelist.models.Torrent
import ro.dekolor.filelist.models.TrendingWeekResponse
import ro.dekolor.filelist.network.FilelistService
import ro.dekolor.filelist.network.TmdbService

class ImdbFragment : Fragment() {

    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_imdb, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSharedPreferences = this.requireActivity().getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

        getMovies()
    }

    fun getMovies() {
        if(Constants.isNetworkAvailable(requireContext())) {
            val username = mSharedPreferences.getString(Constants.USERNAME, "")
            val passkey = mSharedPreferences.getString(Constants.PASSKEY, "")

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.TMDB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: TmdbService = retrofit.create<TmdbService>(TmdbService::class.java)
            val listCall: Call<TrendingWeekResponse> = service.getTrendingWeek(resources.getString(R.string.tmdb_api))

            listCall.enqueue(object: Callback<TrendingWeekResponse> {
                override fun onFailure(t: Throwable?) {
                    Log.e("Eroare", t!!.message.toString())
                }

                override fun onResponse(response: Response<TrendingWeekResponse>?, retrofit: Retrofit?) {
                    if(response!!.isSuccess) {
                        var trending: TrendingWeekResponse = response.body()

                        Log.e("movies", trending.results.toString())

                        rv_movie_list.apply {
                            layoutManager = GridLayoutManager(activity, 2)
                            adapter = MovieAdapter(requireContext(), trending.results as ArrayList<Movie>)
                        }
                    }else {
                        val rc = response.code()
                        when(rc) {
                            400 -> {
                                Log.e("Error 400", "Bad connection")
                            }
                            404 -> {
                                Log.d("Error 404", response.raw().toString())
                            }
                            else -> {
                                Log.e("Error", "Generic error")
                            }
                        }
                    }

                }

            })
        } else {
            Toast.makeText(
                requireContext(),
                "No internet connection available",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        fun newInstance(): ImdbFragment = ImdbFragment()
    }
}