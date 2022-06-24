package com.toy.point.review.entity

import com.toy.point.common.BaseEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class ReviewPhoto(
    @Id
    val reviewPhotoId: ReviewPhotoId = UUID.randomUUID(),
    val deleted: Boolean = false,
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_id")
    val review: Review,
    /**
     * 사진이 저장된 위치 프로퍼티는 미구현.
     */
) : BaseEntity()
