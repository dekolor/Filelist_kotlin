package ro.dekolor.filelist


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_torrent.*
import retrofit.*
import ro.dekolor.filelist.adapters.TorrentListAdapter
import ro.dekolor.filelist.models.Torrent
import ro.dekolor.filelist.network.FilelistService

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    private lateinit var mSharedPreferences: SharedPreferences

    private var mSelectedFragment: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSharedPreferences = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

        setSupportActionBar(toolbar_main)
        toolbar = supportActionBar!!
        toolbar.title = "Trending"
        val imdbFragment = ImdbFragment.newInstance()
        openFragment(imdbFragment)

        val username = mSharedPreferences.getString(Constants.USERNAME, "")
        val passkey = mSharedPreferences.getString(Constants.PASSKEY, "")

        getLatestTorrents(username, passkey)

        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_imdb -> {
                if(mSelectedFragment != 0) {
                    toolbar.title = "Trending"
                    val imdbFragment = ImdbFragment.newInstance()
                    openFragment(imdbFragment)
                    mSelectedFragment = 0
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_latest -> {
                if(mSelectedFragment != 1) {
                    toolbar.title = "Latest"
                    val latestFragment = LatestFragment.newInstance()
                    openFragment(latestFragment)
                    mSelectedFragment = 1
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                if(mSelectedFragment != 2) {
                    toolbar.title = "Search"
                    val searchFragment = SearchFragment.newInstance()
                    openFragment(searchFragment)
                    mSelectedFragment = 2
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun getLatestTorrents(username: String?, passkey: String?) {
        if(Constants.isNetworkAvailable(this)) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: FilelistService = retrofit.create<FilelistService>(FilelistService::class.java)
            val listCall: Call<List<Torrent>> = service.getLatest(username!!, passkey!!, "latest-torrents")

            listCall.enqueue(object: Callback<List<Torrent>> {
                override fun onFailure(t: Throwable?) {
                    Log.e("Eroare", t!!.message.toString())
                }

                override fun onResponse(response: Response<List<Torrent>>?, retrofit: Retrofit?) {
                    if(response!!.isSuccess) {
                        var torrente: List<Torrent> = response.body()
                        val torrenteJson = Gson().toJson(torrente)
                        val editor = mSharedPreferences.edit()
                        editor.putString(Constants.LATEST_TORRENT_DATA, torrenteJson)
                        editor.apply()
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
                this@MainActivity,
                "No internet connection available",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}