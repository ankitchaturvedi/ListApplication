package com.ankit.listapplication.api

import android.content.Context
import com.ankit.listapplication.R
import com.ankit.listapplication.interfaces.DataApiCallBack
import com.ankit.listapplication.model.DataResponse
import com.ankit.listapplication.model.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCallBack {

    companion object{
        fun <T> executDataApi(context: Context, callBack: Call<DataResponse<T>>, dataApiCallBack: DataApiCallBack<T>)
        {
            callBack.enqueue(object: Callback<DataResponse<T>> {
                override fun onFailure(call: Call<DataResponse<T>>, t: Throwable) {
                    dataApiCallBack.onErrorResponse(context.getString(R.string.some_thing_went_wrong),
                        500,context)
                }

                override fun onResponse(call: Call<DataResponse<T>>, response: Response<DataResponse<T>>
                ) {

                    response.body().toString().replace("\"id\"".toRegex(), "\"mid\"")

                    if (response != null){

                        var body: DataResponse<T>? = response.body();

                        if (body != null){
                            var data:List<T> = body.data;
                            if (data.isNotEmpty()){
                                dataApiCallBack.onSuccessfulResponse(data);
                                Resource.pageDetails(body.page,body.per_page,body.total,body.total_page);
                            }else{
                                dataApiCallBack.onErrorResponse(context.getString(R.string.some_thing_went_wrong),
                                    500,context)
                            }
                        }
                    }
                }

            })
        }
    }
}