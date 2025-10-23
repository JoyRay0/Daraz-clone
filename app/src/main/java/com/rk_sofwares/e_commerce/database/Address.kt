package com.rk_sofwares.e_commerce.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.util.getColumnIndex
import java.util.ArrayList

class Address(
    context : Context

) : SQLiteOpenHelper(context, "address.db", null, 1){

    private var TABLE_NAME = "Address"
    private lateinit var db : SQLiteDatabase

    override fun onCreate(db: SQLiteDatabase?) {

        val createSql = "CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, number TEXT, region TEXT, city TEXT, district TEXT, address TEXT, landmark TEXT, address_category TEXT, shipping_address TEXT, billing_address TEXT)" ;

        db?.execSQL(createSql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newVersion: Int) {

        val upgradeSql = "DROP TABLE IF EXISTS $TABLE_NAME"

        db?.execSQL(upgradeSql)
        onCreate(db)
    }

    fun insert(name : String,
               number : String,
               region : String,
               city : String,
               district : String,
               address : String,
               landmark : String,
               categoryAddress : String,
               shippingAddress : String,
               billingAddress : String){

        val db = dbOpen(true)

        val cv = ContentValues()

        try {

            checkNullData("name", name, cv)
            checkNullData("number", number, cv)
            checkNullData("region", region, cv)
            checkNullData("city", city, cv)
            checkNullData("district", district, cv)
            checkNullData("address", address, cv)
            checkNullData("landmark", landmark, cv)
            checkNullData("address_category", categoryAddress, cv)
            checkNullData("shipping_address", shippingAddress, cv)
            checkNullData("billing_address", billingAddress, cv)

            db.insert(TABLE_NAME, null, cv)

        }catch (e : Exception){
            e.printStackTrace()
        }

    }

    fun  getAll() : ArrayList<HashMap<String, String>>{

        val db = dbOpen()

        val list : ArrayList<HashMap<String, String>> = ArrayList()

        var cursor : Cursor? = null

        try {

            cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

            while (cursor.moveToNext()){

                val map = HashMap<String, String>()
                map["name"] = cursor.getString(cursor.getColumnIndexOrThrow("name")) ?: ""
                map["number"] = cursor.getString(cursor.getColumnIndexOrThrow("number")) ?: ""
                map["region"] = cursor.getString(cursor.getColumnIndexOrThrow("region")) ?: ""
                map["city"] = cursor.getString(cursor.getColumnIndexOrThrow("city")) ?: ""
                map["district"] = cursor.getString(cursor.getColumnIndexOrThrow("district")) ?: ""
                map["address"] = cursor.getString(cursor.getColumnIndexOrThrow("address")) ?: ""
                map["landmark"] = cursor.getString(cursor.getColumnIndexOrThrow("landmark")) ?: ""
                map["address_category"] = cursor.getString(cursor.getColumnIndexOrThrow("address_category")) ?: ""
                map["shipping_address"] = cursor.getString(cursor.getColumnIndexOrThrow("shipping_address")) ?: ""
                map["billing_address"] = cursor.getString(cursor.getColumnIndexOrThrow("billing_address")) ?: ""
                list.add(map)

            }

        }catch (e : Exception){
            e.printStackTrace()
        }finally {
            cursor?.close()
        }


        return list

    }

    fun deleteOne(name : String) : Boolean{

        val db = dbOpen(true)

        try {

            db.delete(TABLE_NAME, "name = ?", arrayOf(name))

        }catch (e : Exception){
            e.printStackTrace()
        }

        return true
    }

    fun closeDB(){

        if (::db.isInitialized && db.isOpen){

            db.close()

        }

    }

    private fun checkNullData(key : String, value : String, cv : ContentValues){

        if (!value.isEmpty()){

            cv.put(key, value)

        }else{

            cv.putNull(key)
        }

    }

    private fun dbOpen(writeable : Boolean = false) : SQLiteDatabase{

        if (!::db.isInitialized || !db.isOpen){

           db =  if (writeable) writableDatabase else readableDatabase

        }
        return db
    }
}