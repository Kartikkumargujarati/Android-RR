/*
 * Created by Kartik Kumar Gujarati on 2/11/19 8:57 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/11/19 8:57 PM
 */

package com.kartik.networking.ui

import com.kartik.networking.data.GitHubServiceRepositoryImpl
import com.kartik.networking.model.GitHubRepositories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPresenterImpl(private var mListView: ListView?, private val mGitHubServiceRepository: GitHubServiceRepositoryImpl) : ListPresenter {

    override fun detach() {
        mListView = null
    }

    override fun getKotlinRepositories() {
        mGitHubServiceRepository.getKotlinRepositories(object : Callback<GitHubRepositories> {
            override fun onResponse(call: Call<GitHubRepositories>?, response: Response<GitHubRepositories>?) {
                response?.isSuccessful.let {
                    mListView?.showList(response?.body())
                }
            }

            override fun onFailure(call: Call<GitHubRepositories>?, t: Throwable?) {
                mListView?.showErrorToast()
            }
        })
    }
}