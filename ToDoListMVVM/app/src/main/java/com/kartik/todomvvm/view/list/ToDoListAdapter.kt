/*
 * Created by Kartik Kumar Gujarati on 5/5/19 12:34 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/4/19 8:03 PM
 */

package com.kartik.todomvvm.view.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kartik.todomvvm.R
import com.kartik.todomvvm.model.ToDoItem
import kotlinx.android.synthetic.main.item_list_content.view.*

class ToDoListAdapter(private var values: List<ToDoItem>,
                      private val onClickListener: (ToDoItem) -> Unit, private val onCheckListener: (ToDoItem) -> Unit
) : RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    fun updateData(data: ArrayList<ToDoItem>) {
        values = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(values[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindRepo(item: ToDoItem?) {
            itemView.item_header_tv.text = item?.header
            itemView.item_content_tv.text = item?.content
            itemView.setOnClickListener { onClickListener(item!!) }
            itemView.item_done_cb.setOnCheckedChangeListener { _, isChecked -> if (isChecked) {onCheckListener(item!!)} }
        }
    }
}