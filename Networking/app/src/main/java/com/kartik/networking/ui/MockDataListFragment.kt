/*
 * Created by Kartik Kumar Gujarati on 3/4/19 9:18 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/4/19 9:18 PM
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
import com.kartik.networking.model.MockData
import com.kartik.networking.model.User
import kotlinx.android.synthetic.main.app_bar_drawer.*
import kotlinx.android.synthetic.main.fragment_repo_list.view.*

class MockDataListFragment : Fragment(), MockDataListView {

    private lateinit var mRepoAdapter: MockDataListAdapter
    private lateinit var mListPresenterImpl: ListPresenterImpl
    private var mFab: FloatingActionButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_repo_list, container, false)
        mFab = activity?.findViewById(R.id.fab)
        mFab?.show()
        val itemDecor = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rootView.repo_rl.addItemDecoration(itemDecor)
        //repo_rl.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        rootView.repo_rl.layoutManager = mLayoutManager

        mListPresenterImpl = ListPresenterImpl(this, RemoteServiceRepositoryImpl())

        mRepoAdapter = MockDataListAdapter(ArrayList())
        rootView.repo_rl.adapter = mRepoAdapter
        doLoadData()

        return rootView
    }

    override fun onDetach() {
        super.onDetach()
        mFab?.hide()
        mListPresenterImpl.detach()
    }

    override fun showMockDataList(mockList: MockData?) {
        mRepoAdapter.updateData(mockList?.data as ArrayList<User>)
    }

    override fun showErrorToast() {
        Toast.makeText(activity, "Error Loading data.", Toast.LENGTH_SHORT).show()
    }

    private fun doLoadData() {
        mListPresenterImpl.getMockData()
    }

    companion object {
        val TAG = MockDataListFragment::class.java.canonicalName
        @JvmStatic
        fun newInstance() = MockDataListFragment()
    }
}