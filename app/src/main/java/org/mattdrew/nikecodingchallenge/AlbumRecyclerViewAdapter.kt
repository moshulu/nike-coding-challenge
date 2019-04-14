package org.mattdrew.nikecodingchallenge

/*
    AlbumRecyclerViewAdapter.kt
    This class supports AlbumActivity.kt's RecyclerView with id: albumRecyclerView. The RecyclerView is aimed to show the user the album's thumbnail via Picasso, the album name,
    and the album's artist(s).

    Last Updated: 13 April 2019
    Matthew Drew © 2019
 */

import android.app.Activity
import android.content.Intent
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
            // set up elements within the RecyclerView individual item
            holder.albumTitle.text = albumDataset[position]["title"] as CharSequence
            holder.albumArtist.text = albumDataset[position]["artist"] as CharSequence
            Picasso.get().load(albumDataset[position]["artwork"] as String?).into(holder.albumArt)

            // set up data for passing to AlbumDetailActivity, if needed.
            holder.albumDataset = albumDataset[position]
        }

        override fun getItemCount() = albumDataset.size

    class ViewHolder(itemView: View, activity: Activity): RecyclerView.ViewHolder(itemView){
        // initialize UI elements for use by onBindViewHolder()
        val albumTitle = itemView.findViewById<TextView>(R.id.albumTitle)
        val albumArtist = itemView.findViewById<TextView>(R.id.albumArtist)
        val albumArt = itemView.findViewById<ImageView>(R.id.albumArtImageView)

        // instantiate data for passing to AlbumDetailActivity
        var albumDataset: Map<String, Any> = mutableMapOf()

        init{
            // if the item is clicked...
            itemView.setOnClickListener{
                // ...set up intent with the album's dataset as albumDataset and cast that to a Serializable object
                val intent: Intent = Intent(itemView.context, AlbumDetailActivity::class.java)
                intent.putExtra("albumDataset", albumDataset as Serializable)
                itemView.context.startActivity(intent)
            }
        }
    }
}