package com.ankit.listapplication.utility

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

object NetworkHelper {
    fun isOnline(context: Context): Boolean {
        var have_WIFI = false
        var have_MobileData = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfos = connectivityManager.allNetworkInfo
        for (info in networkInfos) {
            if (info.typeName.equals("WIFI", ignoreCase = true)
            ) if (info.isConnected) have_WIFI = true
            if (info.typeName.equals("MOBILE DATA", ignoreCase = true)
            ) if (info.isConnected) have_MobileData = true
        }
        return have_WIFI || have_MobileData
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
            print(e);
        }

        return mobileDataEnabled
    }
}