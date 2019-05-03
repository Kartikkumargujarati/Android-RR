/*
 * Created by Kartik Kumar Gujarati on 2/11/19 8:57 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/11/19 8:57 PM
 */

package com.kartik.networking.ui

import com.kartik.networking.data.RemoteServiceRepositoryImpl
import com.kartik.networking.model.*
import com.kartik.networking.ui.base.BaseView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPresenterImpl(private var mView: BaseView?, private val mRemoteServiceRepository: RemoteServiceRepositoryImpl) : ListPresenter {

    override fun detach() {
        mView = null
    }

    override fun getKotlinRepositories() {
        mRemoteServiceRepository.getKotlinRepositories(object : Callback<GitHubRepositories> {
            override fun onResponse(call: Call<GitHubRepositories>?, response: Response<GitHubRepositories>?) {
                response?.isSuccessful.let {
                    (mView as RepositoryListView?)?.showGitHubRepoList(response?.body())
                }
            }

            override fun onFailure(call: Call<GitHubRepositories>?, t: Throwable?) {
                mView?.showErrorToast()
            }
        })
    }

    override fun getPublicGists() {
        mRemoteServiceRepository.getPublicGists(object : Callback<List<Gist>> {
            override fun onResponse(call: Call<List<Gist>>?, response: Response<List<Gist>>?) {
                response?.isSuccessful.let {
                    (mView as GistListView?)?.showGistList(response?.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Gist>>?, t: Throwable?) {
                mView?.showErrorToast()
            }
        })
    }
}