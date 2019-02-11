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
import com.google.gson.Gson
import com.kartik.networking.model.Repositories
import com.kartik.networking.model.Repository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var mRepoAdapter: CustomListAdapter
    private val GITHUB_URL = "https://api.github.com/search/repositories?q=language:kotlin&sort=stars"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repo_rl.layoutManager = LinearLayoutManager(this)

        val itemDecor = DividerItemDecoration(repo_rl.context,
        LinearLayoutManager(this).orientation)
        repo_rl.addItemDecoration(itemDecor)
        mRepoAdapter = CustomListAdapter(ArrayList())
        repo_rl.adapter = mRepoAdapter
        makeAPICall()
        refreshButton.setOnClickListener { makeAPICall() }
    }

    private fun makeAPICall() {
        doAsync {
            val repoListJsonStr = URL(GITHUB_URL).readText()
            val repoList = Gson().fromJson(repoListJsonStr, Repositories::class.java)
            uiThread {
                mRepoAdapter.updateData(repoList.items as ArrayList<Repository>)
            }
        }
    }
}
