package com.example.newsagreggatorapp

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsagreggatorapp.api.Retrofit
import com.example.newsagreggatorapp.models.Article
import com.example.newsagreggatorapp.models.News
import com.example.newsagreggatorapp.models.Source
import com.example.project.MyAdapter
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.concurrent.thread

/**
 * This class allow to display articles from eexplore tab
 *
 * @author Piotr Obara
 * 967793
 */
class CategoryArticlesActivity : AppCompatActivity() {

    private var loggedInActivity: LoggedInActivity = LoggedInActivity()
    val retrofit: Retrofit = Retrofit()
    var articlesArrayList: ArrayList<Source> = ArrayList()
    lateinit var adapter : AdapterCategory
    var category: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.fragment_favorite)
        var recyclerView = findViewById<View>(R.id.my_recycler_view) as RecyclerView

        var extras: Bundle? = getIntent().extras

        if (extras != null) {
            category = extras.getString("category")
        }

        // fetch data by top headlines
        var call: Call<News> = retrofit.api().getFavoriteNews(category!!)

        thread {
            try {
                articlesArrayList = call.execute().body()?.sources!!
                System.out.println(articlesArrayList.size)
            } catch (e: Exception) {
                e.toString()
                val snackbar =
                        Snackbar.make(recyclerView,  "No Internet", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }.join()

        adapter = AdapterCategory(articlesArrayList, this)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        System.out.println("jdjdjdejeiei" + articlesArrayList.size)
    }
}