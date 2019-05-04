/*
 * Created by Kartik Kumar Gujarati on 5/4/19 5:22 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/4/19 5:22 PM
 */

package com.kartik.todomvvm.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ToDoItem(
        val id: String,
        val header: String,
        val content: String,
        val updated: Date,
        val image: String) : Parcelable