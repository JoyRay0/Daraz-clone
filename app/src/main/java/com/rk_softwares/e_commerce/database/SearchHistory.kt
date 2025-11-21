package com.rk_softwares.e_commerce.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.room.util.query

class SearchHistory(

    context: Context

) : SQLiteOpenHelper(context, "search_history.db", null, 5){

    private val TABLE_NAME = "History"
    private lateinit var db : SQLiteDatabase


    override fun onCreate(db: SQLiteDatabase?) {

        val create_sql = "CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, history_data TEXT, category_data TEXT)"

        db?.execSQL(create_sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val update_table = "DROP TABLE IF EXISTS $TABLE_NAME"

        db?.execSQL(update_table)
        onCreate(db)

    }

    fun queryTextInsert(text : String){

        val db = dbOpen(true)

        val cv = ContentValues()

        try {

            if (text.isNotEmpty()){

                cv.put("history_data", text)

            }

        }catch (e : Exception){

            e.printStackTrace()
        }

        db.insert(TABLE_NAME, null, cv)


    }

    fun categoryDataInsert(categoryText : String){

        val db = dbOpen(true)

        val cv = ContentValues()

        try {

            if (categoryText.isNotEmpty()){

                cv.put("category_data", categoryText)

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

            cursor = db.rawQuery("SELECT history_data FROM $TABLE_NAME WHERE history_data IS NOT NULL AND history_data != '' ", null)

            while (cursor.moveToNext()){

                val map = HashMap<String, String>()
                map["history_data"] = cursor.getString(cursor.getColumnIndexOrThrow("history_data")) ?: ""
                list.add(map)

            }

        }catch (e : Exception){

            e.printStackTrace()

        }finally {

            cursor?.close()

        }

        Log.e("data", list.toString())

        return list

    }

    fun getLastCategoryData() : String?{

        val db = dbOpen()

        var cursor : Cursor? = null

        return try {

            cursor = db.rawQuery("SELECT category_data FROM $TABLE_NAME WHERE category_data IS NOT NULL AND category_data != '' ORDER BY id DESC LIMIT 1", null)

            if (cursor.moveToFirst()){

                cursor.getString(cursor.getColumnIndexOrThrow("category_data"))

            }else null

        }catch (e : Exception){

            e.printStackTrace()
            null

        }finally {

            cursor?.close()

        }

    }

    fun deleteAll(){

        val db = dbOpen(true)

        db.execSQL("DELETE FROM $TABLE_NAME")

    }

    fun checkDuplicateData(queryText: String, columName : String) : Boolean{

        if (queryText.isEmpty()) return false

        val db = dbOpen()

        var cursor : Cursor? = null

        var exists = false

        try {

            cursor = db.rawQuery("SELECT $columName FROM $TABLE_NAME WHERE $columName = ?", arrayOf(queryText))


            if (cursor.moveToFirst()){

                exists = true
            }

        }catch (e : Exception){

            e.printStackTrace()

        }finally {

            cursor?.close()

        }

        return exists
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