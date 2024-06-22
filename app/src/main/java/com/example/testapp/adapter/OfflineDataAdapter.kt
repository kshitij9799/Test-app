package com.example.testapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.Notes
import com.example.testapp.utils.OnItemClickListener

class OfflineDataAdapter(private val listener: OnItemClickListener, private val itemList: List<Notes>) : RecyclerView.Adapter<OfflineDataAdapter.OfflineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfflineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.online_data_row_item, parent, false)
        return OfflineViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: OfflineViewHolder, position: Int) {
        holder.text.text = itemList[position].noteText
        holder.delete_btn.setOnClickListener {
            listener.onItemClick(itemList[position],true)
        }
    }

    inner class OfflineViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val text =  view.findViewById<TextView>(R.id.onlineTodoTextView)
        val delete_btn = view.findViewById<ImageView>(R.id.delete_optn)
    }

}