package com.rk_softwares.e_commerce.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SearchHistory(

    context: Context

) : SQLiteOpenHelper(context, "search_history.db", null, 1){

    private val TABLE_NAME = "History"
    private lateinit var db : SQLiteDatabase


    override fun onCreate(db: SQLiteDatabase?) {

        val create_sql = "CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, history_data TEXT)"

        db?.execSQL(create_sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val update_table = "DROP TABLE IF EXISTS $TABLE_NAME"

        db?.execSQL(update_table)
        onCreate(db)

    }

    fun insert(text : String){

        val db = dbOpen(true)

        val cv = ContentValues()

        try {

            if (!text.isEmpty()){

                cv.put("history_data", text)

            }else{

                cv.putNull("history_data")

            }

        }catch (e : Exception){

            e.printStackTrace()
        }

        db.insert(TABLE_NAME, null, cv)


    }

    fun getAll() : ArrayList<HashMap<String, String>>{

        val db = dbOpen()

        val list : ArrayList<HashMap<String, String>> = ArrayList()

        var cursor : Cursor? = null

        try {

            cursor = db.rawQuery("SELECT history_data FROM $TABLE_NAME", null)

            while (cursor.moveToNext()){

                val map = HashMap<String, String>()
                map["history_data"] = cursor.getString(cursor.getColumnIndexOrThrow("history_data")) ?: ""
                list.add(map)

            }

        }catch (e : Exception){

            e.printStackTrace()

        }

        cursor?.close()

        return list

    }

    fun deleteAll(){

        val db = dbOpen(true)

        db.execSQL("DELETE FROM $TABLE_NAME")

    }

    fun closeDB(){

        if (::db.isInitialized && db.isOpen){

            db.close()

        }

    }

    private fun dbOpen(writeable : Boolean = false) : SQLiteDatabase{

        if (!::db.isInitialized || !db.isOpen){

            db = if (writeable) writableDatabase else readableDatabase

        }

        return db

    }

}