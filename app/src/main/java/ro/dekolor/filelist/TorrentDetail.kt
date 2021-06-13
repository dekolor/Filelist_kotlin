package ro.dekolor.filelist

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_torrent_detail.*
import ro.dekolor.filelist.models.Torrent

class TorrentDetail : AppCompatActivity() {
    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_torrent_detail)

        mSharedPreferences = this.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

        if(intent.hasExtra("torrent_id")) {
            if(intent.hasExtra("type") && intent.getStringExtra("type") == "latest") {
                var torrenteJson = mSharedPreferences.getString(Constants.LATEST_TORRENT_DATA, "")
                var torrentedata = Gson().fromJson(torrenteJson, Array<Torrent>::class.java).toList() as ArrayList<Torrent>
                val torrentid = intent.getStringExtra("torrent_id").toString()
                id_torrent.setText(torrentid)
                var torrent = torrentedata.filter { it.id.toString() == torrentid}.single()
                title_torrent.text = torrent.name.toString()
            }
            else if(intent.hasExtra("type") && intent.getStringExtra("type") == "search") {
                var torrenteJson = mSharedPreferences.getString(Constants.SEARCH_TORRENT_DATA, "")
                var torrentedata = Gson().fromJson(torrenteJson, Array<Torrent>::class.java).toList() as ArrayList<Torrent>
                val torrentid = intent.getStringExtra("torrent_id").toString()
                id_torrent.setText(torrentid)
                var torrent = torrentedata.filter { it.id.toString() == torrentid}.single()
                title_torrent.text = torrent.name.toString()
            }
        }
    }
}