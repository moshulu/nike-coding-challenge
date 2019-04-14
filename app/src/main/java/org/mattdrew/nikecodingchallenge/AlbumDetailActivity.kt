package org.mattdrew.nikecodingchallenge

/*
    AlbumDetailActivity.kt
    This class implements the view when the RecyclerView in AlbumActivity.kt is clicked. It shows more details about a selected album.

    Last Updated: 13 April 2019
    Matthew Drew © 2019
 */

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.content.Intent
import android.net.Uri
import android.content.pm.PackageManager

class AlbumDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        // get passed data through Intent
        val albumDataset = intent.extras?.get("albumDataset") as Map<String, Any>
        var genre = albumDataset.get("genres") as ArrayList<String>
        val title = albumDataset.get("title") as CharSequence
        val artist = albumDataset.get("artist") as CharSequence
        val artwork = albumDataset.get("artwork") as String
        val copyright = albumDataset.get("copyright") as CharSequence
        val iTunesURL = albumDataset.get("iTunesURL") as String
        val releaseDate = albumDataset.get("releaseDate") as CharSequence

        // get UI elements
        val albumDetailGenre = findViewById<TextView>(R.id.albumDetailGenre)
        val albumDetailTitle = findViewById<TextView>(R.id.albumDetailTitle)
        val albumDetailCopyright = findViewById<TextView>(R.id.albumDetailCopyright)
        val albumDetailiTunesButton = findViewById<Button>(R.id.albumDetailiTunesButton)
        val albumDetailReleaseDate = findViewById<TextView>(R.id.albumDetailReleaseDate)
        val albumDetailArtwork = findViewById<ImageView>(R.id.albumDetailAlbumArtwork)
        val albumDetailArtist = findViewById<TextView>(R.id.albumDetailArtist)

        // unpack genres, since there could be more than one
        var genres = ""
        val genreSize = genre.size
        for(i in 0 until genreSize){
            genres += genre[i]
            if(i + 1 != genreSize){
                genres += ", "
            }

        }

        // set UI's text
        albumDetailReleaseDate.text = releaseDate
        albumDetailCopyright.text = copyright
        albumDetailTitle.text = title
        albumDetailArtist.text = artist
        Picasso.get().load(artwork).into(albumDetailArtwork)
        albumDetailGenre.text = genres

        // if the user clicks on the button at the bottom...
        albumDetailiTunesButton.setOnClickListener {
            val p = this@AlbumDetailActivity.getPackageManager()
            // if 'com.apple.android.music' package is installed on the system (Apple Music for Android)...
            if(isPackageInstalled("com.apple.android.music", p)){
                //...open in apple music app...
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(iTunesURL + "&app=music"))
                startActivity(intent)
            } else {
                //...else, open in default browser
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(iTunesURL))
                startActivity(intent)
            }

        }

    }

    // looks to see if package is installed. If a package is found, it returns true. Else, it returns False.
    private fun isPackageInstalled(packageName: String, packageManager: PackageManager): Boolean {
        var found = true
        try {
            packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            found = false
        }
        return found
    }
}
