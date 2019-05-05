/*
 * Created by Kartik Kumar Gujarati on 5/4/19 5:50 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/4/19 5:50 PM
 */

package com.kartik.todomvvm.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kartik.todomvvm.R
import com.kartik.todomvvm.model.ToDoItem
import kotlinx.android.synthetic.main.activity_add_to_do_item.*
import java.util.*
import android.content.Intent
import android.app.Activity
import com.kartik.todomvvm.view.ToDoListActivity.Companion.NEW_TODO_ITEM
import com.squareup.picasso.Picasso


class AddToDoItemActivity : AppCompatActivity() {

    private var newItemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do_item)
        if (intent?.extras?.getString(TODO_ITEM_ID) != null) {
            newItemId = intent?.extras?.getString(TODO_ITEM_ID)
        }

        add_image_tv.setOnClickListener {
            Picasso.get().load("https://picsum.photos/id/$newItemId/200/200").into(todo_img_iv)
        }

        submit_button.setOnClickListener {
            val item = newItemId?.let { it1 ->
                ToDoItem(
                    it1, header_editText.text.toString(), notes_editText.text.toString(),
                    Calendar.getInstance().time, "https://picsum.photos/id/$newItemId/200/200")
            }
            setResult(Activity.RESULT_OK, Intent().putExtra(NEW_TODO_ITEM, item))
            finish()
        }
    }

    companion object {
        const val TODO_ITEM_ID = "todo_item_id"
    }
}
