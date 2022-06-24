package com.toy.point.event.service

import com.querydsl.core.types.Predicate
import com.toy.point.common.exception.BusinessException
import com.toy.point.event.controller.model.EventAction.DELETE
import com.toy.point.event.controller.model.EventCategory.REVIEW
import com.toy.point.event.repository.PointHistoryRepository
import com.toy.point.event.service.model.EventModel
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
        val model = EventModel(
            type = REVIEW,
            action = DELETE,
            reviewId = UUID.fromString("3423736d-c3d4-4d37-b8c1-fdc988c41a3a"),
            content = "",
            attachedPhotoIds = listOf(),
            userId = UUID.fromString("6b380ff0-b581-40ce-8c3d-77f001c6697b"),
            placeId = UUID.fromString("0c6ae872-3b92-467e-a0f7-aa69d9388664")
        )

        // when/then
        val actualException = assertThrows<BusinessException> {
            eventService.handleEvents(model)
        }
        assertTrue(actualException.code == "EE-100")
    }
}
