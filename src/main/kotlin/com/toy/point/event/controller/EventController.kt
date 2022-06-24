package com.toy.point.event.controller

import com.toy.point.event.controller.model.ReviewAddRequest
import com.toy.point.event.controller.model.ReviewModifyRequest
import com.toy.point.event.service.EventService
import com.toy.point.event.service.model.PointDeleteModel
import com.toy.point.event.service.model.PointModifyModel
import java.util.UUID
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(
    private val eventService: EventService,
) {
    @PostMapping("/events")
    fun handleReviewAddEvent(@RequestBody reviewAddRequest: ReviewAddRequest) {
        return eventService.handleAdd(reviewAddRequest.toPointAddModel())
    }

    @DeleteMapping("/events/{reviewId}")
    fun handleReviewDeleteEvent(@PathVariable reviewId: UUID) {
        val pointDeleteModel = PointDeleteModel(reviewId)

        return eventService.handleDelete(pointDeleteModel)
    }

    @PutMapping("/events/{reviewId}")
    fun handleReviewModifyEvent(
        @PathVariable reviewId: UUID,
        @RequestBody reviewModifyRequest: ReviewModifyRequest,
    ) {
        eventService.handleModify(
            PointModifyModel(reviewModifyRequest = reviewModifyRequest, reviewId = reviewId)
        )
    }
}
