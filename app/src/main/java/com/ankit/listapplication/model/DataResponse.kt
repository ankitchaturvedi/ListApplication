package com.ankit.listapplication.model

import com.google.gson.annotations.SerializedName


data class DataResponse<T>(
    @SerializedName("data")
    var data: List<T>,

    @SerializedName("page")
    var page: Int,

    @SerializedName("per_page")
    var per_page: Int,

    @SerializedName("total")
    var total: Int,

    @SerializedName("total_page")
    var total_page: Int
)