package com.toy.point.user.entity

import com.toy.point.common.BaseEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DomainUser(
    @Id
    val id: DomainUserId = UUID.randomUUID(),
    /**
     * 사용자의 이름, 별명, 연령 등 다양한 정보는 미구현.
     */
) : BaseEntity()
