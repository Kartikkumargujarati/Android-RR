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
    private var gist: Gist? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_gist)
        if (intent?.extras?.get(GIST) != null) {
            gist = intent?.extras?.get(GIST) as Gist
            desc_editText.setText(gist?.description)
            if (!gist?.files?.isEmpty()!!) {
                fileName_editText.setText(gist?.files?.keys?.elementAt(0)?.toString())
            }
            //fileContent_editText.setText(gist?.description)
        } else {
            submit_button.text = "Add Gist"
        }
        supportActionBar?.title = "Add Gist"
        mListPresenterImpl = ListPresenterImpl(this, RemoteServiceRepositoryImpl())

        submit_button.setOnClickListener {
            val file = GistReq.GistFile(fileContent_editText.text.toString())
            val req = GistReq(desc_editText.text.toString(), mapOf(fileName_editText.text.toString() to file))
            if (gist != null) {
                mListPresenterImpl.updateGist(gist?.id.toString(), req)
            } else {
                mListPresenterImpl.addPublicGist(req)
            }
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

    override fun showUpdateSuccessToast(gist: Gist) {
        Toast.makeText(this, "Gist Updated Successfully", Toast.LENGTH_LONG).show()
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun showUpdateFailedToast() {
        Toast.makeText(this, "Updating gist failed. Try again.", Toast.LENGTH_LONG).show()
    }

    override fun showErrorToast() {
        Toast.makeText(this, "Adding gist failed. Try again.", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val GIST = "GIST"
    }
}
