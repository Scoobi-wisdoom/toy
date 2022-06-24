package com.toy.point.event.controller.model

import java.util.UUID

data class ReviewModifyRequest(
    val attachedPhotoIds: List<UUID>?,
    val userId: UUID,
    val placeId: UUID,
)
