package com.ankit.listapplication.utility

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager


object NetworkHelper {
    fun isOnline(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun isMobileDataOn(activity: Activity): Boolean {
        var mobileDataEnabled = false // Assume disabled
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try {
            val cmClass = Class.forName(cm.javaClass.name)
            val method = cmClass.getDeclaredMethod("getMobileDataEnabled")
            method.isAccessible = true
            mobileDataEnabled = method.invoke(cm) as Boolean
        } catch (e: Exception) {
            System.out.print(e);
        }

        return mobileDataEnabled
    }
}