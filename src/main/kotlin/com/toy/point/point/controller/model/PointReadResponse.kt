package com.toy.point.point.controller.model

import com.toy.point.point.service.model.RetrievePointResultModel
import java.util.UUID

data class PointReadResponse(
    val userId: UUID,
    val accumulatedPoint: Int,
) {
    companion object {
        fun from(model: RetrievePointResultModel): PointReadResponse {
            return PointReadResponse(
                userId = model.userId,
                accumulatedPoint = model.accumulatedPoint
            )
        }
    }
}
