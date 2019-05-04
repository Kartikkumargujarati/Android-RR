/*
 * Created by Kartik Kumar Gujarati on 2/11/19 8:49 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/11/19 8:49 PM
 */

package com.kartik.networking.data

import com.kartik.networking.model.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteServiceRepositoryImpl {

    private val retrofit = Retrofit.Builder()
            .baseUrl(GITHUB_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val mRemoteServiceRepository = retrofit.create(RemoteServiceRepository::class.java)

    fun getKotlinRepositories(callback: Callback<GitHubRepositories>) {
        val retrofit = Retrofit.Builder()
                .baseUrl(GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val mRemoteServiceRepository = retrofit.create(RemoteServiceRepository::class.java)

        val call = mRemoteServiceRepository.getKotlinRepositories()
        call.enqueue(callback)
    }

    fun getPublicGists(callback: Callback<List<Gist>>) {
        val call = mRemoteServiceRepository.getPublicGists()
        call.enqueue(callback)
    }

    fun postPublicGist(body: GistReq, callback: Callback<Gist>) {
        val call = mRemoteServiceRepository.postPublicGist(body)
        call.enqueue(callback)
    }

    fun deleteGist(id: String, callback: Callback<Void>) {
        val call = mRemoteServiceRepository.deleteGist(id)
        call.enqueue(callback)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val client = OkHttpClient().newBuilder()
        client.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder().addHeader("Authorization", "token 123").build()
                return chain.proceed(request)
            }
        })
        return client.build()
    }

    companion object {
        const val GITHUB_URL = "https://api.github.com/"
    }
}