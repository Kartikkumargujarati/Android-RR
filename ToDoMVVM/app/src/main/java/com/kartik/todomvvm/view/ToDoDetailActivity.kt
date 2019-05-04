/*
 * Created by Kartik Kumar Gujarati on 5/4/19 5:14 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/4/19 4:56 PM
 */

package com.kartik.todomvvm.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.kartik.todomvvm.R
import com.kartik.todomvvm.model.ToDoItem
import kotlinx.android.synthetic.main.activity_item_detail.*

class ToDoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ToDoDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(
                        ToDoDetailFragment.ARG_TODO_ITEM,
                        intent.getParcelableExtra<ToDoItem>(ToDoDetailFragment.ARG_TODO_ITEM)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                navigateUpTo(Intent(this, ToDoListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
