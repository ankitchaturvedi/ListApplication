package com.ankit.listapplication.utility

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import com.ankit.listapplication.R

class MessageShow {

    companion object{
        fun showToast(context: Context?, message: String?) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        fun showToast(context: Context?, message: Int) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        fun noInternetConnection(context: Context?) {
            Toast.makeText(context, R.string.no_connection, Toast.LENGTH_LONG).show()
        }
    }

    fun showMessage(context: Context?, msg: String?) {
        if (context != null) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.ErrorDialog)
            builder.setTitle(context.getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(R.drawable.ic_dialog_alert)
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
                .create()
            if (!(context as Activity).isFinishing) builder.show()
        }
    }
}