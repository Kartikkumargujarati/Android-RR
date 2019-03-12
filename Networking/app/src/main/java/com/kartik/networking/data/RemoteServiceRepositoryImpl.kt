/*
 * Created by Kartik Kumar Gujarati on 2/11/19 8:49 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/11/19 8:49 PM
 */

package com.kartik.networking.data

import com.kartik.networking.model.*
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteServiceRepositoryImpl {

    fun getKotlinRepositories(callback: Callback<GitHubRepositories>) {
        val retrofit = Retrofit.Builder()
                .baseUrl(GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val mRemoteServiceRepository = retrofit.create(RemoteServiceRepository::class.java)
        val call = mRemoteServiceRepository.getKotlinRepositories()
        call.enqueue(callback)
    }

    fun getMockData(callback: Callback<MockData>) {
        val retrofit = Retrofit.Builder()
                .baseUrl(MOCK_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val mRemoteServiceRepository = retrofit.create(RemoteServiceRepository::class.java)
        val call = mRemoteServiceRepository.getMockData()
        call.enqueue(callback)
    }

    fun addMockUser(user: NewUserRequest, callback: Callback<NewUserResponse>) {
        val retrofit = Retrofit.Builder()
                .baseUrl(MOCK_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val mRemoteServiceRepository = retrofit.create(RemoteServiceRepository::class.java)
        val call = mRemoteServiceRepository.addMockUser(user)
        call.enqueue(callback)
    }

    companion object {
        const val GITHUB_URL = "https://api.github.com/"
        const val MOCK_URL = "https://reqres.in/"
    }
}