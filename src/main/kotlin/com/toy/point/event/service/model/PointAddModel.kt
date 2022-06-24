package com.toy.point.event.service.model

import com.toy.point.event.controller.model.EventAction
import com.toy.point.event.controller.model.EventCategory
import com.toy.point.event.entity.PointCause
import com.toy.point.event.entity.PointHistory
import java.util.UUID

data class PointAddModel(
    val type: EventCategory,
    val action: EventAction,
    val reviewId: UUID,
    val content: String,
    val attachedPhotoIds: List<UUID>,
    val userId: UUID,
    val placeId: UUID,
) {
    fun toEntityBy(pointCause: PointCause): PointHistory {
        return PointHistory(
            reviewId = reviewId,
            placeId = placeId,
            userId = userId,
            pointCause = pointCause,
        )
    }
}
