/*
 * Created by Kartik Kumar Gujarati on 5/5/19 1:46 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/5/19 1:46 PM
 */

package com.kartik.todomvvm.view

class CustomObservable<T> {

    private var observers = mutableListOf<(T) -> Unit>()

    fun addObserver(observer: (T) -> Unit) {
        observers.add(observer)
    }

    fun notifyObservers(newValue: T) {
        observers.forEach { it(newValue) }
    }

    fun clearObservers() {
        observers = mutableListOf()
    }
}