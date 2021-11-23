package com.example.newsagreggatorapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsagreggatorapp.api.NewsApi
import com.example.newsagreggatorapp.api.Retrofit
import com.example.newsagreggatorapp.models.Article
import com.example.newsagreggatorapp.models.News
import com.example.newsagreggatorapp.models.Source
import com.example.project.MyAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.collections.ArrayList
import kotlin.concurrent.thread


/**
 * This class represent favorite fragment
 *
 * @author Piotr Obara
 * 967793
 */
class favoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var loggedInActivity: LoggedInActivity = LoggedInActivity()
    var currentUser = loggedInActivity.mAuth.currentUser?.email
    var currentUserL = loggedInActivity.mAuth.currentUser
    val retrofit: Retrofit = Retrofit()
    var articlesArrayList: ArrayList<Article> = ArrayList()
    var articlesArrayListF: ArrayList<Source> = ArrayList()
    lateinit var mAdapter : MyAdapter
    var contextt: Context? = null
    var dbUserLike: DBUserLike = DBUserLike()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerView = view.findViewById<View>(R.id.my_recycler_view) as RecyclerView
        var sqliteDatabaseLike: SqliteDatabaseUserLike? = dbUserLike.getInstance(contextt!!)

        var call: Call<News> = retrofit.api().getHeadlinesNews()

        thread {
            try {
                articlesArrayList = call.execute().body()?.articles!!
            } catch (e: Exception) {
                e.toString()
                val snackbar =
                        Snackbar.make(view,  "No Internet", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }.join()


        var likes : ArrayList<Like> = sqliteDatabaseLike?.listLikes()!!
        var likesSorted : ArrayList<Like> = getCurrentUserSortedLikeArray(likes)

        if (likesSorted.size >= 3) {
            for (i in 0..2) {
                var call: Call<News> = retrofit.api().getSearchNews(likesSorted[i].name)
                thread {
                    try {
                        articlesArrayList.addAll(call.execute().body()?.articles!!)
                    } catch (e: Exception) {
                        e.toString()
                        val snackbar =
                                Snackbar.make(view, "No Internet", Snackbar.LENGTH_LONG)
                        snackbar.show()
                    }
                }.join()
            }
        }

        mAdapter = MyAdapter(articlesArrayList, contextt!!)
        recyclerView.adapter = mAdapter
        val layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextt = context
    }

    fun saveArticles(articles: ArrayList<Article>){
        articlesArrayList = articles
    }

    /**
     * Method return sorted current user likes array
     */
    fun getCurrentUserSortedLikeArray(likes : ArrayList<Like>) : ArrayList<Like> {
        var like : ArrayList<Like> = ArrayList()
        if (likes != null && currentUserL != null) {
            for (l in likes) {
                if (l.user != currentUser) {
                    like.add(l)
                }
            }
        }
        likes.removeAll(like)
        likes.sortBy { it.numberOfLike }
        return likes
    }

}