package com.example.workmanagerdemo.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Retrofit Instance
object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }
}