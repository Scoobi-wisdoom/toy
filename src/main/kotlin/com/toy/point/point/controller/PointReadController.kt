package com.toy.point.point.controller

import com.toy.point.point.controller.model.PointReadResponse
import com.toy.point.point.service.PointReadService
import com.toy.point.point.service.model.RetrievePointModel
import java.util.UUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PointReadController(
    private val pointReadService: PointReadService,
) {
    @GetMapping("/point/{userId}")
    fun retrievePointByUser(@PathVariable userId: UUID): PointReadResponse {
        return PointReadResponse.from(
            pointReadService.retrievePointByUser(
                RetrievePointModel(userId = userId)
            )
        )
    }
}
