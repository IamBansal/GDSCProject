package com.example.firstfirebaseapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLHelper(context: Context) : SQLiteOpenHelper(context, DB_Name, null, 1) {

    companion object {
        const val DB_Name = "lists.db "
        const val TB_Name = "List "
        const val id = "ID"
        const val item = "L_item"
        const val price = "L_price"
        const val date = "L_date"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table $TB_Name (ID INTEGER PRIMARY KEY AUTOINCREMENT, L_item TEXT, L_price TEXT, L_date TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TB_Name")
    }

    fun addData(title_text : String, desc_text : String, date_text : String) {
        val DB = this.writableDatabase
        val values = ContentValues()
        values.put(item, title_text)
        values.put(price, desc_text)
        values.put(date, date_text)

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