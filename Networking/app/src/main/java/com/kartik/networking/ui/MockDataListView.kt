/*
 * Created by Kartik Kumar Gujarati on 3/5/19 8:08 AM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 3/5/19 8:08 AM
 */

package com.kartik.networking.ui

import com.kartik.networking.model.MockData
import com.kartik.networking.ui.base.BaseView

interface MockDataListView : BaseView {
    fun showMockDataList(mockList: MockData?)
}