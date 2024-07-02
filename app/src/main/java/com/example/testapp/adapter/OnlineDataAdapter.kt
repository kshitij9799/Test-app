package com.example.testapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.Todo

class OnlineDataAdapter(private val onlineData: MutableList<Todo> ) :  RecyclerView.Adapter<OnlineDataAdapter.OnlineViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.online_data_row_item, parent, false)

        return OnlineViewHolder(view)
    }

    override fun getItemCount(): Int {
        return onlineData.size
    }

    override fun onBindViewHolder(holder: OnlineViewHolder, position: Int) {
        holder.text.text = onlineData[position].todo
    }

    inner class OnlineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text =  view.findViewById<TextView>(R.id.onlineTodoTextView)
    }
}