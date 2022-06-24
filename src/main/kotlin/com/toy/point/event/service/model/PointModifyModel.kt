package com.toy.point.event.service.model

import com.toy.point.event.controller.model.ReviewModifyRequest
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

    val isPhotoDeleted: Boolean
        get() {
            return attachedPhotoIds.isEmpty()
        }
}
