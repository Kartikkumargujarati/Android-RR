/*
 * Created by Kartik Kumar Gujarati on 5/4/19 4:55 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/4/19 4:54 PM
 */

package com.kartik.todomvvm.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import com.kartik.todomvvm.R

import com.kartik.todomvvm.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.item_list.*

class ToDoListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            //Go to add ToDo item
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(item_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter =
            ToDoListAdapter(DummyContent.ITEMS, object : ToDoListAdapter.OnClickListener {
                override fun onClick(item: DummyContent.DummyItem?) {
                    if (twoPane) {
                        val fragment = ToDoDetailFragment().apply {
                            arguments = Bundle().apply {
                                putString(ToDoDetailFragment.ARG_ITEM_ID, item?.id)
                            }
                        }
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit()
                    } else {
                        val intent = Intent(this@ToDoListActivity, ToDoDetailActivity::class.java).apply {
                            putExtra(ToDoDetailFragment.ARG_ITEM_ID, item?.id)
                        }
                        startActivity(intent)
                    }
                }

            })
    }
}
