/*
 * Created by Kartik Kumar Gujarati on 5/5/19 12:31 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/5/19 12:31 PM
 */

package com.kartik.todomvvm.view.list

import com.kartik.todomvvm.model.ToDoItem
import java.util.ArrayList

interface ToDoListView {

    fun showList(itemList: ArrayList<ToDoItem>)

    fun showProgress()

    fun hideProgress()
}