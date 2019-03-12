/*
 * Created by Kartik Kumar Gujarati on 3/11/19 9:36 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/11/19 9:36 PM
 */

package com.kartik.networking.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kartik.networking.R
import com.kartik.networking.data.RemoteServiceRepositoryImpl
import com.kartik.networking.model.NewUserRequest
import com.kartik.networking.model.NewUserResponse
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserActivity : AppCompatActivity(), AddUserView {

    private lateinit var mListPresenterImpl: ListPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        supportActionBar?.title = "Add User"
        mListPresenterImpl = ListPresenterImpl(this, RemoteServiceRepositoryImpl())

        add_button.setOnClickListener { mListPresenterImpl.addMockUser(NewUserRequest(name_editText.text.toString(), job_editText.text.toString())) }
    }

    override fun onDestroy() {
        super.onDestroy()
        mListPresenterImpl.detach()
    }

    override fun showSuccessToast(user: NewUserResponse?) {
        Toast.makeText(this, "User ${user?.name} created at ${user?.createdAt}", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun showErrorToast() {
        Toast.makeText(this, "Adding user failed", Toast.LENGTH_LONG).show()
    }
}
