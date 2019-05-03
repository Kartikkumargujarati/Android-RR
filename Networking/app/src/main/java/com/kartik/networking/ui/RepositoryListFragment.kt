/*
 * Created by Kartik Kumar Gujarati on 3/4/19 8:34 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/4/19 8:34 PM
 */

package com.kartik.networking.ui

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
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
import com.kartik.networking.model.Repository
import kotlinx.android.synthetic.main.fragment_repo_list.view.*

class RepositoryListFragment : Fragment(), RepositoryListView {

    private lateinit var mRepoAdapter: RepositoryListAdapter
    private lateinit var mListPresenterImpl: ListPresenterImpl
    private var mFab: FloatingActionButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_repo_list, container, false)
        retainInstance = true
        mFab = activity?.findViewById(R.id.fab)
        mFab?.hide()
        val itemDecor = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rootView.repo_rl.addItemDecoration(itemDecor)
        //repo_rl.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        rootView.repo_rl.layoutManager = mLayoutManager

        mListPresenterImpl = ListPresenterImpl(this, RemoteServiceRepositoryImpl())

        mRepoAdapter = RepositoryListAdapter(ArrayList())
        rootView.repo_rl.adapter = mRepoAdapter
        doLoadData()

        return rootView
    }

    override fun onDetach() {
        super.onDetach()
        mListPresenterImpl.detach()
    }

    override fun showGitHubRepoList(repositoryList: GitHubRepositories?) {
        mRepoAdapter.updateData(repositoryList?.items as ArrayList<Repository>)
    }

    override fun showErrorToast() {
        Toast.makeText(activity, "Error Loading data.", Toast.LENGTH_SHORT).show()
    }

    private fun doLoadData() {
        mListPresenterImpl.getKotlinRepositories()
    }

    companion object {
        val TAG = RepositoryListFragment::class.java.canonicalName
        @JvmStatic
        fun newInstance() = RepositoryListFragment()
    }
}
