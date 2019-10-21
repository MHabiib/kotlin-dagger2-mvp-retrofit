package com.future.mvpexample.dagger

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.BookRes
import com.future.mvpexample.R
import kotlinx.android.synthetic.main.list_item.view.*

class ItemAdapter(val postList: List<BookRes>, val context: Context) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item,
            parent, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
/*
        holder.itemView.txtPostTitle.text = postList.get(position).title
        holder.itemView.txtPostBody.text = postList.get(position).body*/

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}