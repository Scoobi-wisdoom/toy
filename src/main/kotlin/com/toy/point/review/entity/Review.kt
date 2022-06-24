package com.toy.point.review.entity

import com.toy.point.common.BaseEntity
import com.toy.point.place.entity.PlaceId
import com.toy.point.user.entity.DomainUserId
import java.util.UUID
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Review(
    @Id
    val reviewId: ReviewId = UUID.randomUUID(),
    val userId: DomainUserId,
    val placeId: PlaceId,
    var content: String,
    var deleted: Boolean = false,
    @OneToMany(cascade = [ALL], fetch = LAZY, mappedBy = "review")
    val reviewPhotos: MutableList<ReviewPhoto> = mutableListOf(),
) : BaseEntity()
