package com.example.newsagreggatorapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

/**
 * This class represent article activity, thorugh this activity user can logout, see saved articles, choose category
 * that user want to see articles and set notyfications settings
 *
 * @author Piotr Obara
 * 967793
 */
class ArticleActivity: AppCompatActivity() {

    var titleA: String? = ""
    var sourceA: String? = ""
    var publishedAtA: String? = ""
    var urlA: String? = ""
    var urlToImageA: String? = ""
    var descriptionA: String? = ""
    var currentUser = LoggedInActivity().mAuth.currentUser?.email
    var currentUserL = LoggedInActivity().mAuth.currentUser

    var db: DBArticle = DBArticle()
    var sqliteDatabaseArticle: SqliteDatabaseArticle? = db.getInstance(this)

    var dbLike: DBUserLike = DBUserLike()
    var sqliteDatabaseUserLike: SqliteDatabaseUserLike? = dbLike.getInstance(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_article)

        var articlesArray = sqliteDatabaseArticle?.listArticles()

        val image = findViewById<View>(R.id.articleImage) as ImageView
        val source = findViewById<View>(R.id.articleSource) as TextView
        val title = findViewById<View>(R.id.articleTitle) as TextView
        val description = findViewById<View>(R.id.articleDescription) as TextView
        val publishedAt = findViewById<View>(R.id.articleDate) as TextView
        val url: String? = null

        var imageButtonSaveArticle = findViewById<View>(R.id.articleImageButtonSave)
        var buttonFullArticle = findViewById<View>(R.id.articleButton)
        var imageButtonShareArticle = findViewById<View>(R.id.articleImageButtonShare)
        var imageButtonLikeArticle = findViewById<View>(R.id.articleImageButtonLike)

        setImageButton(imageButtonSaveArticle)

        var extras: Bundle? = getIntent().extras

        if (extras != null) {
            urlToImageA = extras.getString("urlToImage")
            Picasso.get().load(urlToImageA.toString()).fit().into(image)
            sourceA = extras.getString("source")
            source.text = sourceA
            titleA = extras.getString("title")
            title.text = titleA
            descriptionA = extras.getString("description")
            description.text = descriptionA
            publishedAtA = extras.getString("publishedAt")
            publishedAt.text = publishedAtA
            urlA = extras.getString("url")

            setImageEmptyIfnull()

        }

        buttonFullArticle.setOnClickListener { view ->
            val newIntent = Intent(this, WebViewActivity::class.java)
            newIntent.putExtra("url", urlA)
            startActivity(newIntent)
        }

        imageButtonSaveArticle.setOnClickListener {

            articlesArray = sqliteDatabaseArticle?.listArticles()
            if (currentUserL != null && checkIfUserSavedArticle(titleA!!, articlesArray!!) == false) {

                sqliteDatabaseArticle?.addArticle(
                        titleA!!,
                        sourceA!!,
                        publishedAtA!!,
                        urlToImageA!!,
                        urlA!!,
                        descriptionA!!,
                        currentUser!!
                )

                imageButtonSaveArticle.setBackgroundResource(R.drawable.ic_saved)

                val snackbar =
                        Snackbar.make(imageButtonSaveArticle, "Article Saved!", Snackbar.LENGTH_LONG)
                snackbar.show()

            } else if (currentUser != null && checkIfUserSavedArticle(titleA!!, articlesArray!!) == true) {
                imageButtonSaveArticle.setBackgroundResource(R.drawable.ic_save)

                sqliteDatabaseArticle?.deleteArticle(titleA!!)
                articlesArray = sqliteDatabaseArticle?.listArticles()

                AdapterSaved(articlesArray!!, SavedArticlesActivity()).setChanges(articlesArray!!)

                val snackbar = Snackbar.make(imageButtonSaveArticle, "Article deleted",
                        Snackbar.LENGTH_LONG)
                snackbar.show()
            } else {
                val snackbar = Snackbar.make(imageButtonSaveArticle,
                        "You need too be log in to save article", Snackbar.LENGTH_LONG
                )
                snackbar.show()
            }
        }

        imageButtonShareArticle.setOnClickListener {
            var intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, urlA)
            startActivity(Intent.createChooser(intent, "Share Article"))
        }

        imageButtonLikeArticle.setOnClickListener {

            if (currentUserL != null) {
                sqliteDatabaseUserLike?.addLike(sourceA!!, currentUser!!)
                val snackbar = Snackbar.make(imageButtonLikeArticle, "Article liked",
                        Snackbar.LENGTH_LONG)
                snackbar.show()
            } else {
                val snackbar = Snackbar.make(imageButtonLikeArticle, "You need to be loged in to like articles",
                        Snackbar.LENGTH_LONG)
                snackbar.show()
            }

        }

    }

    override fun onStart() {
        super.onStart()

        var imageButtonSaveArticle = findViewById<View>(R.id.articleImageButtonSave)
        setImageButton(imageButtonSaveArticle)
    }

    override fun onResume() {
        super.onResume()

        var imageButtonSaveArticle = findViewById<View>(R.id.articleImageButtonSave)
        setImageButton(imageButtonSaveArticle)
    }

    fun checkIfUserSavedArticle(title: String, articlesArray: ArrayList<ArticleToSave>): Boolean{
        var returningValue: Boolean = false

        for(article in articlesArray!!){
            if (article.title == title && article.user == currentUser){
                returningValue = true
            }
        }
        return returningValue
    }

    fun setImageEmptyIfnull() {
        if (urlToImageA == null) {
            urlToImageA = ""
        } else if (sourceA == null) {
            sourceA = ""
        } else if (publishedAtA == null) {
            publishedAtA = ""
        } else if (urlToImageA == null) {
            urlToImageA = ""
        } else if (urlA == null) {
            urlA = ""
        } else if (descriptionA == null) {
            descriptionA = ""
        } else if (currentUser == null) {
            currentUser = ""
        }
    }

    fun setImageButton(imageButtonSaveArticle: View){
        var articlesArray = sqliteDatabaseArticle?.listArticles()
        System.out.println("helo     ")
        for(article in articlesArray!!){
            if(article.title == titleA && article.user == currentUser){
                System.out.println("helo     s")
                imageButtonSaveArticle.setBackgroundResource(R.drawable.ic_saved)
                break
            } else {
                System.out.println("helo     su")
                imageButtonSaveArticle.setBackgroundResource(R.drawable.ic_save)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var position: Int = 0
        var articlesArray = sqliteDatabaseArticle?.listArticles()
        for(article in articlesArray!!){
            position += 1
            if(article.title == titleA){
                position = articlesArray.indexOf(article)
            }
        }
    AdapterSaved(articlesArray!!, this).notifyDataSetChanged()

    }

    fun ifLikeAlreadyAdded(arrayOfLikes: ArrayList<Like>): Boolean {
        var returningValue: Boolean = false

        for(like in arrayOfLikes!!) {
            if (like.name == sourceA){
                returningValue = true
                break
            }
    }
        return returningValue
    }

}

