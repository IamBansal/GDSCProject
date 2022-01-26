package com.example.firstfirebaseapp

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CustomItemAdapter(var context: Context,private var data : ArrayList<CustomItem>) : RecyclerView.Adapter<CustomItemAdapter.ViewHolder>() {

    private var DB = SQLHelper(context)

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val item: TextView = itemView.findViewById(R.id.tvItem)
        val price: TextView = itemView.findViewById(R.id.tvRate)
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val id: TextView = itemView.findViewById(R.id.id)
        val card : CardView = itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater.from(context).inflate(R.layout.custom_item, parent, false)
        return ViewHolder(layout)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.item.text = data[position].item
        holder.price.text = "₹ ${data[position].price}"
        holder.id.text = data[position].id.toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")
            holder.date.text = current.format(formatter)
            holder.date.text = data[position].date
        } else {
            Toast.makeText(context, "Can't display date and time due to lower android version.", Toast.LENGTH_SHORT).show()
        }


        holder.card.setOnClickListener {

            //Deleting from database
            DB.deleteData(data[position].id.toString())

            //deleting from list
            data.remove(data[position])

            //notifying that it is removed.
            notifyItemRemoved(position)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

}