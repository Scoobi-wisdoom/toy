package com.toy.point.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR

sealed class ErrorCode(val errorCode: String, val httpStatus: HttpStatus, val message: String) {
    sealed class UnKnown(errorCode: String, httpStatus: HttpStatus, message: String) :
    ErrorCode(errorCode, httpStatus, message) {
        object ErrorUnKnown: UnKnown("UK-100", INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생했습니다.")
    }

    fun buildMessage() = "[$errorCode]$message"
}
