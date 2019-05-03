/*
 * Created by Kartik Kumar Gujarati on 3/11/19 10:26 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/11/19 10:26 PM
 */

package com.kartik.networking.ui

import com.kartik.networking.model.Gist
import com.kartik.networking.ui.base.BaseView

interface AddGistView : BaseView {

    fun showSuccessToast(gist: Gist)
}