/*
 * Created by Kartik Kumar Gujarati on 2/9/19 11:54 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/9/19 11:54 PM
 */

package com.kartik.networking.model

data class GitHubRepositories(val items: List<GitHubRepository>)

data class GitHubRepository(
        val name: String?,
        val owner: Owner,
        val stargazers_count: String?,
        val language: String?)

data class Owner(val avatar_url: String?)