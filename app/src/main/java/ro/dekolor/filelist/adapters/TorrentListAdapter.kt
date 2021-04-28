package ro.dekolor.filelist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_torrent.view.*
import ro.dekolor.filelist.R
import ro.dekolor.filelist.models.Torrent

open class TorrentListAdapter (
    private val context: Context,
    private val list: ArrayList<Torrent>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_torrent,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if(holder is MyViewHolder) {
            holder.itemView.tvTitle.text = model.name
            holder.itemView.tvDescription.text = model.small_description

            when(model.category) {
                "Filme SD" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.sd)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Filme DVD" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.dvd)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Filme DVD-RO" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.dvd_ro)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Filme HD" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.hd)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "FLAC" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.flac)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Filme 4K" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.i4k)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "XXX" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.xxx)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Programe" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.apps)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Jocuri PC" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.games)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Jocuri Console" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.console)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Audio" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.music)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Videoclip" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.vids)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Sport" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.sport)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "TV" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.sdtv)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Desene" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.cartoons)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Docs" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.docs)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Linux" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.linux)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Diverse" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.misc)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Filme HD-RO" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.hd_ro)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Filme Blu-Ray" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.bluray)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Seriale HD" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.hdtv)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Mobile" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.mobile)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Seriale SD" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.sdtv)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Anime" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.anime)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Filme 3D" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.i3d)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Filme 4K Blu-Ray" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.i4kbd)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
                "Seriale 4K" -> {
                    Glide
                        .with(holder.itemView)
                        .load(R.drawable.i4ks)
                        .centerCrop()
                        .into(holder.itemView.iv_place_image)
                }
            }
        }
    }

    interface OnClickListener{
        fun onClick(position: Int, model: Torrent)
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

}