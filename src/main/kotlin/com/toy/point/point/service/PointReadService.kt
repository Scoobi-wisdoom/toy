package com.toy.point.point.service

import com.querydsl.core.BooleanBuilder
import com.toy.point.event.entity.QPointHistory
import com.toy.point.event.repository.PointHistoryRepository
import com.toy.point.point.service.model.RetrievePointModel
import com.toy.point.point.service.model.RetrievePointResultModel
import org.springframework.stereotype.Service

@Service
class PointReadService(
    private val pointHistoryRepository: PointHistoryRepository,
) {
    fun retrievePointByUser(model: RetrievePointModel): RetrievePointResultModel {
        val predicate = BooleanBuilder(
            QPointHistory.pointHistory.userId.eq(model.userId)
                .and(
                    QPointHistory.pointHistory.active.isTrue
                )
        )

        val accumulatedPoints = pointHistoryRepository.findAll(predicate).count()

        return RetrievePointResultModel(userId = model.userId, accumulatedPoint = accumulatedPoints)
    }
}
