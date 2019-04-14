package org.mattdrew.nikecodingchallenge

/*
    AlbumActivity.kt
    This class was created to show the user the top albums according to the iTunes RSS feed at https://rss.itunes.apple.com/en-us. AlbumActivity shows the user
    a RecyclerView with data from the RSS feed.

    Last Updated: 13 April 2019
    Matthew Drew © 2019
 */

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject


class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

       // convert url to readable JSON
        val jsonReader = ReadJSONFromURL("https://rss.itunes.apple.com/api/v1/us/apple-music/top-albums/all/10/explicit.json")
        val jsonString = jsonReader.execute().get()
        val json = (jsonReader.parse(jsonString).get("feed") as JSONObject).get("results") as JSONArray

        // set useful information for album view into an array list
        val albumList: ArrayList<Map<String, Any>> = arrayListOf<Map<String, Any>>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val artistName = jsonObject["artistName"] as String
            val albumName = jsonObject["name"] as String
            val artworkURL = jsonObject["artworkUrl100"]
            val copyright = jsonObject["copyright"]
            val iTunesURL = jsonObject["url"]
            val releaseDate = jsonObject["releaseDate"]

            // compile all genres into a mutable list, since there can be more than one genre
            val genres = jsonObject["genres"] as JSONArray
            val genreNames: MutableList<String> = mutableListOf()
            for(j in 0 until genres.length()){
                val genreName = genres.getJSONObject(j)["name"] as String
                genreNames.add(genreName)
            }

            // add this album's information into one map, and add the map to the array list
            albumList.add(mapOf("artist" to artistName, "title" to albumName, "artwork" to artworkURL, "genres" to genreNames, "copyright" to copyright, "iTunesURL" to iTunesURL, "releaseDate" to releaseDate))
        }

        //set up the RecyclerView
        val albumRecyclerView = findViewById<RecyclerView>(R.id.albumRecyclerView)
        albumRecyclerView.adapter = AlbumRecyclerViewAdapter(albumList,this)
        albumRecyclerView.layoutManager = LinearLayoutManager(this)
        albumRecyclerView.isScrollContainer = true



    }




}
