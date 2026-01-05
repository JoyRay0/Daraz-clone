package com.rk_softwares.e_commerce.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.rk_softwares.e_commerce.model.Product

class Wishlist(
   context: Context
) : SQLiteOpenHelper(context, "wishlist.db", null, 1) {

    private val TABLE_NAME = "wishlist"
    private lateinit var db: SQLiteDatabase

    override fun onCreate(db: SQLiteDatabase?) {

        val createSql = "CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, product_id INTEGER, sku TEXT)"

        db?.execSQL(createSql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val updateSql = "DROP TABLE IF EXISTS $TABLE_NAME"

        db?.execSQL(updateSql)

        onCreate(db)

    }

    fun insert(id : Int, sku : String){

        val db = dbOpen(true)

        val cv = ContentValues()


        try {

            checkNullData("product_id", id.toString(), cv)
            checkNullData("sku", sku, cv)

            db.insert(TABLE_NAME, null, cv)

        }catch (e : Exception){
            e.printStackTrace()
        }



    }

    fun getAll() : List<Product>{

        val db = dbOpen()

        val list : MutableList<Product> = mutableListOf()

        var cursor : Cursor ? = null

        try {

            cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

            while (cursor.moveToNext()){

                val id = cursor.getInt(cursor.getColumnIndexOrThrow("product_id"))
                val sku = cursor.getString(cursor.getColumnIndexOrThrow("sku")) ?: ""

                list.add(
                    Product(
                        id = id,
                        sku = sku
                    )
                )
            }

        }catch (e : Exception){

            e.printStackTrace()

        }finally {

            cursor?.close()

        }


        return list
    }

    fun delete(sku : String) : Boolean{

        val db = dbOpen(true)

        try {

            db.delete(TABLE_NAME, "sku = ?", arrayOf(sku))

        }catch (e : Exception){

            e.printStackTrace()
        }

        return true
    }

    fun closeDB(){

        if (::db.isInitialized && db.isOpen) db.close()

    }

    private fun checkNullData(key : String, value : String, cv : ContentValues){

        if (!value.isEmpty()){

            cv.put(key, value)

        }else{

            cv.putNull(key)
        }

    }

    private fun dbOpen(writeable : Boolean = false) : SQLiteDatabase{

        if (!::db.isInitialized || !db.isOpen) db = if (writeable) writableDatabase else readableDatabase

        return db

    }
}