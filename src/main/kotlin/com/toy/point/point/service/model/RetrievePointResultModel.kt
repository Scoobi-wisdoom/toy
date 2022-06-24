package com.toy.point.point.service.model

import java.util.UUID

data class RetrievePointResultModel(
    val userId: UUID,
    val accumulatedPoint: Int,
)
