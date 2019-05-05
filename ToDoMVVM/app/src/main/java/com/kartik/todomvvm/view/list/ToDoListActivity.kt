/*
 * Created by Kartik Kumar Gujarati on 5/5/19 12:34 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/5/19 12:34 PM
 */

package com.kartik.todomvvm.view.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kartik.todomvvm.R
import com.kartik.todomvvm.model.ToDoItem
import com.kartik.todomvvm.view.add.AddToDoItemActivity
import com.kartik.todomvvm.view.detail.ToDoDetailActivity
import com.kartik.todomvvm.view.detail.ToDoDetailFragment
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.item_list.*
import kotlin.collections.ArrayList

class ToDoListActivity : AppCompatActivity(), ToDoListView {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private lateinit var listAdapter: ToDoListAdapter
    private var todoList = ArrayList<ToDoItem>()
    private lateinit var presenter: ToDoListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        setupFab(fab)
        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
        presenter = ToDoListPresenterImpl(this)
        setupRecyclerView(item_list)
        presenter.getToDoList()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setupFab(fab: FloatingActionButton?) {
        fab?.setOnClickListener {
            val intent = Intent(this, AddToDoItemActivity::class.java)
            intent.putExtra(AddToDoItemActivity.TODO_ITEM_ID, (todoList.size + 1).toString())
            startActivityForResult(intent, 1001)
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        listAdapter = ToDoListAdapter(ArrayList(), object : ToDoListAdapter.OnClickListener {
                override fun onClick(item: ToDoItem?) {
                    if (twoPane) {
                        val fragment = ToDoDetailFragment().apply {
                            arguments = Bundle().apply {
                                putParcelable(ToDoDetailFragment.ARG_TODO_ITEM, item)
                            }
                        }
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit()
                    } else {
                        val intent = Intent(this@ToDoListActivity, ToDoDetailActivity::class.java).apply {
                            putExtra(ToDoDetailFragment.ARG_TODO_ITEM, item)
                        }
                        startActivity(intent)
                    }
                }
            })
        recyclerView.adapter = listAdapter
    }

    override fun showList(itemList: ArrayList<ToDoItem>) {
        todoList.addAll(itemList)
        listAdapter.updateData(itemList)
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode) {
            Activity.RESULT_OK -> {
                val newItem = data?.extras?.getParcelable(NEW_TODO_ITEM) as ToDoItem
                todoList.add(newItem)
                listAdapter.updateData(todoList)
            }
        }
    }

    companion object {
        const val NEW_TODO_ITEM = "new_todo_item"
    }
}
