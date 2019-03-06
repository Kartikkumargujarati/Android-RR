/*
 * Created by Kartik Kumar Gujarati on 3/4/19 8:34 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/4/19 8:34 PM
 */

package com.kartik.networking.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.kartik.networking.R
import com.kartik.networking.data.RemoteServiceRepositoryImpl
import com.kartik.networking.model.GitHubRepositories
import com.kartik.networking.model.GitHubRepository
import kotlinx.android.synthetic.main.fragment_repo_list.view.*

class GitHubRepoListFragment : Fragment(), GitHubRepoListView {

    private lateinit var mRepoAdapter: GitHubListAdapter
    private lateinit var mListPresenterImpl: ListPresenterImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_repo_list, container, false)

        val itemDecor = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rootView.repo_rl.addItemDecoration(itemDecor)
        //repo_rl.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        rootView.repo_rl.layoutManager = mLayoutManager

        mListPresenterImpl = ListPresenterImpl(this, RemoteServiceRepositoryImpl())

        mRepoAdapter = GitHubListAdapter(ArrayList())
        rootView.repo_rl.adapter = mRepoAdapter
        doLoadData()

        return rootView
    }

    override fun onDetach() {
        super.onDetach()
        mListPresenterImpl.detach()
    }

    override fun showGitHubRepoList(repositoryList: GitHubRepositories?) {
        mRepoAdapter.updateData(repositoryList?.items as ArrayList<GitHubRepository>)
    }

    override fun showErrorToast() {
        Toast.makeText(activity, "Error Loading data.", Toast.LENGTH_SHORT).show()
    }

    private fun doLoadData() {
        mListPresenterImpl.getKotlinRepositories()
    }

    companion object {
        val TAG = GitHubRepoListFragment::class.java.canonicalName
        @JvmStatic
        fun newInstance() = GitHubRepoListFragment()
    }
}
