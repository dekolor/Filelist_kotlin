package ro.dekolor.filelist

import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
                var torrent = torrentedata.filter { it.id.toString() == torrentid}.single()
                torrent_name.text = torrent.name.toString()
                if(torrent.freeleech==1) {
                    card_freeleech.visibility = View.VISIBLE
                }
                if(torrent.internal==1) {
                    card_internal.visibility = View.VISIBLE
                }
                if(torrent.doubleup==1) {
                    card_doubleup.visibility = View.VISIBLE
                }
            }
            else if(intent.hasExtra("type") && intent.getStringExtra("type") == "search") {
                var torrenteJson = mSharedPreferences.getString(Constants.SEARCH_TORRENT_DATA, "")
                var torrentedata = Gson().fromJson(torrenteJson, Array<Torrent>::class.java).toList() as ArrayList<Torrent>
                val torrentid = intent.getStringExtra("torrent_id").toString()
                var torrent = torrentedata.filter { it.id.toString() == torrentid}.single()
                torrent_name.text = torrent.name.toString()
                if(torrent.freeleech==1) {
                    card_freeleech.visibility = View.VISIBLE
                }
                if(torrent.internal==1) {
                    card_internal.visibility = View.VISIBLE
                }
                if(torrent.doubleup==1) {
                    card_doubleup.visibility = View.VISIBLE
                }
            }
        }
    }
}