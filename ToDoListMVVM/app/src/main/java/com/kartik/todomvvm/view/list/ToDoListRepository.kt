/*
 * Created by Kartik Kumar Gujarati on 5/5/19 4:02 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/5/19 4:02 PM
 */

package com.kartik.todomvvm.view.list

import com.kartik.todomvvm.model.ToDoItem

interface ToDoListRepository {
    fun onSuccess(items: List<ToDoItem>)
    fun showMessage(string: String)
}