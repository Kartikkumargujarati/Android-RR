/*
 * Created by Kartik Kumar Gujarati on 2/9/19 11:37 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/9/19 6:08 PM
 */

package com.kartik.networking.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kartik.networking.R
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration
import android.widget.Toast
import com.kartik.networking.data.GitHubServiceRepositoryImpl
import com.kartik.networking.model.GitHubRepositories
import com.kartik.networking.model.GitHubRepository

class ListActivity : AppCompatActivity(), ListView {

    private lateinit var mRepoAdapter: GitHubListAdapter
    private lateinit var mListPresenterImpl: ListPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemDecor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        repo_rl.addItemDecoration(itemDecor)
        //repo_rl.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        repo_rl.layoutManager = mLayoutManager

        mListPresenterImpl = ListPresenterImpl(this, GitHubServiceRepositoryImpl())

        mRepoAdapter = GitHubListAdapter(ArrayList())
        repo_rl.adapter = mRepoAdapter
        doLoadData()
        refreshButton.setOnClickListener { doLoadData() }
    }

    override fun showList(repositoryList: GitHubRepositories?) {
        mRepoAdapter.updateData(repositoryList?.items as ArrayList<GitHubRepository>)
    }

    override fun showErrorToast() {
        Toast.makeText(this, "Error Loading data.", Toast.LENGTH_SHORT).show()
    }

    private fun doLoadData() {
        mListPresenterImpl.getKotlinRepositories()
    }
}
