package org.mattdrew.nikecodingchallenge

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import java.net.URL


class ReadJSONFromURL(u: String) : AsyncTask<String, Int, String>() {
    private val url : String = u

    override fun doInBackground(vararg params: String): String {
        return getJSON(url)
    }

    private fun getJSON(url: String): String {
        val apiResponse = URL(url).readText()
        //println("apiResponse: ${parse(apiResponse)}")

        return apiResponse
    }

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