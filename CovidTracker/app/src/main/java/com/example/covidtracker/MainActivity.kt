package com.example.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

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
                Log.d(TAG+"_response",response.body?.string())
            }
        }
    }
}