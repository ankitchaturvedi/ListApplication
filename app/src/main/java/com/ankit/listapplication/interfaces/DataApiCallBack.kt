package com.ankit.listapplication.interfaces

import android.content.Context

interface DataApiCallBack<T> {
    fun onSuccessfulResponse(responseBody:List<T>)
    fun onErrorResponse(errorMessage: String, errorCode: Int, activity: Context) {
        //.showMessage(errorMessage, activity);
    }
}