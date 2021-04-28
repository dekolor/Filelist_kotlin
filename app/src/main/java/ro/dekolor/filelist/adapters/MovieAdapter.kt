package ro.dekolor.filelist.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_torrent.view.*
import kotlinx.android.synthetic.main.movie_row.view.*
import ro.dekolor.filelist.R
import ro.dekolor.filelist.models.Movie
import java.io.InputStream
import java.net.URI
import java.net.URL

open class MovieAdapter (
    private val context: Context,
    private val list: ArrayList<Movie>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.movie_row,
                parent,
                false
            )
        )
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]


        if(holder is MyViewHolder) {
            // TODO

            Glide
                .with(holder.itemView)
                .load("https://image.tmdb.org/t/p/w500" + model.poster_path)
                .centerCrop()
                .into(holder.itemView.movie_poster)

            if(model.title.isNullOrEmpty()) {
                holder.itemView.movie_title.text = model.name
            } else {
                holder.itemView.movie_title.text = model.title
            }

            holder.itemView.setOnClickListener {
                if(onClickListener != null) {
                    onClickListener!!.onClick(position, model)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener : View.OnClickListener {
        fun onClick(position: Int, model: Movie) {}
        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}