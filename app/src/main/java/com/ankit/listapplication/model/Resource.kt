package com.ankit.listapplication.model

const val SUCCESS = 90
const val LOADING = 91
const val ERROR = 92

class Resource<T> {

    var status: Int = 0
    var message: String? = null
    var data: List<T>? = null
    var statusCode: Int = 0

    constructor(status: Int, data: List<T>?) {
        this.status = status
        this.data = data
    }

    constructor(status: Int, statusCode: Int, msg: String) {
        this.status = status
        this.statusCode = statusCode
        this.message = msg
    }

    companion object {
        var page: Int = 0
        var perPage: Int = 0
        var total: Int = 0
        var total_pages: Int = 0

        fun <T> success(data: List<T>?): Resource<T> {
            return Resource(SUCCESS, data)
        }

        fun <T> error(msg: String, statusCode: Int): Resource<T> {
            return Resource(ERROR, statusCode, msg)
        }

        fun pageDetails(page: Int, perPage: Int, total: Int, total_pages: Int) {
            this.page = page
            this.perPage = perPage
            this.total = total
            this.total_pages = total_pages
        }

        fun <T> loading(data: List<T>?): Resource<T> {
            return Resource(LOADING,data)
        }
    }
}