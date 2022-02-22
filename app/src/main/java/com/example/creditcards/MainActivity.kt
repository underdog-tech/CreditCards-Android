package com.example.creditcards

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.creditcards.databinding.ActivityMainBinding
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    private var requestQueue: RequestQueue? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        requestQueue = Volley.newRequestQueue(this)

        binding.fab.setOnClickListener { view ->
            fetchCards()
        }
    }

    private fun fetchCards() {
        val url = "https://rawbee.com/work/pinwheel/cards/list.json"
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val jsonArray = response.getJSONArray("cards")
                for (i in 0 until jsonArray.length()) {
                    val card = jsonArray.getJSONObject(i)
                    val id = card.getString("id")
                    Log.i("rawbee", id)
                    Log.i("rawbee", card.toString())
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}