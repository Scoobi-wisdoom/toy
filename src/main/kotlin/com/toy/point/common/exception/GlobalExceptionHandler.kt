package com.toy.point.common.exception

import com.toy.point.common.exception.ErrorCode.UnKnown.ErrorUnKnown
import java.time.OffsetDateTime
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(annotations = [RestController::class])
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    protected fun defaultErrorHandler(): ResponseEntity<ErrorResponse> {
        return ResponseEntity.internalServerError()
            .body(
                ErrorResponse(
                    message = ErrorUnKnown.buildMessage(),
                    errorCode = ErrorUnKnown.errorCode,
                )
            )
    }
}

data class ErrorResponse(
    val timestamp: OffsetDateTime = OffsetDateTime.now(),
    val message: String?,
    val errorCode: String,
)
