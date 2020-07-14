package com.ankit.listapplication.activities

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ankit.listapplication.R
import com.ankit.listapplication.api.ApiCallBack
import com.ankit.listapplication.api.ApiClient
import com.ankit.listapplication.api.ApiInterface
import com.ankit.listapplication.database.UserDataBase
import com.ankit.listapplication.interfaces.DataApiCallBack
import com.ankit.listapplication.model.Resource
import com.ankit.listapplication.model.UserModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.functions.Consumer

class UserRepository (val context: Context) {
    val apiInterface: ApiInterface =  ApiClient.getClient(ApiClient.BASE_URL, context).create(ApiInterface::class.java)

    fun getUserData(page:Int, page_size:Int): MutableLiveData<Resource<UserModel>> {
        var usersLiveData = MutableLiveData<Resource<UserModel>>();
        usersLiveData.value = Resource.loading(null);
        if (Resource.page+1 > Resource.total_pages) {
            Resource.page = 0
        }
        val call = apiInterface.getUserData(Resource.page+1, 5);
        ApiCallBack.executDataApi(context, call, object : DataApiCallBack<UserModel> {
            override fun onSuccessfulResponse(responseBody: List<UserModel>) {
                usersLiveData.value = Resource.success(responseBody);
            }

            override fun onErrorResponse(errorMessage: String, errorCode: Int, activity: Context) {
                super.onErrorResponse(errorMessage, errorCode, activity)
                usersLiveData.value = Resource.error(errorMessage, errorCode)
            }
        })

        return usersLiveData
    }

    fun getDBUsers(): MutableLiveData<Resource<UserModel>> {
        var userLiveData = MutableLiveData<Resource<UserModel>>();
        userLiveData.value = Resource.loading(null);
        var userDataBase: UserDataBase? = UserDataBase.getInstance(context);

        userDataBase!!.userDAO()!!.getUsersList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Consumer<List<UserModel>> {
                @Throws(Exception::class)
                override fun accept(userModel: List<UserModel>) {
                    if (userModel != null && userModel.size > 0) {
                        userLiveData.value = Resource.success(userModel);
                    } else {
                        userLiveData.value =
                            Resource.error(context.getString(R.string.blank_data), 500)
                    }
                }
            })
        return userLiveData
    }
}

