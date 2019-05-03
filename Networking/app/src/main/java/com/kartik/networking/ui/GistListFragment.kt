/*
 * Created by Kartik Kumar Gujarati on 3/4/19 9:18 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/4/19 9:18 PM
 */

package com.kartik.networking.ui

import android.content.Intent
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
import com.kartik.networking.model.Gist
import kotlinx.android.synthetic.main.fragment_repo_list.view.*

class GistListFragment : Fragment(), GistListView {

    private lateinit var mGistListAdapter: GistListAdapter
    private lateinit var mListPresenterImpl: ListPresenterImpl
    private var mFab: FloatingActionButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_repo_list, container, false)
        retainInstance = true
        mFab = activity?.findViewById(R.id.fab)
        mFab?.show()
        mFab?.setOnClickListener { navigateToAddUserScreen() }
        val itemDecor = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rootView.repo_rl.addItemDecoration(itemDecor)
        //repo_rl.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        rootView.repo_rl.layoutManager = mLayoutManager

        mListPresenterImpl = ListPresenterImpl(this, RemoteServiceRepositoryImpl())

        mGistListAdapter = GistListAdapter(ArrayList())
        rootView.repo_rl.adapter = mGistListAdapter
        doLoadData()

        return rootView
    }

    private fun navigateToAddUserScreen() {
        Toast.makeText(activity, "Add new Gist", Toast.LENGTH_LONG).show()
        //startActivity(Intent(activity, AddUserActivity::class.java))
    }

    override fun onDetach() {
        super.onDetach()
        mFab?.hide()
        mListPresenterImpl.detach()
    }

    override fun showGistList(gistList: List<Gist>) {
        mGistListAdapter.updateData(gistList as ArrayList<Gist>)
    }

    override fun showErrorToast() {
        Toast.makeText(activity, "Error Loading data.", Toast.LENGTH_SHORT).show()
    }

    private fun doLoadData() {
        mListPresenterImpl.getPublicGists()
    }

    companion object {
        val TAG = GistListFragment::class.java.canonicalName
        @JvmStatic
        fun newInstance() = GistListFragment()
    }
}