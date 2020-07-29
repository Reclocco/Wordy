package com.example.wordy.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.wordy.MainActivity
import com.example.wordy.R
import com.example.wordy.database.WordReaderDbHelper.Companion.COLUMN_NAME
import com.example.wordy.database.WordReaderDbHelper.Companion.TABLE_NAME
import java.io.File
import java.io.InputStream
import java.lang.Exception
import java.lang.StringBuilder
import java.nio.charset.Charset
import java.util.*

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE $TABLE_NAME ($COLUMN_NAME TEXT)"

private const val SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS $TABLE_NAME"


class WordReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        println("EXECUTING ON CREATE")

        val fileContent = MainActivity::class.java.getResource("/res/raw/slowa.txt").readText().lines()

        fileContent.forEach() {
            // Create a new map of values, where column names are the keys
            val values = ContentValues().apply {
                put(COLUMN_NAME, it)
            }

            // Insert the new row, returning the primary key value of the new row
            db.insert(TABLE_NAME, null, values)
            println("INSERTED $it")
        }
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    fun getText(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
    fun ifWordExist(potentialWord: String): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_NAME FROM $TABLE_NAME WHERE $COLUMN_NAME LIKE '$potentialWord'", null)
        cursor.moveToFirst()
        var myWord = ""

        try {
            myWord = myWord.plus(cursor.getString(cursor.getColumnIndex(WordReaderDbHelper.COLUMN_NAME)))
            cursor.close()

            return if (myWord != "") {
                1
            } else {
                return 0
            }
        } catch (e: Exception){
            return 0
        }
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 7
        const val DATABASE_NAME = "WordReader.db"
        const val TABLE_NAME = WordReaderContract.WordEntry.TABLE_NAME
        const val COLUMN_NAME = WordReaderContract.WordEntry.COLUMN_NAME_TITLE
    }
}