package com.toy.point.event.service.model

import com.toy.point.event.controller.model.ReviewModifyRequest
import com.toy.point.event.entity.PointCause.PHOTO
import com.toy.point.event.entity.PointHistory
import java.util.UUID

data class PointModifyModel(
    val reviewId: UUID,
    val attachedPhotoIds: List<UUID>,
    val userId: UUID,
    val placeId: UUID,
) {
    constructor(reviewModifyRequest: ReviewModifyRequest, reviewId: UUID) : this(
        reviewId = reviewId,
        attachedPhotoIds = reviewModifyRequest.attachedPhotoIds ?: listOf(),
        userId = reviewModifyRequest.userId,
        placeId = reviewModifyRequest.placeId,
    )

    fun toEntityWithPhotoPointCause(): PointHistory {
        return PointHistory(
            reviewId = reviewId,
            placeId = placeId,
            userId = userId,
            pointCause = PHOTO
        )
    }

    val isPhotoDeleted: Boolean
        get() {
            return attachedPhotoIds.isEmpty()
        }
}
