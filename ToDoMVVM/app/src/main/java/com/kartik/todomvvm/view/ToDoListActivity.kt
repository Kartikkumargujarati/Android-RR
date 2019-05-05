/*
 * Created by Kartik Kumar Gujarati on 5/4/19 4:55 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/4/19 4:54 PM
 */

package com.kartik.todomvvm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.kartik.todomvvm.R
import com.kartik.todomvvm.model.ToDoItem
import com.kartik.todomvvm.view.AddToDoItemActivity.Companion.TODO_ITEM_ID

import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.item_list.*
import java.util.*

class ToDoListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private lateinit var listAdapter: ToDoListAdapter
    private var todoList = ArrayList<ToDoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener {
            val intent = Intent(this, AddToDoItemActivity::class.java)
            intent.putExtra(TODO_ITEM_ID, (todoList.size + 1).toString())
            startActivityForResult(intent, 1001)
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
        for (i in 1..25) {
            todoList.add(createDummyItem(i))
        }

        listAdapter = ToDoListAdapter(todoList, object : ToDoListAdapter.OnClickListener {
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

    private fun createDummyItem(position: Int): ToDoItem {
        return ToDoItem(position.toString(), "Item Header $position", "Item Content $position",
            Calendar.getInstance().time, "https://picsum.photos/id/$position/200/200")
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
