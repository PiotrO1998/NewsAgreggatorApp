package com.example.newsagreggatorapp

import android.content.Context

/**
 * This class represent Singleton Pattern of SqliteDatabase
 *
 * @author Piotr Obara
 * 967793
 */
class DB {

   private var sqliteDatabase: SqliteDatabase? = null

    fun getInstance(context: Context): SqliteDatabase? {

       if (sqliteDatabase == null){
           sqliteDatabase = SqliteDatabase(context)
       }
           return sqliteDatabase
   }

}