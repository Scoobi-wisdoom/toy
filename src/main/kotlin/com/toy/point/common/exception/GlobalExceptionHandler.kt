package com.toy.point.common.exception

import java.time.OffsetDateTime
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(annotations = [RestController::class])
class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException::class)
    protected fun businessErrorHandler(
        e: BusinessException,
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest()
            .body(
                ErrorResponse(
                    status = e.status,
                    message = e.message,
                    errorCode = e.code
                )
            )
    }
}

data class ErrorResponse(
    val timestamp: OffsetDateTime = OffsetDateTime.now(),
    val status: HttpStatus,
    val message: String?,
    val errorCode: String,
)
