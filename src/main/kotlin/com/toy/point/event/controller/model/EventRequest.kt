package com.toy.point.event.controller.model

import com.toy.point.event.service.model.EventModel
import java.util.UUID

data class ReviewRequest(
    val type: EventCategory,
    val action: EventAction,
    val reviewId: UUID,
    val content: String,
    val attachedPhotoIds: List<UUID>?,
    /**
     * 삭제된 user가 작성한 리뷰를 관리자가 삭제하는 요청이 있을 수도 있기 때문에 nullable
     */
    val userId: UUID?,
    val placeId: UUID,
) {
    fun toPointModel(): EventModel {
        return EventModel(
            type = type,
            action = action,
            reviewId = reviewId,
            content = content,
            attachedPhotoIds = attachedPhotoIds,
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
