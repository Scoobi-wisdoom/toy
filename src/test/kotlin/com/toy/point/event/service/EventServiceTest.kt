package com.toy.point.event.service

import com.querydsl.core.types.Predicate
import com.toy.point.common.exception.BusinessException
import com.toy.point.event.repository.PointHistoryRepository
import com.toy.point.event.service.model.PointDeleteModel
import io.mockk.every
import io.mockk.mockk
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class EventServiceTest {
    private val pointHistoryRepository: PointHistoryRepository = mockk()
    private val eventService = EventService(
        pointHistoryRepository = pointHistoryRepository,
    )

    @Test
    fun `model 의 action 이 DELETE 일 때, model 의 reviewId 로 검색 결과가 없으면 BusinessException 이 발생하며 에러코드는 EE-100 이다`() {
        // given
        every { pointHistoryRepository.findAll(any<Predicate>()) } returns listOf()
        val model = PointDeleteModel(
            reviewId = UUID.fromString("3423736d-c3d4-4d37-b8c1-fdc988c41a3a"),
        )

        // when/then
        val actualException = assertThrows<BusinessException> {
            eventService.handleDelete(model)
        }
        assertTrue(actualException.code == "EE-100")
    }
}
