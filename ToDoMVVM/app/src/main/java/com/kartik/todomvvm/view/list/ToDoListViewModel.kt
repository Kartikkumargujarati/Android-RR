/*
 * Created by Kartik Kumar Gujarati on 5/5/19 12:37 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/5/19 12:37 PM
 */

package com.kartik.todomvvm.view.list

import com.kartik.todomvvm.model.ToDoItem
import com.kartik.todomvvm.view.CustomObservable

class ToDoListViewModel(private val repository: ToDoListRepositoryImpl) : ToDoListPresenter {

    val todoListStateObservable = CustomObservable<ToDoListState>()

    override fun showToDoList() {
        todoListStateObservable.notifyObservers(ToDoListState.ShowLoading)
        repository.getData(object : ToDoListRepository {
            override fun onSuccess(items: List<ToDoItem>) {
                todoListStateObservable.notifyObservers(ToDoListState.ShowList(items))
            }

            override fun showMessage(string: String) {
                todoListStateObservable.notifyObservers(ToDoListState.ShowMessage(string))
            }
        })
    }

    fun onItemClicked(item: ToDoItem) {
        todoListStateObservable.notifyObservers(ToDoListState.ShowToDoDetails(item))
    }

    override fun onDestroy() {
        todoListStateObservable.clearObservers()
    }
}