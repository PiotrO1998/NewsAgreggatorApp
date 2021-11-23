package com.example.newsagreggatorapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_ID_LIKE
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_LIKE_NAME
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_NUMBER_OF_LIKE
import com.example.newsagreggatorapp.Constants.Companion.TABLE_LIKES

/**
 * This class represent Database that store Like
 *
 * @author Piotr Obara
 * 967793
 */
class SqliteDatabaseUserLike (contex: Context) : SQLiteOpenHelper(contex, Constants.DATABASE_NAME_LIKE, null, 7) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_LIKE_TABLE = "CREATE TABLE ${TABLE_LIKES}(${COLUMN_ID_LIKE} INTEGER PRIMARY KEY, ${COLUMN_LIKE_NAME} STRING, ${COLUMN_NUMBER_OF_LIKE} INTEGER, ${Constants.COLUMN_USER_LIKE} STRING)"
        db.execSQL(CREATE_LIKE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_LIKES}")
            onCreate(db)
        }
    }

    fun listLikes(): ArrayList<Like> {
        val sql = "select * from ${Constants.TABLE_LIKES}"
        val db = this.readableDatabase
        val storeLikes = arrayListOf<Like>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = Integer.parseInt(cursor.getString(0))
                val name = cursor.getString(1)
                val numberOfLike = Integer.parseInt(cursor.getString(2))
                val user = cursor.getString(3)

                storeLikes.add(Like(id, name, numberOfLike, user))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return storeLikes
    }

    fun addLike(likeName: String, user: String) {
        var likes = listLikes()

        if (checkIfLikeAdded(likes, likeName, user) == false) {
            val values = ContentValues()
            values.put(Constants.COLUMN_LIKE_NAME, likeName)
            values.put(Constants.COLUMN_NUMBER_OF_LIKE, 1)
            values.put(Constants.COLUMN_USER_LIKE, user)
            val db = this.writableDatabase
            db.insert(Constants.TABLE_LIKES, null, values)
        } else {
            likes = addNumber(likes, likeName, user)
            deleteDatabase()
            populateDatabase(likes)
        }
    }

    fun deleteTask(name: String) {
        val db = this.writableDatabase
        db.delete(Constants.TABLE_LIKES, "${Constants.COLUMN_LIKE_NAME} = ?", arrayOf(name.toString()))
    }

    fun checkIfLikeAdded(likes : ArrayList<Like>, likeName: String, user: String): Boolean{
        var returningValue : Boolean = false
        for (like in likes) {
            if (like.name == likeName && like.user == user){
                returningValue = true
            }
        }
        return returningValue
    }

    fun addNumber(likes : ArrayList<Like>, likeName: String, user: String): ArrayList<Like> {
        for (like in likes) {
            if (like.name == likeName && like.user == user){
                like.numberOfLike += 1
            }
        }
        return likes
    }

    fun deleteDatabase(){
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_LIKES}")
        onCreate(db)
    }

    private fun populateDatabase(likes : ArrayList<Like>){
        for (like in likes){
            val values = ContentValues()
            values.put(Constants.COLUMN_LIKE_NAME, like.name)
            values.put(Constants.COLUMN_NUMBER_OF_LIKE, like.numberOfLike)
            values.put(Constants.COLUMN_USER_LIKE, like.user)
            val db = this.writableDatabase
            db.insert(Constants.TABLE_LIKES, null, values)
        }
    }


}