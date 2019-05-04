/*
 * Created by Kartik Kumar Gujarati on 5/4/19 4:56 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/4/19 4:56 PM
 */

package com.kartik.todomvvm.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kartik.todomvvm.R
import com.kartik.todomvvm.dummy.DummyContent
import kotlinx.android.synthetic.main.item_list_content.view.*

class ToDoListAdapter(private var values: List<DummyContent.DummyItem>,
                      private val onClickListener: OnClickListener) : RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ToDoListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    fun updateData(data: ArrayList<DummyContent.DummyItem>) {
        values = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ToDoListAdapter.ViewHolder, position: Int) {
        holder.bindRepo(values[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindRepo(item: DummyContent.DummyItem?) {
            itemView.id_text.text = item?.id
            itemView.content.text = item?.content
            itemView.setOnClickListener { onClickListener.onClick(item) }
        }
    }

    interface OnClickListener {
        fun onClick(item: DummyContent.DummyItem?)
    }
}