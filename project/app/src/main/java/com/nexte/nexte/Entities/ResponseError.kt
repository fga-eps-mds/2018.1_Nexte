package com.nexte.nexte.Entities

class ResponseError(val type: ResponseErrorType,
                    val title: String,
                    val detail: String) {

    enum class ResponseErrorType { DATABASE_ERROR, REQUEST_ERROR, PARAMETER_ERROR, INSTANTIATE_ERROR }
}