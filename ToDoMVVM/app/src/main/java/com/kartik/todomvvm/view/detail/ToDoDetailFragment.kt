/*
 * Created by Kartik Kumar Gujarati on 5/5/19 12:34 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/4/19 10:25 PM
 */

package com.kartik.todomvvm.view.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kartik.todomvvm.R
import com.kartik.todomvvm.model.ToDoItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

class ToDoDetailFragment : Fragment() {

    private var item: ToDoItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_TODO_ITEM)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getParcelable(ARG_TODO_ITEM)
                activity?.toolbar_layout?.title = item?.header
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            rootView.item_detail.text = it.content
            rootView.item_updated.text = "Updated on: ${it.updated}"
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_TODO_ITEM = "todo_item"
    }
}
