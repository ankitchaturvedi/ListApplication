package com.ankit.listapplication.api

import com.ankit.listapplication.model.DataResponse
import com.ankit.listapplication.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/users")
    abstract fun getUserData(@Query("page") page:Int, @Query("per_page") page_size:Int): Call<DataResponse<UserModel>>
}