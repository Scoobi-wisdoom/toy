package com.toy.point.event.service.model

import com.toy.point.event.controller.model.EventAction
import com.toy.point.event.controller.model.EventCategory
import java.util.UUID

data class EventModel(
    val type: EventCategory,
    val action: EventAction,
    val reviewId: UUID,
    val content: String,
    val attachedPhotoIds: List<UUID>,
    val userId: UUID,
    val placeId: UUID,
)
