/*
 * Created by Kartik Kumar Gujarati on 5/3/19 5:07 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 5/3/19 5:07 PM
 */

package com.kartik.networking.model

class GistReq(val description: String, val files: Map<String, GistFile>, val public: Boolean = true) {

    class GistFile(val content: String)
}