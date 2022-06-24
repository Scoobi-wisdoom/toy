package com.toy.point.review.repository

import com.toy.point.review.entity.ReviewPhoto
import com.toy.point.review.entity.ReviewPhotoId
import org.springframework.data.jpa.repository.JpaRepository

interface PhotoRepository : JpaRepository<ReviewPhoto, ReviewPhotoId>
