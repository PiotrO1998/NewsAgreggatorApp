package com.example.newsagreggatorapp

import android.content.Context

/**
 * This class represent Singleton Pattern of SqliteDatabaseArticle
 *
 * @author Piotr Obara
 * 967793
 */
class DBArticle {

    private var sqliteDatabaseArticle: SqliteDatabaseArticle? = null

    fun getInstance(context: Context): SqliteDatabaseArticle? {

        if (sqliteDatabaseArticle == null){
            sqliteDatabaseArticle = SqliteDatabaseArticle(context)
        }
        return sqliteDatabaseArticle
    }

}