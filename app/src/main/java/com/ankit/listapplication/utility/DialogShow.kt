package com.ankit.listapplication.utility

import android.app.ProgressDialog
import android.content.Context


class DialogShow {
    companion object{
        var progressDialog: ProgressDialog? = null
        fun showProgressDialog(context: Context?, message: String?): ProgressDialog? {
            progressDialog = ProgressDialog(context)
            progressDialog!!.setCancelable(false)
            progressDialog!!.setMessage(message)
            progressDialog!!.show()
            return progressDialog
        }

        fun dismissProgressDialog() {
            try {
                if (progressDialog != null && progressDialog!!.isShowing) {
                    progressDialog!!.dismiss()
                }
            } catch (e: Exception) {
            }
        }
    }
}