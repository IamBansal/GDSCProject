package com.example.firstfirebaseapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLHelper(context: Context) : SQLiteOpenHelper(context, DB_Name, null, 1) {

    companion object {
        const val DB_Name = "items.db "
        const val TB_Name = "Item "
        const val id = "ID"
        const val item = "I_item"
        const val price = "I_price"
        const val date = "I_date"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table $TB_Name (ID INTEGER PRIMARY KEY AUTOINCREMENT, I_item TEXT, I_price TEXT, I_date TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TB_Name")
    }

    fun addData(title_text : String, desc_text : String) {
        val DB = this.writableDatabase
        val values = ContentValues()
        values.put(item, title_text)
        values.put(price, desc_text)
//        values.put(date, date_text)

        DB.insert(TB_Name, null, values)
    }

//    fun deleteData(id: String): Int {
//        val DB = this.writableDatabase
//        return DB.delete(TB_Name, "id = ?", arrayOf(id))
//    }
    fun deleteData(id: String): Int {
        val DB = this.writableDatabase
        return DB.delete(TB_Name, "id = ?", arrayOf(id))
    }

    val dataGetter : Cursor
        get() {
            val DB = this.writableDatabase
            return DB.rawQuery("select * from $TB_Name", null)
        }
}