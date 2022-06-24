package com.toy.point.event.controller.model

import com.toy.point.event.service.model.EventModel
import java.util.UUID

data class ReviewRequest(
    val type: EventCategory,
    val action: EventAction,
    val reviewId: UUID,
    val content: String,
    val attachedPhotoIds: List<UUID>?,
    val userId: UUID,
    val placeId: UUID,
) {
    fun toPointModel(): EventModel {
        return EventModel(
            type = type,
            action = action,
            reviewId = reviewId,
            content = content,
            attachedPhotoIds = attachedPhotoIds ?: listOf(),
            userId = userId,
            placeId = placeId,
        )
    }
}

enum class EventCategory {
    REVIEW,
}

enum class EventAction {
    ADD,
    MOD,
    DELETE,
}
