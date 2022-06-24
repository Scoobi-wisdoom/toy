package com.toy.point.event.repository

import com.toy.point.event.entity.PointHistory
import com.toy.point.event.entity.PointHistoryId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface PointHistoryRepository : JpaRepository<PointHistory, PointHistoryId>, QuerydslPredicateExecutor<PointHistory>
