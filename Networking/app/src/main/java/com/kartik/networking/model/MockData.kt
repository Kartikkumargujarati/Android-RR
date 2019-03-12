/*
 * Created by Kartik Kumar Gujarati on 3/4/19 9:24 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/4/19 9:24 PM
 */

package com.kartik.networking.model

data class MockData(val data: ArrayList<User>)

data class User(
        val id: String?,
        val first_name: String,
        val last_name: String?,
        val avatar: String?)

data class NewUserRequest(
        val name: String?,
        val job: String?)

data class NewUserResponse(
        val id: String?,
        val name: String?,
        val job: String?,
        val createdAt: String?)