/*
 * Created by Kartik Kumar Gujarati on 5/5/19 12:37 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/5/19 12:37 PM
 */

package com.kartik.todomvvm.view.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kartik.todomvvm.model.ToDoItem

class ToDoListViewModel(private val repository: ToDoListRepositoryImpl) : ViewModel(), ToDoListPresenter {

    private val _todoListState = MutableLiveData<ToDoListState>()

    val todoListState: LiveData<ToDoListState>
        get() = _todoListState

    override fun showToDoList() {
        _todoListState.value = ToDoListState.ShowLoading
        repository.getData(object : ToDoListRepository {
            override fun onSuccess(items: List<ToDoItem>) {
                _todoListState.value = ToDoListState.ShowList(items)
            }

            override fun showMessage(string: String) {
                _todoListState.value = ToDoListState.ShowMessage(string)
            }
        })
    }

    fun onItemClicked(item: ToDoItem) {
        _todoListState.value = ToDoListState.ShowToDoDetails(item)
    }

    fun onItemChecked(item: ToDoItem) {
        _todoListState.value = ToDoListState.RemoveToDoItemFromList(item)
    }
}

class ToDoListViewModelFactory(private val repository: ToDoListRepositoryImpl) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ToDoListViewModel(repository) as T
    }
}