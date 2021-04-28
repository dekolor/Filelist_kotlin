import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit.*
import ro.dekolor.filelist.Constants
import ro.dekolor.filelist.R
import ro.dekolor.filelist.adapters.TorrentListAdapter
import ro.dekolor.filelist.models.Torrent
import ro.dekolor.filelist.network.FilelistService

class SearchFragment : Fragment() {

    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSharedPreferences = this.getActivity()!!.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

        btn_search.setOnClickListener {
            searchTorrent(input_search.text.toString())
        }
    }

    private fun searchTorrent(term: String) {
        if(Constants.isNetworkAvailable(requireContext())) {
            val username = mSharedPreferences.getString(Constants.USERNAME, "")
            val passkey = mSharedPreferences.getString(Constants.PASSKEY, "")

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: FilelistService = retrofit.create<FilelistService>(FilelistService::class.java)
            val listCall: Call<List<Torrent>> = service.search(username!!, passkey!!, "search-torrents", "name", term)

            listCall.enqueue(object: Callback<List<Torrent>> {
                override fun onFailure(t: Throwable?) {
                    Log.e("Eroare", t!!.message.toString())
                }

                override fun onResponse(response: Response<List<Torrent>>?, retrofit: Retrofit?) {
                    if(response!!.isSuccess) {
                        var torrente: List<Torrent> = response.body()
                        rv_torrent_search.apply {
                            layoutManager = LinearLayoutManager(activity)
                            adapter = TorrentListAdapter(requireContext(), torrente as ArrayList<Torrent>)
                        }
                    }else {
                        val rc = response.code()
                        when(rc) {
                            400 -> {
                                Log.e("Error 400", "Bad connection")
                            }
                            404 -> {
                                Log.d("Error 404", "Not found")
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
        fun newInstance(): SearchFragment = SearchFragment()
    }
}