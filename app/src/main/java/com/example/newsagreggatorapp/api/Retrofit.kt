package com.example.newsagreggatorapp.api

import com.example.newsagreggatorapp.Constants.Companion.BASEURL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class represent retrofit
 */
class Retrofit {

    fun retrofit(): Retrofit {
        var client =  OkHttpClient.Builder().build()
        var retrofit = Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).client(client).build()

        //var client = Retrofit.Builder().baseUrl(BASEURL).client(OkHttpClient()).build().create(NewsApi::class.java)

        //var retrofit = Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build()

        //val retrofit = Retrofit.Builder().baseUrl("https://api.example.com/").build()

        return retrofit
    }

    fun api(): NewsApi {
        val newsApi = retrofit().create(NewsApi::class.java)

        return newsApi
    }
}