package com.toy.point.point.controller.model

import java.util.UUID

data class PointReadResponse(
    val userId: UUID,
    val accumulatedPoint: Int,
)
