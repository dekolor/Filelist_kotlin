import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_latest.*
import ro.dekolor.filelist.Constants
import ro.dekolor.filelist.R
import ro.dekolor.filelist.adapters.TorrentListAdapter
import ro.dekolor.filelist.models.Torrent

class LatestFragment : Fragment() {

    private lateinit var mSharedPreferences: SharedPreferences

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_latest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSharedPreferences = this.getActivity()!!.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

        var torrenteJson = mSharedPreferences.getString(Constants.LATEST_TORRENT_DATA, "")


        rv_torrent_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TorrentListAdapter(requireContext(), Gson().fromJson(torrenteJson, Array<Torrent>::class.java).toList() as ArrayList<Torrent>)
        }
    }

    companion object {
        fun newInstance(): LatestFragment = LatestFragment()
    }
}