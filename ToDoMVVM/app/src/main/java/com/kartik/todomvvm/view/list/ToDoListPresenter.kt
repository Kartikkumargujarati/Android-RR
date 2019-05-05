/*
 * Created by Kartik Kumar Gujarati on 5/5/19 12:36 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/5/19 12:36 PM
 */

package com.kartik.todomvvm.view.list

interface ToDoListPresenter {

    fun getToDoList()

    fun onDestroy()
}