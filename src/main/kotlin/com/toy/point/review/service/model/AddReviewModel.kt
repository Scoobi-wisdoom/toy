package com.toy.point.review.service.model

import com.toy.point.event.service.model.EventModel
import java.util.UUID

data class AddReviewModel(
    val reviewId: UUID,
    val content: String,
    val attachedPhotoIds: List<UUID>,
    val userId: UUID,
    val placeId: UUID,
) {
    companion object {
        fun from(model: EventModel): AddReviewModel {
            return AddReviewModel(
                reviewId = model.reviewId,
                content = model.content,
                attachedPhotoIds = model.attachedPhotoIds,
                userId = model.userId,
                placeId = model.placeId
            )
        }
    }
}
