package com.toy.point.place.entity

import com.toy.point.common.BaseEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Place(
    @Id
    val id: PlaceId = UUID.randomUUID(),
    /**
     * 장소의 이름, 국가, 위도, 경도 등 다양한 정보는 미구현.
     */
) : BaseEntity()
