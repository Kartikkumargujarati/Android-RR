/*
 * Created by Kartik Kumar Gujarati on 2/11/19 8:57 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/11/19 8:57 PM
 */

package com.kartik.networking.ui

import com.kartik.networking.data.RemoteServiceRepositoryImpl
import com.kartik.networking.model.GitHubRepositories
import com.kartik.networking.model.MockData
import com.kartik.networking.model.NewUserRequest
import com.kartik.networking.model.NewUserResponse
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
                    (mView as GitHubRepoListView?)?.showGitHubRepoList(response?.body())
                }
            }

            override fun onFailure(call: Call<GitHubRepositories>?, t: Throwable?) {
                mView?.showErrorToast()
            }
        })
    }

    override fun getMockData() {
        mRemoteServiceRepository.getMockData(object : Callback<MockData> {
            override fun onResponse(call: Call<MockData>?, response: Response<MockData>?) {
                response?.isSuccessful.let {
                    (mView as MockDataListView?)?.showMockDataList(response?.body())
                }
            }

            override fun onFailure(call: Call<MockData>?, t: Throwable?) {
                mView?.showErrorToast()
            }
        })
    }

    override fun addMockUser(user: NewUserRequest) {
        mRemoteServiceRepository.addMockUser(user, object : Callback<NewUserResponse> {
            override fun onResponse(call: Call<NewUserResponse>?, response: Response<NewUserResponse>?) {
                response?.isSuccessful.let {
                    (mView as AddUserView?)?.showSuccessToast(response?.body())
                }
            }

            override fun onFailure(call: Call<NewUserResponse>?, t: Throwable?) {
                mView?.showErrorToast()
            }
        })
    }


}