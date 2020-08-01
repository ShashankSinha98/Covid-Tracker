package com.example.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val TAG  = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get Covid data from api
        fetchData()
    }

    private fun fetchData() {

        // Coroutine will run this on separate thread
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.execute() }

            if(response.isSuccessful){
                //Log.d(TAG+"_response",response.body?.string())
                // ?. - Safe check, return null if null is present, else value
                val data = Gson().fromJson(response.body?.string(),Response::class.java) // Converting Json array to list of state wise data

                // Updating UI using - launch Dispatchers.Main
                launch (Dispatchers.Main){
                    // Displaying top header data
                    bindCombinedData(data.statewise[0])
                }
            }
        }
    }

    private fun bindCombinedData(data: StatewiseItem) {

        val lastUpdatedTime : String? = data.lastupdatedtime
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        lastUpdatedTv.text = "Last Updated\n ${getTimeAgo(simpleDateFormat.parse(lastUpdatedTime))}"

        confirmedTv.text = data.confirmed
        recoveredTv.text = data.recovered
        activeTv.text = data.active
        deceasedTv.text = data.deaths

    }

    private fun getTimeAgo(past : Date) : String {
        val now = Date() // Getting current date
        val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
        val hours = TimeUnit.MILLISECONDS.toHours(now.time-past.time)

        return when {
            seconds < 60 ->{
                "Few Seconds ago"
            }
            minutes < 60 ->{
                "$minutes minutes ago"
            }
            hours < 24 ->{
                "$hours hour ${minutes % 60} min ago"
            }
            else ->{
                SimpleDateFormat("dd/MM/yy, hh:mm a").format(past).toString()
            }
        }
    }
}