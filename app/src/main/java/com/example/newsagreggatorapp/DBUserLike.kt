package com.example.newsagreggatorapp

import android.content.Context

/**
 * This class represent Singleton Pattern of SqliteDatabaseUserLike
 *
 * @author Piotr Obara
 * 967793
 */
class DBUserLike {

    private var sqliteDatabaseLike: SqliteDatabaseUserLike? = null

    fun getInstance(context: Context): SqliteDatabaseUserLike? {

        if (sqliteDatabaseLike == null){
            sqliteDatabaseLike = SqliteDatabaseUserLike(context)
        }
        return sqliteDatabaseLike
    }

}