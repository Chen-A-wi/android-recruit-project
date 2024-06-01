package com.hahow.data.api

import com.hahow.data.schema.CourseSchema
import retrofit2.http.GET

interface CourseApi {
    @GET(".")
    suspend fun getCourses(): List<CourseSchema>
}