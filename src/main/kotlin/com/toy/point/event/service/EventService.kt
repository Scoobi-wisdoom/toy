package com.toy.point.event.service

import com.querydsl.core.BooleanBuilder
import com.toy.point.common.exception.BusinessException
import com.toy.point.common.exception.ErrorCode.EntityError.EntityNotFound
import com.toy.point.common.exception.ErrorCode.EventHandleError.EventHandleFail
import com.toy.point.event.controller.model.EventAction.ADD
import com.toy.point.event.controller.model.EventAction.DELETE
import com.toy.point.event.controller.model.EventAction.MOD
import com.toy.point.event.entity.QPointHistory
import com.toy.point.event.repository.PointHistoryRepository
import com.toy.point.event.service.model.EventModel
import java.util.UUID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EventService(
    private val pointHistoryRepository: PointHistoryRepository,
) {
    fun handleEvents(model: EventModel) {
        try {
            when (model.action) {
                ADD -> handleAdd(model)
                MOD -> handleModify(model)
                DELETE -> handleDelete(model.reviewId)
            }
        } catch (e: Exception) {
            when (e) {
                is BusinessException -> throw e
                else -> throw BusinessException(EventHandleFail, cause = e)
            }
        }
    }

    private fun handleAdd(model: EventModel) {
    }

    private fun handleModify(model: EventModel) {
    }

    private fun handleDelete(reviewId: UUID) {
        val predicate = BooleanBuilder(
            QPointHistory.pointHistory.reviewId.eq(reviewId)
                .and(
                    QPointHistory.pointHistory.confiscated.isFalse
                )
        )
        val pointHistories = pointHistoryRepository.findAll(predicate)

        if (pointHistories.count() == 0) throw BusinessException(EntityNotFound)

        pointHistories.forEach {
            it.confiscated = true
        }
    }
}
