/*
 * Created by Kartik Kumar Gujarati on 3/4/19 9:18 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/4/19 9:18 PM
 */

package com.kartik.networking.ui

import android.app.Activity
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
    private var gistList = ArrayList<Gist>()
    private var mFab: FloatingActionButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_repo_list, container, false)
        retainInstance = true
        mFab = activity?.findViewById(R.id.fab)
        mFab?.setOnClickListener { navigateToAddUserScreen() }
        val itemDecor = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rootView.repo_rl.addItemDecoration(itemDecor)
        //repo_rl.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        rootView.repo_rl.layoutManager = mLayoutManager

        mListPresenterImpl = ListPresenterImpl(this, RemoteServiceRepositoryImpl())

        mGistListAdapter = GistListAdapter(ArrayList(), object : GistListAdapter.OnClickListener {
            override fun onEditClicked(gist: Gist) {
                val intent = Intent(activity, AddGistActivity::class.java)
                intent.putExtra(AddGistActivity.GIST, gist)
                startActivityForResult(intent, 101)
            }

            override fun onDeleteClicked(gist: Gist) {
                mListPresenterImpl.deleteGist(gist)
            }
        })
        rootView.repo_rl.adapter = mGistListAdapter
        doLoadData()
        return rootView
    }

    private fun navigateToAddUserScreen() {
        startActivityForResult(Intent(activity, AddGistActivity::class.java), 101)
    }

    override fun onDetach() {
        super.onDetach()
        mFab?.hide()
        mListPresenterImpl.detach()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                doLoadData()
            }
        }
    }

    override fun showGistList(gistList: List<Gist>) {
        this.gistList = gistList as ArrayList<Gist>
        mGistListAdapter.updateData(gistList)
    }

    override fun showErrorToast() {
        Toast.makeText(activity, "Error Loading Gists.", Toast.LENGTH_SHORT).show()
    }

    override fun gistDeleteSuccess(gist: Gist) {
        Toast.makeText(activity, "Gist deleted successfully", Toast.LENGTH_SHORT).show()
        gistList.remove(gist)
        mGistListAdapter.updateData(gistList)
    }

    override fun gistDeleteFailed() {
        Toast.makeText(activity, "Cloud not delete Gist. Try again.", Toast.LENGTH_SHORT).show()
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