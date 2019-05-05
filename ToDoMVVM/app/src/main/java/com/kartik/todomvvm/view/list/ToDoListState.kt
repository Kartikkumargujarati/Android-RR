/*
 * Created by Kartik Kumar Gujarati on 5/5/19 3:44 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/5/19 3:44 PM
 */

package com.kartik.todomvvm.view.list

import com.kartik.todomvvm.model.ToDoItem

sealed class ToDoListState {
    class ShowList(val items: List<ToDoItem>): ToDoListState()
    class ShowMessage(val message: String) : ToDoListState()
    object ShowLoading : ToDoListState()
    class ShowToDoDetails(val item: ToDoItem) : ToDoListState()
}