package com.hahow.data.schema


import com.hahow.domain.model.Course
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseSchema(
    @SerialName("accessExpiredReason")
    val accessExpiredReason: String?,
    @SerialName("averageRating")
    val averageRating: Int?,
    @SerialName("completionPercentage")
    val completionPercentage: Double?,
    @SerialName("completionStatus")
    val completionStatus: String?,
    @SerialName("coverImageUrl")
    val coverImageUrl: String?,
    @SerialName("enrolled")
    val enrolled: Boolean?,
    @SerialName("enrollmentsCount")
    val enrollmentsCount: Int?,
    @SerialName("id")
    val id: String?,
    @SerialName("lastViewedAt")
    val lastViewedAt: String?,
    @SerialName("level")
    val level: String?,
    @SerialName("recentStartedAssignment")
    val recentStartedAssignment: RecentStartedAssignmentSchema?,
    @SerialName("source")
    val source: String?,
    @SerialName("studiedAt")
    val studiedAt: String?,
    @SerialName("teacher")
    val teacher: TeacherSchema?,
    @SerialName("title")
    val title: String?,
    @SerialName("totalSeconds")
    val totalSeconds: Int?,
    @SerialName("__typename")
    val typename: String?
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