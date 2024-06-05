package com.hahow.data.repository

import com.hahow.data.api.CourseApi
import com.hahow.data.schema.CourseSchema
import com.hahow.data.schema.toDomain
import com.hahow.domain.model.Course
import com.hahow.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CoursesRepositoryImpl(
    private val courseApi: CourseApi,
) : CoursesRepository {
    override fun getCourses(): Flow<List<Course>> = flow {
        courseApi.getCourses().also { response -> emit(response) }
    }.map(List<CourseSchema>::toDomain)
}