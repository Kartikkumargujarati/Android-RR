/*
 * Created by Kartik Kumar Gujarati on 2/11/19 8:58 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/11/19 8:58 PM
 */

package com.kartik.networking.ui

import com.kartik.networking.model.GitHubRepositories
import com.kartik.networking.ui.base.BaseView

interface GitHubRepoListView : BaseView {
    fun showGitHubRepoList(repositoryList: GitHubRepositories?)
}