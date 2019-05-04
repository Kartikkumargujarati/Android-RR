/*
 * Created by Kartik Kumar Gujarati on 5/3/19 12:07 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/3/19 12:07 PM
 */

package com.kartik.networking.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gist(
        val id: String?,
        val created_at: String,
        val description: String?, val files: Map<String, File?>) : Parcelable

@Parcelize
data class File(val filename: String?) : Parcelable