package com.hahow.domain.repository

import com.hahow.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    fun getCourses(): Flow<List<Course>>
}