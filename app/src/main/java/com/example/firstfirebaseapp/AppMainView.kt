package com.example.firstfirebaseapp

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class AppMainView : AppCompatActivity() {

    private lateinit var lists : ArrayList<CustomItem>
    private lateinit var DB : SQLHelper
    private lateinit var data : Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_main_view)

        findViewById<FloatingActionButton>(R.id.btnAddItem).setOnClickListener {
            startActivity(Intent(this, AddItem::class.java))
        }

        lists = ArrayList<CustomItem>()
        DB = SQLHelper(applicationContext)
        data = DB.dataGetter

        val adapter = CustomItemAdapter(applicationContext, lists)
        val recyclerView = findViewById<RecyclerView>(R.id.rvRecyclerView)

        showData()

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)
        recyclerView.adapter = adapter

    }

    //For closing the app when back is pressed at launcher.
    override fun onBackPressed() {
        finishAffinity()
    }

    private fun showData(){
        if (data.count == 0) {
            Toast.makeText(this, "There is no item in list.", Toast.LENGTH_SHORT).show()
        }

        while (data.moveToNext()) {
            lists.add(CustomItem(data.getInt(0),data.getString(1),data.getString(2), data.getString(3)))
        }
    }

    override fun onStart() {
        super.onStart()
        showData()
    }

}