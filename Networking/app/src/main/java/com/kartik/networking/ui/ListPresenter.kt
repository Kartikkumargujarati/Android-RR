/*
 * Created by Kartik Kumar Gujarati on 2/11/19 8:56 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/11/19 8:56 PM
 */

package com.kartik.networking.ui

import com.kartik.networking.model.NewUserRequest

interface ListPresenter {

    fun detach()

    fun getKotlinRepositories()

    fun getMockData()

    fun addMockUser(user: NewUserRequest)
}