package com.example.newsagreggatorapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.MyAdapter

/**
 * This class represent saved articles activity
 *
 * @author Piotr Obara
 * 967793
 */
class SavedArticlesActivity : AppCompatActivity() {

    var sqliteDatabaseArticle = DBArticle().getInstance(this)
    var currentUser = LoggedInActivity().mAuth.currentUser?.email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        var loggedInActivity = LoggedInActivity()
        var array: ArrayList<ArticleToSave>? = sqliteDatabaseArticle!!.listArticles()
        setImageEmptyToNull(array!!)

        if (checkedIfCurrentUserHaveSavedArticles(array!!) == true) {
            array = getCurrentUserArticlesArray(array)
        } else {
            array = ArrayList()
        }

        val recyclerView = findViewById<View>(R.id.my_recycler_view_saved) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val mAdapter = AdapterSaved(array, this)
        recyclerView.adapter = mAdapter
    }

    override fun onRestart() {
        super.onRestart()
        setContentView(R.layout.activity_saved)

        var array: ArrayList<ArticleToSave>? = sqliteDatabaseArticle!!.listArticles()
        setImageEmptyToNull(array!!)

        if (checkedIfCurrentUserHaveSavedArticles(array!!) == true) {
            array = getCurrentUserArticlesArray(array)
        } else {
            array = ArrayList()
        }

        val recyclerView = findViewById<View>(R.id.my_recycler_view_saved) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val mAdapter = AdapterSaved(array, this)
        recyclerView.adapter = mAdapter
    }

    fun arrayListSavedArticles(): ArrayList<ArticleToSave> {
        var arrayArticles = sqliteDatabaseArticle!!.listArticles()
        return arrayArticles
    }

    fun checkedIfCurrentUserHaveSavedArticles(articles: ArrayList<ArticleToSave>): Boolean {
        var returningValue: Boolean = false

        for (article in articles) {
            if (article.user == currentUser) {
                returningValue = true
            }
        }
        return returningValue
    }

    fun setImageEmptyToNull(articles: ArrayList<ArticleToSave>) {
        for (article in articles) {
            if (article.urlToImage == "") {
                article.urlToImage = null.toString()
            }
        }
    }

    fun getCurrentUserArticlesArray(articlesArray: ArrayList<ArticleToSave>): ArrayList<ArticleToSave> {
        var array: ArrayList<ArticleToSave> = ArrayList()
        if (articlesArray.size != 0) {
            for (article in articlesArray) {
                if (article.user != currentUser) {
                    array.add(article)
                }
            }
        }
        articlesArray.removeAll(array)
    return articlesArray
    }
}