package com.example.covidtracker

import okhttp3.OkHttpClient
import okhttp3.Request


/*
*  object - for creating singleton class in kotlin
*  object is thread-safe singleton implementation.
*  (Java problem - . If two threads access singleton at a time, two instances of this object could be generated.
*   Solution- synchronized keyword ensures that there are no thread interferences when creating the instance.)
*   @Link- https://medium.com/swlh/singleton-class-in-kotlin-c3398e7fd76b
* */


object Client{

    private val okHttpClient = OkHttpClient() // This will help in networking

    // For Telling from where info need to be fetched
    private val request = Request.Builder()
        .url("https://api.covid19india.org/data.json")
        .build()

    // For fetching info
    val api = okHttpClient.newCall(request)

}