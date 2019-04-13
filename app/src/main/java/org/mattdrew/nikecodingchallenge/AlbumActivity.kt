package org.mattdrew.nikecodingchallenge

/*
Create a sample Android app that displays the top albums across all genres using Apple's RSS generator found here: https://rss.itunes.apple.com/en-us.

The app will need to target android Pie with the minimum Api of Nougat.

Limit the external dependencies to only AndroidX 1.+, Material Design Components, and Glide or Picasso for Image Processing.

Expected behavior:
On launch, the user should see a RecyclerView showing one album per item. Each item should display the name of the album, the artist, and the album art (thumbnail image).

Tapping on an item will launch a new activity where we see a larger image at the top of the screen and the same information that was shown in the item view, plus genre, release date,
and copyright info below the image. A button should also be included on this detailed activity that when tapped fast app switches to the album page in either the Apple Music app —
if installed on the phone — or a browser. The button should be centered horizontally and pinned 20 dp from the bottom of the view and 20dp from the leading and trailing edges of the
view. The detail activity is expected to be a single flat ViewGroup.

Bonus: An accompaning Espresso test mimicking a golden path (select 1st element, view its contents, back press, select 2nd element..)

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

            // compile all genres, since there can be more than one genre
            val genres = jsonObject["genres"] as JSONArray
            val genreNames: MutableList<String> = mutableListOf()
            for(j in 0 until genres.length()){
                val genreName = genres.getJSONObject(j)["name"] as String
                genreNames.add(genreName)
            }

            albumList.add(mapOf("artist" to artistName, "title" to albumName, "artwork" to artworkURL, "genres" to genreNames, "copyright" to copyright, "iTunesURL" to iTunesURL, "releaseDate" to releaseDate))
        }

        println(albumList)

        //set up the RecyclerView
        val albumRecyclerView = findViewById<RecyclerView>(R.id.albumRecyclerView)
        albumRecyclerView.adapter = AlbumRecyclerViewAdapter(albumList,this)
        albumRecyclerView.layoutManager = LinearLayoutManager(this)
        albumRecyclerView.isScrollContainer = true



    }




}
