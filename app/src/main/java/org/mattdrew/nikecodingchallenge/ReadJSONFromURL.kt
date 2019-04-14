package org.mattdrew.nikecodingchallenge

/*
    READJSONFromURL.kt
    This class supports AlbumActivity.kt by reading and parsing JSON data through an AsyncTask, so we don't get a NetworkOnMainThreadException.

    Last Updated: 13 April 2019
    Matthew Drew © 2019
 */

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class ReadJSONFromURL(u: String) : AsyncTask<String, Int, String>() {
    private val url : String = u

    override fun doInBackground(vararg params: String): String {
        return getJSON(url)
    }

    // reads JSON from a URL
    private fun getJSON(url: String): String {
        return URL(url).readText()
    }

    // parse a JSON string into a JSONObject.
    fun parse(json: String): JSONObject {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject!!
    }
}