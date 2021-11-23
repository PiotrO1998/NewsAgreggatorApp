package com.example.newsagreggatorapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.newsagreggatorapp.Constants.Companion.DATABASE_NAME
import com.example.newsagreggatorapp.Constants.Companion.DATABASE_VERSION
import com.example.newsagreggatorapp.Constants.Companion.TABLE_PREFERENCES
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_ID
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_PREFERENCE_TITLE
import com.example.newsagreggatorapp.Constants.Companion.COLUMN_USER
import java.util.prefs.Preferences

/**
 * This class represent Database that store Preference
 *
 * @author Piotr Obara
 * 967793
 */
class SqliteDatabase(contex: Context) : SQLiteOpenHelper(contex, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PREFERENCE_TABLE = "CREATE TABLE $TABLE_PREFERENCES($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_PREFERENCE_TITLE PREFERENCE, $COLUMN_USER STRING)"
        db.execSQL(CREATE_PREFERENCE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_PREFERENCES")
            onCreate(db)
        }
    }

    fun listPreferences(): ArrayList<Preference> {
        val sql = "select * from $TABLE_PREFERENCES"
        val db = this.readableDatabase
        val storePreferences = arrayListOf<Preference>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = Integer.parseInt(cursor.getString(0))
                val name = cursor.getString(1)
                val user = cursor.getString(2)
                storePreferences.add(Preference(id, name, user))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return storePreferences
    }

    fun addTask(preferenceName: String, user: String) {
        val values = ContentValues()
        values.put(COLUMN_PREFERENCE_TITLE, preferenceName)
        values.put(COLUMN_USER, user)
        val db = this.writableDatabase
        db.insert(TABLE_PREFERENCES, null, values)
    }

    /*
    fun deleteTask(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_PREFERENCES, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }
     */

    fun deleteTask(name: String) {
        val db = this.writableDatabase
        db.delete(TABLE_PREFERENCES, "$COLUMN_PREFERENCE_TITLE = ?", arrayOf(name.toString()))
    }

}