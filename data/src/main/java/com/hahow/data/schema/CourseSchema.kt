package com.hahow.data.schema


import com.hahow.domain.model.Course
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseSchema(
    @SerialName("accessExpiredReason")
    val accessExpiredReason: String? = null,
    @SerialName("averageRating")
    val averageRating: Double? = null,
    @SerialName("completionPercentage")
    val completionPercentage: Double? = null,
    @SerialName("completionStatus")
    val completionStatus: String? = null,
    @SerialName("coverImageUrl")
    val coverImageUrl: String? = null,
    @SerialName("enrolled")
    val enrolled: Boolean? = null,
    @SerialName("enrollmentsCount")
    val enrollmentsCount: Int? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("lastViewedAt")
    val lastViewedAt: String? = null,
    @SerialName("level")
    val level: String? = null,
    @SerialName("recentStartedAssignment")
    val recentStartedAssignment: RecentStartedAssignmentSchema? = null,
    @SerialName("source")
    val source: String? = null,
    @SerialName("studiedAt")
    val studiedAt: String? = null,
    @SerialName("teacher")
    val teacher: TeacherSchema? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("totalSeconds")
    val totalSeconds: Int? = null,
    @SerialName("__typename")
    val typename: String? = null
)

internal fun List<CourseSchema>.toDomain(): List<Course> = map(CourseSchema::toDomain)

internal fun CourseSchema.toDomain(): Course = Course(
    accessExpiredReason = accessExpiredReason,
    averageRating = averageRating,
    completionPercentage = completionPercentage,
    completionStatus = completionStatus,
    coverImageUrl = coverImageUrl,
    enrolled = enrolled,
    enrollmentsCount = enrollmentsCount,
    id = id,
    lastViewedAt = lastViewedAt,
    level = level,
    recentStartedAssignment = recentStartedAssignment?.toDomain(),
    source = source,
    studiedAt = studiedAt,
    teacher = teacher?.toDomain(),
    title = title,
    totalSeconds = totalSeconds,
    typename = typename
)