package com.example.challenge.api.common

import android.os.Bundle

class ApiResponse<T> {

    var response : T?
    var error : Int?
    var status : Status
    var throwable : Throwable?
    var extra : Bundle?

    constructor(status: Status,
                response:T? = null,
                error:Int? = null,
                throwable: Throwable? = null,
                extra: Bundle? = null ){
        this.response = response
        this.error = error
        this.status = status
        this.throwable = throwable
        this.extra = extra
    }

    companion object {
        fun <T>loading() : ApiResponse<T> {
            return ApiResponse(status = Status.LOADING)
        }

        fun <T>success(response:T? = null,extra: Bundle? = null) : ApiResponse<T> {
            return ApiResponse(
                status = Status.SUCCESS,
                response = response,
                extra = extra
            )
        }

        fun <T>error(error:Int? = null ,extra: Bundle? = null) : ApiResponse<T> {
            return ApiResponse(
                status = Status.ERROR,
                error = error,
                extra = extra
            )
        }

        fun <T>fail(throwable: Throwable) : ApiResponse<T> {
            return ApiResponse(
                status = Status.FAIL,
                throwable = throwable
            )
        }
    }
}


