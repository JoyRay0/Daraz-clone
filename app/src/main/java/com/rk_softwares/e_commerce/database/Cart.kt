package com.rk_softwares.e_commerce.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.rk_softwares.e_commerce.model.Product

class Cart(
    context: Context
) : SQLiteOpenHelper(context, "cart.db", null, 1) {

    private val TABLE_NAME = "cart"

    private lateinit var db: SQLiteDatabase

    override fun onCreate(db: SQLiteDatabase?) {

        val create_sql = "CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, imageUrl Text, sku TEXT, title TEXT, price VARCHER, discount VARCHER)"

        db?.execSQL(create_sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val update_sql = "DROP TABLE IF EXISTS $TABLE_NAME"

        db?.execSQL(update_sql)
        onCreate(db)

    }

    fun insert(sku : String, imageUrl : String, title : String, price : Double, discount : Double){

        val db = dbOpen(true)

        val cv = ContentValues()

        try {

            cv.put("sku", sku)
            cv.put("imageUrl", imageUrl)
            cv.put("title", title)
            cv.put("price", price)
            cv.put("discount", discount)

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

            cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY id DESC", null)

            while (cursor.moveToNext()){

                val imageUrl = cursor.getString(cursor.getColumnIndexOrThrow("imageUrl"))
                val sku = cursor.getString(cursor.getColumnIndexOrThrow("sku"))
                val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
                val price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
                val discount = cursor.getDouble(cursor.getColumnIndexOrThrow("discount"))

                list.add(
                    Product(
                        sku = sku,
                        thumbnail = imageUrl,
                        title = title,
                        price = price,
                        discountPercentage = discount
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

        return try {

            db.delete(TABLE_NAME, "sku = ?", arrayOf(sku)) > 0

        }catch (e : Exception){

            e.printStackTrace()
            false

        }

    }

    fun checkDuplicateData(sku: String) : Boolean{

        if (sku.isEmpty()) return false

        val db = dbOpen()

        var cursor : Cursor? = null

        var exists = false

        try {

            cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE sku = ?", arrayOf(sku))

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

        if (::db.isInitialized && db.isOpen) db.close()

    }

    private fun dbOpen(writeable : Boolean = false) : SQLiteDatabase{

        if (!::db.isInitialized || !db.isOpen) db = if (writeable) writableDatabase else readableDatabase

        return db

    }


}