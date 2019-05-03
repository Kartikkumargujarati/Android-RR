/*
 * Created by Kartik Kumar Gujarati on 3/11/19 9:36 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/11/19 9:36 PM
 */

package com.kartik.networking.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kartik.networking.R
import com.kartik.networking.data.RemoteServiceRepositoryImpl
import com.kartik.networking.model.Gist
import com.kartik.networking.model.GistReq
import kotlinx.android.synthetic.main.activity_add_gist.*

class AddGistActivity : AppCompatActivity(), AddGistView {

    private lateinit var mListPresenterImpl: ListPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_gist)
        supportActionBar?.title = "Add Gist"
        mListPresenterImpl = ListPresenterImpl(this, RemoteServiceRepositoryImpl())

        add_button.setOnClickListener {
            val file = GistReq.GistFile(fileContent_editText.text.toString())
            val req = GistReq(desc_editText.text.toString(), mapOf(fileName_editText.text.toString() to file))
            mListPresenterImpl.addPublicGist(req)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mListPresenterImpl.detach()
    }

    override fun showSuccessToast(gist: Gist) {
        Toast.makeText(this, "Gist Created Successfully", Toast.LENGTH_LONG).show()
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun showErrorToast() {
        Toast.makeText(this, "Adding gist failed", Toast.LENGTH_LONG).show()
    }
}
