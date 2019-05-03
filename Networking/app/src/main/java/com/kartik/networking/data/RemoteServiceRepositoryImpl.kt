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

    private val retrofit = Retrofit.Builder()
            .baseUrl(GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val mRemoteServiceRepository = retrofit.create(RemoteServiceRepository::class.java)

    fun getKotlinRepositories(callback: Callback<GitHubRepositories>) {
        val call = mRemoteServiceRepository.getKotlinRepositories()
        call.enqueue(callback)
    }

    fun getPublicGists(callback: Callback<List<Gist>>) {
        val call = mRemoteServiceRepository.getPublicGists()
        call.enqueue(callback)
    }

    companion object {
        const val GITHUB_URL = "https://api.github.com/"
    }
}