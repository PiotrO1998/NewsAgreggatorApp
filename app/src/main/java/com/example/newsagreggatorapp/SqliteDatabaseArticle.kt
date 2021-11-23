package com.example.newsagreggatorapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.newsagreggatorapp.Constants.Companion.TABLE_ARTICLES
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_ID_ARTICLE
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_PUBLISHEDAT_ARTICLE
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_SOURCE_ARTICLE
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_TITLE_ARTICLE
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_URLTOIMAGE_ARTICLE
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_URL_ARTICLE
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_USER_ARTICLE

import com.example.newsagreggatorapp.Constants.Companion.COLUMN_DESCRIPTION_ARTICLE
import com.example.newsagreggatorapp.models.Source


/**
 * This class represent database that store articles
 *
 * @author Piotr Obara
 * 967793
 */
class SqliteDatabaseArticle(contex: Context) : SQLiteOpenHelper(contex, Constants.DATABASE_NAME_ARTICLE, null,
    Constants.DATABASE_VERSION_ARTICLE
) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_ARTICLE_TABLE = "CREATE TABLE ${TABLE_ARTICLES}(${COLUMN_ID_ARTICLE
        } INTEGER PRIMARY KEY, ${COLUMN_TITLE_ARTICLE} ARTICLE, ${COLUMN_SOURCE_ARTICLE
        } ARTICLE, ${COLUMN_PUBLISHEDAT_ARTICLE} ARTICLE, ${COLUMN_URLTOIMAGE_ARTICLE} ARTICLE, ${
        COLUMN_URL_ARTICLE} ARTICLE, ${COLUMN_DESCRIPTION_ARTICLE} ARTICLE, ${
        COLUMN_USER_ARTICLE} STRING)"
        db.execSQL(CREATE_ARTICLE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_ARTICLES}")
            onCreate(db)
        }
    }

    fun listArticles(): ArrayList<ArticleToSave> {

            val sql = "select * from ${Constants.TABLE_ARTICLES}"
            val db = this.readableDatabase
            val savedArticles = arrayListOf<ArticleToSave>()
            val cursor = db.rawQuery(sql, null)
            if (cursor.moveToFirst()) {
                do {
                    val id = Integer.parseInt(cursor.getString(0))
                    val title = cursor.getString(1)
                    val source = cursor.getString(2)
                    val publishedAt = cursor.getString(3)
                    val urlToImage = cursor.getString(4)
                    val url = cursor.getString(5)
                    val description = cursor.getString(6)
                    val user: String? = cursor.getString(7)
                    savedArticles.add(
                        ArticleToSave(
                            id,
                            title,
                            source,
                            publishedAt,
                            urlToImage,
                            url,
                            description,
                            user
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
            return savedArticles
    }

    fun addArticle(title: String, source: String, publishedAt: String, urlToImage: String, url: String,
                   description: String, user: String) {
        val values = ContentValues()
        values.put(Constants.COLUMN_TITLE_ARTICLE, title)
        values.put(Constants.COLUMN_SOURCE_ARTICLE, source)
        values.put(Constants.COLUMN_PUBLISHEDAT_ARTICLE, publishedAt)
        values.put(Constants.COLUMN_URLTOIMAGE_ARTICLE, urlToImage)
        values.put(Constants.COLUMN_URL_ARTICLE, url)
        values.put(COLUMN_DESCRIPTION_ARTICLE, description)
        values.put(Constants.COLUMN_USER_ARTICLE, user)
        val db = this.writableDatabase
        db.insert(Constants.TABLE_ARTICLES, null, values)
    }

    fun deleteArticle(name: String) {
        val db = this.writableDatabase
        db.delete(Constants.TABLE_ARTICLES, "${Constants.COLUMN_TITLE_ARTICLE} = ?", arrayOf(name.toString()))
    }

}