/*
 * Created by Kartik Kumar Gujarati on 3/4/19 9:34 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/4/19 9:34 PM
 */

package com.kartik.networking.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kartik.networking.R
import com.kartik.networking.model.Gist
import kotlinx.android.synthetic.main.gist_list_item.view.*

class GistListAdapter(private var gists: ArrayList<Gist>, private val onDeleteClickListener: OnDeleteClickListener) :
        RecyclerView.Adapter<GistListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gist_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(gists[position])
    }

    override fun getItemCount(): Int = gists.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    fun updateData(data: ArrayList<Gist>) {
        gists = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindRepo(gist: Gist?) {
            itemView.gist_desc_tv.text = gist?.description
            itemView.gist_created_tv.text = gist?.created_at
            itemView.gist_delete_btn.setOnClickListener { onDeleteClickListener.onDeleteClicked(gist!!) }
        }
    }

    interface OnDeleteClickListener {
        fun onDeleteClicked(gist: Gist)
    }
}