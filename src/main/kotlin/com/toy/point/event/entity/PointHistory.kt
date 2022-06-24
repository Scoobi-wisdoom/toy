package com.toy.point.event.entity

import com.toy.point.common.BaseEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class PointHistory(
    @Id
    val id: PointHistoryId = 0L,
    val reviewId: UUID,
    val placeId: UUID,
    val userId: UUID,
    @Enumerated(STRING)
    val pointCause: PointCause,
    var active: Boolean = true,
) : BaseEntity()

enum class PointCause {
    FIRST_REVIEW,
    PHOTO,
    REVIEW_ITSELF,
}
