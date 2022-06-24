package com.toy.point.event.controller.model

import com.toy.point.event.service.model.PointDeleteModel
import java.util.UUID

data class ReviewDeleteRequest(
    val reviewId: UUID,
) {
    fun toPointDeleteModel(): PointDeleteModel {
        return PointDeleteModel(reviewId = reviewId)
    }
}
