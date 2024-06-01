package com.hahow.domain.model

data class Course (
    val accessExpiredReason: String?,
    val averageRating: Int?,
    val completionPercentage: Double?,
    val completionStatus: String?,
    val coverImageUrl: String?,
    val enrolled: Boolean?,
    val enrollmentsCount: Int?,
    val id: String?,
    val lastViewedAt: String?,
    val level: String?,
    val recentStartedAssignment: RecentStartedAssignment?,
    val source: String?,
    val studiedAt: String?,
    val teacher: Teacher?,
    val title: String?,
    val totalSeconds: Int?,
    val typename: String?
)