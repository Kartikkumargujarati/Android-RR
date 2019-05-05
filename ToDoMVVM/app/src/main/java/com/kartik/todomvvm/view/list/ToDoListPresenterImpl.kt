/*
 * Created by Kartik Kumar Gujarati on 5/5/19 12:37 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/5/19 12:37 PM
 */

package com.kartik.todomvvm.view.list

import android.os.Handler
import com.kartik.todomvvm.model.ToDoItem
import java.util.*
import kotlin.random.Random

class ToDoListPresenterImpl(var toDoListView: ToDoListView?) : ToDoListPresenter {

    override fun getToDoList() {
        toDoListView?.showProgress()
        Handler().postDelayed({
            val todoList = ArrayList<ToDoItem>()
            for (i in 1..25) {
                todoList.add(createDummyItem(i))
            }
            toDoListView?.hideProgress()
            if (Random.nextBoolean()) {
                toDoListView?.showSuccessToast()
                toDoListView?.showList(todoList)
            } else {
                toDoListView?.showErrorToast()
            }
        }, 3000)
    }

    private fun createDummyItem(position: Int): ToDoItem {
        return ToDoItem(position.toString(), "Item Header $position", "Item Content $position",
            Calendar.getInstance().time, "https://picsum.photos/id/$position/200/200")
    }

    override fun onDestroy() {
        toDoListView = null
    }
}