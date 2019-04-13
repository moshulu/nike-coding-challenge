package org.mattdrew.nikecodingchallenge

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.io.Serializable

class AlbumRecyclerViewAdapter(private val albumDataset: ArrayList<Map<String, Any>>, var activity: Activity) :
    RecyclerView.Adapter<AlbumRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_album_item, parent, false)
            return ViewHolder(v, activity)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.albumTitle.text = albumDataset[position]["title"] as CharSequence
            holder.albumArtist.text = albumDataset[position]["artist"] as CharSequence
            Picasso.get().load(albumDataset[position]["artwork"] as String?).into(holder.albumArt)
            holder.albumDataset = albumDataset[position]
        }

        override fun getItemCount() = albumDataset.size

    class ViewHolder(itemView: View, activity: Activity): RecyclerView.ViewHolder(itemView){
        val albumTitle = itemView.findViewById<TextView>(R.id.albumTitle)
        val albumArtist = itemView.findViewById<TextView>(R.id.albumArtist)
        val albumArt = itemView.findViewById<ImageView>(R.id.albumArtImageView)
        var albumDataset: Map<String, Any> = mutableMapOf()

        init{
            itemView.setOnClickListener{
                val intent: Intent = Intent(itemView.context, AlbumDetailActivity::class.java)
                intent.putExtra("albumDataset", albumDataset as Serializable)
                itemView.context.startActivity(intent)
            }
        }
    }
}