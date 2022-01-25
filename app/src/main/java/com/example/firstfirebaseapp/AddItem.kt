package com.example.firstfirebaseapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val DB = SQLHelper(applicationContext)
        val itemInput = findViewById<EditText>(R.id.etItem)
        val priceInput = findViewById<EditText>(R.id.etPrice)
        var date : String

        findViewById<Button>(R.id.btnAddItem).setOnClickListener {
            val itemText = itemInput.text.toString().trim()
            val priceText = priceInput.text.toString().trim()

            /*
            This code is for updated date at time when item is added. But is showing an error.

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
                date = current.format(formatter).toString()


                DB.addData(itemText, priceText, date)
                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AppMainView::class.java))

            } else {
                Toast.makeText(applicationContext, "Can't display date and time due to android version.", Toast.LENGTH_SHORT).show()
            }

             */

            DB.addData(itemText, priceText)
            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AppMainView::class.java))
        }

    }
}