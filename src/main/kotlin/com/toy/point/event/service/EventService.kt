package com.toy.point.event.service

import com.querydsl.core.BooleanBuilder
import com.toy.point.common.exception.BusinessException
import com.toy.point.common.exception.ErrorCode.EntityError.EntityAlreadyExist
import com.toy.point.common.exception.ErrorCode.EntityError.EntityNotFound
import com.toy.point.event.entity.PointCause.FIRST_REVIEW
import com.toy.point.event.entity.PointCause.PHOTO
import com.toy.point.event.entity.PointCause.REVIEW_ITSELF
import com.toy.point.event.entity.PointHistory
import com.toy.point.event.entity.QPointHistory
import com.toy.point.event.repository.PointHistoryRepository
import com.toy.point.event.service.model.PointAddModel
import com.toy.point.event.service.model.PointDeleteModel
import java.util.UUID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EventService(
    private val pointHistoryRepository: PointHistoryRepository,
) {
    fun handleAdd(model: PointAddModel) {
        validateAdd(model)

        val pointHistories = getPointHistoriesToSave(model)

        pointHistoryRepository.saveAll(pointHistories)
    }

    private fun validateAdd(model: PointAddModel) {
        val predicate = BooleanBuilder(
            QPointHistory.pointHistory.userId.eq(model.userId)
                .and(
                    QPointHistory.pointHistory.placeId.eq(model.placeId)
                )
                .and(
                    QPointHistory.pointHistory.active.isTrue
                )
        )

        val pointHistories = findAllByPredicate(predicate)

        if (pointHistories.count() > 0) throw BusinessException(EntityAlreadyExist)
    }

    private fun findAllByPredicate(predicate: BooleanBuilder): MutableIterable<PointHistory> =
        pointHistoryRepository.findAll(predicate)

    private fun getPointHistoriesToSave(model: PointAddModel): MutableList<PointHistory> {
        val pointHistoriesToSave = mutableListOf<PointHistory>()

        pointHistoriesToSave.add(model.toEntityBy(REVIEW_ITSELF))

        if (model.attachedPhotoIds.isNotEmpty())
            pointHistoriesToSave.add(model.toEntityBy(PHOTO))

        if (isFirstReviewAtPlace(model.placeId))
            pointHistoriesToSave.add(model.toEntityBy(FIRST_REVIEW))

        return pointHistoriesToSave
    }

    private fun isFirstReviewAtPlace(placeId: UUID): Boolean {
        val predicate = BooleanBuilder(
            QPointHistory.pointHistory.placeId.eq(placeId)
                .and(
                    QPointHistory.pointHistory.active.isTrue
                )
        )

        val reviews = findAllByPredicate(predicate)

        return reviews.count() == 0
    }

    private fun handleModify(model: PointAddModel) {
        if(model.attachedPhotoIds.isNotEmpty()) Unit
    }

    fun handleDelete(model: PointDeleteModel) {
        val pointHistories = getPointHistoriesToDelete(model.reviewId)

        pointHistories.forEach {
            it.active = true
        }
    }

    private fun getPointHistoriesToDelete(reviewId: UUID): MutableIterable<PointHistory> {
        val predicate = BooleanBuilder(
            QPointHistory.pointHistory.reviewId.eq(reviewId)
                .and(
                    QPointHistory.pointHistory.active.isTrue
                )
        )
        val pointHistories = findAllByPredicate(predicate)

        if (pointHistories.count() == 0) throw BusinessException(EntityNotFound)
        return pointHistories
    }
}
