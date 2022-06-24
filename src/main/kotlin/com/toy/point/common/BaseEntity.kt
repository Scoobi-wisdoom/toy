package com.toy.point.common

import java.time.OffsetDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity(
    @CreatedDate
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    @LastModifiedDate
    var lastModifiedAt: OffsetDateTime = OffsetDateTime.now()
)
