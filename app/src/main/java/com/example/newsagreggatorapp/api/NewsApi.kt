package com.example.newsagreggatorapp.api

import com.example.newsagreggatorapp.Constants.Companion.APIKEY
import com.example.newsagreggatorapp.models.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {



    @GET("/v2/top-headlines")
    fun getHeadlinesNews(
        @Query("country")
        codeOfCountry: String = "us",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = APIKEY
    ): Call<News>

    @GET("v2/everything")
    fun getSearchNews(
        @Query("q")
        search: String = "bitcoin",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = APIKEY
    ): Call<News>

    @GET("/v2/sources")
    fun getFavoriteNews(
        @Query("category")
        category: String = "general",
        @Query("language")
        language: String = "en",
        @Query("county")
        country: String = "gb",
        @Query("apiKey")
        apiKey: String = APIKEY
    ): Call<News>

}