package com.example.movieinfo.Repo

import com.example.movieinfo.ApiServices.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val BASIC_URL="https://api.themoviedb.org"
    val Apikey=//"<<Your ApiKey>>"
    val imageUrl="https://image.tmdb.org/t/p/w500"
    fun getApiService():ApiService{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASIC_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}
