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
                if (response != null && response.isSuccessful && response.body() != null) {
                    (mView as GistListView?)?.showGistList(response.body()!!)
                } else {
                    mView?.showErrorToast()
                }
            }

            override fun onFailure(call: Call<List<Gist>>?, t: Throwable?) {
                mView?.showErrorToast()
            }
        })
    }

    override fun addPublicGist(req: GistReq) {
        mRemoteServiceRepository.postPublicGist(req, object : Callback<Gist> {
            override fun onResponse(call: Call<Gist>, response: Response<Gist>?) {
                response?.isSuccessful.let {
                    (mView as AddGistView?)?.showSuccessToast(response?.body()!!)
                }
            }

            override fun onFailure(call: Call<Gist>, t: Throwable) {
                (mView as AddGistView?)?.showErrorToast()
            }
        })
    }

    override fun deleteGist(gist: Gist) {
        mRemoteServiceRepository.deleteGist(gist.id.toString(), object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                (mView as GistListView?)?.gistDeleteSuccess(gist)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                (mView as GistListView?)?.gistDeleteFailed()
            }
        })
    }

    override fun updateGist(id: String, req: GistReq) {
        mRemoteServiceRepository.updateGist(id, req, object : Callback<Gist> {
            override fun onResponse(call: Call<Gist>, response: Response<Gist>?) {
                response?.isSuccessful.let {
                    (mView as AddGistView?)?.showUpdateSuccessToast(response?.body()!!)
                }
            }

            override fun onFailure(call: Call<Gist>, t: Throwable) {
                (mView as AddGistView?)?.showErrorToast()
            }
        })
    }
}