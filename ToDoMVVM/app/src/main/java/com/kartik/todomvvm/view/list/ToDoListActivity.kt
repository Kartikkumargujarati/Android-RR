/*
 * Created by Kartik Kumar Gujarati on 5/5/19 12:34 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/5/19 12:34 PM
 */

package com.kartik.todomvvm.view.list

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.kartik.todomvvm.R
import com.kartik.todomvvm.model.ToDoItem
import com.kartik.todomvvm.view.add.AddToDoItemActivity
import com.kartik.todomvvm.view.detail.ToDoDetailActivity
import com.kartik.todomvvm.view.detail.ToDoDetailFragment
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.item_list.*
import kotlin.collections.ArrayList

class ToDoListActivity : AppCompatActivity() {

    private lateinit var listAdapter: ToDoListAdapter
    private var todoList = ArrayList<ToDoItem>()
    private lateinit var viewModel: ToDoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        viewModel = ViewModelProviders.of(
            this, ToDoListViewModelFactory(ToDoListRepositoryImpl()))[ToDoListViewModel::class.java]

        setupFab(fab)
        setupRecyclerView(item_list)

        // adding observer with function reference
        viewModel.todoListState.observe(::getLifecycle, ::updateView)
    }

    // explicitly clearing out the list and loading data every time the activity resume.
    override fun onResume() {
        super.onResume()
        todoList = ArrayList()
        listAdapter.updateData(todoList)
        viewModel.showToDoList()
    }

    private fun updateView(toDoListState: ToDoListState?) {
        progress.visibility = View.GONE
        when(toDoListState) {
            is ToDoListState.ShowList -> {
                todoList.addAll(toDoListState.items)
                listAdapter.updateData(toDoListState.items as ArrayList<ToDoItem>)
            }
            is ToDoListState.ShowMessage -> Toast.makeText(this, toDoListState.message, Toast.LENGTH_SHORT).show()
            is ToDoListState.ShowLoading -> progress.visibility = View.VISIBLE
            is ToDoListState.ShowToDoDetails -> {
                val intent = Intent(this@ToDoListActivity, ToDoDetailActivity::class.java).apply {
                    putExtra(ToDoDetailFragment.ARG_TODO_ITEM, toDoListState.item)
                }
                startActivity(intent)
            }
        }
    }

    private fun setupFab(fab: FloatingActionButton?) {
        fab?.setOnClickListener {
            val intent = Intent(this, AddToDoItemActivity::class.java)
            intent.putExtra(AddToDoItemActivity.TODO_ITEM_ID, (todoList.size + 1).toString())
            startActivityForResult(intent, 1001)
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        listAdapter = ToDoListAdapter(ArrayList(), viewModel::onItemClicked)
        recyclerView.adapter = listAdapter
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
