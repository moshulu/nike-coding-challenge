package org.mattdrew.nikecodingchallenge

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

        val albumDataset = intent.extras?.get("albumDataset") as Map<String, Any>
        println("albumDataset: $albumDataset")
        var genre = albumDataset.get("genres") as ArrayList<String>
        val title = albumDataset.get("title") as CharSequence
        val artist = albumDataset.get("artist") as CharSequence
        val artwork = albumDataset.get("artwork") as String
        val copyright = albumDataset.get("copyright") as CharSequence
        val iTunesURL = albumDataset.get("iTunesURL") as String
        val releaseDate = albumDataset.get("releaseDate") as CharSequence

        val albumDetailGenre = findViewById<TextView>(R.id.albumDetailGenre)
        val albumDetailTitle = findViewById<TextView>(R.id.albumDetailTitle)
        val albumDetailCopyright = findViewById<TextView>(R.id.albumDetailCopyright)
        val albumDetailiTunesButton = findViewById<Button>(R.id.albumDetailiTunesButton)
        val albumDetailReleaseDate = findViewById<TextView>(R.id.albumDetailReleaseDate)
        val albumDetailArtwork = findViewById<ImageView>(R.id.albumDetailAlbumArtwork)
        val albumDetailArtist = findViewById<TextView>(R.id.albumDetailArtist)

        //
        var genres = ""
        val genreSize = genre.size
        for(i in 0 until genreSize){
            genres += genre[i]
            if(i + 1 != genreSize){
                genres += ", "
            }

        }

        albumDetailReleaseDate.text = releaseDate
        albumDetailCopyright.text = copyright
        albumDetailTitle.text = title
        albumDetailArtist.text = artist
        Picasso.get().load(artwork).into(albumDetailArtwork)
        albumDetailGenre.text = genres


        albumDetailiTunesButton.setOnClickListener {
            val p = this@AlbumDetailActivity.getPackageManager()
            if(isPackageInstalled("com.apple.android.music", p)){
                //open in apple music
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(iTunesURL + "&app=music"))
                startActivity(intent)
            } else {
                //open in browser
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(iTunesURL))
                startActivity(intent)
            }

        }

    }

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
