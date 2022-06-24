package com.toy.point.review.repository

import com.toy.point.review.entity.Review
import com.toy.point.review.entity.ReviewId
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, ReviewId>
