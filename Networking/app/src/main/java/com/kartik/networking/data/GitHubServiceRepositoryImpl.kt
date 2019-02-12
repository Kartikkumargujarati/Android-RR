/*
 * Created by Kartik Kumar Gujarati on 2/11/19 8:49 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/11/19 8:49 PM
 */

package com.kartik.networking.data

import com.kartik.networking.model.GitHubRepositories
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubServiceRepositoryImpl {

    private val gitHubServiceRepository: GitHubServiceRepository

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        gitHubServiceRepository = retrofit.create(GitHubServiceRepository::class.java)
    }

    fun getKotlinRepositories(callback: Callback<GitHubRepositories>) {
        val call = gitHubServiceRepository.getKotlinRepositories()
        call.enqueue(callback)
    }

    companion object {
        const val GITHUB_URL = "https://api.github.com/"
    }
}