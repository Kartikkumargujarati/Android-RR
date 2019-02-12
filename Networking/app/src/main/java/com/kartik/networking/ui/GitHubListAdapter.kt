/*
 * Created by Kartik Kumar Gujarati on 2/10/19 12:01 AM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/10/19 12:01 AM
 */

package com.kartik.networking.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kartik.networking.R
import com.kartik.networking.model.GitHubRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class GitHubListAdapter(private var gitHubRepositories: ArrayList<GitHubRepository>) : RecyclerView.Adapter<GitHubListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(gitHubRepositories[position])
    }

    override fun getItemCount(): Int = gitHubRepositories.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int =  position

    fun updateData(data: ArrayList<GitHubRepository>) {
        gitHubRepositories = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindRepo(repo: GitHubRepository?) {

            with(repo) {
                itemView.repo_name.text = repo?.name
                itemView.repo_language.text = repo?.language
                itemView.repo_starCount.text = "Stars: ${repo?.stargazers_count}"
                Picasso.get().load(repo?.owner?.avatar_url).into(itemView.repo_icon)
            }
        }
    }
}