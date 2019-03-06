/*
 * Created by Kartik Kumar Gujarati on 2/11/19 8:41 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/11/19 8:39 PM
 */

package com.kartik.networking.data

import com.kartik.networking.model.GitHubRepositories
import com.kartik.networking.model.MockData
import retrofit2.Call
import retrofit2.http.GET

interface RemoteServiceRepository {

    @GET("/search/repositories?q=language:kotlin&sort=stars&order=desc")
    fun getKotlinRepositories(): Call<GitHubRepositories>

    @GET("/api/users")
    fun getMockData(): Call<MockData>

}
