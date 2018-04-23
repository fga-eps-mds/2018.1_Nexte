package com.nexte.nexte.ObjectModels

data class ResponseError(val type: ResponseErrorType,
                         val title: String,
                         val detail: String) {

    enum class ResponseErrorType(val code: Int)
}