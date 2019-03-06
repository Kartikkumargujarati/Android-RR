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
import com.kartik.networking.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.mock_list_item.view.*

class MockDataListAdapter(private var posts: ArrayList<User>) : RecyclerView.Adapter<MockDataListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mock_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    fun updateData(data: ArrayList<User>) {
        posts = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindRepo(user: User?) {

            with(user) {
                itemView.user_id.text = user?.id
                itemView.user_fName.text = user?.first_name
                itemView.user_lName.text = user?.last_name
                Picasso.get().load(user?.avatar).into(itemView.avatar_icon)
            }
        }
    }
}